import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProgramDescription from './program-description';
import ProgramDescriptionDetail from './program-description-detail';
import ProgramDescriptionUpdate from './program-description-update';
import ProgramDescriptionDeleteDialog from './program-description-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProgramDescriptionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProgramDescriptionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProgramDescriptionDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProgramDescription} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProgramDescriptionDeleteDialog} />
  </>
);

export default Routes;
