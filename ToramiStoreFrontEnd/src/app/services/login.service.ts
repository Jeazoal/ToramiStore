import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { GenericResponse } from '../core/models/generic-response';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Login } from '../modules/torami-web/domain/login';
import { VerificarUsuario } from '../modules/torami-web/domain/verificarUsuario';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private urlCrearCuenta: string;
  private urlLogin: string;
  private urlVerify: string;

  constructor(private readonly http: HttpClient) {
    this.urlCrearCuenta = `${
      environment.baseUrlApi + environment.endPoint.passport.create
    }`;
    this.urlLogin = `${
      environment.baseUrlApi + environment.endPoint.passport.login
    }`;
    this.urlVerify = `${
      environment.baseUrlApi + environment.endPoint.passport.verify
    }`;
  }

  crearCuenta(body: any): Observable<GenericResponse<any>> {
    return this.http
      .post<GenericResponse<any>>(`${this.urlCrearCuenta}`, body)
      .pipe(
        tap((response: GenericResponse<any>) => {
          // console.log(response);
        })
      );
  }

  authlogin(body: any): Observable<Login> {
    return this.http.post<Login>(`${this.urlLogin}`, body).pipe(
      tap((response: Login) => {
        console.log(response);
      })
    );
  }

  verifyUser(token: string): Observable<VerificarUsuario> {
    return this.http
      .get<VerificarUsuario>(`${this.urlVerify}?token=${token}`)
      .pipe(
        tap((response: VerificarUsuario) => {
          if (response.success && response.token) {
            localStorage.setItem('token', response.token);
          }
        })
      );
  }
}
