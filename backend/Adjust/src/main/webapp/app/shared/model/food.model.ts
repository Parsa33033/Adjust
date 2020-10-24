export interface IFood {
  id?: number;
  name?: string;
  description?: string;
  nutritionId?: number;
}

export const defaultValue: Readonly<IFood> = {};
