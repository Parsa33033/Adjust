
class Order {
  int id;

  String username;

  DateTime dateOfOrder;

  String firstName;

  String lastName;

  String phoneNumber;

  String email;

  String country;

  String state;

  String city;

  String address1;

  String address2;

  bool done;

  bool paid;

  bool received;

  double amount;

  int adjustClientId;

  Order(
      this.id,
      this.username,
      this.dateOfOrder,
      this.firstName,
      this.lastName,
      this.phoneNumber,
      this.email,
      this.country,
      this.state,
      this.city,
      this.address1,
      this.address2,
      this.done,
      this.paid,
      this.received,
      this.amount,
      this.adjustClientId);
}