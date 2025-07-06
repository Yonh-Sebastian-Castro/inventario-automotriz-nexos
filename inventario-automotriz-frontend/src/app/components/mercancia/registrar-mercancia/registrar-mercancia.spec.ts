import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrarMercanciaComponent } from './registrar-mercancia';

describe('RegistrarMercancia', () => {
  let component: RegistrarMercanciaComponent;
  let fixture: ComponentFixture<RegistrarMercanciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistrarMercanciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrarMercanciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
