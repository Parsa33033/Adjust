import { Moment } from 'moment';
import { IOrderItem } from 'app/shared/model/order-item.model';

export interface IOrder {
  id?: number;
  username?: string;
  dateOfOrder?: string;
  firstName?: string;
  lastName?: string;
  phoneNumber?: string;
  email?: string;
  country?: string;
  state?: string;
  city?: string;
  address1?: string;
  address2?: string;
  done?: boolean;
  paid?: boolean;
  received?: boolean;
  amount?: number;
  orderItems?: IOrderItem[];
  adjustClientId?: number;
}

export const defaultValue: Readonly<IOrder> = {
  done: false,
  paid: false,
  received: false,
};
