import { Routes } from '@angular/router';
import { ListarMercanciaComponent } from './components/mercancia/listar-mercancia/listar-mercancia';
import { RegistrarMercanciaComponent } from './components/mercancia/registrar-mercancia/registrar-mercancia';

export const routes: Routes = [
    { path: '', redirectTo: 'mercancia', pathMatch: 'full' },
    { path: 'mercancia', component: ListarMercanciaComponent },
    { path: 'mercancia/registrar', component: RegistrarMercanciaComponent },
    { path: 'mercancia/editar/:id', component: RegistrarMercanciaComponent },
    { path: 'usuarios', loadComponent: () => import('./pages/usuario/listar-usuario').then(m => m.ListarUsuarioComponent)}
    
];
