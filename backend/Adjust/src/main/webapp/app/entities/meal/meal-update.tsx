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
import { INutritionProgram } from 'app/shared/model/nutrition-program.model';
import { getEntities as getNutritionPrograms } from 'app/entities/nutrition-program/nutrition-program.reducer';
import { getEntity, updateEntity, createEntity, reset } from './meal.reducer';
import { IMeal } from 'app/shared/model/meal.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMealUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MealUpdate = (props: IMealUpdateProps) => {
  const [idsnutritions, setIdsnutritions] = useState([]);
  const [nutritionProgramId, setNutritionProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { mealEntity, nutritions, nutritionPrograms, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/meal');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getNutritions();
    props.getNutritionPrograms();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...mealEntity,
        ...values,
        nutritions: mapIdList(values.nutritions),
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
          <h2 id="adjustApp.meal.home.createOrEditLabel">
            <Translate contentKey="adjustApp.meal.home.createOrEditLabel">Create or edit a Meal</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : mealEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="meal-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="meal-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="mealTypeLabel" for="meal-mealType">
                  <Translate contentKey="adjustApp.meal.mealType">Meal Type</Translate>
                </Label>
                <AvInput
                  id="meal-mealType"
                  type="select"
                  className="form-control"
                  name="mealType"
                  value={(!isNew && mealEntity.mealType) || 'BREAKFAST'}
                >
                  <option value="BREAKFAST">{translate('adjustApp.MealType.BREAKFAST')}</option>
                  <option value="LUNCH">{translate('adjustApp.MealType.LUNCH')}</option>
                  <option value="DINNER">{translate('adjustApp.MealType.DINNER')}</option>
                  <option value="BRUNCH">{translate('adjustApp.MealType.BRUNCH')}</option>
                  <option value="EVEMEAL">{translate('adjustApp.MealType.EVEMEAL')}</option>
                  <option value="AFTERNOONMEAL">{translate('adjustApp.MealType.AFTERNOONMEAL')}</option>
                  <option value="BEFORETRAINING">{translate('adjustApp.MealType.BEFORETRAINING')}</option>
                  <option value="AFTERTRAINING">{translate('adjustApp.MealType.AFTERTRAINING')}</option>
                  <option value="MIDNIGHTSUPPER">{translate('adjustApp.MealType.MIDNIGHTSUPPER')}</option>
                  <option value="IFTAR">{translate('adjustApp.MealType.IFTAR')}</option>
                  <option value="SNACK1">{translate('adjustApp.MealType.SNACK1')}</option>
                  <option value="SNACK2">{translate('adjustApp.MealType.SNACK2')}</option>
                  <option value="SNACK3">{translate('adjustApp.MealType.SNACK3')}</option>
                  <option value="SNACK4">{translate('adjustApp.MealType.SNACK4')}</option>
                  <option value="SNACK5">{translate('adjustApp.MealType.SNACK5')}</option>
                  <option value="SNACK6">{translate('adjustApp.MealType.SNACK6')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="numberLabel" for="meal-number">
                  <Translate contentKey="adjustApp.meal.number">Number</Translate>
                </Label>
                <AvField id="meal-number" type="string" className="form-control" name="number" />
              </AvGroup>
              <AvGroup>
                <Label for="meal-nutritions">
                  <Translate contentKey="adjustApp.meal.nutritions">Nutritions</Translate>
                </Label>
                <AvInput
                  id="meal-nutritions"
                  type="select"
                  multiple
                  className="form-control"
                  name="nutritions"
                  value={mealEntity.nutritions && mealEntity.nutritions.map(e => e.id)}
                >
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
              <AvGroup>
                <Label for="meal-nutritionProgram">
                  <Translate contentKey="adjustApp.meal.nutritionProgram">Nutrition Program</Translate>
                </Label>
                <AvInput id="meal-nutritionProgram" type="select" className="form-control" name="nutritionProgramId">
                  <option value="" key="0" />
                  {nutritionPrograms
                    ? nutritionPrograms.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/meal" replace color="info">
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
  nutritionPrograms: storeState.nutritionProgram.entities,
  mealEntity: storeState.meal.entity,
  loading: storeState.meal.loading,
  updating: storeState.meal.updating,
  updateSuccess: storeState.meal.updateSuccess,
});

const mapDispatchToProps = {
  getNutritions,
  getNutritionPrograms,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MealUpdate);
