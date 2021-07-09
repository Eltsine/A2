import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IFormateur, Formateur } from 'app/shared/model/formateur.model';
import { FormateurService } from './formateur.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IEtablissement } from 'app/shared/model/etablissement.model';
import { EtablissementService } from 'app/entities/etablissement/etablissement.service';

@Component({
  selector: 'jhi-formateur-update',
  templateUrl: './formateur-update.component.html',
})
export class FormateurUpdateComponent implements OnInit {
  isSaving = false;
  etablissements: IEtablissement[] = [];

  editForm = this.fb.group({
    id: [],
    photo: [],
    photoContentType: [],
    cnib: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    prenom: [null, [Validators.required]],
    statut: [],
    contact: [null, [Validators.required]],
    email: [],
    salaireHoraire: [],
    salaireMensuel: [],
    etablissementId: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected formateurService: FormateurService,
    protected etablissementService: EtablissementService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formateur }) => {
      this.updateForm(formateur);

      this.etablissementService.query().subscribe((res: HttpResponse<IEtablissement[]>) => (this.etablissements = res.body || []));
    });
  }

  updateForm(formateur: IFormateur): void {
    this.editForm.patchValue({
      id: formateur.id,
      photo: formateur.photo,
      photoContentType: formateur.photoContentType,
      cnib: formateur.cnib,
      nom: formateur.nom,
      prenom: formateur.prenom,
      statut: formateur.statut,
      contact: formateur.contact,
      email: formateur.email,
      salaireHoraire: formateur.salaireHoraire,
      salaireMensuel: formateur.salaireMensuel,
      etablissementId: formateur.etablissementId,
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
    const formateur = this.createFromForm();
    if (formateur.id !== undefined) {
      this.subscribeToSaveResponse(this.formateurService.update(formateur));
    } else {
      this.subscribeToSaveResponse(this.formateurService.create(formateur));
    }
  }

  private createFromForm(): IFormateur {
    return {
      ...new Formateur(),
      id: this.editForm.get(['id'])!.value,
      photoContentType: this.editForm.get(['photoContentType'])!.value,
      photo: this.editForm.get(['photo'])!.value,
      cnib: this.editForm.get(['cnib'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      statut: this.editForm.get(['statut'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      email: this.editForm.get(['email'])!.value,
      salaireHoraire: this.editForm.get(['salaireHoraire'])!.value,
      salaireMensuel: this.editForm.get(['salaireMensuel'])!.value,
      etablissementId: this.editForm.get(['etablissementId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormateur>>): void {
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

  trackById(index: number, item: IEtablissement): any {
    return item.id;
  }
}
