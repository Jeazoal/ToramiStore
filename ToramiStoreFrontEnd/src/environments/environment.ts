export const environment = {
  production: false,
  baseUrlApi: 'http://localhost:8080/toramistore',
  paths: {
    path1: '/p',
  },
  endPoint: {
    passport: {
      create: '/account/create',
      login: '/account/login',
      verify: '/account/verify',
      cambiarPassword: '/usuarios/cambiar-contraseña',
      busquedaMedico: '/usuarios/buscar-medicos',
    },
  },
};
