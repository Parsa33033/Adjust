import 'package:adjust_client/model/order.dart';
import 'package:adjust_client/states/order_item_state.dart';

class OrderState extends Order {
  List<OrderItemState> orderItems;
  OrderState(
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
}
