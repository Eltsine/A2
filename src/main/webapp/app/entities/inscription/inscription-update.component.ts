import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInscription, Inscription } from 'app/shared/model/inscription.model';
import { InscriptionService } from './inscription.service';
import { IPeriode } from 'app/shared/model/periode.model';
import { PeriodeService } from 'app/entities/periode/periode.service';
import { IEtablissement } from 'app/shared/model/etablissement.model';
import { EtablissementService } from 'app/entities/etablissement/etablissement.service';
import { IAnneeScolaire } from 'app/shared/model/annee-scolaire.model';
import { AnneeScolaireService } from 'app/entities/annee-scolaire/annee-scolaire.service';

type SelectableEntity = IPeriode | IEtablissement | IAnneeScolaire;

@Component({
  selector: 'jhi-inscription-update',
  templateUrl: './inscription-update.component.html',
})
export class InscriptionUpdateComponent implements OnInit {
  isSaving = false;
  periodes: IPeriode[] = [];
  etablissements: IEtablissement[] = [];
  anneescolaires: IAnneeScolaire[] = [];
  dateInscriptionDp: any;

  editForm = this.fb.group({
    id: [],
    dateInscription: [null, [Validators.required]],
    montantApayer: [null, [Validators.required]],
    montantVerse: [null, [Validators.required]],
    resteApayer: [null, [Validators.required]],
    etatEtudiant: [null, [Validators.required]],
    periodeId: [],
    etablissementId: [],
    anneeScolaireId: [],
  });

  constructor(
    protected inscriptionService: InscriptionService,
    protected periodeService: PeriodeService,
    protected etablissementService: EtablissementService,
    protected anneeScolaireService: AnneeScolaireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inscription }) => {
      this.updateForm(inscription);

      this.periodeService
        .query({ filter: 'inscription-is-null' })
        .pipe(
          map((res: HttpResponse<IPeriode[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPeriode[]) => {
          if (!inscription.periodeId) {
            this.periodes = resBody;
          } else {
            this.periodeService
              .find(inscription.periodeId)
              .pipe(
                map((subRes: HttpResponse<IPeriode>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPeriode[]) => (this.periodes = concatRes));
          }
        });

      this.etablissementService.query().subscribe((res: HttpResponse<IEtablissement[]>) => (this.etablissements = res.body || []));

      this.anneeScolaireService.query().subscribe((res: HttpResponse<IAnneeScolaire[]>) => (this.anneescolaires = res.body || []));
    });
  }

  updateForm(inscription: IInscription): void {
    this.editForm.patchValue({
      id: inscription.id,
      dateInscription: inscription.dateInscription,
      montantApayer: inscription.montantApayer,
      montantVerse: inscription.montantVerse,
      resteApayer: inscription.resteApayer,
      etatEtudiant: inscription.etatEtudiant,
      periodeId: inscription.periodeId,
      etablissementId: inscription.etablissementId,
      anneeScolaireId: inscription.anneeScolaireId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inscription = this.createFromForm();
    if (inscription.id !== undefined) {
      this.subscribeToSaveResponse(this.inscriptionService.update(inscription));
    } else {
      this.subscribeToSaveResponse(this.inscriptionService.create(inscription));
    }
  }

  private createFromForm(): IInscription {
    return {
      ...new Inscription(),
      id: this.editForm.get(['id'])!.value,
      dateInscription: this.editForm.get(['dateInscription'])!.value,
      montantApayer: this.editForm.get(['montantApayer'])!.value,
      montantVerse: this.editForm.get(['montantVerse'])!.value,
      resteApayer: this.editForm.get(['resteApayer'])!.value,
      etatEtudiant: this.editForm.get(['etatEtudiant'])!.value,
      periodeId: this.editForm.get(['periodeId'])!.value,
      etablissementId: this.editForm.get(['etablissementId'])!.value,
      anneeScolaireId: this.editForm.get(['anneeScolaireId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInscription>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
