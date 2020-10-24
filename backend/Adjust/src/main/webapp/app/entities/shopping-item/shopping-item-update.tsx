import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './shopping-item.reducer';
import { IShoppingItem } from 'app/shared/model/shopping-item.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IShoppingItemUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ShoppingItemUpdate = (props: IShoppingItemUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { shoppingItemEntity, loading, updating } = props;

  const { description, image, imageContentType } = shoppingItemEntity;

  const handleClose = () => {
    props.history.push('/shopping-item');
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
        ...shoppingItemEntity,
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
          <h2 id="adjustApp.shoppingItem.home.createOrEditLabel">
            <Translate contentKey="adjustApp.shoppingItem.home.createOrEditLabel">Create or edit a ShoppingItem</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : shoppingItemEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="shopping-item-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="shopping-item-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="shopping-item-name">
                  <Translate contentKey="adjustApp.shoppingItem.name">Name</Translate>
                </Label>
                <AvField id="shopping-item-name" type="text" name="name" />
              </AvGroup>
              <AvGroup>
                <Label id="itemIdLabel" for="shopping-item-itemId">
                  <Translate contentKey="adjustApp.shoppingItem.itemId">Item Id</Translate>
                </Label>
                <AvField id="shopping-item-itemId" type="string" className="form-control" name="itemId" />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="shopping-item-description">
                  <Translate contentKey="adjustApp.shoppingItem.description">Description</Translate>
                </Label>
                <AvInput id="shopping-item-description" type="textarea" name="description" />
              </AvGroup>
              <AvGroup>
                <Label id="priceLabel" for="shopping-item-price">
                  <Translate contentKey="adjustApp.shoppingItem.price">Price</Translate>
                </Label>
                <AvField id="shopping-item-price" type="string" className="form-control" name="price" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="imageLabel" for="image">
                    <Translate contentKey="adjustApp.shoppingItem.image">Image</Translate>
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
              <AvGroup check>
                <Label id="orderableLabel">
                  <AvInput id="shopping-item-orderable" type="checkbox" className="form-check-input" name="orderable" />
                  <Translate contentKey="adjustApp.shoppingItem.orderable">Orderable</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="numberLabel" for="shopping-item-number">
                  <Translate contentKey="adjustApp.shoppingItem.number">Number</Translate>
                </Label>
                <AvField id="shopping-item-number" type="string" className="form-control" name="number" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/shopping-item" replace color="info">
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
  shoppingItemEntity: storeState.shoppingItem.entity,
  loading: storeState.shoppingItem.loading,
  updating: storeState.shoppingItem.updating,
  updateSuccess: storeState.shoppingItem.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingItemUpdate);
