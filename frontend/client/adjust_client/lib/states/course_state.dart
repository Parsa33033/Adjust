import 'package:adjust_client/model/course.dart';
import 'package:adjust_client/states/tutorial_state.dart';

final CourseListState courseListStateInit = CourseListState(List());

class CourseListState {
  List<CourseState> courses;

  CourseListState(this.courses);
}

class CourseState extends Course {
  List<TutorialState> tutorials;

  CourseState(int id, String title, String description, String thumbnail,
      String thumbnailContentType, this.tutorials)
      : super(id, title, description, thumbnail, thumbnailContentType);
}
