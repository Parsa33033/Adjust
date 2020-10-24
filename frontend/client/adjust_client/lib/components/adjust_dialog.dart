
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:flutter/material.dart';

void showAdjustDialog(BuildContext context, String text, bool isChoice, Function func, Color color) {
  showDialog(
    context: context,
    child: Dialog(
     child: Container(
       padding: EdgeInsets.all(20),
       height: 200,
       child: LimitedBox(
         maxHeight: 200,
         maxWidth: MediaQuery.of(context).size.width,
         child: Column(
           mainAxisAlignment: MainAxisAlignment.spaceEvenly,
           children: <Widget>[
             Expanded(
               flex: 8,
               child: SingleChildScrollView(
                 child: Center(
                   child: AdjustText(
                     text, fontSize: 18, color: FONT_COLOR,
                   ),
                 )
               ),
             ),
             Expanded(
               flex: 2,
               child: isChoice
                   ?
               Container(
                 height: 100,
                 child: Row(
                   mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                   children: <Widget>[
                     Container(
                         child: AdjustRaisedButton(
                           textDirection: txtDir,
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
                           textDirection: txtDir,
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
                     textDirection: txtDir,
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
             )

           ],
         ),
       )
     ),
    )
  );
}