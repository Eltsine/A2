import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFormateur } from 'app/shared/model/formateur.model';
import { FormateurService } from './formateur.service';

@Component({
  templateUrl: './formateur-delete-dialog.component.html',
})
export class FormateurDeleteDialogComponent {
  formateur?: IFormateur;

  constructor(protected formateurService: FormateurService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.formateurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('formateurListModification');
      this.activeModal.close();
    });
  }
}
