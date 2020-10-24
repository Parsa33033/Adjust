import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { getEntities as getBodyCompositions } from 'app/entities/body-composition/body-composition.reducer';
import { IDisease } from 'app/shared/model/disease.model';
import { getEntities as getDiseases } from 'app/entities/disease/disease.reducer';
import { IAdjustClient } from 'app/shared/model/adjust-client.model';
import { getEntities as getAdjustClients } from 'app/entities/adjust-client/adjust-client.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './medical-profile.reducer';
import { IMedicalProfile } from 'app/shared/model/medical-profile.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMedicalProfileUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MedicalProfileUpdate = (props: IMedicalProfileUpdateProps) => {
  const [idsdiseases, setIdsdiseases] = useState([]);
  const [bodyCompositionId, setBodyCompositionId] = useState('0');
  const [adjustClientId, setAdjustClientId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { medicalProfileEntity, bodyCompositions, diseases, adjustClients, loading, updating } = props;

  const { medicines } = medicalProfileEntity;

  const handleClose = () => {
    props.history.push('/medical-profile');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getBodyCompositions();
    props.getDiseases();
    props.getAdjustClients();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...medicalProfileEntity,
        ...values,
        diseases: mapIdList(values.diseases),
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.medicalProfile.home.createOrEditLabel">
            <Translate contentKey="adjustApp.medicalProfile.home.createOrEditLabel">Create or edit a MedicalProfile</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : medicalProfileEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="medical-profile-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="medical-profile-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup check>
                <Label id="haveLostWeightLabel">
                  <AvInput id="medical-profile-haveLostWeight" type="checkbox" className="form-check-input" name="haveLostWeight" />
                  <Translate contentKey="adjustApp.medicalProfile.haveLostWeight">Have Lost Weight</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="drowsinessLabel">
                  <AvInput id="medical-profile-drowsiness" type="checkbox" className="form-check-input" name="drowsiness" />
                  <Translate contentKey="adjustApp.medicalProfile.drowsiness">Drowsiness</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="goodNightSleepLabel">
                  <AvInput id="medical-profile-goodNightSleep" type="checkbox" className="form-check-input" name="goodNightSleep" />
                  <Translate contentKey="adjustApp.medicalProfile.goodNightSleep">Good Night Sleep</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="hoursOfTraningPerWeekLabel" for="medical-profile-hoursOfTraningPerWeek">
                  <Translate contentKey="adjustApp.medicalProfile.hoursOfTraningPerWeek">Hours Of Traning Per Week</Translate>
                </Label>
                <AvField id="medical-profile-hoursOfTraningPerWeek" type="string" className="form-control" name="hoursOfTraningPerWeek" />
              </AvGroup>
              <AvGroup check>
                <Label id="loveForSugarLabel">
                  <AvInput id="medical-profile-loveForSugar" type="checkbox" className="form-check-input" name="loveForSugar" />
                  <Translate contentKey="adjustApp.medicalProfile.loveForSugar">Love For Sugar</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="overEatingLabel">
                  <AvInput id="medical-profile-overEating" type="checkbox" className="form-check-input" name="overEating" />
                  <Translate contentKey="adjustApp.medicalProfile.overEating">Over Eating</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="maritalStatusLabel" for="medical-profile-maritalStatus">
                  <Translate contentKey="adjustApp.medicalProfile.maritalStatus">Marital Status</Translate>
                </Label>
                <AvInput
                  id="medical-profile-maritalStatus"
                  type="select"
                  className="form-control"
                  name="maritalStatus"
                  value={(!isNew && medicalProfileEntity.maritalStatus) || 'MARRIED'}
                >
                  <option value="MARRIED">{translate('adjustApp.MaritalStatus.MARRIED')}</option>
                  <option value="SINGLE">{translate('adjustApp.MaritalStatus.SINGLE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="childrenNumberLabel" for="medical-profile-childrenNumber">
                  <Translate contentKey="adjustApp.medicalProfile.childrenNumber">Children Number</Translate>
                </Label>
                <AvField id="medical-profile-childrenNumber" type="string" className="form-control" name="childrenNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="educationLabel" for="medical-profile-education">
                  <Translate contentKey="adjustApp.medicalProfile.education">Education</Translate>
                </Label>
                <AvInput
                  id="medical-profile-education"
                  type="select"
                  className="form-control"
                  name="education"
                  value={(!isNew && medicalProfileEntity.education) || 'NONE'}
                >
                  <option value="NONE">{translate('adjustApp.Education.NONE')}</option>
                  <option value="LOW">{translate('adjustApp.Education.LOW')}</option>
                  <option value="DIPLOMA">{translate('adjustApp.Education.DIPLOMA')}</option>
                  <option value="BACHELOR">{translate('adjustApp.Education.BACHELOR')}</option>
                  <option value="MASTERS">{translate('adjustApp.Education.MASTERS')}</option>
                  <option value="DOCTORATE">{translate('adjustApp.Education.DOCTORATE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="occupationLabel" for="medical-profile-occupation">
                  <Translate contentKey="adjustApp.medicalProfile.occupation">Occupation</Translate>
                </Label>
                <AvInput
                  id="medical-profile-occupation"
                  type="select"
                  className="form-control"
                  name="occupation"
                  value={(!isNew && medicalProfileEntity.occupation) || 'NONE'}
                >
                  <option value="NONE">{translate('adjustApp.Occupation.NONE')}</option>
                  <option value="STUDENT">{translate('adjustApp.Occupation.STUDENT')}</option>
                  <option value="FREELANCER">{translate('adjustApp.Occupation.FREELANCER')}</option>
                  <option value="LABOR">{translate('adjustApp.Occupation.LABOR')}</option>
                  <option value="EMPLOYEE">{translate('adjustApp.Occupation.EMPLOYEE')}</option>
                  <option value="MEDICAL">{translate('adjustApp.Occupation.MEDICAL')}</option>
                  <option value="ENGINEERING">{translate('adjustApp.Occupation.ENGINEERING')}</option>
                  <option value="MANAGER">{translate('adjustApp.Occupation.MANAGER')}</option>
                  <option value="HOUSEWIFE">{translate('adjustApp.Occupation.HOUSEWIFE')}</option>
                  <option value="OTHERS">{translate('adjustApp.Occupation.OTHERS')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="hoursOfWorkDailyLabel" for="medical-profile-hoursOfWorkDaily">
                  <Translate contentKey="adjustApp.medicalProfile.hoursOfWorkDaily">Hours Of Work Daily</Translate>
                </Label>
                <AvField id="medical-profile-hoursOfWorkDaily" type="string" className="form-control" name="hoursOfWorkDaily" />
              </AvGroup>
              <AvGroup>
                <Label id="financialStatusLabel" for="medical-profile-financialStatus">
                  <Translate contentKey="adjustApp.medicalProfile.financialStatus">Financial Status</Translate>
                </Label>
                <AvInput
                  id="medical-profile-financialStatus"
                  type="select"
                  className="form-control"
                  name="financialStatus"
                  value={(!isNew && medicalProfileEntity.financialStatus) || 'BAD'}
                >
                  <option value="BAD">{translate('adjustApp.FinancialStatus.BAD')}</option>
                  <option value="NORMAL">{translate('adjustApp.FinancialStatus.NORMAL')}</option>
                  <option value="GOOD">{translate('adjustApp.FinancialStatus.GOOD')}</option>
                  <option value="PERFECT">{translate('adjustApp.FinancialStatus.PERFECT')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="cityLabel" for="medical-profile-city">
                  <Translate contentKey="adjustApp.medicalProfile.city">City</Translate>
                </Label>
                <AvField id="medical-profile-city" type="text" name="city" />
              </AvGroup>
              <AvGroup check>
                <Label id="useOfMedicineLabel">
                  <AvInput id="medical-profile-useOfMedicine" type="checkbox" className="form-check-input" name="useOfMedicine" />
                  <Translate contentKey="adjustApp.medicalProfile.useOfMedicine">Use Of Medicine</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="medicinesLabel" for="medical-profile-medicines">
                  <Translate contentKey="adjustApp.medicalProfile.medicines">Medicines</Translate>
                </Label>
                <AvInput id="medical-profile-medicines" type="textarea" name="medicines" />
              </AvGroup>
              <AvGroup check>
                <Label id="haveAllergyLabel">
                  <AvInput id="medical-profile-haveAllergy" type="checkbox" className="form-check-input" name="haveAllergy" />
                  <Translate contentKey="adjustApp.medicalProfile.haveAllergy">Have Allergy</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="allergyLabel" for="medical-profile-allergy">
                  <Translate contentKey="adjustApp.medicalProfile.allergy">Allergy</Translate>
                </Label>
                <AvField id="medical-profile-allergy" type="text" name="allergy" />
              </AvGroup>
              <AvGroup check>
                <Label id="haveDiseaseLabel">
                  <AvInput id="medical-profile-haveDisease" type="checkbox" className="form-check-input" name="haveDisease" />
                  <Translate contentKey="adjustApp.medicalProfile.haveDisease">Have Disease</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="bloodTypeLabel" for="medical-profile-bloodType">
                  <Translate contentKey="adjustApp.medicalProfile.bloodType">Blood Type</Translate>
                </Label>
                <AvInput
                  id="medical-profile-bloodType"
                  type="select"
                  className="form-control"
                  name="bloodType"
                  value={(!isNew && medicalProfileEntity.bloodType) || 'A'}
                >
                  <option value="A">{translate('adjustApp.BloodType.A')}</option>
                  <option value="B">{translate('adjustApp.BloodType.B')}</option>
                  <option value="AB">{translate('adjustApp.BloodType.AB')}</option>
                  <option value="O">{translate('adjustApp.BloodType.O')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="medical-profile-bodyComposition">
                  <Translate contentKey="adjustApp.medicalProfile.bodyComposition">Body Composition</Translate>
                </Label>
                <AvInput id="medical-profile-bodyComposition" type="select" className="form-control" name="bodyCompositionId">
                  <option value="" key="0" />
                  {bodyCompositions
                    ? bodyCompositions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="medical-profile-diseases">
                  <Translate contentKey="adjustApp.medicalProfile.diseases">Diseases</Translate>
                </Label>
                <AvInput
                  id="medical-profile-diseases"
                  type="select"
                  multiple
                  className="form-control"
                  name="diseases"
                  value={medicalProfileEntity.diseases && medicalProfileEntity.diseases.map(e => e.id)}
                >
                  <option value="" key="0" />
                  {diseases
                    ? diseases.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/medical-profile" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  bodyCompositions: storeState.bodyComposition.entities,
  diseases: storeState.disease.entities,
  adjustClients: storeState.adjustClient.entities,
  medicalProfileEntity: storeState.medicalProfile.entity,
  loading: storeState.medicalProfile.loading,
  updating: storeState.medicalProfile.updating,
  updateSuccess: storeState.medicalProfile.updateSuccess,
});

const mapDispatchToProps = {
  getBodyCompositions,
  getDiseases,
  getAdjustClients,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MedicalProfileUpdate);
