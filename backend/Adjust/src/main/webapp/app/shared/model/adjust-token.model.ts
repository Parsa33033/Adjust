export interface IAdjustToken {
  id?: number;
  name?: string;
  description?: any;
  token?: number;
  price?: number;
  imageContentType?: string;
  image?: any;
}

export const defaultValue: Readonly<IAdjustToken> = {};
