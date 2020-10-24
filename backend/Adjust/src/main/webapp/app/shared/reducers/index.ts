import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import adjustClient, {
  AdjustClientState
} from 'app/entities/adjust-client/adjust-client.reducer';
// prettier-ignore
import medicalProfile, {
  MedicalProfileState
} from 'app/entities/medical-profile/medical-profile.reducer';
// prettier-ignore
import disease, {
  DiseaseState
} from 'app/entities/disease/disease.reducer';
// prettier-ignore
import adjustToken, {
  AdjustTokenState
} from 'app/entities/adjust-token/adjust-token.reducer';
// prettier-ignore
import adjustPrice, {
  AdjustPriceState
} from 'app/entities/adjust-price/adjust-price.reducer';
// prettier-ignore
import order, {
  OrderState
} from 'app/entities/order/order.reducer';
// prettier-ignore
import orderItem, {
  OrderItemState
} from 'app/entities/order-item/order-item.reducer';
// prettier-ignore
import shoppingItem, {
  ShoppingItemState
} from 'app/entities/shopping-item/shopping-item.reducer';
// prettier-ignore
import specialist, {
  SpecialistState
} from 'app/entities/specialist/specialist.reducer';
// prettier-ignore
import adjustProgram, {
  AdjustProgramState
} from 'app/entities/adjust-program/adjust-program.reducer';
// prettier-ignore
import programDescription, {
  ProgramDescriptionState
} from 'app/entities/program-description/program-description.reducer';
// prettier-ignore
import development, {
  DevelopmentState
} from 'app/entities/development/development.reducer';
// prettier-ignore
import bodyComposition, {
  BodyCompositionState
} from 'app/entities/body-composition/body-composition.reducer';
// prettier-ignore
import fitnessProgram, {
  FitnessProgramState
} from 'app/entities/fitness-program/fitness-program.reducer';
// prettier-ignore
import workout, {
  WorkoutState
} from 'app/entities/workout/workout.reducer';
// prettier-ignore
import exercise, {
  ExerciseState
} from 'app/entities/exercise/exercise.reducer';
// prettier-ignore
import move, {
  MoveState
} from 'app/entities/move/move.reducer';
// prettier-ignore
import nutritionProgram, {
  NutritionProgramState
} from 'app/entities/nutrition-program/nutrition-program.reducer';
// prettier-ignore
import meal, {
  MealState
} from 'app/entities/meal/meal.reducer';
// prettier-ignore
import nutrition, {
  NutritionState
} from 'app/entities/nutrition/nutrition.reducer';
// prettier-ignore
import food, {
  FoodState
} from 'app/entities/food/food.reducer';
// prettier-ignore
import mealsForNutrition, {
  MealsForNutritionState
} from 'app/entities/meals-for-nutrition/meals-for-nutrition.reducer';
// prettier-ignore
import conversation, {
  ConversationState
} from 'app/entities/conversation/conversation.reducer';
// prettier-ignore
import chatMessage, {
  ChatMessageState
} from 'app/entities/chat-message/chat-message.reducer';
// prettier-ignore
import course, {
  CourseState
} from 'app/entities/course/course.reducer';
// prettier-ignore
import tutorial, {
  TutorialState
} from 'app/entities/tutorial/tutorial.reducer';
// prettier-ignore
import tutorialVideo, {
  TutorialVideoState
} from 'app/entities/tutorial-video/tutorial-video.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly adjustClient: AdjustClientState;
  readonly medicalProfile: MedicalProfileState;
  readonly disease: DiseaseState;
  readonly adjustToken: AdjustTokenState;
  readonly adjustPrice: AdjustPriceState;
  readonly order: OrderState;
  readonly orderItem: OrderItemState;
  readonly shoppingItem: ShoppingItemState;
  readonly specialist: SpecialistState;
  readonly adjustProgram: AdjustProgramState;
  readonly programDescription: ProgramDescriptionState;
  readonly development: DevelopmentState;
  readonly bodyComposition: BodyCompositionState;
  readonly fitnessProgram: FitnessProgramState;
  readonly workout: WorkoutState;
  readonly exercise: ExerciseState;
  readonly move: MoveState;
  readonly nutritionProgram: NutritionProgramState;
  readonly meal: MealState;
  readonly nutrition: NutritionState;
  readonly food: FoodState;
  readonly mealsForNutrition: MealsForNutritionState;
  readonly conversation: ConversationState;
  readonly chatMessage: ChatMessageState;
  readonly course: CourseState;
  readonly tutorial: TutorialState;
  readonly tutorialVideo: TutorialVideoState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  adjustClient,
  medicalProfile,
  disease,
  adjustToken,
  adjustPrice,
  order,
  orderItem,
  shoppingItem,
  specialist,
  adjustProgram,
  programDescription,
  development,
  bodyComposition,
  fitnessProgram,
  workout,
  exercise,
  move,
  nutritionProgram,
  meal,
  nutrition,
  food,
  mealsForNutrition,
  conversation,
  chatMessage,
  course,
  tutorial,
  tutorialVideo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
