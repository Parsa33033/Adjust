import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './adjust-token.reducer';
import { IAdjustToken } from 'app/shared/model/adjust-token.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAdjustTokenUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTokenUpdate = (props: IAdjustTokenUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { adjustTokenEntity, loading, updating } = props;

  const { description, image, imageContentType } = adjustTokenEntity;

  const handleClose = () => {
    props.history.push('/adjust-token');
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
        ...adjustTokenEntity,
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
          <h2 id="adjustApp.adjustToken.home.createOrEditLabel">
            <Translate contentKey="adjustApp.adjustToken.home.createOrEditLabel">Create or edit a AdjustToken</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : adjustTokenEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="adjust-token-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="adjust-token-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="adjust-token-name">
                  <Translate contentKey="adjustApp.adjustToken.name">Name</Translate>
                </Label>
                <AvField id="adjust-token-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="adjust-token-description">
                  <Translate contentKey="adjustApp.adjustToken.description">Description</Translate>
                </Label>
                <AvInput id="adjust-token-description" type="textarea" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="tokenLabel" for="adjust-token-token">
                  <Translate contentKey="adjustApp.adjustToken.token">Token</Translate>
                </Label>
                <AvField id="adjust-token-token" type="string" className="form-control" name="token" />
              </AvGroup>
              <AvGroup>
                <Label id="priceLabel" for="adjust-token-price">
                  <Translate contentKey="adjustApp.adjustToken.price">Price</Translate>
                </Label>
                <AvField id="adjust-token-price" type="string" className="form-control" name="price" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="imageLabel" for="image">
                    <Translate contentKey="adjustApp.adjustToken.image">Image</Translate>
                  </Label>
                  <br />
                  {image ? (
                    <div>
                      {imageContentType ? (
                        <a onClick={openFile(imageContentType, image)}>
                          <img src={`data:${imageContentType};base64,${image}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {imageContentType}, {byteSize(image)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('image')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_image" type="file" onChange={onBlobChange(true, 'image')} accept="image/*" />
                  <AvInput type="hidden" name="image" value={image} />
                </AvGroup>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/adjust-token" replace color="info">
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
  adjustTokenEntity: storeState.adjustToken.entity,
  loading: storeState.adjustToken.loading,
  updating: storeState.adjustToken.updating,
  updateSuccess: storeState.adjustToken.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTokenUpdate);
