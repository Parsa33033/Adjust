import 'package:adjust_specialist/dto/shopping_item_dto.dart';
import 'package:adjust_specialist/model/order_item.dart';
import 'package:json_annotation/json_annotation.dart';

part 'order_item_dto.g.dart';

@JsonSerializable()
class OrderItemDTO extends OrderItem {
  ShoppingItemDTO shoppingItem;
  OrderItemDTO(int id, int number, int orderId, this.shoppingItem) : super(id, number, orderId);


  factory OrderItemDTO.fromJson(Map<String, dynamic> json) =>
      _$OrderItemDTOFromJson(json);

  Map<String, dynamic> toJson() => _$OrderItemDTOToJson(this);
}
