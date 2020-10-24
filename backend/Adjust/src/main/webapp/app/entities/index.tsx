import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AdjustClient from './adjust-client';
import MedicalProfile from './medical-profile';
import Disease from './disease';
import AdjustToken from './adjust-token';
import AdjustPrice from './adjust-price';
import Order from './order';
import OrderItem from './order-item';
import ShoppingItem from './shopping-item';
import Specialist from './specialist';
import AdjustProgram from './adjust-program';
import ProgramDescription from './program-description';
import Development from './development';
import BodyComposition from './body-composition';
import FitnessProgram from './fitness-program';
import Workout from './workout';
import Exercise from './exercise';
import Move from './move';
import NutritionProgram from './nutrition-program';
import Meal from './meal';
import Nutrition from './nutrition';
import Food from './food';
import MealsForNutrition from './meals-for-nutrition';
import Conversation from './conversation';
import ChatMessage from './chat-message';
import Course from './course';
import Tutorial from './tutorial';
import TutorialVideo from './tutorial-video';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}adjust-client`} component={AdjustClient} />
      <ErrorBoundaryRoute path={`${match.url}medical-profile`} component={MedicalProfile} />
      <ErrorBoundaryRoute path={`${match.url}disease`} component={Disease} />
      <ErrorBoundaryRoute path={`${match.url}adjust-token`} component={AdjustToken} />
      <ErrorBoundaryRoute path={`${match.url}adjust-price`} component={AdjustPrice} />
      <ErrorBoundaryRoute path={`${match.url}order`} component={Order} />
      <ErrorBoundaryRoute path={`${match.url}order-item`} component={OrderItem} />
      <ErrorBoundaryRoute path={`${match.url}shopping-item`} component={ShoppingItem} />
      <ErrorBoundaryRoute path={`${match.url}specialist`} component={Specialist} />
      <ErrorBoundaryRoute path={`${match.url}adjust-program`} component={AdjustProgram} />
      <ErrorBoundaryRoute path={`${match.url}program-description`} component={ProgramDescription} />
      <ErrorBoundaryRoute path={`${match.url}development`} component={Development} />
      <ErrorBoundaryRoute path={`${match.url}body-composition`} component={BodyComposition} />
      <ErrorBoundaryRoute path={`${match.url}fitness-program`} component={FitnessProgram} />
      <ErrorBoundaryRoute path={`${match.url}workout`} component={Workout} />
      <ErrorBoundaryRoute path={`${match.url}exercise`} component={Exercise} />
      <ErrorBoundaryRoute path={`${match.url}move`} component={Move} />
      <ErrorBoundaryRoute path={`${match.url}nutrition-program`} component={NutritionProgram} />
      <ErrorBoundaryRoute path={`${match.url}meal`} component={Meal} />
      <ErrorBoundaryRoute path={`${match.url}nutrition`} component={Nutrition} />
      <ErrorBoundaryRoute path={`${match.url}food`} component={Food} />
      <ErrorBoundaryRoute path={`${match.url}meals-for-nutrition`} component={MealsForNutrition} />
      <ErrorBoundaryRoute path={`${match.url}conversation`} component={Conversation} />
      <ErrorBoundaryRoute path={`${match.url}chat-message`} component={ChatMessage} />
      <ErrorBoundaryRoute path={`${match.url}course`} component={Course} />
      <ErrorBoundaryRoute path={`${match.url}tutorial`} component={Tutorial} />
      <ErrorBoundaryRoute path={`${match.url}tutorial-video`} component={TutorialVideo} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
