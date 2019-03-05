import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Patient} from '../model/patient';
import {HttpInterceptorService} from './http-interceptor.service';
import {HttpConstants} from './http-constants';
import {NotificationsService} from 'angular2-notifications';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  patients: Patient[] = [];

  patientsSubject: Subject<Patient[]>;
  patientSubject: Subject<Patient>;

  constructor(private httpInterceptorService: HttpInterceptorService
    , private notificationsService: NotificationsService) {
    this.patientsSubject = new Subject<Patient[]>();
    this.patientSubject = new Subject<Patient>();
  }

  patientsData() {
    if (this.patients.length === 0) {
      this.getAllPatients();
    }
    return this.patients.slice();
  }


  private getAllPatients() {
    this.httpInterceptorService.doHTTPGetRequest(HttpConstants.GET_ALL_PATIENTS_URL, null, this.onReceiveAllPatients.bind(this));
  }

  onReceiveAllPatients(response) {
    this.patients = response ? response as Patient[] : [];
    this.patientsSubject.next(this.patients.slice());
  }

  addPatient(patient: Patient) {
    this.httpInterceptorService.doHTTPPostRequest(HttpConstants.CREATE_PATIENT_URL, patient, this.onReceiveAddPatientResponse.bind(this));
  }

  onReceiveAddPatientResponse(patient) {
    if (patient) {
      this.patients.push(patient as Patient);
      this.patientsSubject.next(this.patients.slice());
    }
  }

  getPatient(id: number) {
    this.httpInterceptorService.doHTTPGetRequest(HttpConstants.GET_PATIENT_BY_ID_URL + id, null, this.onReceiveGetPatient.bind(this));
  }

  onReceiveGetPatient(response) {
    const patient: Patient = response ? response as Patient : null;
    this.patientSubject.next(patient);
  }


  updatePatient(patient: Patient) {
    this.httpInterceptorService.doHTTPPostRequest(HttpConstants.UPDATE_PATIENT_URL, patient, this.onReceiveUpdateUpdateResponse.bind(this));
  }

  onReceiveUpdateUpdateResponse(patient) {
    patient = patient as Patient;
    for (let i = 0; i < this.patients.length; i++) {
      if (this.patients[i].id === patient.id) {
        this.patients[i] = patient;
        break;
      }
    }
    this.patientsSubject.next(this.patients.slice());
    this.patientSubject.next(patient);
    this.notificationsService.success('Success', 'Patient Updated Successfully', {
      timeOut: 3000,
      pauseOnHover: true,
      clickToClose: true
    });
  }
}
