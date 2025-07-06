import { Component, OnInit } from '@angular/core';
import { MercanciaService } from 'src/app/services/mercancia.service';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { UsuarioService, Usuario } from 'src/app/services/usuario.service';


@Component({
  selector: 'app-registrar-mercancia',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './registrar-mercancia.html',
  styleUrl: './registrar-mercancia.css'
})
export class RegistrarMercanciaComponent implements OnInit {
  form!: FormGroup;
  id?: number;
  enEdicion: boolean = false;
  usuarios: Usuario[] = [];

  constructor(
    private mercanciaService: MercanciaService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private usuarioService: UsuarioService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.enEdicion = !!this.id;

    this.form = this.fb.group({
      nombre: ['', {
        validators: [Validators.required],
        asyncValidators: [this.nombreDuplicadoValidator()],
        updateOn: 'blur'  // Dispara la validación async al salir del input
      }],
      cantidad: [0, [Validators.required, Validators.min(1)]],
      fechaIngreso: ['', [Validators.required, this.fechaNoFuturaValidator]],
      usuarioRegistraId: [null, [Validators.required, Validators.min(1)]],
      usuarioModificaId: [],
      fechaModificacion: []
    });

    if (this.enEdicion) {
      this.mercanciaService.buscarPorId(this.id!).subscribe(data => {
        this.form.patchValue(data);
      });
    }
    
    this.usuarioService.listar().subscribe({
      next: data => this.usuarios = data,
      error: err => console.error('Error al cargar usuarios:', err)
    });

  }

  // Validador sincrónico: fecha no futura
  fechaNoFuturaValidator(control: AbstractControl): ValidationErrors | null {
    const valor = control.value;
    if (!valor) return null;

    const hoy = new Date().toISOString().split('T')[0];
    return valor > hoy ? { fechaFutura: true } : null;
  }

  // Validador asíncrono: nombre duplicado (excluye el actual si está en edición)
  nombreDuplicadoValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      const nombre = control.value;
      if (!nombre) return new Observable(observer => observer.next(null));

      return this.mercanciaService.nombreExiste(nombre).pipe(
        map((existe: boolean) => {
          if (this.enEdicion && this.form?.get('nombre')?.value === nombre) {
            return null;
          }
          return existe ? { nombreDuplicado: true } : null;
        })
      );
    };
  }

  guardar(): void {
    if (this.form.invalid) {
      alert("Formulario inválido. Revisa los campos.");
      return;
    }

    const mercancia = this.form.value;

    if (this.enEdicion && this.id) {
      mercancia.usuarioModificaId = mercancia.usuarioRegistraId;
      this.mercanciaService.actualizar(this.id, mercancia).subscribe({
        next: () => {
          alert('Mercancía actualizada con éxito.');
          this.router.navigate(['/mercancia']);
        },
        error: err => {
          console.error(err);
          alert(err.error.message || 'Error al actualizar.');
        }
      });
    } else {
      this.mercanciaService.registrar(mercancia).subscribe({
        next: () => {
          alert('Mercancía registrada con éxito.');
          this.router.navigate(['/mercancia']);
        },
        error: err => {
          console.error('Error al registrar:', err);
          if (err.status === 400 && typeof err.error === 'string') {
            alert(err.error); // Mostrará "La fecha no puede ser futura."
          } else {
            alert('Error al registrar. Intenta de nuevo.');
          }
        }
      });
    }
  }
}
