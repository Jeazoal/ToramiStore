import { Routes } from '@angular/router';

// ui
import { AppBadgeComponent } from './badge/badge.component';
import { AppDialogComponent } from './dialog/dialog.component';
import { AppSlideToggleComponent } from './slide-toggle/slide-toggle.component';
import { AppSliderComponent } from './slider/slider.component';
import { AppTabsComponent } from './tabs/tabs.component';

export const UiComponentsRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'badge',
        component: AppBadgeComponent,
        data: {
          title: 'Badge',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Badge' },
          ],
        },
      },

      {
        path: 'dialog',
        component: AppDialogComponent,
        data: {
          title: 'Dialog',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Dialog' },
          ],
        },
      },

      {
        path: 'slide-toggle',
        component: AppSlideToggleComponent,
        data: {
          title: 'Slide Toggle',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Slide Toggle' },
          ],
        },
      },
      {
        path: 'slider',
        component: AppSliderComponent,
        data: {
          title: 'Slider',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Slider' },
          ],
        },
      },
      
      {
        path: 'tabs',
        component: AppTabsComponent,
        data: {
          title: 'Tabs',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Tabs' },
          ],
        },
      },
      
    ],
  },
];
