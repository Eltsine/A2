import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRapport, Rapport } from 'app/shared/model/rapport.model';
import { RapportService } from './rapport.service';
import { RapportComponent } from './rapport.component';
import { RapportDetailComponent } from './rapport-detail.component';
import { RapportUpdateComponent } from './rapport-update.component';

@Injectable({ providedIn: 'root' })
export class RapportResolve implements Resolve<IRapport> {
  constructor(private service: RapportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRapport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rapport: HttpResponse<Rapport>) => {
          if (rapport.body) {
            return of(rapport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Rapport());
  }
}

export const rapportRoute: Routes = [
  {
    path: '',
    component: RapportComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'azimutApp.rapport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RapportDetailComponent,
    resolve: {
      rapport: RapportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.rapport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RapportUpdateComponent,
    resolve: {
      rapport: RapportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.rapport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RapportUpdateComponent,
    resolve: {
      rapport: RapportResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.rapport.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
