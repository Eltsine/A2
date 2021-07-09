import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { AzimutTestModule } from '../../../test.module';
import { FormateurDetailComponent } from 'app/entities/formateur/formateur-detail.component';
import { Formateur } from 'app/shared/model/formateur.model';

describe('Component Tests', () => {
  describe('Formateur Management Detail Component', () => {
    let comp: FormateurDetailComponent;
    let fixture: ComponentFixture<FormateurDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ formateur: new Formateur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AzimutTestModule],
        declarations: [FormateurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(FormateurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FormateurDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load formateur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.formateur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
