import 'package:adjust_client/model/shopping_item.dart';

final ShoppingItemState shoppingItemStateInit = ShoppingItemState(0, "", 0, "", 0, "", "", false, 0);

class ShoppingItemState extends ShoppingItem {
  ShoppingItemState(int id, String name, int itemId, String description,
      double price, String image, String imageContentType, bool orderable, int number)
      : super(id, name, itemId, description, price, image, imageContentType,
            orderable, number);
}
