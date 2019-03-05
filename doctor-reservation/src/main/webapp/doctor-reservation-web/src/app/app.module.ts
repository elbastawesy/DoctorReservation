import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SearchComponent} from './setup/reservation/search/search.component';
import {SearchResultComponent} from './setup/reservation/search-result/search-result.component';
import {ReservationService} from './services/reservation.service';
import {DoctorService} from './services/doctor.service';
import {PatientService} from './services/patient.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {CreatePatientComponent} from './setup/patient/create-patient/create-patient.component';
import {HttpInterceptorService} from './services/http-interceptor.service';
import {CreateDoctorComponent} from './setup/doctor/create-doctor/create-doctor.component';
import {EntityService} from './services/entity.service';
import {SimpleNotificationsModule} from 'angular2-notifications';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule, MatToolbarModule} from '@angular/material';
import {EditPatientComponent} from './setup/patient/edit-patient/edit-patient.component';
import {AuthService} from './services/auth.service';
import {AuthGuardService} from './services/auth-guard.service';
import {LoginComponent} from './setup/login/login.component';
import {AuthGuardChildService} from './services/auth-guard-child.service';
import {CanDeactivateGuard} from './services/can-deactivate-guard';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
    SearchResultComponent,
    CreatePatientComponent,
    CreateDoctorComponent,
    EditPatientComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    BrowserAnimationsModule,
    SimpleNotificationsModule.forRoot(),
    MatToolbarModule,
    MatButtonModule
  ],
  providers: [EntityService,
    ReservationService,
    DoctorService,
    PatientService,
    HttpInterceptorService,
    AuthService,
    AuthGuardService,
    AuthGuardChildService,
    CanDeactivateGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
