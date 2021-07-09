import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISalle } from 'app/shared/model/salle.model';

type EntityResponseType = HttpResponse<ISalle>;
type EntityArrayResponseType = HttpResponse<ISalle[]>;

@Injectable({ providedIn: 'root' })
export class SalleService {
  public resourceUrl = SERVER_API_URL + 'api/salles';

  constructor(protected http: HttpClient) {}

  create(salle: ISalle): Observable<EntityResponseType> {
    return this.http.post<ISalle>(this.resourceUrl, salle, { observe: 'response' });
  }

  update(salle: ISalle): Observable<EntityResponseType> {
    return this.http.put<ISalle>(this.resourceUrl, salle, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISalle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISalle[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
