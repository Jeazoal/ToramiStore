export interface GenericResponse<T> {
  statusCode?: any;
  success: boolean;
  message: unknown;
  data: T;
  errors?: unknown;
}
