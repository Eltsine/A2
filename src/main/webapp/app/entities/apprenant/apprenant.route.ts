import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IApprenant, Apprenant } from 'app/shared/model/apprenant.model';
import { ApprenantService } from './apprenant.service';
import { ApprenantComponent } from './apprenant.component';
import { ApprenantDetailComponent } from './apprenant-detail.component';
import { ApprenantUpdateComponent } from './apprenant-update.component';

@Injectable({ providedIn: 'root' })
export class ApprenantResolve implements Resolve<IApprenant> {
  constructor(private service: ApprenantService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IApprenant> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((apprenant: HttpResponse<Apprenant>) => {
          if (apprenant.body) {
            return of(apprenant.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Apprenant());
  }
}

export const apprenantRoute: Routes = [
  {
    path: '',
    component: ApprenantComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'azimutApp.apprenant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApprenantDetailComponent,
    resolve: {
      apprenant: ApprenantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.apprenant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApprenantUpdateComponent,
    resolve: {
      apprenant: ApprenantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.apprenant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApprenantUpdateComponent,
    resolve: {
      apprenant: ApprenantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.apprenant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
