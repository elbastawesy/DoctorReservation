import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreateDoctorComponent} from './setup/doctor/create-doctor/create-doctor.component';
import {CreatePatientComponent} from './setup/patient/create-patient/create-patient.component';
import {SearchResultComponent} from './setup/reservation/search-result/search-result.component';
import {EditPatientComponent} from './setup/patient/edit-patient/edit-patient.component';
import {AuthGuardService} from './services/auth-guard.service';
import {LoginComponent} from './setup/login/login.component';
import {CanDeactivateGuard} from './services/can-deactivate-guard';

const routes: Routes = [
    {path: '', redirectTo: '/login', pathMatch: 'full'},
    {path: 'login', component: LoginComponent},
    {path: 'doctor', component: CreateDoctorComponent, canActivate: [AuthGuardService]},
    {path: 'patient', component: CreatePatientComponent, canActivate: [AuthGuardService]},
    {path: 'patient/:id', component: EditPatientComponent, canActivate: [AuthGuardService], canDeactivate: [CanDeactivateGuard]},
    {path: 'reservation', component: SearchResultComponent, canActivate: [AuthGuardService]},
    {path: '**', redirectTo: '/'}
  ]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
