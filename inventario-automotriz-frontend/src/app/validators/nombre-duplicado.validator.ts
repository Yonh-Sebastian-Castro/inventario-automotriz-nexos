import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { MercanciaService } from '../services/mercancia.service';

export function nombreDuplicadoValidator(
  service: MercanciaService,
  idActual?: number
): AsyncValidatorFn {
  return (control: AbstractControl): Observable<ValidationErrors | null> => {
    const nombre = control.value;
    if (!nombre) return of(null);

    return service.nombreExiste(nombre).pipe(
      map(existe => {
        return existe ? { nombreDuplicado: true } : null;
      }),
      catchError(() => of(null))  // En caso de error, no bloquea el formulario
    );
  };
}
