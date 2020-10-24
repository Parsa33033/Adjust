

import 'package:adjust_client/states/order_item_state.dart';

final CartState cartStateInit = CartState(List());

class CartState {
  List<OrderItemState> orderItemListState;

  CartState(this.orderItemListState);
}