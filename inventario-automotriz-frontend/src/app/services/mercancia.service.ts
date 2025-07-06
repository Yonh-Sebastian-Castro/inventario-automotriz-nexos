import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mercancia } from 'src/app/models/mercancia.model';

@Injectable({
  providedIn: 'root'
})
export class MercanciaService {

  private baseUrl = 'http://localhost:8080/api/mercancia';

  constructor(private http: HttpClient) { }

  obtenerTodas(): Observable<Mercancia[]> {
    return this.http.get<Mercancia[]>(this.baseUrl);
  }

  registrar(mercancia: Mercancia): Observable<Mercancia> {
    return this.http.post<Mercancia>(this.baseUrl, mercancia);
  }

  buscarPorId(id: number): Observable<Mercancia> {
    return this.http.get<Mercancia>(`${this.baseUrl}/${id}`);
  }

  actualizar(id: number, mercancia: Mercancia): Observable<Mercancia> {
    return this.http.put<Mercancia>(`${this.baseUrl}/${id}`, mercancia);
  }

  eliminar(id: number, usuarioId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}?usuarioId=${usuarioId}`);
  }


  filtrar(nombre?: string, fecha?: string, usuarioId?: number): Observable<Mercancia[]> {
    const params: any = {};
    if (nombre) params.nombre = nombre;
    if (fecha) params.fecha = fecha;
    if (usuarioId) params.usuarioId = usuarioId;

    return this.http.get<Mercancia[]>(`${this.baseUrl}/buscar`, { params });
  }

  nombreExiste(nombre: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/existe`, {
      params: { nombre }
    });
  }

}
