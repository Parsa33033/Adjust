import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDevelopment, defaultValue } from 'app/shared/model/development.model';

export const ACTION_TYPES = {
  FETCH_DEVELOPMENT_LIST: 'development/FETCH_DEVELOPMENT_LIST',
  FETCH_DEVELOPMENT: 'development/FETCH_DEVELOPMENT',
  CREATE_DEVELOPMENT: 'development/CREATE_DEVELOPMENT',
  UPDATE_DEVELOPMENT: 'development/UPDATE_DEVELOPMENT',
  DELETE_DEVELOPMENT: 'development/DELETE_DEVELOPMENT',
  RESET: 'development/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDevelopment>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type DevelopmentState = Readonly<typeof initialState>;

// Reducer

export default (state: DevelopmentState = initialState, action): DevelopmentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DEVELOPMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DEVELOPMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DEVELOPMENT):
    case REQUEST(ACTION_TYPES.UPDATE_DEVELOPMENT):
    case REQUEST(ACTION_TYPES.DELETE_DEVELOPMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DEVELOPMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DEVELOPMENT):
    case FAILURE(ACTION_TYPES.CREATE_DEVELOPMENT):
    case FAILURE(ACTION_TYPES.UPDATE_DEVELOPMENT):
    case FAILURE(ACTION_TYPES.DELETE_DEVELOPMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEVELOPMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DEVELOPMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DEVELOPMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_DEVELOPMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DEVELOPMENT):
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

const apiUrl = 'api/developments';

// Actions

export const getEntities: ICrudGetAllAction<IDevelopment> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DEVELOPMENT_LIST,
  payload: axios.get<IDevelopment>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IDevelopment> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DEVELOPMENT,
    payload: axios.get<IDevelopment>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDevelopment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DEVELOPMENT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDevelopment> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DEVELOPMENT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDevelopment> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DEVELOPMENT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
