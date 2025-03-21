import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ToramiWebRoutingModule } from './torami-web-routing.module';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PaginaPrincipalComponent } from './presentation/pages/pagina-principal/pagina-principal.component';
import { TablerIconsModule } from 'angular-tabler-icons';
import { MaterialModule } from 'src/app/material.module';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { CreateAccountComponent } from './presentation/components/create-account/create-account.component';
import { ProductComponent } from './presentation/components/product/product.component';
import { LoginComponent } from './presentation/components/login/login.component';

@NgModule({
  declarations: [
    PaginaPrincipalComponent,
    LoginComponent,
    CreateAccountComponent,
    ProductComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    TablerIconsModule,
    RouterModule.forChild(ToramiWebRoutingModule),
    TablerIconsModule,
    MaterialModule,
    NgScrollbarModule,
    ReactiveFormsModule,
  ],
})
export class ToramiWebModule {}
