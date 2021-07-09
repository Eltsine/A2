import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISalle } from 'app/shared/model/salle.model';
import { SalleService } from './salle.service';

@Component({
  templateUrl: './salle-delete-dialog.component.html',
})
export class SalleDeleteDialogComponent {
  salle?: ISalle;

  constructor(protected salleService: SalleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.salleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('salleListModification');
      this.activeModal.close();
    });
  }
}
