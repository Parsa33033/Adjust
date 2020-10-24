import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/adjust-client">
      <Translate contentKey="global.menu.entities.adjustClient" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/medical-profile">
      <Translate contentKey="global.menu.entities.medicalProfile" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/disease">
      <Translate contentKey="global.menu.entities.disease" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/adjust-token">
      <Translate contentKey="global.menu.entities.adjustToken" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/adjust-price">
      <Translate contentKey="global.menu.entities.adjustPrice" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/order">
      <Translate contentKey="global.menu.entities.order" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/order-item">
      <Translate contentKey="global.menu.entities.orderItem" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/shopping-item">
      <Translate contentKey="global.menu.entities.shoppingItem" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/specialist">
      <Translate contentKey="global.menu.entities.specialist" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/adjust-program">
      <Translate contentKey="global.menu.entities.adjustProgram" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/program-description">
      <Translate contentKey="global.menu.entities.programDescription" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/development">
      <Translate contentKey="global.menu.entities.development" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/body-composition">
      <Translate contentKey="global.menu.entities.bodyComposition" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/fitness-program">
      <Translate contentKey="global.menu.entities.fitnessProgram" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/workout">
      <Translate contentKey="global.menu.entities.workout" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/exercise">
      <Translate contentKey="global.menu.entities.exercise" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/move">
      <Translate contentKey="global.menu.entities.move" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/nutrition-program">
      <Translate contentKey="global.menu.entities.nutritionProgram" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/meal">
      <Translate contentKey="global.menu.entities.meal" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/nutrition">
      <Translate contentKey="global.menu.entities.nutrition" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/food">
      <Translate contentKey="global.menu.entities.food" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/meals-for-nutrition">
      <Translate contentKey="global.menu.entities.mealsForNutrition" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/conversation">
      <Translate contentKey="global.menu.entities.conversation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/chat-message">
      <Translate contentKey="global.menu.entities.chatMessage" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/course">
      <Translate contentKey="global.menu.entities.course" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tutorial">
      <Translate contentKey="global.menu.entities.tutorial" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tutorial-video">
      <Translate contentKey="global.menu.entities.tutorialVideo" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
