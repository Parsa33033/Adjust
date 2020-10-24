import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './shopping-item.reducer';
import { IShoppingItem } from 'app/shared/model/shopping-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShoppingItemProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const ShoppingItem = (props: IShoppingItemProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { shoppingItemList, match, loading } = props;
  return (
    <div>
      <h2 id="shopping-item-heading">
        <Translate contentKey="adjustApp.shoppingItem.home.title">Shopping Items</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.shoppingItem.home.createLabel">Create new Shopping Item</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {shoppingItemList && shoppingItemList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.itemId">Item Id</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.price">Price</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.image">Image</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.orderable">Orderable</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.shoppingItem.number">Number</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {shoppingItemList.map((shoppingItem, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${shoppingItem.id}`} color="link" size="sm">
                      {shoppingItem.id}
                    </Button>
                  </td>
                  <td>{shoppingItem.name}</td>
                  <td>{shoppingItem.itemId}</td>
                  <td>{shoppingItem.description}</td>
                  <td>{shoppingItem.price}</td>
                  <td>
                    {shoppingItem.image ? (
                      <div>
                        {shoppingItem.imageContentType ? (
                          <a onClick={openFile(shoppingItem.imageContentType, shoppingItem.image)}>
                            <img src={`data:${shoppingItem.imageContentType};base64,${shoppingItem.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {shoppingItem.imageContentType}, {byteSize(shoppingItem.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{shoppingItem.orderable ? 'true' : 'false'}</td>
                  <td>{shoppingItem.number}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${shoppingItem.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${shoppingItem.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${shoppingItem.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.shoppingItem.home.notFound">No Shopping Items found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ shoppingItem }: IRootState) => ({
  shoppingItemList: shoppingItem.entities,
  loading: shoppingItem.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingItem);
