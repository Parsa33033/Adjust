import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustToken from './adjust-token';
import AdjustTokenDetail from './adjust-token-detail';
import AdjustTokenUpdate from './adjust-token-update';
import AdjustTokenDeleteDialog from './adjust-token-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AdjustTokenUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AdjustTokenUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AdjustTokenDetail} />
      <ErrorBoundaryRoute path={match.url} component={AdjustToken} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AdjustTokenDeleteDialog} />
  </>
);

export default Routes;
