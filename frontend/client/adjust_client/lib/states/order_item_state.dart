import 'package:adjust_client/model/order_item.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/states/shopping_item_state.dart';
import 'package:adjust_client/states/shopping_state.dart';

final OrderItemState orderItemStateInit = OrderItemState(0, 0, 0, shoppingItemStateInit);

class OrderItemState extends OrderItem {
  ShoppingItemState shoppingItem;
  OrderItemState(int id, int number, int orderId, this.shoppingItem) : super(id, number, orderId);
}
