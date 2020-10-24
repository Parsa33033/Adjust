import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/shopping_item_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';

final ShoppingState shoppingStateInit = ShoppingState(List());

class ShoppingState {
  List<ShoppingItemState> items;
  ShoppingState(this.items);
}

