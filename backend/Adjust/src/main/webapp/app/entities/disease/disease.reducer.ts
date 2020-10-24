import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDisease, defaultValue } from 'app/shared/model/disease.model';

export const ACTION_TYPES = {
  FETCH_DISEASE_LIST: 'disease/FETCH_DISEASE_LIST',
  FETCH_DISEASE: 'disease/FETCH_DISEASE',
  CREATE_DISEASE: 'disease/CREATE_DISEASE',
  UPDATE_DISEASE: 'disease/UPDATE_DISEASE',
  DELETE_DISEASE: 'disease/DELETE_DISEASE',
  RESET: 'disease/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDisease>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type DiseaseState = Readonly<typeof initialState>;

// Reducer

export default (state: DiseaseState = initialState, action): DiseaseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DISEASE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DISEASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_DISEASE):
    case REQUEST(ACTION_TYPES.UPDATE_DISEASE):
    case REQUEST(ACTION_TYPES.DELETE_DISEASE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_DISEASE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DISEASE):
    case FAILURE(ACTION_TYPES.CREATE_DISEASE):
    case FAILURE(ACTION_TYPES.UPDATE_DISEASE):
    case FAILURE(ACTION_TYPES.DELETE_DISEASE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISEASE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_DISEASE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_DISEASE):
    case SUCCESS(ACTION_TYPES.UPDATE_DISEASE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_DISEASE):
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

const apiUrl = 'api/diseases';

// Actions

export const getEntities: ICrudGetAllAction<IDisease> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DISEASE_LIST,
  payload: axios.get<IDisease>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IDisease> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DISEASE,
    payload: axios.get<IDisease>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IDisease> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DISEASE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDisease> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DISEASE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDisease> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DISEASE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
