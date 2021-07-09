import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IFormation, Formation } from 'app/shared/model/formation.model';
import { FormationService } from './formation.service';
import { IRapport } from 'app/shared/model/rapport.model';
import { RapportService } from 'app/entities/rapport/rapport.service';
import { IInscription } from 'app/shared/model/inscription.model';
import { InscriptionService } from 'app/entities/inscription/inscription.service';

type SelectableEntity = IRapport | IInscription;

@Component({
  selector: 'jhi-formation-update',
  templateUrl: './formation-update.component.html',
})
export class FormationUpdateComponent implements OnInit {
  isSaving = false;
  rapports: IRapport[] = [];
  inscriptions: IInscription[] = [];

  editForm = this.fb.group({
    id: [],
    nomFormation: [null, [Validators.required]],
    rapportId: [],
    inscriptionId: [],
  });

  constructor(
    protected formationService: FormationService,
    protected rapportService: RapportService,
    protected inscriptionService: InscriptionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formation }) => {
      this.updateForm(formation);

      this.rapportService
        .query({ filter: 'formation-is-null' })
        .pipe(
          map((res: HttpResponse<IRapport[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRapport[]) => {
          if (!formation.rapportId) {
            this.rapports = resBody;
          } else {
            this.rapportService
              .find(formation.rapportId)
              .pipe(
                map((subRes: HttpResponse<IRapport>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRapport[]) => (this.rapports = concatRes));
          }
        });

      this.inscriptionService.query().subscribe((res: HttpResponse<IInscription[]>) => (this.inscriptions = res.body || []));
    });
  }

  updateForm(formation: IFormation): void {
    this.editForm.patchValue({
      id: formation.id,
      nomFormation: formation.nomFormation,
      rapportId: formation.rapportId,
      inscriptionId: formation.inscriptionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formation = this.createFromForm();
    if (formation.id !== undefined) {
      this.subscribeToSaveResponse(this.formationService.update(formation));
    } else {
      this.subscribeToSaveResponse(this.formationService.create(formation));
    }
  }

  private createFromForm(): IFormation {
    return {
      ...new Formation(),
      id: this.editForm.get(['id'])!.value,
      nomFormation: this.editForm.get(['nomFormation'])!.value,
      rapportId: this.editForm.get(['rapportId'])!.value,
      inscriptionId: this.editForm.get(['inscriptionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormation>>): void {
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
