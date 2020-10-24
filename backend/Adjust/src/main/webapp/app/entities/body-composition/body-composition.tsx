import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './body-composition.reducer';
import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBodyCompositionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const BodyComposition = (props: IBodyCompositionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { bodyCompositionList, match, loading } = props;
  return (
    <div>
      <h2 id="body-composition-heading">
        <Translate contentKey="adjustApp.bodyComposition.home.title">Body Compositions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.bodyComposition.home.createLabel">Create new Body Composition</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {bodyCompositionList && bodyCompositionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.age">Age</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.gender">Gender</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.height">Height</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.weight">Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.wrist">Wrist</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.waist">Waist</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.hip">Hip</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.abdomen">Abdomen</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.neck">Neck</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.waistHipRatio">Waist Hip Ratio</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.idealWeightMin">Ideal Weight Min</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.idealWeightMax">Ideal Weight Max</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bestWeight">Best Weight</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bmi">Bmi</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bmiCategory">Bmi Category</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bmr">Bmr</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.lbm">Lbm</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.pbf">Pbf</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bodyFatCategory">Body Fat Category</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.smm">Smm</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bodyTypeNumber">Body Type Number</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bodyType">Body Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.frameSize">Frame Size</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.bodyFrameSize">Body Frame Size</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.visceralFatLevel">Visceral Fat Level</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.workoutLocation">Workout Location</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.goal">Goal</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.activityType">Activity Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.bodyComposition.workoutType">Workout Type</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bodyCompositionList.map((bodyComposition, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${bodyComposition.id}`} color="link" size="sm">
                      {bodyComposition.id}
                    </Button>
                  </td>
                  <td>
                    {bodyComposition.createdAt ? (
                      <TextFormat type="date" value={bodyComposition.createdAt} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{bodyComposition.age}</td>
                  <td>
                    <Translate contentKey={`adjustApp.Gender.${bodyComposition.gender}`} />
                  </td>
                  <td>{bodyComposition.height}</td>
                  <td>{bodyComposition.weight}</td>
                  <td>{bodyComposition.wrist}</td>
                  <td>{bodyComposition.waist}</td>
                  <td>{bodyComposition.hip}</td>
                  <td>{bodyComposition.abdomen}</td>
                  <td>{bodyComposition.neck}</td>
                  <td>{bodyComposition.waistHipRatio}</td>
                  <td>{bodyComposition.idealWeightMin}</td>
                  <td>{bodyComposition.idealWeightMax}</td>
                  <td>{bodyComposition.bestWeight}</td>
                  <td>{bodyComposition.bmi}</td>
                  <td>
                    <Translate contentKey={`adjustApp.BMICategory.${bodyComposition.bmiCategory}`} />
                  </td>
                  <td>{bodyComposition.bmr}</td>
                  <td>{bodyComposition.lbm}</td>
                  <td>{bodyComposition.pbf}</td>
                  <td>
                    <Translate contentKey={`adjustApp.BodyFatCategory.${bodyComposition.bodyFatCategory}`} />
                  </td>
                  <td>{bodyComposition.smm}</td>
                  <td>{bodyComposition.bodyTypeNumber}</td>
                  <td>
                    <Translate contentKey={`adjustApp.BodyType.${bodyComposition.bodyType}`} />
                  </td>
                  <td>{bodyComposition.frameSize}</td>
                  <td>
                    <Translate contentKey={`adjustApp.BodyFrameSize.${bodyComposition.bodyFrameSize}`} />
                  </td>
                  <td>{bodyComposition.visceralFatLevel}</td>
                  <td>
                    <Translate contentKey={`adjustApp.WorkoutLocation.${bodyComposition.workoutLocation}`} />
                  </td>
                  <td>
                    <Translate contentKey={`adjustApp.Goal.${bodyComposition.goal}`} />
                  </td>
                  <td>
                    <Translate contentKey={`adjustApp.ActivityType.${bodyComposition.activityType}`} />
                  </td>
                  <td>
                    <Translate contentKey={`adjustApp.WorkoutType.${bodyComposition.workoutType}`} />
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bodyComposition.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bodyComposition.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bodyComposition.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.bodyComposition.home.notFound">No Body Compositions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ bodyComposition }: IRootState) => ({
  bodyCompositionList: bodyComposition.entities,
  loading: bodyComposition.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BodyComposition);
