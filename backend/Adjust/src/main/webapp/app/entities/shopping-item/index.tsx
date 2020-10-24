import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ShoppingItem from './shopping-item';
import ShoppingItemDetail from './shopping-item-detail';
import ShoppingItemUpdate from './shopping-item-update';
import ShoppingItemDeleteDialog from './shopping-item-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ShoppingItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ShoppingItemUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ShoppingItemDetail} />
      <ErrorBoundaryRoute path={match.url} component={ShoppingItem} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ShoppingItemDeleteDialog} />
  </>
);

export default Routes;
