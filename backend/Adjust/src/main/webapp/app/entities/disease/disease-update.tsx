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
import { getEntity, updateEntity, createEntity, reset } from './disease.reducer';
import { IDisease } from 'app/shared/model/disease.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDiseaseUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DiseaseUpdate = (props: IDiseaseUpdateProps) => {
  const [medicalProfilesId, setMedicalProfilesId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { diseaseEntity, medicalProfiles, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/disease');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getMedicalProfiles();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...diseaseEntity,
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
          <h2 id="adjustApp.disease.home.createOrEditLabel">
            <Translate contentKey="adjustApp.disease.home.createOrEditLabel">Create or edit a Disease</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : diseaseEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="disease-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="disease-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="disease-name">
                  <Translate contentKey="adjustApp.disease.name">Name</Translate>
                </Label>
                <AvField id="disease-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="proteinPercentageLabel" for="disease-proteinPercentage">
                  <Translate contentKey="adjustApp.disease.proteinPercentage">Protein Percentage</Translate>
                </Label>
                <AvField id="disease-proteinPercentage" type="string" className="form-control" name="proteinPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="carbsPercentageLabel" for="disease-carbsPercentage">
                  <Translate contentKey="adjustApp.disease.carbsPercentage">Carbs Percentage</Translate>
                </Label>
                <AvField id="disease-carbsPercentage" type="string" className="form-control" name="carbsPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="fatPercentageLabel" for="disease-fatPercentage">
                  <Translate contentKey="adjustApp.disease.fatPercentage">Fat Percentage</Translate>
                </Label>
                <AvField id="disease-fatPercentage" type="string" className="form-control" name="fatPercentage" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/disease" replace color="info">
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
  diseaseEntity: storeState.disease.entity,
  loading: storeState.disease.loading,
  updating: storeState.disease.updating,
  updateSuccess: storeState.disease.updateSuccess,
});

const mapDispatchToProps = {
  getMedicalProfiles,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DiseaseUpdate);
