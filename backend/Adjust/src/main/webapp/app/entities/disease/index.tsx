import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Disease from './disease';
import DiseaseDetail from './disease-detail';
import DiseaseUpdate from './disease-update';
import DiseaseDeleteDialog from './disease-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={DiseaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={DiseaseUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={DiseaseDetail} />
      <ErrorBoundaryRoute path={match.url} component={Disease} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={DiseaseDeleteDialog} />
  </>
);

export default Routes;
