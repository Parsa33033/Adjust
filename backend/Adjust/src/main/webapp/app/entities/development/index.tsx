import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Development from './development';
import DevelopmentDetail from './development-detail';
import DevelopmentUpdate from './development-update';
import DevelopmentDeleteDialog from './development-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DevelopmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DevelopmentUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DevelopmentDetail} />
      <ErrorBoundaryRoute path={match.url} component={Development} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DevelopmentDeleteDialog} />
  </>
);

export default Routes;
