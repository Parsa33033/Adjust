

import 'package:adjust_client/actions/tutorial_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/course_state.dart';
import 'package:adjust_client/states/tutorial_state.dart';

AppState tutorialReducer(AppState state, dynamic action) {
  if (action is GetTutorialAction) {
    TutorialState tutorialState = state.tutorialState;
    tutorialState.id = action.payload.id != null ? action.payload.id : tutorialState.id;
    tutorialState.text = action.payload.text != null ? action.payload.text : tutorialState.text;
    tutorialState.description = action.payload.description != null ? action.payload.description : tutorialState.description;
    tutorialState.thumbnail = action.payload.thumbnail != null ? action.payload.thumbnail : tutorialState.thumbnail;
    tutorialState.title = action.payload.title != null ? action.payload.title : tutorialState.title;
    tutorialState.tokenPrice = action.payload.tokenPrice != null ? action.payload.tokenPrice : tutorialState.tokenPrice;
    tutorialState.videoId = action.payload.videoId != null ? action.payload.videoId : tutorialState.videoId;
    tutorialState.thumbnailContentType = action.payload.thumbnailContentType != null ? action.payload.thumbnailContentType : tutorialState.thumbnailContentType;
    tutorialState.courseId = action.payload.courseId != null ? action.payload.courseId : tutorialState.courseId;
    state.tutorialState = tutorialState;
    return state;
  } else if (action is GetTutorialsAction) {
    TutorialListState tutorialListState = state.tutorialListState;
    tutorialListState.items = action.payload.items != null ? action.payload.items : tutorialListState.items;
    state.tutorialListState = tutorialListState;
    return state;
  } else if (action is GetClientTutorialsAction) {
    ClientTutorialsState clientTutorialsState = state.clientTutorialsState;
    clientTutorialsState.items =
    action.payload.items != null ? action.payload.items : clientTutorialsState
        .items;
    state.clientTutorialsState = clientTutorialsState;
    return state;
  } else if (action is GetCourseListAction) {
    CourseListState courseListState = state.courseListState;
    courseListState.courses = action.payload.courses != null ? action.payload.courses : courseListState.courses;
    state.courseListState = courseListState;
    return state;
  } else {
    return state;
  }
}