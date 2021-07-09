import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPeriode } from 'app/shared/model/periode.model';

type EntityResponseType = HttpResponse<IPeriode>;
type EntityArrayResponseType = HttpResponse<IPeriode[]>;

@Injectable({ providedIn: 'root' })
export class PeriodeService {
  public resourceUrl = SERVER_API_URL + 'api/periodes';

  constructor(protected http: HttpClient) {}

  create(periode: IPeriode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(periode);
    return this.http
      .post<IPeriode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(periode: IPeriode): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(periode);
    return this.http
      .put<IPeriode>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPeriode>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPeriode[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(periode: IPeriode): IPeriode {
    const copy: IPeriode = Object.assign({}, periode, {
      dateDebut: periode.dateDebut && periode.dateDebut.isValid() ? periode.dateDebut.format(DATE_FORMAT) : undefined,
      dateFin: periode.dateFin && periode.dateFin.isValid() ? periode.dateFin.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateDebut = res.body.dateDebut ? moment(res.body.dateDebut) : undefined;
      res.body.dateFin = res.body.dateFin ? moment(res.body.dateFin) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((periode: IPeriode) => {
        periode.dateDebut = periode.dateDebut ? moment(periode.dateDebut) : undefined;
        periode.dateFin = periode.dateFin ? moment(periode.dateFin) : undefined;
      });
    }
    return res;
  }
}
