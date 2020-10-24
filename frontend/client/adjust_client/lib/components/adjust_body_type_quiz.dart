import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

typedef BodyTypeAdd(int index);
class AdjustBodyTypeQuiz extends StatelessWidget {
  String question;
  double questionSize;
  Color questionColor;

  String tag;
  double tagSize;
  Color tagColor;

  List<String> answers;
  Color answerButtonColor;
  Color answerColor;

  BodyTypeAdd bodyTypeAdd;

  AdjustBodyTypeQuiz(
      {this.question,
      this.questionSize,
      this.questionColor,
      this.tag,
      this.tagSize,
      this.tagColor,
      this.answers,
      this.answerButtonColor,
      this.answerColor,
      this.bodyTypeAdd});



  @override
  Widget build(BuildContext context) {
    return Container(
      child: Card(
          child: questionView(this.tag, this.question, this.answers)),
    );
  }

  Widget questionView(String tag, String question, List<String> answers) {
    return Container(
      padding: EdgeInsets.all(20),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: 1,
            child: AdjustText(
              tag,
              color: tagColor,
              fontSize: tagSize,
            ),
          ),
          Expanded(
            flex: 3,
            child: AdjustText(
              question,
              color: questionColor,
              fontSize: questionSize,
            ),
          ),
          Expanded(
            flex: 6,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: answers.asMap().map((index, e) {
                return MapEntry(index, Expanded(
                  flex: 2,
                  child: InkWell(
                    child: Container(
                      margin: EdgeInsets.only(
                          left: 20, right: 20, top: 5, bottom: 5),
                      decoration: BoxDecoration(
                          color: GREEN_COLOR,
                          borderRadius: BorderRadius.all(Radius.circular(5)),
                          boxShadow: [
                            BoxShadow(
                                color: SHADOW_COLOR,
                                blurRadius: 1,
                                offset: Offset(1, 1),
                                spreadRadius: 1)
                          ]),
                      child: Center(
                        child: FittedBox(
                          fit: BoxFit.scaleDown,
                          child: AdjustText(
                            e,
                            color: answerColor,
                          ),
                        ),
                      ),
                    ),
                    onTap: () {
                      bodyTypeAdd(index + 1);
                    },
                  ),
                ));
              }).values.toList(),
            ),
          )
        ],
      ),
    );
  }
}
