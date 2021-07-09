import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ApprenantService } from 'app/entities/apprenant/apprenant.service';
import { IApprenant, Apprenant } from 'app/shared/model/apprenant.model';

describe('Service Tests', () => {
  describe('Apprenant Service', () => {
    let injector: TestBed;
    let service: ApprenantService;
    let httpMock: HttpTestingController;
    let elemDefault: IApprenant;
    let expectedResult: IApprenant | IApprenant[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ApprenantService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Apprenant(0, 'image/png', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Apprenant', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Apprenant()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Apprenant', () => {
        const returnedFromService = Object.assign(
          {
            photo: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            statut: 'BBBBBB',
            niveau: 'BBBBBB',
            contact: 'BBBBBB',
            email: 'BBBBBB',
            addParent: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Apprenant', () => {
        const returnedFromService = Object.assign(
          {
            photo: 'BBBBBB',
            nom: 'BBBBBB',
            prenom: 'BBBBBB',
            statut: 'BBBBBB',
            niveau: 'BBBBBB',
            contact: 'BBBBBB',
            email: 'BBBBBB',
            addParent: 'BBBBBB',
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

      it('should delete a Apprenant', () => {
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
