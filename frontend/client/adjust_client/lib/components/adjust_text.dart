
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';

class AdjustText extends StatelessWidget {
  TextDirection textDirection;
  String text;
  double fontSize;
  Color color;
  AdjustText(this.text, {this.textDirection, this.fontSize, this.color}) {
    this.fontSize = this.fontSize == null ? 16 : this.fontSize;
    this.color = this.color == null ? FONT_COLOR : this.color;
  }

  @override
  Widget build(BuildContext context) {
    return Directionality(
      textDirection: textDirection == null ? txtDir : textDirection,
      child: Text(this.text, style: TextStyle(fontFamily: fontFamily(), color: this.color, fontSize: this.fontSize, ),),
    );
  }
}