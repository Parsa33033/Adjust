import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './medical-profile.reducer';
import { IMedicalProfile } from 'app/shared/model/medical-profile.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMedicalProfileProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const MedicalProfile = (props: IMedicalProfileProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { medicalProfileList, match, loading } = props;
  return (
    <div>
      <h2 id="medical-profile-heading">
        <Translate contentKey="adjustApp.medicalProfile.home.title">Medical Profiles</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.medicalProfile.home.createLabel">Create new Medical Profile</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {medicalProfileList && medicalProfileList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.haveLostWeight">Have Lost Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.drowsiness">Drowsiness</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.goodNightSleep">Good Night Sleep</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.hoursOfTraningPerWeek">Hours Of Traning Per Week</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.loveForSugar">Love For Sugar</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.overEating">Over Eating</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.maritalStatus">Marital Status</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.childrenNumber">Children Number</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.education">Education</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.occupation">Occupation</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.hoursOfWorkDaily">Hours Of Work Daily</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.financialStatus">Financial Status</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.city">City</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.useOfMedicine">Use Of Medicine</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.medicines">Medicines</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.haveAllergy">Have Allergy</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.allergy">Allergy</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.haveDisease">Have Disease</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.bloodType">Blood Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.bodyComposition">Body Composition</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.medicalProfile.diseases">Diseases</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {medicalProfileList.map((medicalProfile, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${medicalProfile.id}`} color="link" size="sm">
                      {medicalProfile.id}
                    </Button>
                  </td>
                  <td>{medicalProfile.haveLostWeight ? 'true' : 'false'}</td>
                  <td>{medicalProfile.drowsiness ? 'true' : 'false'}</td>
                  <td>{medicalProfile.goodNightSleep ? 'true' : 'false'}</td>
                  <td>{medicalProfile.hoursOfTraningPerWeek}</td>
                  <td>{medicalProfile.loveForSugar ? 'true' : 'false'}</td>
                  <td>{medicalProfile.overEating ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`adjustApp.MaritalStatus.${medicalProfile.maritalStatus}`} />
                  </td>
                  <td>{medicalProfile.childrenNumber}</td>
                  <td>
                    <Translate contentKey={`adjustApp.Education.${medicalProfile.education}`} />
                  </td>
                  <td>
                    <Translate contentKey={`adjustApp.Occupation.${medicalProfile.occupation}`} />
                  </td>
                  <td>{medicalProfile.hoursOfWorkDaily}</td>
                  <td>
                    <Translate contentKey={`adjustApp.FinancialStatus.${medicalProfile.financialStatus}`} />
                  </td>
                  <td>{medicalProfile.city}</td>
                  <td>{medicalProfile.useOfMedicine ? 'true' : 'false'}</td>
                  <td>{medicalProfile.medicines}</td>
                  <td>{medicalProfile.haveAllergy ? 'true' : 'false'}</td>
                  <td>{medicalProfile.allergy}</td>
                  <td>{medicalProfile.haveDisease ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`adjustApp.BloodType.${medicalProfile.bloodType}`} />
                  </td>
                  <td>
                    {medicalProfile.bodyCompositionId ? (
                      <Link to={`body-composition/${medicalProfile.bodyCompositionId}`}>{medicalProfile.bodyCompositionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {medicalProfile.diseases
                      ? medicalProfile.diseases.map((val, j) => (
                          <span key={j}>
                            <Link to={`disease/${val.id}`}>{val.id}</Link>
                            {j === medicalProfile.diseases.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${medicalProfile.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${medicalProfile.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${medicalProfile.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.medicalProfile.home.notFound">No Medical Profiles found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ medicalProfile }: IRootState) => ({
  medicalProfileList: medicalProfile.entities,
  loading: medicalProfile.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MedicalProfile);
