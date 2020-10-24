library ruler_picker;

import 'dart:math';

import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

/// a triangle painter
class _TrianglePainter extends CustomPainter {
  final double lineSize;
  Color color;
  _TrianglePainter({this.lineSize = 16, this.color});
  @override
  void paint(Canvas canvas, Size size) {
    Path path = Path();
    path.moveTo(0, 0);
    path.lineTo(lineSize, 0);
    path.lineTo(lineSize / 2, tan(pi / 3) * lineSize / 2);
    path.close();
    Paint paint = Paint();
    paint.color = this.color;
    paint.style = PaintingStyle.fill;
    canvas.drawPath(path, paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return false;
  }
}

/// The controller for the ruler picker
/// init the ruler value from the controller
/// 用于 RulerPicker 的控制器，可以在构造函数里初始化默认值
class RulerPickerController extends ValueNotifier<num> {
  RulerPickerController({num value = 0.0}) : super(value);
  num get value => super.value;
  set value(num newValue) {
    super.value = newValue;
  }
}

typedef void ValueChangedCallback(num value);

/// RulerPicker 标尺选择器
/// [width] 必须是具体的值，包括父级container的width，不能是 double.infinity，
/// 可以传入MediaQuery.of(context).size.width
class RulerPicker extends StatefulWidget {
  final ValueChangedCallback onValueChange;
  final double width;
  final double height;
  final Color backgroundColor;
  /// the marker on the ruler, default is a arrow
  final Widget marker;
  double _value;
  /// the fraction digits of the picker value
  int fractionDigits;
  RulerPickerController controller;

  double currentValue;

  Color pickerColor;
  Color textColor;
  RulerPicker({
    @required this.onValueChange,
    @required this.width,
    @required this.height,
    this.backgroundColor = Colors.white,
    this.fractionDigits = 0,
    this.controller,
    this.marker,
    this.pickerColor,
    this.textColor,
    this.currentValue
  }){
    this.currentValue = this.currentValue == null ? 0 : this.currentValue;
  }
  @override
  State<StatefulWidget> createState() {
    return RulerPickerState();
  }
}

// todo 实现 animateTo
class RulerPickerState extends State<RulerPicker> {
  double lastOffset = 0;
  bool isPosFixed = false;
  String value;
  ScrollController scrollController;

  /// default mark
  Widget mark() {
    /// default mark arrow
    Widget triangle() {
      return SizedBox(
        width: 16,
        height: 16,
        child: CustomPaint(
          painter: _TrianglePainter(color: this.widget.pickerColor),
        ),
      );
    }

    return Container(
      child: SizedBox(
        width: 16,
        height: 34,
        child: Stack(
          children: <Widget>[
            triangle(),
            Container(
              width: 3,
              height: 34,
              margin: EdgeInsets.only(left: 6),
              color: this.widget.pickerColor,
            ),
          ],
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: widget.width,
      height: widget.height,
      color: widget.backgroundColor,
      child: Stack(
        children: <Widget>[
          Listener(
            onPointerDown: (event) {
              FocusScope.of(context).requestFocus(new FocusNode());
              isPosFixed = false;
            },
            onPointerUp: (event) {},
            child: NotificationListener(
              onNotification: (scrollNotification) {
                if (scrollNotification is ScrollStartNotification) {
                } else if (scrollNotification is ScrollUpdateNotification) {
                } else if (scrollNotification is ScrollEndNotification) {
                  if (!isPosFixed) {
                    isPosFixed = true;
                    fixPosition((scrollNotification.metrics.pixels / 10)
                        .roundToDouble() *
                        10);
                    scrollController.notifyListeners();
                  }
                }
                return true;
              },
              child: ListView.builder(
                controller: scrollController,
                scrollDirection: Axis.horizontal,
                itemBuilder: (BuildContext context, int index) {
                  return Container(
                    // constraints: BoxConstraints(maxWidth: 10),
                    padding: index == 0
                        ? EdgeInsets.only(
                      left: widget.width / 2,
                    )
                        : EdgeInsets.zero,
                    child: Container(
                      width: 10,
                      child: Stack(
                        overflow: Overflow.visible,
                        children: <Widget>[
                          Container(
                            width: index % 10 == 0 ? 2 : 1,
                            height: index % 10 == 0 ? 32 : 20,
                            color: this.widget.textColor,
                          ),
                          Positioned(
                            bottom: 5,
                            width: 50,
                            left: -25,
                            child: index % 10 == 0
                                ? Container(
                              alignment: Alignment.center,
                              child: AdjustText(
                                getNumber(index.toString()),
                                color: this.widget.textColor,
                                fontSize: 14,
                              )
                            )
                                : Container(),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
          ),
          Positioned(
            left: widget.width / 2 - 6,
            child: widget.marker ?? mark(),
          ),
        ],
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    scrollController = ScrollController(initialScrollOffset: this.widget.currentValue * 10);
    this.widget.controller.value = this.widget.currentValue;
    scrollController.addListener(() {
      setState(() {
        widget._value = double.parse((scrollController.offset / 10)
            .toStringAsFixed(widget.fractionDigits));
        if (widget._value < 0) widget._value = 0;
        if (widget.onValueChange != null) {
          widget.onValueChange(widget._value);
        }
      });
    });
    widget.controller.addListener(() {
      setPositionByValue(widget.controller.value);
    });
  }

  @override
  void dispose() {
    super.dispose();
    scrollController.dispose();
  }

  @override
  void didUpdateWidget(RulerPicker oldWidget) {
    super.didUpdateWidget(oldWidget);
  }

  /// 滑动后修正标记，使之对齐
  void fixPosition(double curPos) {
//    print('curPos: ${curPos}');
    double targetPos =
    double.parse(curPos.toStringAsFixed(widget.fractionDigits));
//    print('targetPos: ${targetPos}');
    if (targetPos < 0) targetPos = 0;
    // todo animateTo 异步操作
    if (scrollController.hasClients) {
      scrollController.jumpTo(
        targetPos,
        // duration: Duration(milliseconds: 500),
        // curve: Curves.easeOut,
      );
    }
  }

  /// 根据数值设置标记位置
  void setPositionByValue(num value) {
    num targetPos = value * 10;
    if (targetPos < 0) targetPos = 0;
    if (scrollController.hasClients) {
      scrollController.jumpTo(
        targetPos.toDouble(),
        // duration: Duration(milliseconds: 500),
        // curve: Curves.easeOut,
      );
    }
  }
}
