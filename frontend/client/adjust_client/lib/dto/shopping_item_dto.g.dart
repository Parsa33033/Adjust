// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'shopping_item_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

ShoppingItemDTO _$ShoppingItemDTOFromJson(Map<String, dynamic> json) {
  return ShoppingItemDTO(
    json['id'] as int,
    json['name'] as String,
    json['itemId'] as int,
    json['description'] as String,
    (json['price'] as num)?.toDouble(),
    json['image'] as String,
    json['imageContentType'] as String,
    json['orderable'] as bool,
    json['number'] as int,
  );
}

Map<String, dynamic> _$ShoppingItemDTOToJson(ShoppingItemDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'name': instance.name,
      'itemId': instance.itemId,
      'description': instance.description,
      'price': instance.price,
      'image': instance.image,
      'imageContentType': instance.imageContentType,
      'orderable': instance.orderable,
      'number': instance.number,
    };
