import { NavItem } from './nav-item/nav-item';

export const navItems: NavItem[] = [
  {
    navCap: 'Inicio',
  },
  {
    displayName: 'Dashboard',
    iconName: 'chart-pie',
    route: '/dashboards/dashboard1',
  },
  {
    navCap: 'Gestión De Productos',
  },
  {
    displayName: 'Listado Productos',
    iconName: 'apps',
    route: 'apps/productos',
  },
  {
    displayName: 'Proveedores',
    iconName: 'user-hexagon',
    route: 'apps/proveedor',
  },
  {
    displayName: 'Reporte de ventas',
    iconName: 'calendar-event',
    route: 'apps/reportes',
  },
  {
    navCap: 'Administración de usuarios',
  },
  {
    displayName: 'Usuarios',
    iconName: 'user-circle',
    route: 'apps/employee',
  },
  {
    displayName: 'Permisos',
    iconName: 'brand-ctemplar',
    route: 'apps/permisos',
  },
  // {
  //   displayName: 'Notas',
  //   iconName: 'note',
  //   route: 'apps/notes',
  // },
  {
    navCap: 'Cerrar Sesión',
  },
  {
    displayName: 'Login',
    iconName: 'login',
    route: '/authentication/side-login',
  },
  // {
  //   displayName: 'Error',
  //   iconName: 'alert-circle',
  //   route: '/authentication/error',
  // },
];
