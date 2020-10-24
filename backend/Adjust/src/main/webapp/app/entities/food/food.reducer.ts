import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IFood, defaultValue } from 'app/shared/model/food.model';

export const ACTION_TYPES = {
  FETCH_FOOD_LIST: 'food/FETCH_FOOD_LIST',
  FETCH_FOOD: 'food/FETCH_FOOD',
  CREATE_FOOD: 'food/CREATE_FOOD',
  UPDATE_FOOD: 'food/UPDATE_FOOD',
  DELETE_FOOD: 'food/DELETE_FOOD',
  RESET: 'food/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IFood>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type FoodState = Readonly<typeof initialState>;

// Reducer

export default (state: FoodState = initialState, action): FoodState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_FOOD_LIST):
    case REQUEST(ACTION_TYPES.FETCH_FOOD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_FOOD):
    case REQUEST(ACTION_TYPES.UPDATE_FOOD):
    case REQUEST(ACTION_TYPES.DELETE_FOOD):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_FOOD_LIST):
    case FAILURE(ACTION_TYPES.FETCH_FOOD):
    case FAILURE(ACTION_TYPES.CREATE_FOOD):
    case FAILURE(ACTION_TYPES.UPDATE_FOOD):
    case FAILURE(ACTION_TYPES.DELETE_FOOD):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_FOOD_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_FOOD):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_FOOD):
    case SUCCESS(ACTION_TYPES.UPDATE_FOOD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_FOOD):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/foods';

// Actions

export const getEntities: ICrudGetAllAction<IFood> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_FOOD_LIST,
  payload: axios.get<IFood>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IFood> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_FOOD,
    payload: axios.get<IFood>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IFood> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_FOOD,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IFood> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_FOOD,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IFood> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_FOOD,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
