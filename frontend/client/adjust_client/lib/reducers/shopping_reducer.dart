

import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/cart_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/shopping_state.dart';
import 'package:adjust_client/states/token_state.dart';
import 'package:flutter/cupertino.dart';

AppState shopingItemReducer(AppState state, dynamic action) {
  if (action is GetShopingItemsAction) {
    ShoppingState shoppingState = state.shoppingState;
    shoppingState.items = action.payload.items != null ? action.payload.items : shoppingState.items;
    state.shoppingState = shoppingState;
    return state;
  } else if (action is GetTokenItemsAction) {
    TokenState tokenState = state.tokenState;
    tokenState.items =
    action.payload.items != null ? action.payload.items : tokenState.items;
    state.tokenState = tokenState;
    return state;
  } else if (action is AddToCartAction) {
    CartState cartState = state.cartState;
    cartState.orderItemListState = action.payload.orderItemListState != null ? action.payload.orderItemListState : cartState.orderItemListState;
    state.cartState = cartState;
    return state;
  }  else if (action is RemoveFromCartAction) {
    CartState cartState = state.cartState;
    cartState.orderItemListState = action.payload.orderItemListState != null ? action.payload.orderItemListState : cartState.orderItemListState;
    state.cartState = cartState;
    return state;
  } else {
    return state;
  }
}