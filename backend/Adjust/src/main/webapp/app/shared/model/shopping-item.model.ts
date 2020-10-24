export interface IShoppingItem {
  id?: number;
  name?: string;
  itemId?: number;
  description?: any;
  price?: number;
  imageContentType?: string;
  image?: any;
  orderable?: boolean;
  number?: number;
}

export const defaultValue: Readonly<IShoppingItem> = {
  orderable: false,
};
