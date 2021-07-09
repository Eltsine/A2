import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFormateur, Formateur } from 'app/shared/model/formateur.model';
import { FormateurService } from './formateur.service';
import { FormateurComponent } from './formateur.component';
import { FormateurDetailComponent } from './formateur-detail.component';
import { FormateurUpdateComponent } from './formateur-update.component';

@Injectable({ providedIn: 'root' })
export class FormateurResolve implements Resolve<IFormateur> {
  constructor(private service: FormateurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormateur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((formateur: HttpResponse<Formateur>) => {
          if (formateur.body) {
            return of(formateur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Formateur());
  }
}

export const formateurRoute: Routes = [
  {
    path: '',
    component: FormateurComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'azimutApp.formateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormateurDetailComponent,
    resolve: {
      formateur: FormateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.formateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormateurUpdateComponent,
    resolve: {
      formateur: FormateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.formateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormateurUpdateComponent,
    resolve: {
      formateur: FormateurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'azimutApp.formateur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
