import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/course_dto.dart';
import 'package:adjust_client/dto/tutorial_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/course.dart';
import 'package:adjust_client/states/course_state.dart';
import 'package:http/http.dart' as http;
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

import 'jwt.dart';

class GetTutorialsAction {
  TutorialListState payload;

  GetTutorialsAction({this.payload});
}

class GetClientTutorialsAction {
  ClientTutorialsState payload;

  GetClientTutorialsAction({this.payload});
}

class GetTutorialAction {
  TutorialState payload;

  GetTutorialAction({this.payload});
}

class GetCourseListAction {
  CourseListState payload;

  GetCourseListAction({this.payload});
}

Future<int> getTutorials(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.get(TUTORIAL_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<TutorialState> items = l.map((e) {
      TutorialDTO tutorialDTO = TutorialDTO.fromJson(e);
      return TutorialState(
          tutorialDTO.id,
          tutorialDTO.title,
          tutorialDTO.description,
          tutorialDTO.text,
          tutorialDTO.thumbnail,
          tutorialDTO.thumbnailContentType,
          tutorialDTO.tokenPrice,
          tutorialDTO.videoId,
          tutorialDTO.courseId);
    }).toList();
    TutorialListState tutorialListState = TutorialListState(items);
    StoreProvider.of<AppState>(context)
        .dispatch(GetTutorialsAction(payload: tutorialListState));
    return 1;
  }
  return 0;
}

Future<int> buyTutorial(BuildContext context, int videoId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  http.Response response = await http.post(BUY_TUTORIAL_URL,
      headers: headers,
      body: videoId.toString(),
      encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    await getClientTutorials(context);
    return 1;
  } else if (response.statusCode == 422) {
    // client has the tutorial already
    return 2;
  } else if (response.statusCode == 424) {
    // client does not have enough token
    return 3;
  }
  return 0;
}

Future<int> getClientTutorials(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  http.Response response =
      await http.get(GET_CLIENT_TUTORIALS_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<TutorialState> items = l.map((e) {
      TutorialDTO tutorialDTO = TutorialDTO.fromJson(e);
      return TutorialState(
          tutorialDTO.id,
          tutorialDTO.title,
          tutorialDTO.description,
          tutorialDTO.text,
          tutorialDTO.thumbnail,
          tutorialDTO.thumbnailContentType,
          tutorialDTO.tokenPrice,
          tutorialDTO.videoId,
          tutorialDTO.courseId);
    }).toList();
    ClientTutorialsState tutorialListState = ClientTutorialsState(items);
    store.dispatch(GetClientTutorialsAction(payload: tutorialListState));
    return 1;
  }
  return 0;
}

Future<int> getCourseList(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)
    ..putIfAbsent("Content-Type", () => "application/json");

  http.Response response = await http.get(COURSE_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));
    List<CourseState> courseDTOList = l.map((e) {
      CourseDTO courseDTO = CourseDTO.fromJson(e);
      List<TutorialState> tutorialStateList =
          courseDTO.tutorials.map((tutorialDTO) {
        return TutorialState(
            tutorialDTO.id,
            tutorialDTO.title,
            tutorialDTO.description,
            tutorialDTO.text,
            tutorialDTO.thumbnail,
            tutorialDTO.thumbnailContentType,
            tutorialDTO.tokenPrice,
            tutorialDTO.videoId,
            tutorialDTO.courseId);
      }).toList();
      return CourseState(
          courseDTO.id,
          courseDTO.title,
          courseDTO.description,
          courseDTO.thumbnail,
          courseDTO.thumbnailContentType,
          tutorialStateList);
    }).toList();

    CourseListState courseListState = CourseListState(courseDTOList);
    try {
      StoreProvider.of<AppState>(context)
          .dispatch(GetCourseListAction(payload: courseListState));
    } catch (e) {
      store.dispatch(GetCourseListAction(payload: courseListState));
    }
    return 1;
  }
  return 0;
}
