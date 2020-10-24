import 'dart:convert';
import 'dart:ffi';
import 'dart:io';
import 'dart:math';
import 'dart:typed_data';
import 'package:adjust_client/actions/program_action.dart';
import 'package:adjust_client/components/adjust_body_composition.dart';
import 'package:adjust_client/components/adjust_body_compostion_info.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/program_dto.dart';
import 'package:adjust_client/dto/nutrition_program_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/activity_type.dart';
import 'package:adjust_client/model/enum/bmi_category.dart';
import 'package:adjust_client/model/enum/body_fat_category.dart';
import 'package:adjust_client/model/enum/body_frame_size.dart';
import 'package:adjust_client/model/enum/body_type.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/model/enum/goal.dart';
import 'package:adjust_client/model/enum/workout_location.dart';
import 'package:adjust_client/model/enum/workout_type.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/program_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/specialist_state.dart';
import 'package:age/age.dart';
import 'package:avatar_glow/avatar_glow.dart';
import 'package:enum_to_string/enum_to_string.dart';
import 'package:expandable_card/expandable_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:image_picker/image_picker.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:shamsi_date/shamsi_date.dart';
import 'package:simple_image_crop/simple_image_crop.dart';
import 'package:stepper_counter_swipe/stepper_counter_swipe.dart';

class BodyCompositionPage extends StatefulWidget {

  @override
  _BodyCompositionPageState createState() => _BodyCompositionPageState();
}

class _BodyCompositionPageState extends State<BodyCompositionPage> {

  BodyCompositionDTO bodyCompositionDTO;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  void setBodyCompositionDTO(BodyCompositionDTO bodyCompositionDTO) {
    setState(() {
      this.bodyCompositionDTO = bodyCompositionDTO;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Align(
              alignment: adjustAlignment,
              child: AdjustText(
                t.body_composition.body_composition,
                fontSize: 20,
                color: WHITE_COLOR,
              )
          ),
          backgroundColor: GREEN_COLOR,
          elevation: 4,
          leading: InkWell(
            child: Icon(Icons.arrow_back),
            onTap: () {
              Navigator.of(context).pop();
            },
          ),
        ),
        body: AdjustBodyCompositionInfo()
//        AdjustBodyComposition((bodyCompositionDTO) => setBodyCompositionDTO(bodyCompositionDTO), () {
//          print(EnumToString.parse(this.bodyCompositionDTO.bodyType));
//        })
    );
  }

}