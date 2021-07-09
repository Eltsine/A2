import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { AzimutTestModule } from '../../../test.module';
import { ApprenantDetailComponent } from 'app/entities/apprenant/apprenant-detail.component';
import { Apprenant } from 'app/shared/model/apprenant.model';

describe('Component Tests', () => {
  describe('Apprenant Management Detail Component', () => {
    let comp: ApprenantDetailComponent;
    let fixture: ComponentFixture<ApprenantDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ apprenant: new Apprenant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AzimutTestModule],
        declarations: [ApprenantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ApprenantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApprenantDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load apprenant on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apprenant).toEqual(jasmine.objectContaining({ id: 123 }));
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
