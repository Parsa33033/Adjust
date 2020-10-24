import 'dart:convert';
import 'dart:io';

import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/constants/urls.dart';
import 'package:adjust_client/dto/order_dto.dart';
import 'package:adjust_client/dto/order_item_dto.dart';
import 'package:adjust_client/dto/shopping_item_dto.dart';
import 'package:adjust_client/dto/token_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/client.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/model/token.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/cart_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/client_state.dart';
import 'package:adjust_client/states/order_state.dart';
import 'package:adjust_client/states/shopping_item_state.dart';
import 'package:adjust_client/states/shopping_state.dart';
import 'package:adjust_client/states/token_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:http/http.dart' as http;
import 'package:redux/redux.dart';

import 'jwt.dart';

class GetShopingItemsAction {
  ShoppingState payload;

  GetShopingItemsAction({this.payload});
}

class GetTokenItemsAction {
  TokenState payload;

  GetTokenItemsAction({this.payload});
}

class AddToCartAction {
  CartState payload;

  AddToCartAction({this.payload});
}

class RemoveFromCartAction {
  CartState payload;

  RemoveFromCartAction({this.payload});
}

Future<int> getShoppingItems(BuildContext context) async {
  http.Response response = await http.get(
    CLIENT_SHOPPING_URL,
  );
  if (response.statusCode == HttpStatus.ok) {
    List m = jsonDecode(utf8.decode(response.bodyBytes));
    List<ShoppingItemDTO> shoppingItemDTOList =
    m.map((e) => ShoppingItemDTO.fromJson(e)).toList();
    List<ShoppingItemState> shoppingItemStateList = shoppingItemDTOList.map((
        shoppingItemDTO) {
      return ShoppingItemState(
          shoppingItemDTO.id,
          shoppingItemDTO.name,
          shoppingItemDTO.itemId,
          shoppingItemDTO.description,
          shoppingItemDTO.price,
          shoppingItemDTO.image,
          shoppingItemDTO.imageContentType,
          shoppingItemDTO.orderable,
          shoppingItemDTO.number);
    }).toList();
    ShoppingState shoppingState = ShoppingState(shoppingItemStateList);
    try {
      StoreProvider.of<AppState>(context).dispatch(
          GetShopingItemsAction(payload: shoppingState));
    } catch (e) {
      store.dispatch(GetShopingItemsAction(payload: shoppingState));
    }
    return 1;
  }
  return 0;
}

Future<int> getTokenItems(BuildContext context) async {
  http.Response response = await http.get(
    CLIENT_TOKENS_URL,
  );
  if (response.statusCode == HttpStatus.ok) {
    List m = jsonDecode(utf8.decode(response.bodyBytes));
    List content = m.map((e) {
      return e;
    }).toList();

    List<TokenItem> tokenList =
    content.map((e) => TokenItemDTO.fromJson(e)).toList();
    Token token = Token(tokenList);
    setTokenState(context, token);
    return 1;
  }
  return 0;
}

Future<int> buyToken(BuildContext context, int shopingItemId) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt);

  http.Response response = await http.post(BUY_TOKEN_URL,
      headers: headers, body: shopingItemId.toString());
  if (response.statusCode == HttpStatus.ok) {
    double token = double.parse(response.body);
    ClientState clientState = store.state.clientState;
    clientState.token = token;
    try {
      StoreProvider.of<AppState>(context).dispatch(
          UpdateClientAction(payload: clientState));
    } catch (e) {
      store.dispatch(UpdateClientAction(payload: clientState));
    }
    return 1;
  }
  return 0;
}

void addToCart(BuildContext context, List<OrderItemState> orderItemStateList) {
  CartState cartState = CartState(orderItemStateList);
  StoreProvider.of<AppState>(context)
      .dispatch(AddToCartAction(payload: cartState));
}

Future<int> order(BuildContext context, OrderDTO orderDTO) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  String content = jsonEncode(orderDTO.toJson());

  http.Response response = await http.post(ORDER_URL,
      body: content, headers: headers, encoding: Encoding.getByName("UTF-8"));
  if (response.statusCode == HttpStatus.ok) {
    return 1;
  }
  return 0;
}


Future<int> getClientOrders(BuildContext context) async {
  String jwt = await getJwt(context);

  Map<String, String> headers = Map<String, String>()
    ..putIfAbsent("Authorization", () => "Bearer " + jwt)..putIfAbsent(
        "Content-Type", () => "application/json");

  http.Response response = await http.get(
      GET_CLIENT_ORSERS_URL, headers: headers);
  if (response.statusCode == HttpStatus.ok) {
    List l = jsonDecode(utf8.decode(response.bodyBytes));

    List<OrderState> orderStateList = l.map((e) {
      OrderDTO orderDTO = OrderDTO.fromJson(e);
      List<OrderItemState> orderItemStateList = orderDTO.orderItems.map((
          orderItemDTO) {
        ShoppingItemDTO shoppingItemDTO = orderItemDTO.shoppingItem;
        ShoppingItemState shoppingItemState = ShoppingItemState(
            shoppingItemDTO.id,
            shoppingItemDTO.name,
            shoppingItemDTO.itemId,
            shoppingItemDTO.description,
            shoppingItemDTO.price,
            shoppingItemDTO.image,
            shoppingItemDTO.imageContentType,
            shoppingItemDTO.orderable,
            shoppingItemDTO.number);
        return OrderItemState(
            orderItemDTO.id, orderItemDTO.number, orderItemDTO.orderId,
            shoppingItemState);
      }).toList();

      return OrderState(
          orderDTO.id,
          orderDTO.username,
          orderDTO.dateOfOrder,
          orderDTO.firstName,
          orderDTO.lastName,
          orderDTO.phoneNumber,
          orderDTO.email,
          orderDTO.country,
          orderDTO.state,
          orderDTO.city,
          orderDTO.address1,
          orderDTO.address2,
          orderDTO.done,
          orderDTO.paid,
          orderDTO.received,
          orderDTO.amount,
          orderDTO.adjustClientId,
          orderItemStateList);
    }).toList();

    Store<AppState> s;
    try {
      s = StoreProvider.of<AppState>(context);
    } catch (e) {
      s = store;
    }
    s.state.clientState.orders = orderStateList;
    s.dispatch(UpdateClientAction(payload: s.state.clientState));
    return 1;
  }
  return 0;
}