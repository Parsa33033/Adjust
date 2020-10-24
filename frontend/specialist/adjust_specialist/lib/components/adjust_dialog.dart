
import 'package:adjust_specialist/components/adjust_raised_button.dart';
import 'package:adjust_specialist/config/i18n.dart';
import 'package:adjust_specialist/constants/adjust_colors.dart';
import 'package:adjust_specialist/constants/words.dart';
import 'package:flutter/material.dart';

void showAdjustDialog(BuildContext context, String text, bool isChoice, Function func, Color color) {
  showDialog(
    context: context,
    child: Dialog(
     child: Container(
       padding: EdgeInsets.all(20),
       height: 400,
       child: SingleChildScrollView(
         child: LimitedBox(
           maxHeight: 400,
           maxWidth: MediaQuery.of(context).size.width,
           child: Column(
             mainAxisAlignment: MainAxisAlignment.spaceEvenly,
             children: <Widget>[
               Directionality(
                 textDirection: TextDirection.rtl,
                 child: Text(text, style: TextStyle(fontFamily: "Iransans", fontSize: 18, color: FONT_COLOR),),
               ),
               isChoice
                   ?
               Container(
                 height: 50,
                 child: Row(
                   mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                   children: <Widget>[
                     Container(
                         child: AdjustRaisedButton(
                           textDirection: TextDirection.rtl,
                           height: 40,
                           width: 80,
                           primaryColor: color == null ? GREEN_COLOR : color,
                           secondaryColor: color == null ? GREEN_COLOR : color,
                           text: t.main.cancel,
                           onPressed: () {
                             Navigator.of(context, rootNavigator: true).pop("dialog");
                           },
                         )
                     ),
                     Container(
                         child: AdjustRaisedButton(
                           textDirection: TextDirection.rtl,
                           height: 40,
                           width: 80,
                           primaryColor: color == null ? GREEN_COLOR : color,
                           secondaryColor: color == null ? GREEN_COLOR : color,
                           text: t.main.ok,
                           onPressed: () {
                             Navigator.of(context, rootNavigator: true).pop("dialog");
                             func();
                           },
                         )
                     )
                   ],
                 ),
               )
                   :
               Container(
                   child: AdjustRaisedButton(
                     textDirection: TextDirection.rtl,
                     height: 40,
                     width: 80,
                     primaryColor: color == null ? GREEN_COLOR : color,
                     secondaryColor: color == null ? GREEN_COLOR : color,
                     text: t.main.ok,
                     onPressed: () {
                       Navigator.of(context, rootNavigator: true).pop("dialog");
                     },
                   )
               ),

             ],
           ),
         )
       )
     ),
    )
  );
}