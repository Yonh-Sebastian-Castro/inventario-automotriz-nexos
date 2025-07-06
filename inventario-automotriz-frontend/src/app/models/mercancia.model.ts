export interface Mercancia {
  id?: number;
  nombre: string;
  cantidad: number;
  fechaIngreso: string;
  usuarioRegistraId: number;
  usuarioModificaId?: number;
  fechaModificacion?: string;
}

