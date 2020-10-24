import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IMedicalProfile } from 'app/shared/model/medical-profile.model';
import { getEntities as getMedicalProfiles } from 'app/entities/medical-profile/medical-profile.reducer';
import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { getEntities as getAdjustPrograms } from 'app/entities/adjust-program/adjust-program.reducer';
import { getEntity, updateEntity, createEntity, reset } from './body-composition.reducer';
import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBodyCompositionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BodyCompositionUpdate = (props: IBodyCompositionUpdateProps) => {
  const [medicalProfileId, setMedicalProfileId] = useState('0');
  const [adjustProgramId, setAdjustProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { bodyCompositionEntity, medicalProfiles, adjustPrograms, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/body-composition');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMedicalProfiles();
    props.getAdjustPrograms();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...bodyCompositionEntity,
        ...values,
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
          <h2 id="adjustApp.bodyComposition.home.createOrEditLabel">
            <Translate contentKey="adjustApp.bodyComposition.home.createOrEditLabel">Create or edit a BodyComposition</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : bodyCompositionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="body-composition-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="body-composition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="createdAtLabel" for="body-composition-createdAt">
                  <Translate contentKey="adjustApp.bodyComposition.createdAt">Created At</Translate>
                </Label>
                <AvField id="body-composition-createdAt" type="date" className="form-control" name="createdAt" />
              </AvGroup>
              <AvGroup>
                <Label id="ageLabel" for="body-composition-age">
                  <Translate contentKey="adjustApp.bodyComposition.age">Age</Translate>
                </Label>
                <AvField id="body-composition-age" type="string" className="form-control" name="age" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="body-composition-gender">
                  <Translate contentKey="adjustApp.bodyComposition.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="body-composition-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && bodyCompositionEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('adjustApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('adjustApp.Gender.FEMALE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="heightLabel" for="body-composition-height">
                  <Translate contentKey="adjustApp.bodyComposition.height">Height</Translate>
                </Label>
                <AvField id="body-composition-height" type="string" className="form-control" name="height" />
              </AvGroup>
              <AvGroup>
                <Label id="weightLabel" for="body-composition-weight">
                  <Translate contentKey="adjustApp.bodyComposition.weight">Weight</Translate>
                </Label>
                <AvField id="body-composition-weight" type="string" className="form-control" name="weight" />
              </AvGroup>
              <AvGroup>
                <Label id="wristLabel" for="body-composition-wrist">
                  <Translate contentKey="adjustApp.bodyComposition.wrist">Wrist</Translate>
                </Label>
                <AvField id="body-composition-wrist" type="string" className="form-control" name="wrist" />
              </AvGroup>
              <AvGroup>
                <Label id="waistLabel" for="body-composition-waist">
                  <Translate contentKey="adjustApp.bodyComposition.waist">Waist</Translate>
                </Label>
                <AvField id="body-composition-waist" type="string" className="form-control" name="waist" />
              </AvGroup>
              <AvGroup>
                <Label id="hipLabel" for="body-composition-hip">
                  <Translate contentKey="adjustApp.bodyComposition.hip">Hip</Translate>
                </Label>
                <AvField id="body-composition-hip" type="string" className="form-control" name="hip" />
              </AvGroup>
              <AvGroup>
                <Label id="abdomenLabel" for="body-composition-abdomen">
                  <Translate contentKey="adjustApp.bodyComposition.abdomen">Abdomen</Translate>
                </Label>
                <AvField id="body-composition-abdomen" type="string" className="form-control" name="abdomen" />
              </AvGroup>
              <AvGroup>
                <Label id="neckLabel" for="body-composition-neck">
                  <Translate contentKey="adjustApp.bodyComposition.neck">Neck</Translate>
                </Label>
                <AvField id="body-composition-neck" type="string" className="form-control" name="neck" />
              </AvGroup>
              <AvGroup>
                <Label id="waistHipRatioLabel" for="body-composition-waistHipRatio">
                  <Translate contentKey="adjustApp.bodyComposition.waistHipRatio">Waist Hip Ratio</Translate>
                </Label>
                <AvField id="body-composition-waistHipRatio" type="string" className="form-control" name="waistHipRatio" />
              </AvGroup>
              <AvGroup>
                <Label id="idealWeightMinLabel" for="body-composition-idealWeightMin">
                  <Translate contentKey="adjustApp.bodyComposition.idealWeightMin">Ideal Weight Min</Translate>
                </Label>
                <AvField id="body-composition-idealWeightMin" type="string" className="form-control" name="idealWeightMin" />
              </AvGroup>
              <AvGroup>
                <Label id="idealWeightMaxLabel" for="body-composition-idealWeightMax">
                  <Translate contentKey="adjustApp.bodyComposition.idealWeightMax">Ideal Weight Max</Translate>
                </Label>
                <AvField id="body-composition-idealWeightMax" type="string" className="form-control" name="idealWeightMax" />
              </AvGroup>
              <AvGroup>
                <Label id="bestWeightLabel" for="body-composition-bestWeight">
                  <Translate contentKey="adjustApp.bodyComposition.bestWeight">Best Weight</Translate>
                </Label>
                <AvField id="body-composition-bestWeight" type="string" className="form-control" name="bestWeight" />
              </AvGroup>
              <AvGroup>
                <Label id="bmiLabel" for="body-composition-bmi">
                  <Translate contentKey="adjustApp.bodyComposition.bmi">Bmi</Translate>
                </Label>
                <AvField id="body-composition-bmi" type="string" className="form-control" name="bmi" />
              </AvGroup>
              <AvGroup>
                <Label id="bmiCategoryLabel" for="body-composition-bmiCategory">
                  <Translate contentKey="adjustApp.bodyComposition.bmiCategory">Bmi Category</Translate>
                </Label>
                <AvInput
                  id="body-composition-bmiCategory"
                  type="select"
                  className="form-control"
                  name="bmiCategory"
                  value={(!isNew && bodyCompositionEntity.bmiCategory) || 'UNDERWEIGHT'}
                >
                  <option value="UNDERWEIGHT">{translate('adjustApp.BMICategory.UNDERWEIGHT')}</option>
                  <option value="NORMAL">{translate('adjustApp.BMICategory.NORMAL')}</option>
                  <option value="OVERWEIGHT">{translate('adjustApp.BMICategory.OVERWEIGHT')}</option>
                  <option value="OBESE">{translate('adjustApp.BMICategory.OBESE')}</option>
                  <option value="HIGHOBESE">{translate('adjustApp.BMICategory.HIGHOBESE')}</option>
                  <option value="EXTRAOBESE">{translate('adjustApp.BMICategory.EXTRAOBESE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="bmrLabel" for="body-composition-bmr">
                  <Translate contentKey="adjustApp.bodyComposition.bmr">Bmr</Translate>
                </Label>
                <AvField id="body-composition-bmr" type="string" className="form-control" name="bmr" />
              </AvGroup>
              <AvGroup>
                <Label id="lbmLabel" for="body-composition-lbm">
                  <Translate contentKey="adjustApp.bodyComposition.lbm">Lbm</Translate>
                </Label>
                <AvField id="body-composition-lbm" type="string" className="form-control" name="lbm" />
              </AvGroup>
              <AvGroup>
                <Label id="pbfLabel" for="body-composition-pbf">
                  <Translate contentKey="adjustApp.bodyComposition.pbf">Pbf</Translate>
                </Label>
                <AvField id="body-composition-pbf" type="string" className="form-control" name="pbf" />
              </AvGroup>
              <AvGroup>
                <Label id="bodyFatCategoryLabel" for="body-composition-bodyFatCategory">
                  <Translate contentKey="adjustApp.bodyComposition.bodyFatCategory">Body Fat Category</Translate>
                </Label>
                <AvInput
                  id="body-composition-bodyFatCategory"
                  type="select"
                  className="form-control"
                  name="bodyFatCategory"
                  value={(!isNew && bodyCompositionEntity.bodyFatCategory) || 'ESSENTIAL'}
                >
                  <option value="ESSENTIAL">{translate('adjustApp.BodyFatCategory.ESSENTIAL')}</option>
                  <option value="ATHLETE">{translate('adjustApp.BodyFatCategory.ATHLETE')}</option>
                  <option value="FITNESS">{translate('adjustApp.BodyFatCategory.FITNESS')}</option>
                  <option value="AVERAGE">{translate('adjustApp.BodyFatCategory.AVERAGE')}</option>
                  <option value="OBESE">{translate('adjustApp.BodyFatCategory.OBESE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="smmLabel" for="body-composition-smm">
                  <Translate contentKey="adjustApp.bodyComposition.smm">Smm</Translate>
                </Label>
                <AvField id="body-composition-smm" type="string" className="form-control" name="smm" />
              </AvGroup>
              <AvGroup>
                <Label id="bodyTypeNumberLabel" for="body-composition-bodyTypeNumber">
                  <Translate contentKey="adjustApp.bodyComposition.bodyTypeNumber">Body Type Number</Translate>
                </Label>
                <AvField id="body-composition-bodyTypeNumber" type="string" className="form-control" name="bodyTypeNumber" />
              </AvGroup>
              <AvGroup>
                <Label id="bodyTypeLabel" for="body-composition-bodyType">
                  <Translate contentKey="adjustApp.bodyComposition.bodyType">Body Type</Translate>
                </Label>
                <AvInput
                  id="body-composition-bodyType"
                  type="select"
                  className="form-control"
                  name="bodyType"
                  value={(!isNew && bodyCompositionEntity.bodyType) || 'MESOMORPH'}
                >
                  <option value="MESOMORPH">{translate('adjustApp.BodyType.MESOMORPH')}</option>
                  <option value="ECTOMORPH">{translate('adjustApp.BodyType.ECTOMORPH')}</option>
                  <option value="ENDOMORPH">{translate('adjustApp.BodyType.ENDOMORPH')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="frameSizeLabel" for="body-composition-frameSize">
                  <Translate contentKey="adjustApp.bodyComposition.frameSize">Frame Size</Translate>
                </Label>
                <AvField id="body-composition-frameSize" type="string" className="form-control" name="frameSize" />
              </AvGroup>
              <AvGroup>
                <Label id="bodyFrameSizeLabel" for="body-composition-bodyFrameSize">
                  <Translate contentKey="adjustApp.bodyComposition.bodyFrameSize">Body Frame Size</Translate>
                </Label>
                <AvInput
                  id="body-composition-bodyFrameSize"
                  type="select"
                  className="form-control"
                  name="bodyFrameSize"
                  value={(!isNew && bodyCompositionEntity.bodyFrameSize) || 'SMALL'}
                >
                  <option value="SMALL">{translate('adjustApp.BodyFrameSize.SMALL')}</option>
                  <option value="MEDIUM">{translate('adjustApp.BodyFrameSize.MEDIUM')}</option>
                  <option value="LARGE">{translate('adjustApp.BodyFrameSize.LARGE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="visceralFatLevelLabel" for="body-composition-visceralFatLevel">
                  <Translate contentKey="adjustApp.bodyComposition.visceralFatLevel">Visceral Fat Level</Translate>
                </Label>
                <AvField id="body-composition-visceralFatLevel" type="string" className="form-control" name="visceralFatLevel" />
              </AvGroup>
              <AvGroup>
                <Label id="workoutLocationLabel" for="body-composition-workoutLocation">
                  <Translate contentKey="adjustApp.bodyComposition.workoutLocation">Workout Location</Translate>
                </Label>
                <AvInput
                  id="body-composition-workoutLocation"
                  type="select"
                  className="form-control"
                  name="workoutLocation"
                  value={(!isNew && bodyCompositionEntity.workoutLocation) || 'HOME'}
                >
                  <option value="HOME">{translate('adjustApp.WorkoutLocation.HOME')}</option>
                  <option value="GYM">{translate('adjustApp.WorkoutLocation.GYM')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="goalLabel" for="body-composition-goal">
                  <Translate contentKey="adjustApp.bodyComposition.goal">Goal</Translate>
                </Label>
                <AvInput
                  id="body-composition-goal"
                  type="select"
                  className="form-control"
                  name="goal"
                  value={(!isNew && bodyCompositionEntity.goal) || 'DECREASEWEIGHT'}
                >
                  <option value="DECREASEWEIGHT">{translate('adjustApp.Goal.DECREASEWEIGHT')}</option>
                  <option value="INCREASEWEIGHT">{translate('adjustApp.Goal.INCREASEWEIGHT')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="activityTypeLabel" for="body-composition-activityType">
                  <Translate contentKey="adjustApp.bodyComposition.activityType">Activity Type</Translate>
                </Label>
                <AvInput
                  id="body-composition-activityType"
                  type="select"
                  className="form-control"
                  name="activityType"
                  value={(!isNew && bodyCompositionEntity.activityType) || 'SEDENTARY'}
                >
                  <option value="SEDENTARY">{translate('adjustApp.ActivityType.SEDENTARY')}</option>
                  <option value="LIGHT">{translate('adjustApp.ActivityType.LIGHT')}</option>
                  <option value="MODERATE">{translate('adjustApp.ActivityType.MODERATE')}</option>
                  <option value="HIGH">{translate('adjustApp.ActivityType.HIGH')}</option>
                  <option value="EXTRA">{translate('adjustApp.ActivityType.EXTRA')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="workoutTypeLabel" for="body-composition-workoutType">
                  <Translate contentKey="adjustApp.bodyComposition.workoutType">Workout Type</Translate>
                </Label>
                <AvInput
                  id="body-composition-workoutType"
                  type="select"
                  className="form-control"
                  name="workoutType"
                  value={(!isNew && bodyCompositionEntity.workoutType) || 'POWERLIFTING'}
                >
                  <option value="POWERLIFTING">{translate('adjustApp.WorkoutType.POWERLIFTING')}</option>
                  <option value="BODYBUILDING">{translate('adjustApp.WorkoutType.BODYBUILDING')}</option>
                  <option value="BODYBUILDING_CLASSIC">{translate('adjustApp.WorkoutType.BODYBUILDING_CLASSIC')}</option>
                  <option value="FITNESS">{translate('adjustApp.WorkoutType.FITNESS')}</option>
                  <option value="ENDURANCE">{translate('adjustApp.WorkoutType.ENDURANCE')}</option>
                  <option value="CARDIO">{translate('adjustApp.WorkoutType.CARDIO')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/body-composition" replace color="info">
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
  medicalProfiles: storeState.medicalProfile.entities,
  adjustPrograms: storeState.adjustProgram.entities,
  bodyCompositionEntity: storeState.bodyComposition.entity,
  loading: storeState.bodyComposition.loading,
  updating: storeState.bodyComposition.updating,
  updateSuccess: storeState.bodyComposition.updateSuccess,
});

const mapDispatchToProps = {
  getMedicalProfiles,
  getAdjustPrograms,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BodyCompositionUpdate);
