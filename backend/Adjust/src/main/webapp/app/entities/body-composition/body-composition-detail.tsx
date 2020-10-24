import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './body-composition.reducer';
import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBodyCompositionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BodyCompositionDetail = (props: IBodyCompositionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { bodyCompositionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.bodyComposition.detail.title">BodyComposition</Translate> [<b>{bodyCompositionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="createdAt">
              <Translate contentKey="adjustApp.bodyComposition.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {bodyCompositionEntity.createdAt ? (
              <TextFormat value={bodyCompositionEntity.createdAt} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="age">
              <Translate contentKey="adjustApp.bodyComposition.age">Age</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.age}</dd>
          <dt>
            <span id="gender">
              <Translate contentKey="adjustApp.bodyComposition.gender">Gender</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.gender}</dd>
          <dt>
            <span id="height">
              <Translate contentKey="adjustApp.bodyComposition.height">Height</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.height}</dd>
          <dt>
            <span id="weight">
              <Translate contentKey="adjustApp.bodyComposition.weight">Weight</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.weight}</dd>
          <dt>
            <span id="wrist">
              <Translate contentKey="adjustApp.bodyComposition.wrist">Wrist</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.wrist}</dd>
          <dt>
            <span id="waist">
              <Translate contentKey="adjustApp.bodyComposition.waist">Waist</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.waist}</dd>
          <dt>
            <span id="hip">
              <Translate contentKey="adjustApp.bodyComposition.hip">Hip</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.hip}</dd>
          <dt>
            <span id="abdomen">
              <Translate contentKey="adjustApp.bodyComposition.abdomen">Abdomen</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.abdomen}</dd>
          <dt>
            <span id="neck">
              <Translate contentKey="adjustApp.bodyComposition.neck">Neck</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.neck}</dd>
          <dt>
            <span id="waistHipRatio">
              <Translate contentKey="adjustApp.bodyComposition.waistHipRatio">Waist Hip Ratio</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.waistHipRatio}</dd>
          <dt>
            <span id="idealWeightMin">
              <Translate contentKey="adjustApp.bodyComposition.idealWeightMin">Ideal Weight Min</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.idealWeightMin}</dd>
          <dt>
            <span id="idealWeightMax">
              <Translate contentKey="adjustApp.bodyComposition.idealWeightMax">Ideal Weight Max</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.idealWeightMax}</dd>
          <dt>
            <span id="bestWeight">
              <Translate contentKey="adjustApp.bodyComposition.bestWeight">Best Weight</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bestWeight}</dd>
          <dt>
            <span id="bmi">
              <Translate contentKey="adjustApp.bodyComposition.bmi">Bmi</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bmi}</dd>
          <dt>
            <span id="bmiCategory">
              <Translate contentKey="adjustApp.bodyComposition.bmiCategory">Bmi Category</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bmiCategory}</dd>
          <dt>
            <span id="bmr">
              <Translate contentKey="adjustApp.bodyComposition.bmr">Bmr</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bmr}</dd>
          <dt>
            <span id="lbm">
              <Translate contentKey="adjustApp.bodyComposition.lbm">Lbm</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.lbm}</dd>
          <dt>
            <span id="pbf">
              <Translate contentKey="adjustApp.bodyComposition.pbf">Pbf</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.pbf}</dd>
          <dt>
            <span id="bodyFatCategory">
              <Translate contentKey="adjustApp.bodyComposition.bodyFatCategory">Body Fat Category</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bodyFatCategory}</dd>
          <dt>
            <span id="smm">
              <Translate contentKey="adjustApp.bodyComposition.smm">Smm</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.smm}</dd>
          <dt>
            <span id="bodyTypeNumber">
              <Translate contentKey="adjustApp.bodyComposition.bodyTypeNumber">Body Type Number</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bodyTypeNumber}</dd>
          <dt>
            <span id="bodyType">
              <Translate contentKey="adjustApp.bodyComposition.bodyType">Body Type</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bodyType}</dd>
          <dt>
            <span id="frameSize">
              <Translate contentKey="adjustApp.bodyComposition.frameSize">Frame Size</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.frameSize}</dd>
          <dt>
            <span id="bodyFrameSize">
              <Translate contentKey="adjustApp.bodyComposition.bodyFrameSize">Body Frame Size</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.bodyFrameSize}</dd>
          <dt>
            <span id="visceralFatLevel">
              <Translate contentKey="adjustApp.bodyComposition.visceralFatLevel">Visceral Fat Level</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.visceralFatLevel}</dd>
          <dt>
            <span id="workoutLocation">
              <Translate contentKey="adjustApp.bodyComposition.workoutLocation">Workout Location</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.workoutLocation}</dd>
          <dt>
            <span id="goal">
              <Translate contentKey="adjustApp.bodyComposition.goal">Goal</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.goal}</dd>
          <dt>
            <span id="activityType">
              <Translate contentKey="adjustApp.bodyComposition.activityType">Activity Type</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.activityType}</dd>
          <dt>
            <span id="workoutType">
              <Translate contentKey="adjustApp.bodyComposition.workoutType">Workout Type</Translate>
            </span>
          </dt>
          <dd>{bodyCompositionEntity.workoutType}</dd>
        </dl>
        <Button tag={Link} to="/body-composition" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/body-composition/${bodyCompositionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ bodyComposition }: IRootState) => ({
  bodyCompositionEntity: bodyComposition.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BodyCompositionDetail);
