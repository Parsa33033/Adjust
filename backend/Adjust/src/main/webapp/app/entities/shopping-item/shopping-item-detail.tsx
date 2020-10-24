import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './shopping-item.reducer';
import { IShoppingItem } from 'app/shared/model/shopping-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShoppingItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShoppingItemDetail = (props: IShoppingItemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { shoppingItemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.shoppingItem.detail.title">ShoppingItem</Translate> [<b>{shoppingItemEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.shoppingItem.name">Name</Translate>
            </span>
          </dt>
          <dd>{shoppingItemEntity.name}</dd>
          <dt>
            <span id="itemId">
              <Translate contentKey="adjustApp.shoppingItem.itemId">Item Id</Translate>
            </span>
          </dt>
          <dd>{shoppingItemEntity.itemId}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.shoppingItem.description">Description</Translate>
            </span>
          </dt>
          <dd>{shoppingItemEntity.description}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="adjustApp.shoppingItem.price">Price</Translate>
            </span>
          </dt>
          <dd>{shoppingItemEntity.price}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.shoppingItem.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {shoppingItemEntity.image ? (
              <div>
                {shoppingItemEntity.imageContentType ? (
                  <a onClick={openFile(shoppingItemEntity.imageContentType, shoppingItemEntity.image)}>
                    <img
                      src={`data:${shoppingItemEntity.imageContentType};base64,${shoppingItemEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {shoppingItemEntity.imageContentType}, {byteSize(shoppingItemEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="orderable">
              <Translate contentKey="adjustApp.shoppingItem.orderable">Orderable</Translate>
            </span>
          </dt>
          <dd>{shoppingItemEntity.orderable ? 'true' : 'false'}</dd>
          <dt>
            <span id="number">
              <Translate contentKey="adjustApp.shoppingItem.number">Number</Translate>
            </span>
          </dt>
          <dd>{shoppingItemEntity.number}</dd>
        </dl>
        <Button tag={Link} to="/shopping-item" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/shopping-item/${shoppingItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ shoppingItem }: IRootState) => ({
  shoppingItemEntity: shoppingItem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingItemDetail);
