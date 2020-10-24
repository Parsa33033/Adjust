

class Shopping {
  List<ShoppingItem> items;

  Shopping(this.items);
}

class ShoppingItem {
  int id;

  String name;

  int itemId;

  String description;

  double price;

  String image;

  String imageContentType;
  bool orderable;

  ShoppingItem(this.id, this.name, this.itemId, this.description, this.price,
      this.image, this.imageContentType, this.orderable);
}