import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Usuario } from 'src/app/models/usuario.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-listar-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './listar-usuario.html',
  styleUrls: ['./listar-usuario.css']
})
export class ListarUsuarioComponent implements OnInit {

  usuarios: Usuario[] = [];
  nuevoUsuario: Partial<Usuario> = {
    nombre: '',
    edad: 0,
    cargoId: 0,
    fechaIngreso: ''
  };

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios() {
    this.usuarioService.listar().subscribe({
      next: data => this.usuarios = data,
      error: err => console.error('Error al cargar usuarios', err)
    });
  }

  registrar() {
    if (!this.nuevoUsuario.nombre || !this.nuevoUsuario.edad || !this.nuevoUsuario.cargoId || !this.nuevoUsuario.fechaIngreso) {
      alert('Completa todos los campos');
      return;
    }

    this.usuarioService.registrar(this.nuevoUsuario).subscribe({
      next: () => {
        alert('Usuario registrado con éxito');
        this.nuevoUsuario = { nombre: '', edad: 0, cargoId: 0, fechaIngreso: '' };
        this.cargarUsuarios();
      },
      error: err => alert('Error al registrar usuario: ' + (err.error?.mensaje || err.message))
    });
  }

}
