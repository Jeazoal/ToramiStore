import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../../material.module';

// icons
import { TablerIconsModule } from 'angular-tabler-icons';
import * as TablerIcons from 'angular-tabler-icons/icons';

import { UiComponentsRoutes } from './ui-components.routing';

// ui components
import { AppBadgeComponent } from './badge/badge.component';
import {
  AppDialogComponent,
  AppDialogContentComponent,
  AppDialogDataComponent,
  AppDialogMenuComponent,
  AppDialogOverviewComponent,
} from './dialog/dialog.component';

import { AppSlideToggleComponent } from './slide-toggle/slide-toggle.component';
import { AppSliderComponent } from './slider/slider.component';
import { AppTabsComponent } from './tabs/tabs.component';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(UiComponentsRoutes),
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    TablerIconsModule.pick(TablerIcons),
    MatNativeDateModule
  ],
  declarations: [
    AppBadgeComponent,
    AppDialogComponent,
    AppDialogContentComponent,
    AppDialogDataComponent,
    AppDialogMenuComponent,
    AppDialogOverviewComponent,
    AppSlideToggleComponent,
    AppSliderComponent,
    AppTabsComponent
  ],
})
export class UicomponentsModule {}
