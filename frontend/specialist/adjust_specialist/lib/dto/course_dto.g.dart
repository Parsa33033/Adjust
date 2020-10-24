// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'course_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CourseDTO _$CourseDTOFromJson(Map<String, dynamic> json) {
  return CourseDTO(
    json['id'] as int,
    json['title'] as String,
    json['description'] as String,
    (json['tutorials'] as List)
        ?.map((e) =>
            e == null ? null : TutorialDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$CourseDTOToJson(CourseDTO instance) => <String, dynamic>{
      'id': instance.id,
      'title': instance.title,
      'description': instance.description,
      'tutorials': instance.tutorials,
    };
