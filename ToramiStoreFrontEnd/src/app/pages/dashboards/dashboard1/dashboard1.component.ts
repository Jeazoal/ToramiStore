import { Component } from '@angular/core';
import {
  AppSalesOurVisitorsComponent,
  AppSalesOverviewComponent,
  AppTopCardsComponent,
} from 'src/app/components';

@Component({
  selector: 'app-dashboard1',
  standalone: true,
  imports: [
    AppTopCardsComponent,
    AppSalesOverviewComponent,
    AppSalesOurVisitorsComponent,
  ],
  templateUrl: './dashboard1.component.html',
})
export class AppDashboard1Component {
  constructor() {}
}
