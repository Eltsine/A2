import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPeriode, Periode } from 'app/shared/model/periode.model';
import { PeriodeService } from './periode.service';
import { PeriodeComponent } from './periode.component';
import { PeriodeDetailComponent } from './periode-detail.component';
import { PeriodeUpdateComponent } from './periode-update.component';

@Injectable({ providedIn: 'root' })
export class PeriodeResolve implements Resolve<IPeriode> {
  constructor(private service: PeriodeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPeriode> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((periode: HttpResponse<Periode>) => {
          if (periode.body) {
            return of(periode.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Periode());
  }
}

export const periodeRoute: Routes = [
  {
    path: '',
    component: PeriodeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'azimutApp.periode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PeriodeDetailComponent,
    resolve: {
      periode: PeriodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.periode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PeriodeUpdateComponent,
    resolve: {
      periode: PeriodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.periode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PeriodeUpdateComponent,
    resolve: {
      periode: PeriodeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.periode.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
