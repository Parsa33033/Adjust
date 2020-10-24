import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { INutrition } from 'app/shared/model/nutrition.model';
import { getEntities as getNutritions } from 'app/entities/nutrition/nutrition.reducer';
import { getEntity, updateEntity, createEntity, reset } from './food.reducer';
import { IFood } from 'app/shared/model/food.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFoodUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FoodUpdate = (props: IFoodUpdateProps) => {
  const [nutritionId, setNutritionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { foodEntity, nutritions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/food');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNutritions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...foodEntity,
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
          <h2 id="adjustApp.food.home.createOrEditLabel">
            <Translate contentKey="adjustApp.food.home.createOrEditLabel">Create or edit a Food</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : foodEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="food-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="food-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="food-name">
                  <Translate contentKey="adjustApp.food.name">Name</Translate>
                </Label>
                <AvField id="food-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="food-description">
                  <Translate contentKey="adjustApp.food.description">Description</Translate>
                </Label>
                <AvField id="food-description" type="text" name="description" />
              </AvGroup>
              <AvGroup>
                <Label for="food-nutrition">
                  <Translate contentKey="adjustApp.food.nutrition">Nutrition</Translate>
                </Label>
                <AvInput id="food-nutrition" type="select" className="form-control" name="nutritionId">
                  <option value="" key="0" />
                  {nutritions
                    ? nutritions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/food" replace color="info">
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
  nutritions: storeState.nutrition.entities,
  foodEntity: storeState.food.entity,
  loading: storeState.food.loading,
  updating: storeState.food.updating,
  updateSuccess: storeState.food.updateSuccess,
});

const mapDispatchToProps = {
  getNutritions,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FoodUpdate);
