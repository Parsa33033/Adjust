import 'dart:convert';
import 'dart:typed_data';

import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/shopping_item_dto.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/adjust_item_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class ShopingPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return StoreConnector<AppState, AppState>(
      converter: (Store store) => store.state,
      builder: (BuildContext context, AppState state) {
        double width = MediaQuery.of(context).size.width;
        return Container(
          decoration: BoxDecoration(
//            boxShadow: [BoxShadow(color: SHADOW, offset: Offset(4,4), spreadRadius: 2, blurRadius: 2)],
            color: LIGHT_GREY_COLOR
          ),
            width: width,
//            margin: EdgeInsets.only(bottom: 30, top: 10),
            child: SingleChildScrollView(
              padding: EdgeInsets.only(bottom: 30, top: 10),
              child: Column(
                children: <Widget>[
//                  Container(
//                      padding: EdgeInsets.all(10),
//                      width: width,
//                      child: Center(
//                        child: Directionality(
//                          textDirection: TextDirection.rtl,
//                          child: Text(
//                            "توکن ها",
//                            style: TextStyle(
//                                fontFamily: "Iransans",
//                                fontSize: 18,
//                                color: FONT),
//                          ),
//                        ),
//                      )),
                  Container(
                    height: 200,
                    child: ListView(
//                      padding: EdgeInsets.all(10),
                        scrollDirection: Axis.horizontal,
                        children: state.tokenState.items.map((e) {
                          List i = Uint8List.fromList(base64Decode(e.image));
                          List<int> imageByte = List<int>.from(i);
                          Image image =
                              Image.memory(Uint8List.fromList(imageByte));

                          return AdjustInfoButton(
                            width: 160,
                            height: 160,
                            id: e.name,
                            title: e == null
                                ? ""
                                : getNumber(e.price.round().toString() + " " + t.main.currency),
                            description: "",
                            name: e == null ? "" : getNumber(e.token.round().toString() + " " + t.main.token),
                            fontSize: 14,
                            isVertical: true,
                            primaryColor: RED_COLOR,
                            primaryColorLight: LIGHT_RED_COLOR,
                            secondaryColor: WHITE_COLOR,
                            image: image,
                            func: () async {
                              int id = e.id;
                              Navigator.of(context).push(PageRouteBuilder(
                                  transitionDuration:
                                      Duration(milliseconds: 500),
                                  pageBuilder:
                                      (context, animation1, animation2) {
                                    return AdjustItemPage(
                                      image: image,
                                      name: e.name,
                                      title: e.name,
                                      description: e.description,
                                      buttonText: t.main.buy,
                                      isInfo: false,
                                      primaryColor: RED_COLOR,
                                      primaryColorLight: LIGHT_RED_COLOR,
                                      backgroundImagePath: "assets/bg_red.png",
                                      onButtonPressed: () async {
                                        showAdjustDialog(context, t.user.sure_with_decision, true, () async {
                                          await buyToken(context, id);
                                          Navigator.of(context).pushReplacement(
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      MainPage()));
                                        }, RED_COLOR);
                                      },
                                    );
                                  }));
                            },
                          );
                        }).toList()),
                  ),
//                  Container(
//                      padding: EdgeInsets.all(10),
//                      width: width,
//                      child: Center(
//                        child: Directionality(
//                          textDirection: TextDirection.rtl,
//                          child: Text(
//                            "فروشگاه",
//                            style: TextStyle(
//                                fontFamily: "Iransans",
//                                fontSize: 18,
//                                color: FONT),
//                          ),
//                        ),
//                      )),
                  Container(
                    height: 200,
                    child: ListView(
//                      padding: EdgeInsets.all(20),
                        scrollDirection: Axis.horizontal,
                        children: state.shoppingState.items.map((shoppingItem) {
                          List i = Uint8List.fromList(base64Decode(shoppingItem.image));
                          List<int> imageByte = List<int>.from(i);
                          Image image =
                              Image.memory(Uint8List.fromList(imageByte));

                          return AdjustInfoButton(
                            width: 160,
                            height: 160,
                            id: shoppingItem.name,
                            title: shoppingItem == null
                                ? ""
                                : getNumber(shoppingItem.price.round().toString() + " " + t.main.currency),
                            description: "",
                            name: shoppingItem == null ? "" : shoppingItem.name,
                            fontSize: 14,
                            isVertical: true,
                            primaryColor: LIGHT_GREEN_COLOR,
                            primaryColorLight: GREEN_COLOR,
                            secondaryColor: WHITE_COLOR,
                            image: image,
                            func: () async {
                              int id = shoppingItem.id;
                              Navigator.of(context).push(PageRouteBuilder(
                                  transitionDuration:
                                      Duration(milliseconds: 500),
                                  pageBuilder:
                                      (context, animation1, animation2) {
                                    return AdjustItemPage(
                                      image: image,
                                      name: shoppingItem.name,
                                      title: "${t.order.price}: ${getNumber(shoppingItem.price.round().toString())} ${t.main.currency} - ${t.cart.number}: ${getNumber(shoppingItem.number.toString())}",
                                      description: shoppingItem.description,
                                      buttonText: t.cart.add_to_cart,
                                      isInfo: false,
                                      primaryColor: GREEN_COLOR,
                                      primaryColorLight: LIGHT_GREEN_COLOR,
                                      backgroundImagePath: "assets/bg_green.png",
                                      onButtonPressed: () async {
                                        OrderItemState orderItem = OrderItemState(null, 1, null, shoppingItem);
                                        if (state.cartState.orderItemListState.where((orderItemState) => orderItemState.shoppingItem.id == shoppingItem.id).toList().length == 0) {
                                          List<OrderItemState> items =
                                              state.cartState.orderItemListState;
                                          items.add(orderItem);
                                          addToCart(context, items);
                                          Navigator.of(context).pushReplacement(
                                              MaterialPageRoute(
                                                  builder: (context) =>
                                                      MainPage()));
                                        } else {
                                          showAdjustDialog(context, t.cart.already_added, false, null, GREEN_COLOR);
                                        }
                                      },
                                    );
                                  }));
                            },
                          );
                        }).toList()),
                  )
                ],
              ),
            ));
      },
    );
  }
}
