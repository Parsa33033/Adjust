import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAdjustToken, defaultValue } from 'app/shared/model/adjust-token.model';

export const ACTION_TYPES = {
  FETCH_ADJUSTTOKEN_LIST: 'adjustToken/FETCH_ADJUSTTOKEN_LIST',
  FETCH_ADJUSTTOKEN: 'adjustToken/FETCH_ADJUSTTOKEN',
  CREATE_ADJUSTTOKEN: 'adjustToken/CREATE_ADJUSTTOKEN',
  UPDATE_ADJUSTTOKEN: 'adjustToken/UPDATE_ADJUSTTOKEN',
  DELETE_ADJUSTTOKEN: 'adjustToken/DELETE_ADJUSTTOKEN',
  SET_BLOB: 'adjustToken/SET_BLOB',
  RESET: 'adjustToken/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAdjustToken>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AdjustTokenState = Readonly<typeof initialState>;

// Reducer

export default (state: AdjustTokenState = initialState, action): AdjustTokenState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTOKEN_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ADJUSTTOKEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ADJUSTTOKEN):
    case REQUEST(ACTION_TYPES.UPDATE_ADJUSTTOKEN):
    case REQUEST(ACTION_TYPES.DELETE_ADJUSTTOKEN):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTOKEN_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ADJUSTTOKEN):
    case FAILURE(ACTION_TYPES.CREATE_ADJUSTTOKEN):
    case FAILURE(ACTION_TYPES.UPDATE_ADJUSTTOKEN):
    case FAILURE(ACTION_TYPES.DELETE_ADJUSTTOKEN):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTOKEN_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ADJUSTTOKEN):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ADJUSTTOKEN):
    case SUCCESS(ACTION_TYPES.UPDATE_ADJUSTTOKEN):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ADJUSTTOKEN):
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

const apiUrl = 'api/adjust-tokens';

// Actions

export const getEntities: ICrudGetAllAction<IAdjustToken> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ADJUSTTOKEN_LIST,
  payload: axios.get<IAdjustToken>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAdjustToken> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ADJUSTTOKEN,
    payload: axios.get<IAdjustToken>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAdjustToken> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ADJUSTTOKEN,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAdjustToken> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ADJUSTTOKEN,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAdjustToken> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ADJUSTTOKEN,
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
