import 'dart:convert';
import 'dart:io';
import 'dart:typed_data';

import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/body_composition_dto.dart';
import 'package:adjust_client/dto/client_dto.dart';
import 'package:adjust_client/dto/medical_profile_dto.dart';
import 'package:adjust_client/dto/shopping_item_dto.dart';
import 'package:adjust_client/dto/tutorial_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/medical_profile.dart';
import 'package:adjust_client/model/order_item.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/body_composition_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/disease_state.dart';
import 'package:adjust_client/states/medical_profile_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/order_state.dart';
import 'package:adjust_client/states/shopping_item_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;

import 'jwt.dart';

class CreateClientAction {
  ClientState payload;

  CreateClientAction({this.payload});
}

class UpdateClientAction {
  ClientState payload;

  UpdateClientAction({this.payload});
}

class GetClientAction {
  ClientState payload;

  GetClientAction({this.payload});
}

Future<int> updateClient(BuildContext context, ClientDTO clientDTO) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  String content = jsonEncode(clientDTO.toJson());

  http.Response response = await http.put(CLIENT_URL,
      headers: headers, body: content, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    Map<String, dynamic> m = jsonDecode(utf8.decode(response.bodyBytes));

    ClientDTO clientDTO = ClientDTO.fromJson(m);

    ClientState clientState = getClientState(clientDTO);

    try {
      StoreProvider.of<AppState>(context).dispatch(
          UpdateClientAction(payload: clientState));
    } catch (e) {
      store.dispatch(UpdateClientAction(payload: clientState));
    }

    return 1;
  }
  return 0;
}

Future<int> getClient(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);
  http.Response response = await http.get(CLIENT_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    Map<String, dynamic> m = jsonDecode(utf8.decode(response.bodyBytes));

    ClientDTO clientDTO = ClientDTO.fromJson(m);

    ClientState clientState = getClientState(clientDTO);

    try {
      StoreProvider.of<AppState>(context).dispatch(
          UpdateClientAction(payload: clientState));
    } catch (e) {
      store.dispatch(GetClientAction(payload: clientState));
    }

    return 1;
  }
  return 0;
}

Future<int> getClientToken(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response =
  await http.get(GET_CLIENT_TOKENS_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    double token = double.parse(response.body);
    ClientState clientState = store.state.clientState;
    clientState.token = token;
    StoreProvider.of<AppState>(context)
        .dispatch(UpdateClientAction(payload: clientState));
    return 1;
  }
  return 0;
}

Future<int> getClientScore(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response =
  await http.get(GET_CLIENT_SCORE_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    double score = double.parse(response.body);
    ClientState clientState = store.state.clientState;
    clientState.score = score;
    StoreProvider.of<AppState>(context)
        .dispatch(UpdateClientAction(payload: clientState));
    return 1;
  }
  return 0;
}

ClientState getClientState(ClientDTO clientDTO) {
  MedicalProfileState medicalProfileState = null;
  if (clientDTO.medicalProfile != null) {
    MedicalProfileDTO medicalProfileDTO = clientDTO.medicalProfile;
    if (medicalProfileDTO.bodyComposition != null) {
      BodyCompositionDTO bodyCompositionDTO = medicalProfileDTO.bodyComposition;
      BodyCompositionState bodyCompositionState = BodyCompositionState(
          bodyCompositionDTO.id,
          bodyCompositionDTO.createdAt,
          bodyCompositionDTO.age,
          bodyCompositionDTO.gender,
          bodyCompositionDTO.height,
          bodyCompositionDTO.weight,
          bodyCompositionDTO.wrist,
          bodyCompositionDTO.waist,
          bodyCompositionDTO.hip,
          bodyCompositionDTO.abdomen,
          bodyCompositionDTO.neck,
          bodyCompositionDTO.waistHipRatio,
          bodyCompositionDTO.idealWeightMin,
          bodyCompositionDTO.idealWeightMax,
          bodyCompositionDTO.bestWeight,
          bodyCompositionDTO.bmi,
          bodyCompositionDTO.bmiCategory,
          bodyCompositionDTO.bmr,
          bodyCompositionDTO.lbm,
          bodyCompositionDTO.pbf,
          bodyCompositionDTO.bodyFatCategory,
          bodyCompositionDTO.smm,
          bodyCompositionDTO.bodyTypeNumber,
          bodyCompositionDTO.bodyType,
          bodyCompositionDTO.frameSize,
          bodyCompositionDTO.bodyFrameSize,
          bodyCompositionDTO.visceralFatLevel,
          bodyCompositionDTO.workoutLocation,
          bodyCompositionDTO.goal,
          bodyCompositionDTO.activityType,
          bodyCompositionDTO.workoutType);

      List<DiseaseState> diseaseListState =
      medicalProfileDTO.diseaseList.map((diseaseDTO) {
        return DiseaseState(
            diseaseDTO.id,
            diseaseDTO.name,
            diseaseDTO.proteinPercentage,
            diseaseDTO.carbsPercentage,
            diseaseDTO.fatPercentage);
      }).toList();

      medicalProfileState = MedicalProfileState(
          medicalProfileDTO.id,
          medicalProfileDTO.haveLostWeight,
          medicalProfileDTO.drowsiness,
          medicalProfileDTO.goodNightSleep,
          medicalProfileDTO.hoursOfTraningPerWeek,
          medicalProfileDTO.loveForSugar,
          medicalProfileDTO.overEating,
          medicalProfileDTO.maritalStatus,
          medicalProfileDTO.childrenNumber,
          medicalProfileDTO.education,
          medicalProfileDTO.occupation,
          medicalProfileDTO.hoursOfWorkDaily,
          medicalProfileDTO.financialStatus,
          medicalProfileDTO.city,
          medicalProfileDTO.useOfMedicine,
          medicalProfileDTO.medicines,
          medicalProfileDTO.haveAllergy,
          medicalProfileDTO.allergy,
          medicalProfileDTO.haveDisease,
          medicalProfileDTO.bloodType,
          medicalProfileDTO.bodyCompositionId,
          diseaseListState,
          bodyCompositionState);
    }
  }
  List<OrderState> orderListState = List();
  if (clientDTO.orders != null) {
    orderListState = clientDTO.orders.map((orderDTO) {
      List<OrderItem> orderItemListState =
      orderDTO.orderItems.map((orderItemDTO) {
        ShoppingItemDTO shoppingItemDTO = orderItemDTO.shoppingItem;
        ShoppingItemState shoppingItemState = ShoppingItemState(
            shoppingItemDTO.id,
            shoppingItemDTO.name,
            shoppingItemDTO.itemId,
            shoppingItemDTO.description,
            shoppingItemDTO.price,
            shoppingItemDTO.image,
            shoppingItemDTO.imageContentType,
            shoppingItemDTO.orderable,
            shoppingItemDTO.number);
        return OrderItemState(orderItemDTO.id, orderItemDTO.number,
            orderItemDTO.orderId, shoppingItemState);
      }).toList();
      return OrderState(
          orderDTO.id,
          orderDTO.username,
          orderDTO.dateOfOrder,
          orderDTO.firstName,
          orderDTO.lastName,
          orderDTO.phoneNumber,
          orderDTO.email,
          orderDTO.country,
          orderDTO.state,
          orderDTO.city,
          orderDTO.address1,
          orderDTO.address2,
          orderDTO.done,
          orderDTO.paid,
          orderDTO.received,
          orderDTO.amount,
          orderDTO.adjustClientId,
          orderItemListState);
    }).toList();
  }

//  List<TutorialState> tutorialDTOList = null;
//  if (clientDTO.tutorialList != null) {
//    tutorialDTOList = clientDTO.tutorialList.map((tutorialDTO) {
//      return TutorialState(
//          tutorialDTO.id,
//          tutorialDTO.title,
//          tutorialDTO.description,
//          tutorialDTO.text,
//          tutorialDTO.thumbnail,
//          tutorialDTO.thumbnailContentType,
//          tutorialDTO.tokenPrice,
//          tutorialDTO.videoId,
//          tutorialDTO.courseId);
//    }).toList();
//  }


  return ClientState(
      clientDTO.id,
      clientDTO.username,
      clientDTO.firstName,
      clientDTO.lastName,
      clientDTO.birthDate,
      clientDTO.age,
      clientDTO.gender,
      clientDTO.token,
      clientDTO.score,
      clientDTO.image,
      clientDTO.imageContentType,
      clientDTO.medicalProfileId,
      medicalProfileState,
      orderListState,
      null);
}
