import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AzimutTestModule } from '../../../test.module';
import { RapportUpdateComponent } from 'app/entities/rapport/rapport-update.component';
import { RapportService } from 'app/entities/rapport/rapport.service';
import { Rapport } from 'app/shared/model/rapport.model';

describe('Component Tests', () => {
  describe('Rapport Management Update Component', () => {
    let comp: RapportUpdateComponent;
    let fixture: ComponentFixture<RapportUpdateComponent>;
    let service: RapportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AzimutTestModule],
        declarations: [RapportUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RapportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RapportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RapportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rapport(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Rapport();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
