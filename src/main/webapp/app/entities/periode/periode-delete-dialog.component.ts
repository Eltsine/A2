import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPeriode } from 'app/shared/model/periode.model';
import { PeriodeService } from './periode.service';

@Component({
  templateUrl: './periode-delete-dialog.component.html',
})
export class PeriodeDeleteDialogComponent {
  periode?: IPeriode;

  constructor(protected periodeService: PeriodeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.periodeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('periodeListModification');
      this.activeModal.close();
    });
  }
}
