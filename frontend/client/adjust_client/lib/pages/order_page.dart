import 'dart:convert';
import 'dart:ffi';

import 'package:adjust_client/actions/shopping_action.dart';
import 'package:adjust_client/components/adjust_dialog.dart';
import 'package:adjust_client/components/adjust_raised_button.dart';
import 'package:adjust_client/components/adjust_text.dart';
import 'package:adjust_client/components/adjust_text_field.dart';
import 'package:adjust_client/components/preloader.dart';
import 'package:adjust_client/config/i18n.dart';
import 'package:adjust_client/constants/adjust_colors.dart';
import 'package:adjust_client/constants/words.dart';
import 'package:adjust_client/dto/order_dto.dart';
import 'package:adjust_client/dto/order_item_dto.dart';
import 'package:adjust_client/dto/shopping_item_dto.dart';
import 'package:adjust_client/main.dart';
import 'package:adjust_client/model/shopping_item.dart';
import 'package:adjust_client/states/app_state.dart';
import 'package:adjust_client/states/order_item_state.dart';
import 'package:adjust_client/states/shopping_item_state.dart';
import 'package:adjust_client/states/shopping_state.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:persian_number_utility/persian_number_utility.dart';
import 'package:redux/redux.dart';
import 'package:string_validator/string_validator.dart';

class OrderPage extends StatefulWidget {
  @override
  _OrderPageState createState() => _OrderPageState();
}

class _OrderPageState extends State<OrderPage> {
  final _formKey = GlobalKey<FormState>();

  TextEditingController emailTextFieldController;
  TextEditingController firstNameTextFieldController;
  TextEditingController lastNameTextFieldController;
  TextEditingController phoneNumberTextFieldController;
  TextEditingController countryTextFieldController;
  TextEditingController stateTextFieldController;
  TextEditingController cityTextFieldController;
  TextEditingController address1TextFieldController;
  TextEditingController address2TextFieldController;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    emailTextFieldController = TextEditingController();
    firstNameTextFieldController = TextEditingController();
    lastNameTextFieldController = TextEditingController();
    phoneNumberTextFieldController = TextEditingController();
    countryTextFieldController = TextEditingController();
    stateTextFieldController = TextEditingController();
    cityTextFieldController = TextEditingController();
    address1TextFieldController = TextEditingController();
    address2TextFieldController = TextEditingController();

    AppState state = store.state;
    setAppState(state);

  }

  void setAppState(AppState state) {
    setState(() {
      emailTextFieldController.text = state.userState.email;
      firstNameTextFieldController.text = state.clientState.firstName;
      lastNameTextFieldController.text = state.clientState.lastName;
      countryTextFieldController.text = "ایران";
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: StoreConnector<AppState, AppState>(
          converter: (Store store) => store.state,
          builder: (BuildContext context, AppState state) {
            return Container(
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Expanded(
                    flex: 30,
                    child: Container(
                      child: Stack(
                        children: <Widget>[
                          Positioned(
                            top: 0,
                            right: 0,
                            bottom: 100,
                            left: 0,
                            child: Container(
                              color: GREEN_COLOR,
                            ),
                          ),
                          Positioned(
                            bottom: 0,
                            left: 0,
                            right: 0,
                            height: 180,
                            child: Container(
                              child: Image.asset("assets/adjust_logo.png"),
                            ),
                          ),
                          Positioned(
                              left: 20,
                              top: 50,
                              child: Container(
                                height: 50,
                                width: 50,
                                child: InkWell(
                                  child: Icon(
                                    Icons.arrow_back,
                                    color: WHITE_COLOR,
                                    size: 50,
                                  ),
                                  onTap: () {
                                    Navigator.of(context).pop();
                                  },
                                ),
                              )),
                        ],
                      ),
                    ),
                  ),
                  Expanded(
                    flex: 70,
                    child: Container(
                      child: SingleChildScrollView(
                          child: Form(
                            key: _formKey,
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                              children: <Widget>[
                                AdjustTextField(
                                    textDirection: TextDirection.ltr,
                                    controller: emailTextFieldController,
                                    hintText: t.user.email,
                                    enabled: false,
                                    icon: Icon(
                                      Icons.email,
                                      color: GREEN_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: GREEN_COLOR,
                                    validator: (String value) {
                                      if (!isEmail(value)) {
                                        return t.user.wrong_email;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    controller: firstNameTextFieldController,
                                    hintText: t.user.firstname,
                                    enabled: false,
                                    icon: Icon(
                                      Icons.person,
                                      color: RED_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: RED_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    controller: lastNameTextFieldController,
                                    hintText: t.user.lastname,
                                    enabled: false,
                                    icon: Icon(
                                      Icons.person,
                                      color: ORANGE_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: ORANGE_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    isNumber: true,
                                    controller: phoneNumberTextFieldController,
                                    hintText: t.order.phone_number,
                                    enabled: true,
                                    icon: Icon(
                                      Icons.phone,
                                      color: YELLOW_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: YELLOW_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                  textDirection: txtDir,
                                  textAlign: txtAlign,
                                  controller: countryTextFieldController,
                                  hintText: t.order.country,
                                  enabled: false,
                                  icon: Icon(
                                    Icons.location_on,
                                    color: SHADOW_COLOR,
                                  ),
                                  isPassword: false,
                                  primaryColor: SHADOW_COLOR,
                                  validator: (String value) {
                                    if (value == null || value == "") {
                                      return t.user.fill;
                                    }
                                    return null;
                                  },
                                  padding: 0,
                                  margin: 20,
                                ),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    controller: stateTextFieldController,
                                    hintText: t.order.state,
                                    enabled: true,
                                    icon: Icon(
                                      Icons.location_on,
                                      color: SHADOW_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: SHADOW_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    controller: cityTextFieldController,
                                    hintText: t.order.city,
                                    enabled: true,
                                    icon: Icon(
                                      Icons.location_on,
                                      color: SHADOW_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: SHADOW_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    controller: address1TextFieldController,
                                    hintText: t.order.address +
                                        getNumber(" 1".toString()),
                                    enabled: true,
                                    icon: Icon(
                                      Icons.location_on,
                                      color: SHADOW_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: SHADOW_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                AdjustTextField(
                                    textDirection: txtDir,
                                    textAlign: txtAlign,
                                    controller: address2TextFieldController,
                                    hintText: t.order.address +
                                        getNumber(" 2".toString()),
                                    enabled: true,
                                    icon: Icon(
                                      Icons.location_on,
                                      color: SHADOW_COLOR,
                                    ),
                                    isPassword: false,
                                    primaryColor: SHADOW_COLOR,
                                    validator: (String value) {
                                      if (value == null || value == "") {
                                        return t.user.fill;
                                      }
                                      return null;
                                    },
                                    padding: 0,
                                    margin: 20),
                                Container(
                                  padding: EdgeInsets.all(20),
                                  child: AdjustRaisedButton(
                                      text: t.order.order,
                                      textDirection: txtDir,
                                      primaryColor: GREEN_COLOR,
                                      secondaryColor: GREEN_COLOR,
                                      height: 50,
                                      width: MediaQuery
                                          .of(context)
                                          .size
                                          .width,
                                      onPressed: () async {
                                        if (_formKey.currentState.validate()) {
                                          List<OrderItemDTO> orderItems = state
                                              .cartState.orderItemListState
                                              .map((orderItemState) {
                                            ShoppingItemState shoppingItemState = orderItemState
                                                .shoppingItem;
                                            ShoppingItemDTO shoppingItemDTO = ShoppingItemDTO(
                                                shoppingItemState.id,
                                                shoppingItemState.name,
                                                shoppingItemState.itemId,
                                                shoppingItemState.description,
                                                shoppingItemState.price,
                                                shoppingItemState.image,
                                                shoppingItemState
                                                    .imageContentType,
                                                shoppingItemState.orderable,
                                                shoppingItemState.number);
                                            // change number field for this later
                                            OrderItemDTO orderItemListDTO = OrderItemDTO(
                                                null, orderItemState.number, null, shoppingItemDTO);
                                            return orderItemListDTO;
                                          }).toList();

                                          double amount = 0;
                                          state.cartState.orderItemListState.forEach((orderItemState) {
                                            amount += orderItemState.shoppingItem.price * orderItemState.number;
                                          });

                                          OrderDTO orderDTO = OrderDTO(
                                              null,
                                              emailTextFieldController.text,
                                              DateTime.now(),
                                              firstNameTextFieldController.text,
                                              lastNameTextFieldController.text,
                                              phoneNumberTextFieldController
                                                  .text,
                                              emailTextFieldController.text,
                                              countryTextFieldController.text,
                                              stateTextFieldController.text,
                                              cityTextFieldController.text,
                                              address1TextFieldController.text,
                                              address2TextFieldController.text,
                                              false,
                                              false,
                                              false,
                                              amount,
                                              state.clientState.id,
                                              orderItems);

                                          String text = orderDTO.firstName +
                                              " " +
                                              orderDTO.lastName +
                                              "\n" +
                                              t.main.phone_number + ": " +
                                              orderDTO.phoneNumber +
                                              "\n" +
                                              orderDTO.city +
                                              " - " +
                                              orderDTO.address1 +
                                              " - " +
                                              orderDTO.address2 +
                                              "\n";
                                          text += "${t.order.amount}: ${getNumber(amount.toString())} ${t.main.currency}";


                                          showDialog(
                                              context: context,
                                              child: Dialog(
                                                child: Container(
                                                  padding: EdgeInsets.all(10),
                                                  height: MediaQuery.of(context).size.height * (2/3),
                                                  width: MediaQuery.of(context).size.width,
                                                  child: Column(
                                                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                                    children: <Widget>[
                                                      Expanded(
                                                        flex: 4,
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
                                                        flex: 3,
                                                        child: Scrollbar(
                                                          child: ListView.builder(
                                                            itemCount: orderItems.length,
                                                            itemBuilder: (BuildContext context, int pos) {
                                                              OrderItemDTO orderItemDTO = orderItems[pos];
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
                                                                            getNumber((orderItemDTO.shoppingItem.price * orderItemDTO.number).toString())
                                                                        ),
                                                                      ),
                                                                    ),
                                                                    Expanded(
                                                                      flex: 25,
                                                                      child: FittedBox(
                                                                        fit: BoxFit.scaleDown,
                                                                        child: AdjustText(
                                                                            getNumber(orderItemDTO.number.toString())
                                                                        ),
                                                                      ),
                                                                    ),
                                                                    Expanded(
                                                                      flex: 50,
                                                                      child: FittedBox(
                                                                        fit: BoxFit.scaleDown,
                                                                        child: AdjustText(
                                                                            orderItemDTO.shoppingItem.name
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
                                                      Divider(),
                                                      Expanded(
                                                        flex: 2,
                                                        child: Container(
                                                          height: 100,
                                                          child: Row(
                                                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                                                            children: <Widget>[
                                                              Container(
                                                                  child: AdjustRaisedButton(
                                                                    textDirection: txtDir,
                                                                    height: 40,
                                                                    width: 80,
                                                                    primaryColor: GREEN_COLOR,
                                                                    secondaryColor: GREEN_COLOR,
                                                                    text: t.main.cancel,
                                                                    onPressed: () {
                                                                      Navigator.of(context, rootNavigator: true).pop("dialog");
                                                                    },
                                                                  )
                                                              ),
                                                              Container(
                                                                  child: AdjustRaisedButton(
                                                                    textDirection: txtDir,
                                                                    height: 40,
                                                                    width: 80,
                                                                    primaryColor: GREEN_COLOR,
                                                                    secondaryColor: GREEN_COLOR,
                                                                    text: t.main.ok,
                                                                    onPressed: () async {
                                                                      Navigator.of(context, rootNavigator: true).pop("dialog");
                                                                      int i = await order(
                                                                          context, orderDTO);
                                                                      if (i == 1) {
                                                                        // set shopping list state for deducted number of products
                                                                        List<ShoppingItemState> shoppingListState = state.shoppingState.items.map((shoppingItem) {
                                                                          for (OrderItemState temp in state.cartState.orderItemListState) {
                                                                            if (temp.shoppingItem.id == shoppingItem.id) {
                                                                              shoppingItem.number -= temp.number;
                                                                              return shoppingItem;
                                                                            }
                                                                          }
                                                                          return shoppingItem;
                                                                        }).toList();
                                                                        ShoppingState shoppingState = ShoppingState(shoppingListState);
                                                                        StoreProvider.of<AppState>(
                                                                            context).dispatch(GetShopingItemsAction(payload: shoppingState));

                                                                        // empty cart state
                                                                        state.cartState.orderItemListState =
                                                                            List<OrderItemState>();
                                                                        StoreProvider.of<AppState>(
                                                                            context).dispatch(
                                                                            RemoveFromCartAction(
                                                                                payload: state
                                                                                    .cartState));
                                                                        Navigator.of(context).pop();
                                                                        Navigator.of(context).pop();
                                                                      } else if (i == 0) {
                                                                        showAdjustDialog(
                                                                            context, t.user.failure,
                                                                            false, null, null);
                                                                      }
                                                                    },
                                                                  )
                                                              )
                                                            ],
                                                          ),
                                                        )
                                                      )
                                                    ],
                                                  ),
                                                )
                                              )
                                          );
                                        }
                                      }),
                                )
                              ],
                            ),
                          )),
                    ),
                  )
                ],
              ),
            );
          },
        ));
  }

  @override
  void dispose() {
    // TODO: implement dispose
    emailTextFieldController.dispose();
    firstNameTextFieldController.dispose();
    lastNameTextFieldController.dispose();
    super.dispose();
  }
}
