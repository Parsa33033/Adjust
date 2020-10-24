import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MedicalProfile from './medical-profile';
import MedicalProfileDetail from './medical-profile-detail';
import MedicalProfileUpdate from './medical-profile-update';
import MedicalProfileDeleteDialog from './medical-profile-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MedicalProfileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MedicalProfileUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MedicalProfileDetail} />
      <ErrorBoundaryRoute path={match.url} component={MedicalProfile} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MedicalProfileDeleteDialog} />
  </>
);

export default Routes;
