import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProgramDescription, defaultValue } from 'app/shared/model/program-description.model';

export const ACTION_TYPES = {
  FETCH_PROGRAMDESCRIPTION_LIST: 'programDescription/FETCH_PROGRAMDESCRIPTION_LIST',
  FETCH_PROGRAMDESCRIPTION: 'programDescription/FETCH_PROGRAMDESCRIPTION',
  CREATE_PROGRAMDESCRIPTION: 'programDescription/CREATE_PROGRAMDESCRIPTION',
  UPDATE_PROGRAMDESCRIPTION: 'programDescription/UPDATE_PROGRAMDESCRIPTION',
  DELETE_PROGRAMDESCRIPTION: 'programDescription/DELETE_PROGRAMDESCRIPTION',
  SET_BLOB: 'programDescription/SET_BLOB',
  RESET: 'programDescription/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProgramDescription>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ProgramDescriptionState = Readonly<typeof initialState>;

// Reducer

export default (state: ProgramDescriptionState = initialState, action): ProgramDescriptionState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROGRAMDESCRIPTION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROGRAMDESCRIPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROGRAMDESCRIPTION):
    case REQUEST(ACTION_TYPES.UPDATE_PROGRAMDESCRIPTION):
    case REQUEST(ACTION_TYPES.DELETE_PROGRAMDESCRIPTION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROGRAMDESCRIPTION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROGRAMDESCRIPTION):
    case FAILURE(ACTION_TYPES.CREATE_PROGRAMDESCRIPTION):
    case FAILURE(ACTION_TYPES.UPDATE_PROGRAMDESCRIPTION):
    case FAILURE(ACTION_TYPES.DELETE_PROGRAMDESCRIPTION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROGRAMDESCRIPTION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROGRAMDESCRIPTION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROGRAMDESCRIPTION):
    case SUCCESS(ACTION_TYPES.UPDATE_PROGRAMDESCRIPTION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROGRAMDESCRIPTION):
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

const apiUrl = 'api/program-descriptions';

// Actions

export const getEntities: ICrudGetAllAction<IProgramDescription> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PROGRAMDESCRIPTION_LIST,
  payload: axios.get<IProgramDescription>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IProgramDescription> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROGRAMDESCRIPTION,
    payload: axios.get<IProgramDescription>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProgramDescription> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROGRAMDESCRIPTION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProgramDescription> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROGRAMDESCRIPTION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProgramDescription> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROGRAMDESCRIPTION,
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
