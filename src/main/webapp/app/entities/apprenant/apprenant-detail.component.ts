import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IApprenant } from 'app/shared/model/apprenant.model';

@Component({
  selector: 'jhi-apprenant-detail',
  templateUrl: './apprenant-detail.component.html',
})
export class ApprenantDetailComponent implements OnInit {
  apprenant: IApprenant | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ apprenant }) => (this.apprenant = apprenant));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
