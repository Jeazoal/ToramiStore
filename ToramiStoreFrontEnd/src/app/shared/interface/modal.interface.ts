import { EventEmitter } from '@angular/core';

export interface ModalCompartidoInterface {
  setParams(params: {
    id: string;
    isEditMode: boolean;
    isViewMode: boolean;
    datos?: any;
  }): void;
  save: EventEmitter<void>;
  close: EventEmitter<void>;
  guardarFormulario(): boolean;
  cerrarFormulario(): void;
  cambiarModo?: EventEmitter<boolean>;
}
