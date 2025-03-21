import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { LoginService } from 'src/app/services/login.service';
import { ModalComponent } from 'src/app/shared/components/modal/modal.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  loginForm: FormGroup;
  constructor(
    private dialogRef: MatDialogRef<ModalComponent>,
    private fb: FormBuilder,
    private readonly loginService: LoginService
  ) {
    this.loginForm = this.fb.group({
      correo: ['', Validators.required],
      password: ['', Validators.required],
    });
  }
  ngOnInit(): void {}
  setParams(): void {}

  guardarFormulario(): void {
    const body = {
      ...this.loginForm.value,
    };
    console.log(body);
    this.loginService.authlogin(body).subscribe({
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
