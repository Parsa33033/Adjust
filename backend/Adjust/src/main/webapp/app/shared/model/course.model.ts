import { ITutorial } from 'app/shared/model/tutorial.model';

export interface ICourse {
  id?: number;
  title?: string;
  description?: any;
  thumbnailContentType?: string;
  thumbnail?: any;
  tutorials?: ITutorial[];
}

export const defaultValue: Readonly<ICourse> = {};
