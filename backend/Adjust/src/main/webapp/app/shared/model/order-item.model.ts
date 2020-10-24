export interface IOrderItem {
  id?: number;
  number?: number;
  shoppingItemId?: number;
  orderId?: number;
}

export const defaultValue: Readonly<IOrderItem> = {};
