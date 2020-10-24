import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './disease.reducer';
import { IDisease } from 'app/shared/model/disease.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiseaseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Disease = (props: IDiseaseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { diseaseList, match, loading } = props;
  return (
    <div>
      <h2 id="disease-heading">
        <Translate contentKey="adjustApp.disease.home.title">Diseases</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.disease.home.createLabel">Create new Disease</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {diseaseList && diseaseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.disease.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.disease.proteinPercentage">Protein Percentage</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.disease.carbsPercentage">Carbs Percentage</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.disease.fatPercentage">Fat Percentage</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {diseaseList.map((disease, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${disease.id}`} color="link" size="sm">
                      {disease.id}
                    </Button>
                  </td>
                  <td>{disease.name}</td>
                  <td>{disease.proteinPercentage}</td>
                  <td>{disease.carbsPercentage}</td>
                  <td>{disease.fatPercentage}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${disease.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${disease.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${disease.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.disease.home.notFound">No Diseases found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ disease }: IRootState) => ({
  diseaseList: disease.entities,
  loading: disease.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Disease);
