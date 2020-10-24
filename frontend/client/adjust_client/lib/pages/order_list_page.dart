import 'dart:convert';
import 'dart:typed_data';
import 'dart:math';

import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/actions/client_action.dart';
import 'package:adjust_client/actions/tutorial_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_info_button.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/dashboard.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/config/localization.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/model/tutorial.dart';
import 'package:adjust_client/notifications/adjust_state_change_notification.dart';
import 'package:adjust_client/pages/main_page.dart';
import 'package:adjust_client/pages/order_page.dart';
import 'package:adjust_client/pages/tutorial_video_page.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/order_state.dart';
import 'package:expansion_card/expansion_card.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';

class OrderListPage extends StatefulWidget {
  @override
  _OrderListPageState createState() => _OrderListPageState();
}

class _OrderListPageState extends State<OrderListPage> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getClientOrders(context);
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: adjustAlignment,
          child: AdjustText(
            t.order.order,
            fontSize: 20,
            color: WHITE_COLOR,
          ),
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
          return Container(
            color: LIGHT_GREY_COLOR,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Expanded(
                  child: state.clientState.orders.length <= 0 ?
                      Container(
                        child: Center(
                          child: AdjustText(t.main.empty_list(t.order.orders)),
                        )
                      )
                      :ListView.builder(
                      itemCount: state.clientState.orders.length,
                      itemBuilder: (BuildContext context, int pos) {
                        OrderState orderState = state.clientState.orders[pos];

                        // order info text
                        String text = orderState.firstName +
                            " " +
                            orderState.lastName +
                            "\n" +
                            t.main.phone_number + ": " +
                            orderState.phoneNumber +
                            "\n" +
                            orderState.city +
                            " - " +
                            orderState.address1 +
                            " - " +
                            orderState.address2 +
                            "\n";
                        double amount = 0;
                        orderState.orderItems.forEach((orderItemState) {
                          amount += orderItemState.shoppingItem.price * orderItemState.number;
                        });

                        text += "${t.order.amount}: ${getNumber(amount.toString())} ${t.main.currency}";
                        return Container(
                            decoration: BoxDecoration(
                                color: WHITE_COLOR,
                                border: Border(
                                    bottom: BorderSide(
                                        width: 1, color: GREEN_COLOR))),
                            child: ExpansionCard(
                          backgroundColor: GREEN_COLOR.withAlpha(55),
                          trailing: Icon(
                            Icons.remove_shopping_cart,
                            color: GREEN_COLOR,
                            size: 40,
                          ),
                          title: Column(
                            crossAxisAlignment:
                            CrossAxisAlignment.start,
                            children: <Widget>[
                              Container(
                                alignment: adjustAlignment,
                                child: AdjustText(
                                    "${t.main.date}: ${getNumber(jalaliToString(georgianToJalali(orderState.dateOfOrder)))}",
                                    fontSize: 13,
                                    color: FONT_COLOR
                                ),
                              ),
                              Container(
                                padding: EdgeInsets.only(bottom: 20),
                                alignment: adjustAlignment,
                                child: AdjustText(
                                  "${t.order.number} ${t.order.orders}: ${getNumber(orderState.orderItems.length.toString())} ",
                                    fontSize: 13,
                                    color: FONT_COLOR
                                ),
                              )
                            ],
                          ),
                          children: <Widget>[
                            Container(
                              padding: EdgeInsets.all(10),
                              height: MediaQuery.of(context).size.height * (2/3),
                              width: MediaQuery.of(context).size.width,
                              child: Column(
                                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                children: <Widget>[
                                  Expanded(
                                      flex: 3,
                                      child: Container(
                                          width: MediaQuery.of(context).size.width,
                                          padding: EdgeInsets.all(10),
                                          child: Scrollbar(
                                            child: SingleChildScrollView(
                                              child: AdjustText(text,
                                                textDirection: txtDir,
                                              ),
                                            ),
                                          )
                                      )
                                  ),
                                  Divider(),
                                  Expanded(
                                      flex: 1,
                                      child: Container(
                                        padding: EdgeInsets.all(10),
                                        height: 40,
                                        child: Row(
                                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                          children: <Widget>[
                                            Expanded(
                                              flex: 25,
                                              child: FittedBox(
                                                fit: BoxFit.scaleDown,
                                                child: AdjustText(
                                                    t.order.price
                                                ),
                                              ),
                                            ),
                                            Expanded(
                                              flex: 25,
                                              child: FittedBox(
                                                fit: BoxFit.scaleDown,
                                                child: AdjustText(
                                                    t.order.number
                                                ),
                                              ),
                                            ),
                                            Expanded(
                                              flex: 50,
                                              child: FittedBox(
                                                fit: BoxFit.scaleDown,
                                                child: AdjustText(
                                                    t.order.item_name
                                                ),
                                              ),
                                            ),
                                          ],
                                        ),
                                      )
                                  ),
                                  Expanded(
                                      flex: 4,
                                      child: Scrollbar(
                                        child: ListView.builder(
                                          itemCount: orderState.orderItems.length,
                                          itemBuilder: (BuildContext context, int pos) {
                                            OrderItemState orderItemState = orderState.orderItems[pos];
                                            return Container(
                                              padding: EdgeInsets.all(10),
                                              width: MediaQuery.of(context).size.width,
                                              height: 40,
                                              child: Row(
                                                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                                children: <Widget>[
                                                  Expanded(
                                                    flex: 25,
                                                    child: FittedBox(
                                                      fit: BoxFit.scaleDown,
                                                      child: AdjustText(
                                                          getNumber((orderItemState.shoppingItem.price * orderItemState.number).toString())
                                                      ),
                                                    ),
                                                  ),
                                                  Expanded(
                                                    flex: 25,
                                                    child: FittedBox(
                                                      fit: BoxFit.scaleDown,
                                                      child: AdjustText(
                                                          getNumber(orderItemState.number.toString())
                                                      ),
                                                    ),
                                                  ),
                                                  Expanded(
                                                    flex: 50,
                                                    child: FittedBox(
                                                      fit: BoxFit.scaleDown,
                                                      child: AdjustText(
                                                          orderItemState.shoppingItem.name
                                                      ),
                                                    ),
                                                  ),
                                                ],
                                              ),
                                            );
                                          },
                                        ),
                                      )
                                  ),
                                ],
                              ),
                            )
                          ],
                        ))
                        ;
                      }),
                ),
              ],
            ),
          );
        },
      ),
    );
  }
}
