import {
  ChangeDetectorRef,
  Component,
  ElementRef,
  Inject,
  Type,
  ViewChild,
  ViewContainerRef,
} from '@angular/core';
import { ModalCompartidoInterface } from '../../interface/modal.interface';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
interface ModalData {
  title: string;
  content: Type<any>;
  id: string;
  isEditMode: boolean;
  isViewMode: boolean;
  valid: boolean;
  datos?: any;
}
@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrl: './modal.component.scss',
})
export class ModalComponent {
  @ViewChild('contentContainer', { read: ViewContainerRef })
  contentContainer!: ViewContainerRef;
  @ViewChild('focusElement', { static: true }) focusElement!: ElementRef;
  private instance: ModalCompartidoInterface | null = null;
  constructor(
    public dialogRef: MatDialogRef<ModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: ModalData,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngAfterViewInit(): void {
    if (this.contentContainer) {
      try {
        const componentRef = this.contentContainer.createComponent(
          this.data.content
        );

        // Almacenar la instancia del componente dinámico
        this.instance = componentRef.instance as ModalCompartidoInterface;

        this.instance.setParams({
          id: this.data.id,
          isEditMode: this.data.isEditMode,
          isViewMode: this.data.isViewMode,
          datos: this.data.datos,
        });

        this.setupModeChangeListener();
        this.changeDetectorRef.detectChanges();

        // Establecer el foco en el primer campo de entrada del formulario
        if (this.focusElement) {
          this.focusElement.nativeElement.focus();
        }
      } catch (error) {
        console.error('Error al crear el componente dinámico:', error);
      }
    } else {
      console.error('contentContainer is not defined');
    }
  }

  private setupModeChangeListener(): void {
    if (this.instance) {
      if (this.instance.cambiarModo) {
        this.instance.cambiarModo.subscribe((nuevoModo: boolean) => {
          // this.ngZone.run(() => {
          this.data.valid = nuevoModo;
          this.changeDetectorRef.detectChanges();
          // });
        });
      }
    }
  }

  onClose(): void {
    if (this.instance) {
      this.instance.cerrarFormulario();
    }
    this.dialogRef.close();
  }

  onSave(): void {
    if (this.instance) {
      this.instance.guardarFormulario();
    }
    // this.dialogRef.close('save');
  }
}
