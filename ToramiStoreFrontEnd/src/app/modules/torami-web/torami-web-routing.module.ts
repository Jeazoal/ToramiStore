import { Routes } from '@angular/router';
import { PaginaPrincipalComponent } from './presentation/pages/pagina-principal/pagina-principal.component';

const routes: Routes = [];

export const ToramiWebRoutingModule: Routes = [
  {
    path: '',
    children: [
      { path: '', component: PaginaPrincipalComponent, title: 'Torami Store' },
      {
        path: 'toramiStore',
        component: PaginaPrincipalComponent,
        data: {
          title: 'Torami Store',
          urls: [
            { title: 'Torami Store', url: '/torami-store/' },
            { title: 'Torami Store' },
          ],
        },
      },
    ],
  },
];
