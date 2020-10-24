import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './development.reducer';
import { IDevelopment } from 'app/shared/model/development.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDevelopmentProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Development = (props: IDevelopmentProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { developmentList, match, loading } = props;
  return (
    <div>
      <h2 id="development-heading">
        <Translate contentKey="adjustApp.development.home.title">Developments</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.development.home.createLabel">Create new Development</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {developmentList && developmentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.date">Date</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.nutritionScore">Nutrition Score</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.fitnessScore">Fitness Score</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.weight">Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.bmi">Bmi</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.lbm">Lbm</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.pbf">Pbf</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.development.adjustProgram">Adjust Program</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {developmentList.map((development, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${development.id}`} color="link" size="sm">
                      {development.id}
                    </Button>
                  </td>
                  <td>{development.date ? <TextFormat type="date" value={development.date} format={APP_LOCAL_DATE_FORMAT} /> : null}</td>
                  <td>{development.nutritionScore}</td>
                  <td>{development.fitnessScore}</td>
                  <td>{development.weight}</td>
                  <td>{development.bmi}</td>
                  <td>{development.lbm}</td>
                  <td>{development.pbf}</td>
                  <td>
                    {development.adjustProgramId ? (
                      <Link to={`adjust-program/${development.adjustProgramId}`}>{development.adjustProgramId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${development.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${development.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${development.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.development.home.notFound">No Developments found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ development }: IRootState) => ({
  developmentList: development.entities,
  loading: development.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Development);
