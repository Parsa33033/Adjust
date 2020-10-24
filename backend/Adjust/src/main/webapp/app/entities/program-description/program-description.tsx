import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './program-description.reducer';
import { IProgramDescription } from 'app/shared/model/program-description.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProgramDescriptionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ProgramDescription = (props: IProgramDescriptionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { programDescriptionList, match, loading } = props;
  return (
    <div>
      <h2 id="program-description-heading">
        <Translate contentKey="adjustApp.programDescription.home.title">Program Descriptions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.programDescription.home.createLabel">Create new Program Description</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {programDescriptionList && programDescriptionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.programDescription.text">Text</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.programDescription.type">Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {programDescriptionList.map((programDescription, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${programDescription.id}`} color="link" size="sm">
                      {programDescription.id}
                    </Button>
                  </td>
                  <td>{programDescription.text}</td>
                  <td>
                    <Translate contentKey={`adjustApp.ProgramType.${programDescription.type}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${programDescription.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${programDescription.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${programDescription.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.programDescription.home.notFound">No Program Descriptions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ programDescription }: IRootState) => ({
  programDescriptionList: programDescription.entities,
  loading: programDescription.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProgramDescription);
