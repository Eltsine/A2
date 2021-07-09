import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AzimutSharedModule } from 'app/shared/shared.module';
import { RapportComponent } from './rapport.component';
import { RapportDetailComponent } from './rapport-detail.component';
import { RapportUpdateComponent } from './rapport-update.component';
import { RapportDeleteDialogComponent } from './rapport-delete-dialog.component';
import { rapportRoute } from './rapport.route';

@NgModule({
  imports: [AzimutSharedModule, RouterModule.forChild(rapportRoute)],
  declarations: [RapportComponent, RapportDetailComponent, RapportUpdateComponent, RapportDeleteDialogComponent],
  entryComponents: [RapportDeleteDialogComponent],
})
export class AzimutRapportModule {}
