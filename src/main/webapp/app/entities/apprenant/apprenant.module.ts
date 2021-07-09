import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AzimutSharedModule } from 'app/shared/shared.module';
import { ApprenantComponent } from './apprenant.component';
import { ApprenantDetailComponent } from './apprenant-detail.component';
import { ApprenantUpdateComponent } from './apprenant-update.component';
import { ApprenantDeleteDialogComponent } from './apprenant-delete-dialog.component';
import { apprenantRoute } from './apprenant.route';

@NgModule({
  imports: [AzimutSharedModule, RouterModule.forChild(apprenantRoute)],
  declarations: [ApprenantComponent, ApprenantDetailComponent, ApprenantUpdateComponent, ApprenantDeleteDialogComponent],
  entryComponents: [ApprenantDeleteDialogComponent],
})
export class AzimutApprenantModule {}
