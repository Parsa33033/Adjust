import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './adjust-token.reducer';
import { IAdjustToken } from 'app/shared/model/adjust-token.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTokenProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const AdjustToken = (props: IAdjustTokenProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { adjustTokenList, match, loading } = props;
  return (
    <div>
      <h2 id="adjust-token-heading">
        <Translate contentKey="adjustApp.adjustToken.home.title">Adjust Tokens</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.adjustToken.home.createLabel">Create new Adjust Token</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {adjustTokenList && adjustTokenList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustToken.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustToken.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustToken.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustToken.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.adjustToken.image">Image</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {adjustTokenList.map((adjustToken, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${adjustToken.id}`} color="link" size="sm">
                      {adjustToken.id}
                    </Button>
                  </td>
                  <td>{adjustToken.name}</td>
                  <td>{adjustToken.description}</td>
                  <td>{adjustToken.token}</td>
                  <td>{adjustToken.price}</td>
                  <td>
                    {adjustToken.image ? (
                      <div>
                        {adjustToken.imageContentType ? (
                          <a onClick={openFile(adjustToken.imageContentType, adjustToken.image)}>
                            <img src={`data:${adjustToken.imageContentType};base64,${adjustToken.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {adjustToken.imageContentType}, {byteSize(adjustToken.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${adjustToken.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustToken.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${adjustToken.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.adjustToken.home.notFound">No Adjust Tokens found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ adjustToken }: IRootState) => ({
  adjustTokenList: adjustToken.entities,
  loading: adjustToken.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustToken);
