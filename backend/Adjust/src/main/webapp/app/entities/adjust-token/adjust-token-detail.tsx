import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-token.reducer';
import { IAdjustToken } from 'app/shared/model/adjust-token.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTokenDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTokenDetail = (props: IAdjustTokenDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustTokenEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustToken.detail.title">AdjustToken</Translate> [<b>{adjustTokenEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.adjustToken.name">Name</Translate>
            </span>
          </dt>
          <dd>{adjustTokenEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.adjustToken.description">Description</Translate>
            </span>
          </dt>
          <dd>{adjustTokenEntity.description}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="adjustApp.adjustToken.token">Token</Translate>
            </span>
          </dt>
          <dd>{adjustTokenEntity.token}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="adjustApp.adjustToken.price">Price</Translate>
            </span>
          </dt>
          <dd>{adjustTokenEntity.price}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.adjustToken.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {adjustTokenEntity.image ? (
              <div>
                {adjustTokenEntity.imageContentType ? (
                  <a onClick={openFile(adjustTokenEntity.imageContentType, adjustTokenEntity.image)}>
                    <img
                      src={`data:${adjustTokenEntity.imageContentType};base64,${adjustTokenEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {adjustTokenEntity.imageContentType}, {byteSize(adjustTokenEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/adjust-token" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-token/${adjustTokenEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustToken }: IRootState) => ({
  adjustTokenEntity: adjustToken.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTokenDetail);
