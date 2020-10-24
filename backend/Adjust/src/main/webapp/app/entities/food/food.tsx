import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './food.reducer';
import { IFood } from 'app/shared/model/food.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IFoodProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Food = (props: IFoodProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { foodList, match, loading } = props;
  return (
    <div>
      <h2 id="food-heading">
        <Translate contentKey="adjustApp.food.home.title">Foods</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.food.home.createLabel">Create new Food</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {foodList && foodList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.food.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.food.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.food.nutrition">Nutrition</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {foodList.map((food, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${food.id}`} color="link" size="sm">
                      {food.id}
                    </Button>
                  </td>
                  <td>{food.name}</td>
                  <td>{food.description}</td>
                  <td>{food.nutritionId ? <Link to={`nutrition/${food.nutritionId}`}>{food.nutritionId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${food.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${food.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${food.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.food.home.notFound">No Foods found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ food }: IRootState) => ({
  foodList: food.entities,
  loading: food.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Food);
