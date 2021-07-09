import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAnneeScolaire } from 'app/shared/model/annee-scolaire.model';

type EntityResponseType = HttpResponse<IAnneeScolaire>;
type EntityArrayResponseType = HttpResponse<IAnneeScolaire[]>;

@Injectable({ providedIn: 'root' })
export class AnneeScolaireService {
  public resourceUrl = SERVER_API_URL + 'api/annee-scolaires';

  constructor(protected http: HttpClient) {}

  create(anneeScolaire: IAnneeScolaire): Observable<EntityResponseType> {
    return this.http.post<IAnneeScolaire>(this.resourceUrl, anneeScolaire, { observe: 'response' });
  }

  update(anneeScolaire: IAnneeScolaire): Observable<EntityResponseType> {
    return this.http.put<IAnneeScolaire>(this.resourceUrl, anneeScolaire, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnneeScolaire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnneeScolaire[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
