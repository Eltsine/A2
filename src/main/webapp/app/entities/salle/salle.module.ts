import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AzimutSharedModule } from 'app/shared/shared.module';
import { SalleComponent } from './salle.component';
import { SalleDetailComponent } from './salle-detail.component';
import { SalleUpdateComponent } from './salle-update.component';
import { SalleDeleteDialogComponent } from './salle-delete-dialog.component';
import { salleRoute } from './salle.route';

@NgModule({
  imports: [AzimutSharedModule, RouterModule.forChild(salleRoute)],
  declarations: [SalleComponent, SalleDetailComponent, SalleUpdateComponent, SalleDeleteDialogComponent],
  entryComponents: [SalleDeleteDialogComponent],
})
export class AzimutSalleModule {}
