import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './course.reducer';
import { ICourse } from 'app/shared/model/course.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICourseProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Course = (props: ICourseProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { courseList, match, loading } = props;
  return (
    <div>
      <h2 id="course-heading">
        <Translate contentKey="adjustApp.course.home.title">Courses</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.course.home.createLabel">Create new Course</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {courseList && courseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.course.title">Title</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.course.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.course.thumbnail">Thumbnail</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {courseList.map((course, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${course.id}`} color="link" size="sm">
                      {course.id}
                    </Button>
                  </td>
                  <td>{course.title}</td>
                  <td>{course.description}</td>
                  <td>
                    {course.thumbnail ? (
                      <div>
                        {course.thumbnailContentType ? (
                          <a onClick={openFile(course.thumbnailContentType, course.thumbnail)}>
                            <img src={`data:${course.thumbnailContentType};base64,${course.thumbnail}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {course.thumbnailContentType}, {byteSize(course.thumbnail)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${course.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${course.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${course.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="adjustApp.course.home.notFound">No Courses found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ course }: IRootState) => ({
  courseList: course.entities,
  loading: course.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Course);
