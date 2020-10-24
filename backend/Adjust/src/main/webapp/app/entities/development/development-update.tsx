import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { getEntities as getAdjustPrograms } from 'app/entities/adjust-program/adjust-program.reducer';
import { getEntity, updateEntity, createEntity, reset } from './development.reducer';
import { IDevelopment } from 'app/shared/model/development.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDevelopmentUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DevelopmentUpdate = (props: IDevelopmentUpdateProps) => {
  const [adjustProgramId, setAdjustProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { developmentEntity, adjustPrograms, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/development');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

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
        ...developmentEntity,
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
          <h2 id="adjustApp.development.home.createOrEditLabel">
            <Translate contentKey="adjustApp.development.home.createOrEditLabel">Create or edit a Development</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : developmentEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="development-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="development-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="dateLabel" for="development-date">
                  <Translate contentKey="adjustApp.development.date">Date</Translate>
                </Label>
                <AvField id="development-date" type="date" className="form-control" name="date" />
              </AvGroup>
              <AvGroup>
                <Label id="nutritionScoreLabel" for="development-nutritionScore">
                  <Translate contentKey="adjustApp.development.nutritionScore">Nutrition Score</Translate>
                </Label>
                <AvField id="development-nutritionScore" type="string" className="form-control" name="nutritionScore" />
              </AvGroup>
              <AvGroup>
                <Label id="fitnessScoreLabel" for="development-fitnessScore">
                  <Translate contentKey="adjustApp.development.fitnessScore">Fitness Score</Translate>
                </Label>
                <AvField id="development-fitnessScore" type="string" className="form-control" name="fitnessScore" />
              </AvGroup>
              <AvGroup>
                <Label id="weightLabel" for="development-weight">
                  <Translate contentKey="adjustApp.development.weight">Weight</Translate>
                </Label>
                <AvField id="development-weight" type="string" className="form-control" name="weight" />
              </AvGroup>
              <AvGroup>
                <Label id="bmiLabel" for="development-bmi">
                  <Translate contentKey="adjustApp.development.bmi">Bmi</Translate>
                </Label>
                <AvField id="development-bmi" type="string" className="form-control" name="bmi" />
              </AvGroup>
              <AvGroup>
                <Label id="lbmLabel" for="development-lbm">
                  <Translate contentKey="adjustApp.development.lbm">Lbm</Translate>
                </Label>
                <AvField id="development-lbm" type="string" className="form-control" name="lbm" />
              </AvGroup>
              <AvGroup>
                <Label id="pbfLabel" for="development-pbf">
                  <Translate contentKey="adjustApp.development.pbf">Pbf</Translate>
                </Label>
                <AvField id="development-pbf" type="string" className="form-control" name="pbf" />
              </AvGroup>
              <AvGroup>
                <Label for="development-adjustProgram">
                  <Translate contentKey="adjustApp.development.adjustProgram">Adjust Program</Translate>
                </Label>
                <AvInput id="development-adjustProgram" type="select" className="form-control" name="adjustProgramId">
                  <option value="" key="0" />
                  {adjustPrograms
                    ? adjustPrograms.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/development" replace color="info">
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
  adjustPrograms: storeState.adjustProgram.entities,
  developmentEntity: storeState.development.entity,
  loading: storeState.development.loading,
  updating: storeState.development.updating,
  updateSuccess: storeState.development.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustPrograms,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DevelopmentUpdate);
