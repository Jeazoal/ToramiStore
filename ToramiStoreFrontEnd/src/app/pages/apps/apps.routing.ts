import { Routes } from '@angular/router';

import { AppNotesComponent } from './notes/notes.component';
import { AppEmployeeComponent } from './employee/employee.component';
import { ProveedoresComponent } from './proveedores/proveedores.component';
import { ReportesComponent } from './reportes/reportes.component';
import { ProductosComponent } from './productos/productos.component';
import { PermisosComponent } from './permisos/permisos.component';

export const AppsRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'notes',
        component: AppNotesComponent,
        data: {
          title: 'Notes',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Notes' },
          ],
        },
      },
      {
        path: 'employee',
        component: AppEmployeeComponent,
        data: {
          title: 'Employee',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Employee' },
          ],
        },
      },
      {
        path: 'proveedor',
        component: ProveedoresComponent,
        data: {
          title: 'Proveedor',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Proveedor' },
          ],
        },
      },
      {
        path: 'reportes',
        component: ReportesComponent,
        data: {
          title: 'Reportes',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Reportes' },
          ],
        },
      },
      {
        path: 'productos',
        component: ProductosComponent,
        data: {
          title: 'Productos',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Productos' },
          ],
        },
      },
      {
        path: 'permisos',
        component: PermisosComponent,
        data: {
          title: 'Permisos',
          urls: [
            { title: 'Dashboard', url: '/dashboards/dashboard1' },
            { title: 'Permisos' },
          ],
        },
      },
    ],
  },
];
