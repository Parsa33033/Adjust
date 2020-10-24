import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './program-description.reducer';
import { IProgramDescription } from 'app/shared/model/program-description.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IProgramDescriptionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProgramDescriptionUpdate = (props: IProgramDescriptionUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { programDescriptionEntity, loading, updating } = props;

  const { text } = programDescriptionEntity;

  const handleClose = () => {
    props.history.push('/program-description');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...programDescriptionEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.programDescription.home.createOrEditLabel">
            <Translate contentKey="adjustApp.programDescription.home.createOrEditLabel">Create or edit a ProgramDescription</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : programDescriptionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="program-description-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="program-description-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="textLabel" for="program-description-text">
                  <Translate contentKey="adjustApp.programDescription.text">Text</Translate>
                </Label>
                <AvInput id="program-description-text" type="textarea" name="text" />
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="program-description-type">
                  <Translate contentKey="adjustApp.programDescription.type">Type</Translate>
                </Label>
                <AvInput
                  id="program-description-type"
                  type="select"
                  className="form-control"
                  name="type"
                  value={(!isNew && programDescriptionEntity.type) || 'NUTRITION'}
                >
                  <option value="NUTRITION">{translate('adjustApp.ProgramType.NUTRITION')}</option>
                  <option value="FITNESS">{translate('adjustApp.ProgramType.FITNESS')}</option>
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/program-description" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  programDescriptionEntity: storeState.programDescription.entity,
  loading: storeState.programDescription.loading,
  updating: storeState.programDescription.updating,
  updateSuccess: storeState.programDescription.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProgramDescriptionUpdate);
