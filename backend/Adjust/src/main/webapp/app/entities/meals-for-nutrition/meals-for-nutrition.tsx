import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './meals-for-nutrition.reducer';
import { IMealsForNutrition } from 'app/shared/model/meals-for-nutrition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMealsForNutritionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MealsForNutrition = (props: IMealsForNutritionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { mealsForNutritionList, match, loading } = props;
  return (
    <div>
      <h2 id="meals-for-nutrition-heading">
        <Translate contentKey="adjustApp.mealsForNutrition.home.title">Meals For Nutritions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.mealsForNutrition.home.createLabel">Create new Meals For Nutrition</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {mealsForNutritionList && mealsForNutritionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.mealsForNutrition.mealType">Meal Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.mealsForNutrition.nutrition">Nutrition</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mealsForNutritionList.map((mealsForNutrition, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${mealsForNutrition.id}`} color="link" size="sm">
                      {mealsForNutrition.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`adjustApp.MealType.${mealsForNutrition.mealType}`} />
                  </td>
                  <td>
                    {mealsForNutrition.nutritionId ? (
                      <Link to={`nutrition/${mealsForNutrition.nutritionId}`}>{mealsForNutrition.nutritionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${mealsForNutrition.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mealsForNutrition.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${mealsForNutrition.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.mealsForNutrition.home.notFound">No Meals For Nutritions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ mealsForNutrition }: IRootState) => ({
  mealsForNutritionList: mealsForNutrition.entities,
  loading: mealsForNutrition.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MealsForNutrition);
