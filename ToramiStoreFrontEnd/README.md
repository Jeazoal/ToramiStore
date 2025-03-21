## Sistema De Venta Torami Store

- Gestión de Productos
- Gestión de Ventas
- Gestión de Usuarios

## Organización de carpetas del proyecto

Esta estructura sigue el patrón Modular y se apoya en la arquitectura **Core-Feature-Shared**.

```
src/
├── app/
│   ├── core/               # Core Module (singleton services, global components/directives)
│   │   ├── interceptors/   # Interceptors para HTTP requests
│   │   ├── guards/         # Route guards
│   │   ├── services/       # Singleton services (compartido con toda la aplicaciónshared across the app)
│   │   └── core.module.ts  # CoreModule
│   ├── shared/             # SharedModule (compartido components, pipes, directives)
│   │   ├── components/     # Compartido components
│   │   ├── directives/     # Compartido directives
│   │   ├── pipes/          # Compartido pipes
│   │   └── shared.module.ts# SharedModule
│   ├── features/           # Feature modules
│   │   └── gestion-productos/  # gestion-productos module
│   │       ├── domain/     # Interfaces y models
│   │       │   └── index.ts
│   │       ├── services/   # Services relacionados a este modulo
│   │       ├── presentation/
│   │       │   ├── components/ # UI componentes(ejem: formulario, buscador, tarjetas, grillas)
│   │       │   └── pages/      # Páginas principales llaman a componentes
│   │       └── torami-web-routing.module.ts # Module
│   ├── app-routing.module.ts # App routing module
│   ├── app.component.ts      # Root component
│   └── app.module.ts         # Root module
├── assets/                   # Recursos del proyecto (images, styles, etc.)
│
└── environments/        # Configuraciones de entorno
        ├── environment.ts
        └── environment.prod.ts
```

### Configuraciones Generales

1. Router paths:

   > Ejemplo: Rutas para la página web de Torami Store

   - **app-routing-module.ts** : Agregar el módulo creado a las rutas

   ```
   const routes: Routes = [
   {
       path: '',
       component: FullComponent,
       children: [
       {
           path: '',
           redirectTo: '/dashboards/dashboard1',
           pathMatch: 'full',
       },
       {
           path: "toramistore",
           loadChildren: () =>
           import('./modules/torami-web/torami-web.module').then((m) => m.ToramiWebModule),
       },
       ]
   }]
   ```

   - Generar para el módulo su router: **torami-web-routing.module.ts**

   ```
   export const ToramiWebRoutingModule: Routes = [
   {
       path: '',
       children: [
           {path: '',component: PaginaPrincipalComponent, title: 'Torami Store'},
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
       ]
   }]
   ```

   > La apartado data: es para el título de la página y link del breadcrumbs (opcional)

   - Vincular el routing al modulo: **torami-web.module.ts**
     **RouterModule.forChild(ToramiWebRoutingModule)**

   ```
   @NgModule({
   imports: [
       CommonModule,
       RouterModule.forChild(ToramiWebRoutingModule),
   ],})
   ```

   -Sidenav: Link a una url

   - src > app > layouts > full > vertical > sidebar > **sidebar-data.ts**

   ```
   {
       displayName: 'Listado Productos',
       iconName: 'apps',
       route: 'apps/productos',
   },
   ```

2. Environments:

   - Lista de rutas Endpoint del backend o externas con las que interactua la aplicación
   - Archivo: src > environments > **environments.ts**
   - Contenido debe replicarse a **environment.prod.ts**

3. Modificar Clases CSS Angular Material

   - Rutas archivo: src > assets > themecolors > **\_aqua_theme.scss**
   - Agregar el CSS Correspondiente

4. Creación de CSS

   - Utilizar metodología **BEM Bloque - Element - Modificador** :

   ```html
   <div class="block">
     <div class="block__element">Elem 1</div>
     <div class="block__element">Elem 2</div>
     <div class="block__element block__element--modifier">Elem 3</div>
   </div>
   ```

5. Css Genericos :

   > Como ejemplo y otras combinaciones

   - **f-s-10** : font size 10px
   - **p-10** : padding 10px
   - **p-t-15** : padding top 15px
   - **m-l-20** : margin-left-20px

### Comandos generar Modulo, componente y Servicio

> Siguiendo al estructura de carpetas indicada en el apartado anterior.

- Modulo : **ng g m modules/<nombre-modulo>**
- Componente: **ng g c modules/<nombre-modulo>/presentation/components/<nombre-componente-crear>**
- Servicio : **ng g s modules/<nombre-modulo>/services/<nombre-servicio>**
