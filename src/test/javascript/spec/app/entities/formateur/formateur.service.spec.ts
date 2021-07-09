import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { FormateurService } from 'app/entities/formateur/formateur.service';
import { IFormateur, Formateur } from 'app/shared/model/formateur.model';

describe('Service Tests', () => {
  describe('Formateur Service', () => {
    let injector: TestBed;
    let service: FormateurService;
    let httpMock: HttpTestingController;
    let elemDefault: IFormateur;
    let expectedResult: IFormateur | IFormateur[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FormateurService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Formateur(0, 'image/png', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Formateur', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Formateur()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Formateur', () => {
        const returnedFromService = Object.assign(
          {
            photo: 'BBBBBB',
            cnib: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            statut: 'BBBBBB',
            contact: 'BBBBBB',
            email: 'BBBBBB',
            salaireHoraire: 1,
            salaireMensuel: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Formateur', () => {
        const returnedFromService = Object.assign(
          {
            photo: 'BBBBBB',
            cnib: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            statut: 'BBBBBB',
            contact: 'BBBBBB',
            email: 'BBBBBB',
            salaireHoraire: 1,
            salaireMensuel: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Formateur', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
