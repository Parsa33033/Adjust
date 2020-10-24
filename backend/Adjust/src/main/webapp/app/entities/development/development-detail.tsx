import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './development.reducer';
import { IDevelopment } from 'app/shared/model/development.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDevelopmentDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DevelopmentDetail = (props: IDevelopmentDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { developmentEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.development.detail.title">Development</Translate> [<b>{developmentEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="date">
              <Translate contentKey="adjustApp.development.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {developmentEntity.date ? <TextFormat value={developmentEntity.date} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="nutritionScore">
              <Translate contentKey="adjustApp.development.nutritionScore">Nutrition Score</Translate>
            </span>
          </dt>
          <dd>{developmentEntity.nutritionScore}</dd>
          <dt>
            <span id="fitnessScore">
              <Translate contentKey="adjustApp.development.fitnessScore">Fitness Score</Translate>
            </span>
          </dt>
          <dd>{developmentEntity.fitnessScore}</dd>
          <dt>
            <span id="weight">
              <Translate contentKey="adjustApp.development.weight">Weight</Translate>
            </span>
          </dt>
          <dd>{developmentEntity.weight}</dd>
          <dt>
            <span id="bmi">
              <Translate contentKey="adjustApp.development.bmi">Bmi</Translate>
            </span>
          </dt>
          <dd>{developmentEntity.bmi}</dd>
          <dt>
            <span id="lbm">
              <Translate contentKey="adjustApp.development.lbm">Lbm</Translate>
            </span>
          </dt>
          <dd>{developmentEntity.lbm}</dd>
          <dt>
            <span id="pbf">
              <Translate contentKey="adjustApp.development.pbf">Pbf</Translate>
            </span>
          </dt>
          <dd>{developmentEntity.pbf}</dd>
          <dt>
            <Translate contentKey="adjustApp.development.adjustProgram">Adjust Program</Translate>
          </dt>
          <dd>{developmentEntity.adjustProgramId ? developmentEntity.adjustProgramId : ''}</dd>
        </dl>
        <Button tag={Link} to="/development" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/development/${developmentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ development }: IRootState) => ({
  developmentEntity: development.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DevelopmentDetail);
