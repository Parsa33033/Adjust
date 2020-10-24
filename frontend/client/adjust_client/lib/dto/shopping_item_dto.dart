import 'package:adjust_client/model/shopping_item.dart';
import 'package:json_annotation/json_annotation.dart';

part 'shopping_item_dto.g.dart';

@JsonSerializable()
class ShoppingItemDTO extends ShoppingItem {
  ShoppingItemDTO(int id, String name, int itemId, String description,
      double price, String image, String imageContentType, bool orderable, int number)
      : super(id, name, itemId, description, price, image, imageContentType,
            orderable, number);

  factory ShoppingItemDTO.fromJson(Map<String, dynamic> json) =>
      _$ShoppingItemDTOFromJson(json);

  Map<String, dynamic> toJson() => _$ShoppingItemDTOToJson(this);
}
