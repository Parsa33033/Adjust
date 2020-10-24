import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './meals-for-nutrition.reducer';
import { IMealsForNutrition } from 'app/shared/model/meals-for-nutrition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMealsForNutritionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MealsForNutritionDetail = (props: IMealsForNutritionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { mealsForNutritionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.mealsForNutrition.detail.title">MealsForNutrition</Translate> [
          <b>{mealsForNutritionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="mealType">
              <Translate contentKey="adjustApp.mealsForNutrition.mealType">Meal Type</Translate>
            </span>
          </dt>
          <dd>{mealsForNutritionEntity.mealType}</dd>
          <dt>
            <Translate contentKey="adjustApp.mealsForNutrition.nutrition">Nutrition</Translate>
          </dt>
          <dd>{mealsForNutritionEntity.nutritionId ? mealsForNutritionEntity.nutritionId : ''}</dd>
        </dl>
        <Button tag={Link} to="/meals-for-nutrition" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/meals-for-nutrition/${mealsForNutritionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ mealsForNutrition }: IRootState) => ({
  mealsForNutritionEntity: mealsForNutrition.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MealsForNutritionDetail);
