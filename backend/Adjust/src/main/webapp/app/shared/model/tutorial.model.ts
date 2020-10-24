import { IAdjustClient } from 'app/shared/model/adjust-client.model';

export interface ITutorial {
  id?: number;
  title?: string;
  description?: string;
  text?: any;
  thumbnailContentType?: string;
  thumbnail?: any;
  tokenPrice?: number;
  videoId?: number;
  courseId?: number;
  clients?: IAdjustClient[];
}

export const defaultValue: Readonly<ITutorial> = {};
