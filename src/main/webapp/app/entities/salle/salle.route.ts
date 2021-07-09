import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISalle, Salle } from 'app/shared/model/salle.model';
import { SalleService } from './salle.service';
import { SalleComponent } from './salle.component';
import { SalleDetailComponent } from './salle-detail.component';
import { SalleUpdateComponent } from './salle-update.component';

@Injectable({ providedIn: 'root' })
export class SalleResolve implements Resolve<ISalle> {
  constructor(private service: SalleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISalle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((salle: HttpResponse<Salle>) => {
          if (salle.body) {
            return of(salle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Salle());
  }
}

export const salleRoute: Routes = [
  {
    path: '',
    component: SalleComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'azimutApp.salle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SalleDetailComponent,
    resolve: {
      salle: SalleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.salle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SalleUpdateComponent,
    resolve: {
      salle: SalleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.salle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SalleUpdateComponent,
    resolve: {
      salle: SalleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.salle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
