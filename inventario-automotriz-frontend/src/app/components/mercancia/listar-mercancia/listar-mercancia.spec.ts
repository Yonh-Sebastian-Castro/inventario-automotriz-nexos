import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarMercanciaComponent } from './listar-mercancia';

describe('ListarMercancia', () => {
  let component: ListarMercanciaComponent;
  let fixture: ComponentFixture<ListarMercanciaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListarMercanciaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListarMercanciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
