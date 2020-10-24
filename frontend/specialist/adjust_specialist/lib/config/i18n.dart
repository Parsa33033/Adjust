
import 'package:adjust_specialist/i18n/translations.i18n.dart';
import 'package:adjust_specialist/i18n/translations_fa.i18n.dart';
import 'package:adjust_specialist/main.dart';
import 'package:flutter/cupertino.dart';
import 'package:i18n/i18n.dart';
import 'package:persian_number_utility/persian_number_utility.dart';


final String lang = store.state.userState.langKey;

bool rtl() {
  switch (lang) {
    case "en":
      return false;
    case "fa":
      return true;
  }
}

String fontFamily() {
  switch (lang) {
    case "en":
      return null;
    case "fa":
      return "Iransans";
  }
}

final TextDirection txtDir = rtl() ? TextDirection.rtl : TextDirection.ltr;
final TextAlign txtAlign = rtl() ? TextAlign.right : TextAlign.left;
final AlignmentGeometry adjustAlignment = rtl() ? Alignment.centerRight : Alignment.centerLeft;
final Translations t = store.state.userState.langKey == "fa" ? new Translations_fa() : new Translations();

String getNumber(String num) {
  if (lang == "fa") {
    return NumberUtility.changeDigit(num, NumStrLanguage.Farsi);
  } else {
    return num;
  }
}