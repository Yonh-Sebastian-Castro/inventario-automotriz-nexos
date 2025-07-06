import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Usuario {
    id: number;
    nombre: string;
    edad: number;
    fechaIngreso: string;
    cargoId: number;
    nombreCargo?: string;
}

@Injectable({
    providedIn: 'root'
})
export class UsuarioService {
    private baseUrl = 'http://localhost:8080/api/usuarios';

    constructor(private http: HttpClient) { }

    listar(): Observable<Usuario[]> {
        return this.http.get<Usuario[]>(this.baseUrl);
    }

    registrar(usuario: Partial<Usuario>): Observable<Usuario> {
        return this.http.post<Usuario>(this.baseUrl, usuario);
    }

}
