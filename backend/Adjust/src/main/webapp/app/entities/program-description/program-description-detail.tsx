import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './program-description.reducer';
import { IProgramDescription } from 'app/shared/model/program-description.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProgramDescriptionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProgramDescriptionDetail = (props: IProgramDescriptionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { programDescriptionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.programDescription.detail.title">ProgramDescription</Translate> [
          <b>{programDescriptionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="text">
              <Translate contentKey="adjustApp.programDescription.text">Text</Translate>
            </span>
          </dt>
          <dd>{programDescriptionEntity.text}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="adjustApp.programDescription.type">Type</Translate>
            </span>
          </dt>
          <dd>{programDescriptionEntity.type}</dd>
        </dl>
        <Button tag={Link} to="/program-description" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/program-description/${programDescriptionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ programDescription }: IRootState) => ({
  programDescriptionEntity: programDescription.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProgramDescriptionDetail);
