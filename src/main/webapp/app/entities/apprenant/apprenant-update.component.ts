import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IApprenant, Apprenant } from 'app/shared/model/apprenant.model';
import { ApprenantService } from './apprenant.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IInscription } from 'app/shared/model/inscription.model';
import { InscriptionService } from 'app/entities/inscription/inscription.service';
import { IFormateur } from 'app/shared/model/formateur.model';
import { FormateurService } from 'app/entities/formateur/formateur.service';

type SelectableEntity = IInscription | IFormateur;

@Component({
  selector: 'jhi-apprenant-update',
  templateUrl: './apprenant-update.component.html',
})
export class ApprenantUpdateComponent implements OnInit {
  isSaving = false;
  inscriptions: IInscription[] = [];
  formateurs: IFormateur[] = [];

  editForm = this.fb.group({
    id: [],
    photo: [],
    photoContentType: [],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    statut: [null, [Validators.required]],
    niveau: [],
    contact: [null, [Validators.required]],
    email: [],
    addParent: [],
    inscriptionId: [],
    formateurId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected apprenantService: ApprenantService,
    protected inscriptionService: InscriptionService,
    protected formateurService: FormateurService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apprenant }) => {
      this.updateForm(apprenant);

      this.inscriptionService.query().subscribe((res: HttpResponse<IInscription[]>) => (this.inscriptions = res.body || []));

      this.formateurService.query().subscribe((res: HttpResponse<IFormateur[]>) => (this.formateurs = res.body || []));
    });
  }

  updateForm(apprenant: IApprenant): void {
    this.editForm.patchValue({
      id: apprenant.id,
      photo: apprenant.photo,
      photoContentType: apprenant.photoContentType,
      nom: apprenant.nom,
      prenom: apprenant.prenom,
      statut: apprenant.statut,
      niveau: apprenant.niveau,
      contact: apprenant.contact,
      email: apprenant.email,
      addParent: apprenant.addParent,
      inscriptionId: apprenant.inscriptionId,
      formateurId: apprenant.formateurId,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: any, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('azimutApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const apprenant = this.createFromForm();
    if (apprenant.id !== undefined) {
      this.subscribeToSaveResponse(this.apprenantService.update(apprenant));
    } else {
      this.subscribeToSaveResponse(this.apprenantService.create(apprenant));
    }
  }

  private createFromForm(): IApprenant {
    return {
      ...new Apprenant(),
      id: this.editForm.get(['id'])!.value,
      photoContentType: this.editForm.get(['photoContentType'])!.value,
      photo: this.editForm.get(['photo'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      statut: this.editForm.get(['statut'])!.value,
      niveau: this.editForm.get(['niveau'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      email: this.editForm.get(['email'])!.value,
      addParent: this.editForm.get(['addParent'])!.value,
      inscriptionId: this.editForm.get(['inscriptionId'])!.value,
      formateurId: this.editForm.get(['formateurId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IApprenant>>): void {
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
