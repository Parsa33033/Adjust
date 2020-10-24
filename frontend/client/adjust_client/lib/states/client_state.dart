import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/dto/tutorial_dto.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/enum/gender.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

import 'app_state.dart';
import 'medical_profile_state.dart';
import 'order_state.dart';

final ClientState clientStateInit = ClientState(
    1, "", "", "", DateTime(2000), 0, null, 0, 0, null, "image/jpg", 0, medicalProfileStateInit, List(), List());

class ClientState extends Client {
  MedicalProfileState medicalProfile;
  List<OrderState> orders;
  List<TutorialState> tutorialList;

  ClientState(
      int id,
      String username,
      String firstName,
      String lastName,
      DateTime birthDate,
      int age,
      Gender gender,
      double token,
      double score,
      String image,
      String imageContentType,
      int medicalProfileId,
      this.medicalProfile,
      this.orders,
      this.tutorialList)
      : super(id, username, firstName, lastName, birthDate, age, gender, token,
            score, image, imageContentType, medicalProfileId);
}

