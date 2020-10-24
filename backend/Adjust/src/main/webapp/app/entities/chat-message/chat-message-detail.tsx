import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './chat-message.reducer';
import { IChatMessage } from 'app/shared/model/chat-message.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IChatMessageDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ChatMessageDetail = (props: IChatMessageDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { chatMessageEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.chatMessage.detail.title">ChatMessage</Translate> [<b>{chatMessageEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="sender">
              <Translate contentKey="adjustApp.chatMessage.sender">Sender</Translate>
            </span>
          </dt>
          <dd>{chatMessageEntity.sender}</dd>
          <dt>
            <span id="receiver">
              <Translate contentKey="adjustApp.chatMessage.receiver">Receiver</Translate>
            </span>
          </dt>
          <dd>{chatMessageEntity.receiver}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="adjustApp.chatMessage.text">Text</Translate>
            </span>
          </dt>
          <dd>{chatMessageEntity.text}</dd>
          <dt>
            <span id="image">
              <Translate contentKey="adjustApp.chatMessage.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {chatMessageEntity.image ? (
              <div>
                {chatMessageEntity.imageContentType ? (
                  <a onClick={openFile(chatMessageEntity.imageContentType, chatMessageEntity.image)}>
                    <img
                      src={`data:${chatMessageEntity.imageContentType};base64,${chatMessageEntity.image}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {chatMessageEntity.imageContentType}, {byteSize(chatMessageEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="voice">
              <Translate contentKey="adjustApp.chatMessage.voice">Voice</Translate>
            </span>
          </dt>
          <dd>
            {chatMessageEntity.voice ? (
              <div>
                {chatMessageEntity.voiceContentType ? (
                  <a onClick={openFile(chatMessageEntity.voiceContentType, chatMessageEntity.voice)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {chatMessageEntity.voiceContentType}, {byteSize(chatMessageEntity.voice)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="seen">
              <Translate contentKey="adjustApp.chatMessage.seen">Seen</Translate>
            </span>
          </dt>
          <dd>{chatMessageEntity.seen ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="adjustApp.chatMessage.conversation">Conversation</Translate>
          </dt>
          <dd>{chatMessageEntity.conversationId ? chatMessageEntity.conversationId : ''}</dd>
        </dl>
        <Button tag={Link} to="/chat-message" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/chat-message/${chatMessageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ chatMessage }: IRootState) => ({
  chatMessageEntity: chatMessage.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ChatMessageDetail);
