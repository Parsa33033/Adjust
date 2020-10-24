// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'order_item_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OrderItemDTO _$OrderItemDTOFromJson(Map<String, dynamic> json) {
  return OrderItemDTO(
    json['id'] as int,
    json['number'] as int,
    json['orderId'] as int,
    json['shoppingItem'] == null
        ? null
        : ShoppingItemDTO.fromJson(
            json['shoppingItem'] as Map<String, dynamic>),
  );
}

Map<String, dynamic> _$OrderItemDTOToJson(OrderItemDTO instance) =>
    <String, dynamic>{
      'id': instance.id,
      'number': instance.number,
      'orderId': instance.orderId,
      'shoppingItem': instance.shoppingItem,
    };
