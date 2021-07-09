import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAnneeScolaire, AnneeScolaire } from 'app/shared/model/annee-scolaire.model';
import { AnneeScolaireService } from './annee-scolaire.service';

@Component({
  selector: 'jhi-annee-scolaire-update',
  templateUrl: './annee-scolaire-update.component.html',
})
export class AnneeScolaireUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelleAnnee: [],
  });

  constructor(protected anneeScolaireService: AnneeScolaireService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ anneeScolaire }) => {
      this.updateForm(anneeScolaire);
    });
  }

  updateForm(anneeScolaire: IAnneeScolaire): void {
    this.editForm.patchValue({
      id: anneeScolaire.id,
      libelleAnnee: anneeScolaire.libelleAnnee,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const anneeScolaire = this.createFromForm();
    if (anneeScolaire.id !== undefined) {
      this.subscribeToSaveResponse(this.anneeScolaireService.update(anneeScolaire));
    } else {
      this.subscribeToSaveResponse(this.anneeScolaireService.create(anneeScolaire));
    }
  }

  private createFromForm(): IAnneeScolaire {
    return {
      ...new AnneeScolaire(),
      id: this.editForm.get(['id'])!.value,
      libelleAnnee: this.editForm.get(['libelleAnnee'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnneeScolaire>>): void {
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
