import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMedicalProfile, defaultValue } from 'app/shared/model/medical-profile.model';

export const ACTION_TYPES = {
  FETCH_MEDICALPROFILE_LIST: 'medicalProfile/FETCH_MEDICALPROFILE_LIST',
  FETCH_MEDICALPROFILE: 'medicalProfile/FETCH_MEDICALPROFILE',
  CREATE_MEDICALPROFILE: 'medicalProfile/CREATE_MEDICALPROFILE',
  UPDATE_MEDICALPROFILE: 'medicalProfile/UPDATE_MEDICALPROFILE',
  DELETE_MEDICALPROFILE: 'medicalProfile/DELETE_MEDICALPROFILE',
  SET_BLOB: 'medicalProfile/SET_BLOB',
  RESET: 'medicalProfile/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMedicalProfile>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MedicalProfileState = Readonly<typeof initialState>;

// Reducer

export default (state: MedicalProfileState = initialState, action): MedicalProfileState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MEDICALPROFILE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MEDICALPROFILE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MEDICALPROFILE):
    case REQUEST(ACTION_TYPES.UPDATE_MEDICALPROFILE):
    case REQUEST(ACTION_TYPES.DELETE_MEDICALPROFILE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MEDICALPROFILE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MEDICALPROFILE):
    case FAILURE(ACTION_TYPES.CREATE_MEDICALPROFILE):
    case FAILURE(ACTION_TYPES.UPDATE_MEDICALPROFILE):
    case FAILURE(ACTION_TYPES.DELETE_MEDICALPROFILE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEDICALPROFILE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEDICALPROFILE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MEDICALPROFILE):
    case SUCCESS(ACTION_TYPES.UPDATE_MEDICALPROFILE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MEDICALPROFILE):
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

const apiUrl = 'api/medical-profiles';

// Actions

export const getEntities: ICrudGetAllAction<IMedicalProfile> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MEDICALPROFILE_LIST,
  payload: axios.get<IMedicalProfile>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMedicalProfile> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MEDICALPROFILE,
    payload: axios.get<IMedicalProfile>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMedicalProfile> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MEDICALPROFILE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMedicalProfile> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MEDICALPROFILE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMedicalProfile> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MEDICALPROFILE,
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
