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
import { getEntity, updateEntity, createEntity, reset } from './meals-for-nutrition.reducer';
import { IMealsForNutrition } from 'app/shared/model/meals-for-nutrition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMealsForNutritionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MealsForNutritionUpdate = (props: IMealsForNutritionUpdateProps) => {
  const [nutritionId, setNutritionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { mealsForNutritionEntity, nutritions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/meals-for-nutrition');
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
        ...mealsForNutritionEntity,
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
          <h2 id="adjustApp.mealsForNutrition.home.createOrEditLabel">
            <Translate contentKey="adjustApp.mealsForNutrition.home.createOrEditLabel">Create or edit a MealsForNutrition</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : mealsForNutritionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="meals-for-nutrition-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="meals-for-nutrition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="mealTypeLabel" for="meals-for-nutrition-mealType">
                  <Translate contentKey="adjustApp.mealsForNutrition.mealType">Meal Type</Translate>
                </Label>
                <AvInput
                  id="meals-for-nutrition-mealType"
                  type="select"
                  className="form-control"
                  name="mealType"
                  value={(!isNew && mealsForNutritionEntity.mealType) || 'BREAKFAST'}
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
                <Label for="meals-for-nutrition-nutrition">
                  <Translate contentKey="adjustApp.mealsForNutrition.nutrition">Nutrition</Translate>
                </Label>
                <AvInput id="meals-for-nutrition-nutrition" type="select" className="form-control" name="nutritionId">
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
              <Button tag={Link} id="cancel-save" to="/meals-for-nutrition" replace color="info">
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
  mealsForNutritionEntity: storeState.mealsForNutrition.entity,
  loading: storeState.mealsForNutrition.loading,
  updating: storeState.mealsForNutrition.updating,
  updateSuccess: storeState.mealsForNutrition.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(MealsForNutritionUpdate);
