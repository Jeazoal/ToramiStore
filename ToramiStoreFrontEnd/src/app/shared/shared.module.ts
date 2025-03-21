import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ModalComponent } from './components/modal/modal.component';
import { MaterialModule } from '../material.module';
import { MatNativeDateModule } from '@angular/material/core';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [ModalComponent],
  imports: [CommonModule, MaterialModule, MatNativeDateModule, MatIconModule],
})
export class SharedModule {}
