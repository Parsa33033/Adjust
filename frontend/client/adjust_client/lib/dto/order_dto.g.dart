// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'order_dto.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

OrderDTO _$OrderDTOFromJson(Map<String, dynamic> json) {
  return OrderDTO(
    json['id'] as int,
    json['username'] as String,
    json['dateOfOrder'] == null
        ? null
        : DateTime.parse(json['dateOfOrder'] as String),
    json['firstName'] as String,
    json['lastName'] as String,
    json['phoneNumber'] as String,
    json['email'] as String,
    json['country'] as String,
    json['state'] as String,
    json['city'] as String,
    json['address1'] as String,
    json['address2'] as String,
    json['done'] as bool,
    json['paid'] as bool,
    json['received'] as bool,
    (json['amount'] as num)?.toDouble(),
    json['adjustClientId'] as int,
    (json['orderItems'] as List)
        ?.map((e) =>
            e == null ? null : OrderItemDTO.fromJson(e as Map<String, dynamic>))
        ?.toList(),
  );
}

Map<String, dynamic> _$OrderDTOToJson(OrderDTO instance) => <String, dynamic>{
      'id': instance.id,
      'username': instance.username,
      'dateOfOrder': instance.dateOfOrder?.toIso8601String(),
      'firstName': instance.firstName,
      'lastName': instance.lastName,
      'phoneNumber': instance.phoneNumber,
      'email': instance.email,
      'country': instance.country,
      'state': instance.state,
      'city': instance.city,
      'address1': instance.address1,
      'address2': instance.address2,
      'done': instance.done,
      'paid': instance.paid,
      'received': instance.received,
      'amount': instance.amount,
      'adjustClientId': instance.adjustClientId,
      'orderItems': instance.orderItems,
    };
