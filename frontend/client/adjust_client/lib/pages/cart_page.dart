import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/pages/order_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/shopping_item_state.dart';
import 'package:ayn_stepper/ayn_stepper.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:redux/redux.dart';
import 'package:stepo/stepo.dart';
import 'package:stepper_counter_swipe/stepper_counter_swipe.dart';

class CartPage extends StatefulWidget {
  @override
  _CartPageState createState() => _CartPageState();
}

class _CartPageState extends State<CartPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: adjustAlignment,
          child: AdjustText(
            t.cart.cart,
            fontSize: 20,
            color: WHITE_COLOR,
          )
        ),
        backgroundColor: GREEN_COLOR,
        elevation: 4,
        leading: InkWell(
          child: Icon(Icons.arrow_back),
          onTap: () {
            Navigator.of(context).pop();
          },
        ),
      ),
      body: StoreConnector<AppState, AppState>(
        converter: (Store store) => store.state,
        builder: (BuildContext context, AppState state) {

          double amount = 0;
          state.cartState.orderItemListState.forEach((orderItemState) {
            amount += orderItemState.shoppingItem.price * orderItemState.number;
          });
          return Container(
            color: LIGHT_GREY_COLOR,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  flex: 8,
                  child: ListView.builder(
                      itemCount: state.cartState.orderItemListState.length,
                      itemBuilder: (BuildContext context, int pos) {
                        OrderItemState orderItemState = state.cartState.orderItemListState[pos];
                        ShoppingItemState shoppingItemState = orderItemState.shoppingItem;
                        Uint8List imageByte = Uint8List.fromList(base64Decode(shoppingItemState.image));
                        Image image = Image.memory(imageByte);
                        return Dismissible(
                          key: Key(shoppingItemState.name),
                          onDismissed: (direction) {
                            state.cartState.orderItemListState.removeAt(pos);
                            StoreProvider.of<AppState>(context).dispatch(
                                RemoveFromCartAction(payload: state.cartState));
                          },
                          child: AdjustInfoButton(
                            id: shoppingItemState.id.toString(),
                            width: 130,
                            height: 130,
                            title: e == null
                                ? ""
                                : getNumber( shoppingItemState.price.round().toString() + " " + t.main.currency),
                            description: "",
                            name: e == null ? "" : shoppingItemState.name,
                            fontSize: 14,
                            isVertical: false,
                            primaryColor: LIGHT_GREEN_COLOR,
                            primaryColorLight: GREEN_COLOR,
                            secondaryColor: WHITE_COLOR,
                            image: image,
                            func: () async {},
                            tailing: Container(
                              padding: EdgeInsets.all(10),
                              child: AYNStepperView(
                                count: orderItemState.number,
                                activeForegroundColor: GREEN_COLOR,
                                didChangeCount: (count) {
                                  if (count > 0) {
                                    if (orderItemState.shoppingItem.number >= count) {
                                      setState(() {
                                        orderItemState.number = count;
                                      });
                                    } else {
                                      showAdjustDialog(context, t.cart.item_number(getNumber(orderItemState.shoppingItem.number.toString())),
                                          false, null, GREEN_COLOR);
                                    }
                                  }
                                },
                              )
                            )
                          ),
                        );
                      }),
                ),
                Expanded(
                  flex: 2,
                  child: Container(
                    color: WHITE_COLOR,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        Expanded(
                          flex: 3,
                          child: FittedBox(
                            fit: BoxFit.scaleDown,
                            child: Container(
                              padding: EdgeInsets.all(5),
                              child: AdjustText(
                                t.cart.note_to_delete,
                                fontSize: 15,
                              ),
                            ),
                          )
                        ),
                        Expanded(
                          flex: 7,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              Expanded(
                                flex: 4,
                                child: FittedBox(
                                  fit: BoxFit.scaleDown,
                                  child: Container(
                                      padding: EdgeInsets.all(10),
                                      child: AdjustText(
                                        "${getNumber(amount.toString())} ${t.main.currency}",
                                        fontSize: 20,
                                      )
                                  ),
                                )
                              ),
                              Expanded(
                                  flex: 6,
                                  child: Container(
                                    padding: EdgeInsets.all(10),
                                    child: AdjustRaisedButton(
                                      height: 50,
                                      width: 200,
                                      textDirection: txtDir,
                                      text: t.order.order,
                                      fontColor: WHITE_COLOR,
                                      primaryColor: GREEN_COLOR,
                                      secondaryColor: GREEN_COLOR,
                                      onPressed: () {
                                        if (state.cartState.orderItemListState.length <= 0) {
                                          showAdjustDialog(
                                              context, t.cart.empty, false, null, null);
                                        } else {
                                          Navigator.of(context).push(
                                              MaterialPageRoute(
                                                  builder: (context) => OrderPage()));
                                        }
                                      },
                                    ),
                                  )),
                            ],
                          ),
                        )
                      ],
                    )
                  ),
                )
              ],
            ),
          );
        },
      ),
    );
  }
}
