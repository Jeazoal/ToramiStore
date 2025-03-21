import { Injectable } from '@angular/core';
import screenfull from 'screenfull';

@Injectable({
  providedIn: 'root'
})
export class FullscreenService {

  constructor() { }

  toggleFullScreen(): void {
    if (screenfull.isEnabled) {
      screenfull.toggle();
    }
  }
}
