import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './order-item.reducer';
import { IOrderItem } from 'app/shared/model/order-item.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderItemDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrderItemDetail = (props: IOrderItemDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { orderItemEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.orderItem.detail.title">OrderItem</Translate> [<b>{orderItemEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="number">
              <Translate contentKey="adjustApp.orderItem.number">Number</Translate>
            </span>
          </dt>
          <dd>{orderItemEntity.number}</dd>
          <dt>
            <Translate contentKey="adjustApp.orderItem.shoppingItem">Shopping Item</Translate>
          </dt>
          <dd>{orderItemEntity.shoppingItemId ? orderItemEntity.shoppingItemId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.orderItem.order">Order</Translate>
          </dt>
          <dd>{orderItemEntity.orderId ? orderItemEntity.orderId : ''}</dd>
        </dl>
        <Button tag={Link} to="/order-item" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-item/${orderItemEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ orderItem }: IRootState) => ({
  orderItemEntity: orderItem.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderItemDetail);
