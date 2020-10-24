import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'dart:ui' as ui;

class AdjustInfoButton extends StatelessWidget {
  Color primaryColor;
  Color primaryColorLight;
  Color secondaryColor;
  bool isVertical;
  double height;
  double width;
  String id;
  String name;
  String title;
  String description;
  double fontSize;
  Image image;
  bool imageIsCircular;
  Function func;
  Widget tailing;

  AdjustInfoButton(
      {this.primaryColor,
      this.primaryColorLight,
      this.secondaryColor,
      this.isVertical,
      this.height,
      this.width,
      this.id,
      this.name,
      this.title,
      this.description,
      this.fontSize,
      this.image,
      this.imageIsCircular,
      this.func,
      this.tailing}) {
    this.imageIsCircular = this.imageIsCircular == null ? false : this.imageIsCircular;
  }

  @override
  Widget build(BuildContext context) {
    return InkWell(
      child: Container(
        height: this.height,
          width: this.width,
          margin: EdgeInsets.all(10),
          decoration: BoxDecoration(
              color: primaryColor,
              borderRadius: BorderRadius.all(Radius.circular(20)),
              boxShadow: [
                BoxShadow(
                  color: SHADOW_COLOR,
                  blurRadius: 2,
                  spreadRadius: 2,
                  offset: Offset(2, 2),
                )
              ],
              gradient: LinearGradient(
                colors: [primaryColor, primaryColorLight, primaryColorLight],
              )),
          child: Stack(
            children: <Widget>[
              Positioned(
                right: 0,
                bottom: 0,
                height: this.height,
                width: this.width,
                child: CustomPaint(
                  painter: Decoration(20, this.height, this.width, this.primaryColor, this.primaryColorLight),
                )
              ),
              isVertical
                  ? Positioned(
                      child: Container(
                        child: Stack(
                          children: <Widget>[
                            Positioned(
                              bottom: 5,
                              right: 10,
                              height: this.height * 3 / 5,
                              width: this.width * 3 / 5,
                              child: this.image == null ? Container() : this.imageIsCircular ? getCircularImage() : getImage(),
                            ),
                            Positioned(
                              top: 10,
                              right: 5,
                              height: this.height,
                              width: this.width,
                                child: Column(
                                  children: <Widget>[
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: Container(
                                        padding: EdgeInsets.all(4),
                                        width: this.width,
                                        child: AdjustText(
                                            name,
                                            fontSize: this.fontSize,
                                            color: WHITE_COLOR
                                        )
                                      ),
                                    ),
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: Container(
                                        padding: EdgeInsets.all(4),
                                        width: this.width,
                                        child: AdjustText(
                                            title,
                                            fontSize: this.fontSize - 1,
                                            color: WHITE_COLOR
                                        )
                                      ),
                                    ),
                                    FittedBox(
                                      fit: BoxFit.scaleDown,
                                      child: Container(
                                        padding: EdgeInsets.all(4),
                                        width: this.width,
                                        child: AdjustText(
                                            description,
                                            fontSize: this.fontSize - 2,
                                            color: WHITE_COLOR
                                        )
                                      ),
                                    )
                                  ],
                                ),
                              )
                          ],
                        ),
                      ),
                    )
                  : Container(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: <Widget>[
                    this.tailing != null ?
                    Expanded(
                      flex: 3,
                      child: this.tailing,
                    )
                        :
                    Expanded(
                      flex: 0,
                      child: Container(),
                    ),
                    Expanded(
                      flex: 6,
                      child: Container(
                        padding: EdgeInsets.all(20),
                        alignment: adjustAlignment,
                        child: Column(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: <Widget>[
                            Expanded(
                                flex: 36,
                                child: Align(
                                  alignment: adjustAlignment,
                                  child: FittedBox(
                                    fit: BoxFit.scaleDown,
                                    child: AdjustText(
                                        this.name,
                                        fontSize: fontSize, color: secondaryColor
                                    )
                                  )
                                )
                            ),
                            Expanded(
                                flex: 35,
                                child: Align(
                                  alignment: adjustAlignment,
                                  child: FittedBox(
                                    fit: BoxFit.scaleDown,
                                    child: AdjustText(
                                        this.title,
                                        fontSize: fontSize - 2, color: secondaryColor
                                    )
                                  )
                                )
                            ),
                            Expanded(
                                flex: 29,
                                child: Align(
                                  alignment: adjustAlignment,
                                  child: AdjustText(
                                      this.description,
                                      fontSize: fontSize, color: secondaryColor
                                  ),
                                )
                            )
                          ],
                        ),
                      ),
                    ),
                    Expanded(
                      flex: 4,
                      child: Container(
                        width: this.width * 3/4,
                        height: this.height * 3/4,
                        padding: EdgeInsets.all(10),
                        child: this.imageIsCircular ? getCircularImage() : getImage(),
                      ),
                    )
                  ],
                ),
              )
            ],
          )),
      onTap: () {
        func();
      },
    );
  }

  Widget getImage() {
    return Container(
      child: Hero(
        tag: this.id,
        child: image,
      ),
    );
  }

  Widget getCircularImage() {
    return Container(
      child: Hero(
        tag: this.id,
        child: FittedBox(
          fit: BoxFit.scaleDown,
          child: CircleAvatar(
            radius: 80,
            backgroundImage: image.image,
          ),
        )
      ),
    );
  }
}

class Decoration extends CustomPainter{
  double r;
  double height;
  double width;
  Color primaryColor;
  Color lightPrimaryColor;
  Decoration(this.r, this.height, this.width, this.primaryColor, this.lightPrimaryColor);

  @override
  void paint(Canvas canvas, Size size) {
    // TODO: implement paint

    Paint paint = Paint();
    paint.shader = ui.Gradient.linear(Offset(0, 0), Offset(this.width, this.height), [HSLColor.fromColor(this.primaryColor).withLightness(0.8).toColor(), lightPrimaryColor]);

    Path path = Path();
    path.moveTo(0, height);
    path.lineTo(width - r, height);
    path.quadraticBezierTo(width, height, width, height - r);
    path.lineTo(width, r);
    path.lineTo(width, 0 + r);
    path.quadraticBezierTo(width, 0, width - r, 0);

    path.quadraticBezierTo(0, 0, 0, height - r);
    path.close();

    canvas.drawPath(path, paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return true;
  }


}
