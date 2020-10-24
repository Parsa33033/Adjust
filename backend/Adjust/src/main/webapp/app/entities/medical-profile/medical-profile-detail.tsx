import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './medical-profile.reducer';
import { IMedicalProfile } from 'app/shared/model/medical-profile.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMedicalProfileDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MedicalProfileDetail = (props: IMedicalProfileDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { medicalProfileEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.medicalProfile.detail.title">MedicalProfile</Translate> [<b>{medicalProfileEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="haveLostWeight">
              <Translate contentKey="adjustApp.medicalProfile.haveLostWeight">Have Lost Weight</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.haveLostWeight ? 'true' : 'false'}</dd>
          <dt>
            <span id="drowsiness">
              <Translate contentKey="adjustApp.medicalProfile.drowsiness">Drowsiness</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.drowsiness ? 'true' : 'false'}</dd>
          <dt>
            <span id="goodNightSleep">
              <Translate contentKey="adjustApp.medicalProfile.goodNightSleep">Good Night Sleep</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.goodNightSleep ? 'true' : 'false'}</dd>
          <dt>
            <span id="hoursOfTraningPerWeek">
              <Translate contentKey="adjustApp.medicalProfile.hoursOfTraningPerWeek">Hours Of Traning Per Week</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.hoursOfTraningPerWeek}</dd>
          <dt>
            <span id="loveForSugar">
              <Translate contentKey="adjustApp.medicalProfile.loveForSugar">Love For Sugar</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.loveForSugar ? 'true' : 'false'}</dd>
          <dt>
            <span id="overEating">
              <Translate contentKey="adjustApp.medicalProfile.overEating">Over Eating</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.overEating ? 'true' : 'false'}</dd>
          <dt>
            <span id="maritalStatus">
              <Translate contentKey="adjustApp.medicalProfile.maritalStatus">Marital Status</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.maritalStatus}</dd>
          <dt>
            <span id="childrenNumber">
              <Translate contentKey="adjustApp.medicalProfile.childrenNumber">Children Number</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.childrenNumber}</dd>
          <dt>
            <span id="education">
              <Translate contentKey="adjustApp.medicalProfile.education">Education</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.education}</dd>
          <dt>
            <span id="occupation">
              <Translate contentKey="adjustApp.medicalProfile.occupation">Occupation</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.occupation}</dd>
          <dt>
            <span id="hoursOfWorkDaily">
              <Translate contentKey="adjustApp.medicalProfile.hoursOfWorkDaily">Hours Of Work Daily</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.hoursOfWorkDaily}</dd>
          <dt>
            <span id="financialStatus">
              <Translate contentKey="adjustApp.medicalProfile.financialStatus">Financial Status</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.financialStatus}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="adjustApp.medicalProfile.city">City</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.city}</dd>
          <dt>
            <span id="useOfMedicine">
              <Translate contentKey="adjustApp.medicalProfile.useOfMedicine">Use Of Medicine</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.useOfMedicine ? 'true' : 'false'}</dd>
          <dt>
            <span id="medicines">
              <Translate contentKey="adjustApp.medicalProfile.medicines">Medicines</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.medicines}</dd>
          <dt>
            <span id="haveAllergy">
              <Translate contentKey="adjustApp.medicalProfile.haveAllergy">Have Allergy</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.haveAllergy ? 'true' : 'false'}</dd>
          <dt>
            <span id="allergy">
              <Translate contentKey="adjustApp.medicalProfile.allergy">Allergy</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.allergy}</dd>
          <dt>
            <span id="haveDisease">
              <Translate contentKey="adjustApp.medicalProfile.haveDisease">Have Disease</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.haveDisease ? 'true' : 'false'}</dd>
          <dt>
            <span id="bloodType">
              <Translate contentKey="adjustApp.medicalProfile.bloodType">Blood Type</Translate>
            </span>
          </dt>
          <dd>{medicalProfileEntity.bloodType}</dd>
          <dt>
            <Translate contentKey="adjustApp.medicalProfile.bodyComposition">Body Composition</Translate>
          </dt>
          <dd>{medicalProfileEntity.bodyCompositionId ? medicalProfileEntity.bodyCompositionId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.medicalProfile.diseases">Diseases</Translate>
          </dt>
          <dd>
            {medicalProfileEntity.diseases
              ? medicalProfileEntity.diseases.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {medicalProfileEntity.diseases && i === medicalProfileEntity.diseases.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/medical-profile" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/medical-profile/${medicalProfileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ medicalProfile }: IRootState) => ({
  medicalProfileEntity: medicalProfile.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MedicalProfileDetail);
