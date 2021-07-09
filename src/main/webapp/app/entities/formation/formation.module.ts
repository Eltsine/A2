import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AzimutSharedModule } from 'app/shared/shared.module';
import { FormationComponent } from './formation.component';
import { FormationDetailComponent } from './formation-detail.component';
import { FormationUpdateComponent } from './formation-update.component';
import { FormationDeleteDialogComponent } from './formation-delete-dialog.component';
import { formationRoute } from './formation.route';

@NgModule({
  imports: [AzimutSharedModule, RouterModule.forChild(formationRoute)],
  declarations: [FormationComponent, FormationDetailComponent, FormationUpdateComponent, FormationDeleteDialogComponent],
  entryComponents: [FormationDeleteDialogComponent],
})
export class AzimutFormationModule {}
