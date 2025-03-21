import {
  Component,
  Output,
  EventEmitter,
  Input,
  ViewEncapsulation,
} from '@angular/core';
import { CoreService } from 'src/app/services/core.service';
import { MatDialog } from '@angular/material/dialog';
import { navItems } from '../sidebar/sidebar-data';
import { TranslateService } from '@ngx-translate/core';
import { RouterModule } from '@angular/router';
import { MaterialModule } from 'src/app/material.module';
import { TablerIconsModule } from 'angular-tabler-icons';
import { FormsModule } from '@angular/forms';
import { NgScrollbarModule } from 'ngx-scrollbar';
import { BrandingComponent, } from '../sidebar/branding.component';
import { FullscreenService } from 'src/app/services/fullscreen.service';

interface notifications {
  id: number;
  img: string;
  title: string;
  subtitle: string;
}

interface profiledd {
  id: number;
  img: string;
  title: string;
  subtitle: string;
  link: string;
}

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterModule, NgScrollbarModule, TablerIconsModule, MaterialModule, BrandingComponent],
  templateUrl: './header.component.html',
  encapsulation: ViewEncapsulation.None,
})
export class HeaderComponent {
  @Input() showToggle = true;
  @Input() toggleChecked = false;
  @Output() toggleMobileNav = new EventEmitter<void>();
  @Output() toggleMobileFilterNav = new EventEmitter<void>();
  @Output() toggleCollapsed = new EventEmitter<void>();

  showFiller = false;

  constructor(
    private vsidenav: CoreService,
    public dialog: MatDialog,
    private translate: TranslateService,
    private fullscreenService: FullscreenService
  ) {
    translate.setDefaultLang('es');
  }

  toggleFullScreen(): void {
    this.fullscreenService.toggleFullScreen();
  }
  
  notifications: notifications[] = [
    {
      id: 1,
      img: '/assets/images/profile/user-1.jpg',
      title: 'Diego se unio al equipo!',
      subtitle: 'Felicitaciones',
    },
    {
      id: 2,
      img: '/assets/images/profile/user-2.jpg',
      title: 'Nuevo mensaje recibido',
      subtitle: 'Salma te envia nuevo mensaje',
    },
    {
      id: 3,
      img: '/assets/images/profile/user-3.jpg',
      title: 'Nuevo item registrado',
      subtitle: 'Revisar nuevos registros',
    },
    {
      id: 4,
      img: '/assets/images/profile/user-4.jpg',
      title: 'Carlos completo tareas',
      subtitle: 'Asignar nuevas tareas',
    },    
  ];

  profiledd: profiledd[] = [
    {
      id: 1,
      img: '/assets/images/svgs/icon-account.svg',
      title: 'Mi Perfil',
      subtitle: 'Configurar Cuenta',
      link: '/',
    },
    {
      id: 2,
      img: '/assets/images/svgs/icon-inbox.svg',
      title: 'Mi correo',
      subtitle: 'Mensajes y Email',
      link: '/apps/email/inbox',
    },
    {
      id: 3,
      img: '/assets/images/svgs/icon-tasks.svg',
      title: 'Mis Tareas',
      subtitle: 'To-do y Daily Tareas',
      link: '/apps/taskboard',
    },
  ];
}

