import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApprenant } from 'app/shared/model/apprenant.model';
import { ApprenantService } from './apprenant.service';

@Component({
  templateUrl: './apprenant-delete-dialog.component.html',
})
export class ApprenantDeleteDialogComponent {
  apprenant?: IApprenant;

  constructor(protected apprenantService: ApprenantService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.apprenantService.delete(id).subscribe(() => {
      this.eventManager.broadcast('apprenantListModification');
      this.activeModal.close();
    });
  }
}
