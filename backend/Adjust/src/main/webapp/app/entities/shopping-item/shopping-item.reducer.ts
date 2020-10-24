import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IShoppingItem, defaultValue } from 'app/shared/model/shopping-item.model';

export const ACTION_TYPES = {
  FETCH_SHOPPINGITEM_LIST: 'shoppingItem/FETCH_SHOPPINGITEM_LIST',
  FETCH_SHOPPINGITEM: 'shoppingItem/FETCH_SHOPPINGITEM',
  CREATE_SHOPPINGITEM: 'shoppingItem/CREATE_SHOPPINGITEM',
  UPDATE_SHOPPINGITEM: 'shoppingItem/UPDATE_SHOPPINGITEM',
  DELETE_SHOPPINGITEM: 'shoppingItem/DELETE_SHOPPINGITEM',
  SET_BLOB: 'shoppingItem/SET_BLOB',
  RESET: 'shoppingItem/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IShoppingItem>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ShoppingItemState = Readonly<typeof initialState>;

// Reducer

export default (state: ShoppingItemState = initialState, action): ShoppingItemState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SHOPPINGITEM_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SHOPPINGITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SHOPPINGITEM):
    case REQUEST(ACTION_TYPES.UPDATE_SHOPPINGITEM):
    case REQUEST(ACTION_TYPES.DELETE_SHOPPINGITEM):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SHOPPINGITEM_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SHOPPINGITEM):
    case FAILURE(ACTION_TYPES.CREATE_SHOPPINGITEM):
    case FAILURE(ACTION_TYPES.UPDATE_SHOPPINGITEM):
    case FAILURE(ACTION_TYPES.DELETE_SHOPPINGITEM):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOPPINGITEM_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOPPINGITEM):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SHOPPINGITEM):
    case SUCCESS(ACTION_TYPES.UPDATE_SHOPPINGITEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SHOPPINGITEM):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/shopping-items';

// Actions

export const getEntities: ICrudGetAllAction<IShoppingItem> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SHOPPINGITEM_LIST,
  payload: axios.get<IShoppingItem>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IShoppingItem> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SHOPPINGITEM,
    payload: axios.get<IShoppingItem>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IShoppingItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SHOPPINGITEM,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IShoppingItem> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SHOPPINGITEM,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IShoppingItem> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SHOPPINGITEM,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
