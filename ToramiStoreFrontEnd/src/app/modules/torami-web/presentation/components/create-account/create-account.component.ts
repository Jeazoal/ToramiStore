import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { LoginService } from 'src/app/services/login.service';
import { ModalComponent } from 'src/app/shared/components/modal/modal.component';

@Component({
  selector: 'app-create-account',
  templateUrl: './create-account.component.html',
  styleUrl: './create-account.component.scss',
})
export class CreateAccountComponent {
  cuentaForm: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<ModalComponent>,
    private fb: FormBuilder,
    private readonly loginService: LoginService
  ) {
    this.cuentaForm = this.fb.group({
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      correo: ['', Validators.required],
      password: ['', Validators.required],
      dni: ['', Validators.required],
      numero: ['', Validators.required],
    });
  }

  ngOnInit(): void {}
  setParams(): void {}

  guardarFormulario(): void {
    const body = {
      ...this.cuentaForm.value,
    };
    console.log(body);
    this.loginService.crearCuenta(body).subscribe({
      next: (response) => {
        if (response) {
          console.log(response);
          if (this.dialogRef) {
            this.dialogRef.close('save');
          }
        }
      },
      error: (error: any) => {
        console.log(error);
        console.log(error.error.message);
      },
    });
  }

  cerrarFormulario(): void {
    if (this.dialogRef) {
      this.dialogRef.close();
    }
  }
}
