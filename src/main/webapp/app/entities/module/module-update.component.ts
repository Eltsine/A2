import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModule, Module } from 'app/shared/model/module.model';
import { ModuleService } from './module.service';
import { IFormation } from 'app/shared/model/formation.model';
import { FormationService } from 'app/entities/formation/formation.service';

@Component({
  selector: 'jhi-module-update',
  templateUrl: './module-update.component.html',
})
export class ModuleUpdateComponent implements OnInit {
  isSaving = false;
  formations: IFormation[] = [];

  editForm = this.fb.group({
    id: [],
    nomModule: [null, [Validators.required]],
    prix: [],
    formationId: [],
  });

  constructor(
    protected moduleService: ModuleService,
    protected formationService: FormationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ module }) => {
      this.updateForm(module);

      this.formationService.query().subscribe((res: HttpResponse<IFormation[]>) => (this.formations = res.body || []));
    });
  }

  updateForm(module: IModule): void {
    this.editForm.patchValue({
      id: module.id,
      nomModule: module.nomModule,
      prix: module.prix,
      formationId: module.formationId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const module = this.createFromForm();
    if (module.id !== undefined) {
      this.subscribeToSaveResponse(this.moduleService.update(module));
    } else {
      this.subscribeToSaveResponse(this.moduleService.create(module));
    }
  }

  private createFromForm(): IModule {
    return {
      ...new Module(),
      id: this.editForm.get(['id'])!.value,
      nomModule: this.editForm.get(['nomModule'])!.value,
      prix: this.editForm.get(['prix'])!.value,
      formationId: this.editForm.get(['formationId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModule>>): void {
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

  trackById(index: number, item: IFormation): any {
    return item.id;
  }
}
