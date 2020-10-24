import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './disease.reducer';
import { IDisease } from 'app/shared/model/disease.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDiseaseDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DiseaseDetail = (props: IDiseaseDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { diseaseEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.disease.detail.title">Disease</Translate> [<b>{diseaseEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.disease.name">Name</Translate>
            </span>
          </dt>
          <dd>{diseaseEntity.name}</dd>
          <dt>
            <span id="proteinPercentage">
              <Translate contentKey="adjustApp.disease.proteinPercentage">Protein Percentage</Translate>
            </span>
          </dt>
          <dd>{diseaseEntity.proteinPercentage}</dd>
          <dt>
            <span id="carbsPercentage">
              <Translate contentKey="adjustApp.disease.carbsPercentage">Carbs Percentage</Translate>
            </span>
          </dt>
          <dd>{diseaseEntity.carbsPercentage}</dd>
          <dt>
            <span id="fatPercentage">
              <Translate contentKey="adjustApp.disease.fatPercentage">Fat Percentage</Translate>
            </span>
          </dt>
          <dd>{diseaseEntity.fatPercentage}</dd>
        </dl>
        <Button tag={Link} to="/disease" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/disease/${diseaseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ disease }: IRootState) => ({
  diseaseEntity: disease.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DiseaseDetail);
