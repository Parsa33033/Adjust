import 'package:adjust_client/dto/tutorial_dto.dart';
import 'package:adjust_client/model/course.dart';
import 'package:json_annotation/json_annotation.dart';

part 'course_dto.g.dart';

@JsonSerializable()
class CourseDTO extends Course {
  List<TutorialDTO> tutorials;

  CourseDTO(int id, String title, String description, String thumbnail,
      String thumbnailContentType, this.tutorials)
      : super(id, title, description, thumbnail, thumbnailContentType);

  factory CourseDTO.fromJson(Map<String, dynamic> json) =>
      _$CourseDTOFromJson(json);

  Map<String, dynamic> toJson() => _$CourseDTOToJson(this);
}
