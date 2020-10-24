import 'package:adjust_client/model/order.dart';
import 'package:adjust_client/model/order_item.dart';
import 'package:json_annotation/json_annotation.dart';

import 'order_item_dto.dart';

part 'order_dto.g.dart';

@JsonSerializable()
class OrderDTO extends Order {
  List<OrderItemDTO> orderItems;
  OrderDTO(
      int id,
      String username,
      DateTime dateOfOrder,
      String firstName,
      String lastName,
      String phoneNumber,
      String email,
      String country,
      String state,
      String city,
      String address1,
      String address2,
      bool done,
      bool paid,
      bool received,
      double amount,
      int adjustClientId,
      this.orderItems)
      : super(
            id,
            username,
            dateOfOrder,
            firstName,
            lastName,
            phoneNumber,
            email,
            country,
            state,
            city,
            address1,
            address2,
            done,
            paid,
            received,
            amount,
            adjustClientId);

  factory OrderDTO.fromJson(Map<String, dynamic> json) =>
      _$OrderDTOFromJson(json);

  Map<String, dynamic> toJson() => _$OrderDTOToJson(this);
}
