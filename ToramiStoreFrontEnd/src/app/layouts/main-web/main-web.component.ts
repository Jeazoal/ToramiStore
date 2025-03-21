import { BreakpointObserver, MediaMatcher } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { HeaderComponent } from '../full/vertical/header/header.component';
import { SidebarComponent } from '../full/vertical/sidebar/sidebar.component';
import { AppBreadcrumbComponent } from '../full/shared/breadcrumb/breadcrumb.component';
import { MaterialModule } from 'src/app/material.module';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppNavItemComponent } from '../full/vertical/sidebar/nav-item/nav-item.component';
@Component({
  selector: 'app-main-web',
  standalone: true,
  imports: [
    NgScrollbarModule,
    HeaderComponent,
    SidebarComponent,
    AppBreadcrumbComponent,
    MaterialModule,
    RouterModule,
    CommonModule,
    AppNavItemComponent,
  ],
  templateUrl: './main-web.component.html',
  styleUrl: './main-web.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class MainWebComponent {}
