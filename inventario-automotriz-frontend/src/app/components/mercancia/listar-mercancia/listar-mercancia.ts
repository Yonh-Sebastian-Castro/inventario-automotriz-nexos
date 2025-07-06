import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MercanciaService } from 'src/app/services/mercancia.service';
import { Mercancia } from 'src/app/models/mercancia.model';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Usuario } from 'src/app/models/usuario.model';

@Component({
  selector: 'app-listar-mercancia',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listar-mercancia.html',
  styleUrls: ['./listar-mercancia.css']
})
export class ListarMercanciaComponent implements OnInit {

  mercancias: Mercancia[] = [];
  cargando: boolean = false;
  error: string | null = null;
  usuarios: Usuario[] = [];
  usuarioActualId: number | null = null;

  constructor(
    private mercanciaService: MercanciaService,
    private usuarioService: UsuarioService,
    private router: Router
  ) { }

  ngOnInit() {
    this.obtenerMercancias();
    this.cargarUsuarios();
  }


  obtenerMercancias(): void {
    this.cargando = true;
    this.mercanciaService.obtenerTodas().subscribe({
      next: (data) => {
        this.mercancias = data;
        this.cargando = false;
      },
      error: (err) => {
        console.error(err);
        this.error = 'Error al cargar las mercancías';
        this.cargando = false;
      }
    });
  }

  cargarUsuarios() {
    this.usuarioService.listar().subscribe({
      next: (data) => this.usuarios = data,
      error: (e) => console.error('Error al cargar usuarios', e)
    });
  }

  editar(m: Mercancia): void {
    this.router.navigate(['/mercancia/editar', m.id]);
  }

  eliminar(id: number) {
    if (!this.usuarioActualId) {
      alert('Selecciona un usuario para poder eliminar.');
      return;
    }

    if (confirm('¿Estás seguro de eliminar esta mercancía?')) {
      this.mercanciaService.eliminar(id, this.usuarioActualId!).subscribe({
        next: () => {
          alert('Eliminado correctamente');
          this.obtenerMercancias();
        },
        error: (e) => {
          console.error('Error al eliminar', e);
          const mensaje = e.error?.mensaje || e.error || 'Error desconocido';
          alert('No se pudo eliminar: ' + mensaje);
        }

      });
    }
  }



  filtro: { nombre: string; fecha: string; usuarioId?: number } = {
    nombre: '',
    fecha: ''
  };

  filtrar(): void {
    if (!this.filtro.nombre && !this.filtro.fecha && !this.filtro.usuarioId) {
      alert('Debe proporcionar al menos un filtro.');
      return;
    }

    this.cargando = true;
    const params: any = {};
    if (this.filtro.nombre) params.nombre = this.filtro.nombre;
    if (this.filtro.fecha) params.fecha = this.filtro.fecha;
    if (this.filtro.usuarioId) params.usuarioId = this.filtro.usuarioId ?? undefined;

    this.mercanciaService.filtrar(this.filtro.nombre, this.filtro.fecha, this.filtro.usuarioId).subscribe({
      next: data => {
        this.mercancias = data;
        this.cargando = false;
      },
      error: err => {
        console.error(err);
        this.error = 'Error al filtrar';
        this.cargando = false;
      }
    });

  }

  resetear(): void {
    this.filtro = { nombre: '', fecha: '', usuarioId: undefined };
    this.obtenerMercancias();
  }

  irARegistrar(): void {
    this.router.navigate(['/mercancia/registrar']);
  }

  irAUsuarios(): void {
    this.router.navigate(['/usuarios']);
  }


}
