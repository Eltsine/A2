import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AzimutSharedModule } from 'app/shared/shared.module';
import { FormateurComponent } from './formateur.component';
import { FormateurDetailComponent } from './formateur-detail.component';
import { FormateurUpdateComponent } from './formateur-update.component';
import { FormateurDeleteDialogComponent } from './formateur-delete-dialog.component';
import { formateurRoute } from './formateur.route';

@NgModule({
  imports: [AzimutSharedModule, RouterModule.forChild(formateurRoute)],
  declarations: [FormateurComponent, FormateurDetailComponent, FormateurUpdateComponent, FormateurDeleteDialogComponent],
  entryComponents: [FormateurDeleteDialogComponent],
})
export class AzimutFormateurModule {}
