import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MealsForNutrition from './meals-for-nutrition';
import MealsForNutritionDetail from './meals-for-nutrition-detail';
import MealsForNutritionUpdate from './meals-for-nutrition-update';
import MealsForNutritionDeleteDialog from './meals-for-nutrition-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MealsForNutritionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MealsForNutritionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MealsForNutritionDetail} />
      <ErrorBoundaryRoute path={match.url} component={MealsForNutrition} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MealsForNutritionDeleteDialog} />
  </>
);

export default Routes;
