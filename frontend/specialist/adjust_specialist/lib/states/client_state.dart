import 'package:adjust_specialist/actions/client_action.dart';
import 'package:adjust_specialist/model/client.dart';
import 'package:adjust_specialist/model/enum/gender.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

import 'app_state.dart';
import 'medical_profile_state.dart';

final ClientState clientStateInit = ClientState(
    1, "", "", "", DateTime(2000), 0, null, 0, 0, null, "image/jpg", 0, null);

class ClientState extends Client {
  MedicalProfileState medicalProfile;

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
      this.medicalProfile)
      : super(id, username, firstName, lastName, birthDate, age, gender, token,
            score, image, imageContentType, medicalProfileId);
}