

import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/components/adjust_text.dart';
import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:date_utils/date_utils.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'package:shamsi_date/shamsi_date.dart';

import 'adjust_dialog.dart';
import 'adjust_text.dart';
import 'numberpicker.dart';

typedef SetDate(String date);
class AdjustDatePicker extends StatefulWidget {
  Color color;
  SetDate setDate;
  AdjustDatePicker(this.setDate, {this.color, }) {
    this.color = this.color == null ? Colors.blue : this.color;
  }
  @override
  _AdjustDatePickerState createState() => _AdjustDatePickerState();
}

class _AdjustDatePickerState extends State<AdjustDatePicker> {
  int _currentYearVal = 1;
  int _currentMonthVal = 1;
  int _currentDayVal = 1;
  int lastDayOfMonthNum = 28;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    if(rtl()) {
      Jalali now = Jalali.now();
      lastDayOfMonthNum = now.monthLength;
      _currentDayVal = now.day;
      _currentMonthVal = now.month;
      _currentYearVal = now.year;
    } else {
      DateTime now = new DateTime.now();
      lastDayOfMonthNum = Utils.lastDayOfMonth(now).day;
      _currentDayVal = now.day;
      _currentMonthVal = now.month;
      _currentYearVal = now.year;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.all(20),
      height: 300,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Expanded(
            flex: 8,
            child: Row(
              textDirection: txtDir,
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                    flex: 33,
                    child: Column(
                      children: <Widget>[
                        AdjustText(
                            t.main.day,
                          color: this.widget.color,
                        ),
                        NumberPicker.integer(
                            initialValue: _currentDayVal,
                            color: this.widget.color,
                            minValue: 1,
                            maxValue: 31,
                            onChanged: (day) {
                              setState(() {
                                _currentDayVal = day;
                              });
                            }),
                      ],
                    )
                ),
                Expanded(
                    flex: 33,
                    child: Column(
                      children: <Widget>[
                        AdjustText(
                          t.main.month,
                          color: this.widget.color,
                        ),
                        NumberPicker.integer(
                            initialValue: _currentMonthVal,
                            color: this.widget.color,
                            minValue: 1,
                            maxValue: 12,
                            onChanged: (month) {
                              setState(() {
                                _currentMonthVal = month;
                              });
                            }),
                      ],
                    )
                ),
                Expanded(
                    flex: 34,
                    child: Column(
                      children: <Widget>[
                        AdjustText(
                          t.main.year,
                          color: this.widget.color,
                        ),
                        NumberPicker.integer(
                            initialValue: _currentYearVal,
                            color: this.widget.color,
                            minValue: rtl() ? Jalali.now().year - 110 : DateTime.now().year - 110,
                            maxValue: rtl() ? Jalali.now().year : DateTime.now().year,
                            onChanged: (year) {
                              setState(() {
                                _currentYearVal = year;
                              });
                            }),
                      ],
                    )
                ),

              ],
            ),
          ),
          Expanded(
            flex: 2,
            child: Container(
              child: AdjustRaisedButton(
                text: t.main.ok,
                textDirection: txtDir,
                primaryColor: this.widget.color,
                secondaryColor: this.widget.color,
                height: 50,
                width: 90,
                onPressed: () {
                  String date = rtl() ?
                  getNumber(_currentYearVal.toString() + "/" + _currentMonthVal.toString() + "/" + _currentDayVal.toString()) :
                  _currentDayVal.toString() + "/" + _currentMonthVal.toString() + "/" + _currentYearVal.toString();
                  this.widget.setDate(date);
                },
              ),
            ),
          )
        ],
      )
    );
  }

}