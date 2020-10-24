import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Food from './food';
import FoodDetail from './food-detail';
import FoodUpdate from './food-update';
import FoodDeleteDialog from './food-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={FoodUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={FoodUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={FoodDetail} />
      <ErrorBoundaryRoute path={match.url} component={Food} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={FoodDeleteDialog} />
  </>
);

export default Routes;
