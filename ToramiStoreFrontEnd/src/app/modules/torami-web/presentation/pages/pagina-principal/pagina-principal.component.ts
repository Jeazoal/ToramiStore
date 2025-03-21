import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ModalComponent } from 'src/app/shared/components/modal/modal.component';
import { LoginComponent } from '../../components/login/login.component';
import { CreateAccountComponent } from '../../components/create-account/create-account.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-pagina-principal',
  templateUrl: './pagina-principal.component.html',
  styleUrl: './pagina-principal.component.scss',
})
export class PaginaPrincipalComponent {
  searchControl = new FormControl();
  isAuthenticated = false;
  itemsCart: any[] = [
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

  listaCategorias = [
    'One Piece 🏴‍☠️',
    'Dragon Ball 🔥',
    'Naruto / Boruto 🍃',
    'Demon Slayer (Kimetsu no Yaiba) ⚔️',
    'Jujutsu Kaisen 💀',
    'Attack on Titan 🏰',
    'Pokémon ⚡',
    'My Hero Academia 🦸',
  ];

  profiledd: any[] = [
    {
      id: 1,
      img: '/assets/images/svgs/icon-account.svg',
      title: 'Iniciar Sesión',
      action: () => this.login(),
    },
    {
      id: 2,
      img: '/assets/images/svgs/icon-inbox.svg',
      title: 'Registrate',
      action: () => this.account(),
    },
  ];

  loginSuccessMenu: any[] = [
    {
      id: 1,
      img: '/assets/images/svgs/icon-account.svg',
      title: 'Mi cuenta',
      subtitle: 'Iniciar Sesión',
    },
    {
      id: 2,
      img: '/assets/images/svgs/icon-inbox.svg',
      title: 'Mis compras',
      subtitle: 'Registrate',
    },
    {
      id: 3,
      img: '/assets/images/svgs/icon-inbox.svg',
      title: 'Mis Direcciones',
      subtitle: 'Registrate',
    },
  ];

  fullScream(): void {}

  constructor(public dialog: MatDialog) {}

  ngOnInit(): void {
    // this.login();
  }

  login(): void {
    const id = null;
    const isEditMode = false;
    const isViewMode = false;
    const dialogRef = this.dialog.open(ModalComponent, {
      width: '30%',
      data: {
        content: LoginComponent,
        title: 'Iniciar Sesión',
        id,
        isEditMode,
        isViewMode,
        valid: false,
      },
      disableClose: false,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result === 'save') {
        this.isAuthenticated = true;
        console.log(result);
      }
    });
  }

  account(): void {
    const id = null;
    const isEditMode = false;
    const isViewMode = false;
    const dialogRef = this.dialog.open(ModalComponent, {
      width: '50%',
      data: {
        content: CreateAccountComponent,
        title: 'Crear cuenta',
        id,
        isEditMode,
        isViewMode,
        valid: false,
      },
      disableClose: true,
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        console.log(result);
      }
    });
  }

  limpiar() {
    this.searchControl.setValue('');
  }
}
