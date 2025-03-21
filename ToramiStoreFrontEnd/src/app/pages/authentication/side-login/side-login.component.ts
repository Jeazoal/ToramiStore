import { Component } from '@angular/core';
import { CoreService } from 'src/app/services/core.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-side-login',
  templateUrl: './side-login.component.html',
})
export class AppSideLoginComponent {
  showPassword = false;
  typePasswordInput = 'password';
  error: string | null = null;
  success: string | null = null;
  options = this.settings.getOptions();

  constructor(
    private loginService: LoginService,
    private settings: CoreService,
    private router: Router
  ) {}

  form = new FormGroup({
    uname: new FormControl('', [Validators.required, Validators.minLength(8)]),
    password: new FormControl('', [Validators.required]),
  });

  get f() {
    return this.form.controls;
  }

  hideOrShowPassword() {
    this.showPassword = !this.showPassword;
    if (this.showPassword) {
      this.typePasswordInput = 'text';
    } else {
      this.typePasswordInput = 'password';
    }
  }

  submit() {
    // console.log(this.form.value);
    this.router.navigate(['/dashboards/dashboard1']);
  }

  inisiarSesion(): void {
    const usuario = this.form.value.uname;
    const password = this.form.value.password;

    const body = {
      username: usuario,
      password: password,
    };
    this.loginService.authlogin(body).subscribe({
      next: (response) => {
        if (response) {
          this.error = null;
          this.success =
            'Credenciales correctas, para el primer inicio de seción debe cambiar su contraseña';
          setTimeout(() => {
            location.reload();
          }, 2000);
        } else {
          this.error = null;
          this.success = 'Credenciales correctas, bienvenido';
          this.router.navigate(['/dashboards/dashboard1']);
        }
      },
      error: (error) => {
        this.error = error.error.message;
        return false;
      },
    });
  }
}
