import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRapport } from 'app/shared/model/rapport.model';
import { RapportService } from './rapport.service';

@Component({
  templateUrl: './rapport-delete-dialog.component.html',
})
export class RapportDeleteDialogComponent {
  rapport?: IRapport;

  constructor(protected rapportService: RapportService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rapportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rapportListModification');
      this.activeModal.close();
    });
  }
}
