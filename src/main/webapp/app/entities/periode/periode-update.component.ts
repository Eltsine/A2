import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPeriode, Periode } from 'app/shared/model/periode.model';
import { PeriodeService } from './periode.service';

@Component({
  selector: 'jhi-periode-update',
  templateUrl: './periode-update.component.html',
})
export class PeriodeUpdateComponent implements OnInit {
  isSaving = false;
  dateDebutDp: any;
  dateFinDp: any;

  editForm = this.fb.group({
    id: [],
    duree: [],
    dateDebut: [null, [Validators.required]],
    dateFin: [null, [Validators.required]],
  });

  constructor(protected periodeService: PeriodeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ periode }) => {
      this.updateForm(periode);
    });
  }

  updateForm(periode: IPeriode): void {
    this.editForm.patchValue({
      id: periode.id,
      duree: periode.duree,
      dateDebut: periode.dateDebut,
      dateFin: periode.dateFin,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const periode = this.createFromForm();
    if (periode.id !== undefined) {
      this.subscribeToSaveResponse(this.periodeService.update(periode));
    } else {
      this.subscribeToSaveResponse(this.periodeService.create(periode));
    }
  }

  private createFromForm(): IPeriode {
    return {
      ...new Periode(),
      id: this.editForm.get(['id'])!.value,
      duree: this.editForm.get(['duree'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value,
      dateFin: this.editForm.get(['dateFin'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPeriode>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
