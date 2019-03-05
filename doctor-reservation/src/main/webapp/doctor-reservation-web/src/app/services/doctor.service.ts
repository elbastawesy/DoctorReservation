import {Injectable} from '@angular/core';
import {Doctor} from '../model/doctor';
import {Subject} from 'rxjs';
import {HttpConstants} from './http-constants';
import {HttpInterceptorService} from './http-interceptor.service';
import {NotificationsService} from 'angular2-notifications';

@Injectable({
  providedIn: 'root'
})

export class DoctorService {
  doctors: Doctor[] = [];

  doctorsSubject = new Subject<Doctor[]>();
  doctorSubject = new Subject<Doctor>();

  constructor(private httpInterceptorService: HttpInterceptorService
    , private notificationsService: NotificationsService) {
  }

  doctorsData() {
    if (this.doctors.length === 0) {
      this.getAllDoctors();
    }
    return this.doctors.slice();
  }

  private getAllDoctors() {
    this.httpInterceptorService.doHTTPGetRequest(HttpConstants.GET_ALL_DOCTORS_URL, null, this.onReceiveGetDoctorsResponse.bind(this));
  }

  onReceiveGetDoctorsResponse(response) {
    this.doctors = response ? response as Doctor[] : [];
    this.doctorsSubject.next(this.doctors.slice());
  }


  addDoctor(doctor: Doctor) {
    this.httpInterceptorService.doHTTPPostRequest(HttpConstants.CREATE_DOCTOR_URL, doctor, this.onReceiveAddDoctorResponse.bind(this));
  }

  onReceiveAddDoctorResponse(doctor) {
    if (doctor) {
      this.doctors.push(doctor);
      this.doctorsSubject.next(this.doctors.slice());
      this.notificationsService.success('Success', 'Doctor Added Successfully', {
        timeOut: 3000,
        pauseOnHover: true,
        clickToClose: true
      });
    }
  }

  getDoctorById(id: number) {
    this.httpInterceptorService.doHTTPGetRequest(HttpConstants.GET_DOCTOR_BY_ID_URL + id, null, this.onReceiveGetDoctor.bind(this));
  }

  onReceiveGetDoctor(response) {
    if (response) {
      const doctor: Doctor = response as Doctor;
      this.doctorSubject.next(doctor);
    } else {
      this.notificationsService.error('Error retrieving Doctors', response, {
        timeOut: 3000,
        pauseOnHover: true,
        clickToClose: true
      });
    }
  }

  updateDoctor(doctor: Doctor) {
    this.httpInterceptorService.doHTTPPostRequest(HttpConstants.UPDATE_DOCTOR_URL, doctor, this.onReceiveUpdateDoctorResponse.bind(this));
  }

  onReceiveUpdateDoctorResponse(response) {
    if (response) {
      const doctor: Doctor = response as Doctor;
      for (let i = 0; i < this.doctors.length; i++) {
        if (this.doctors[i].id === doctor.id) {
          this.doctors[i] = doctor;
          this.doctorsSubject.next(this.doctors.slice());
          this.notificationsService.success('Success', 'Doctor Updated Successfully', {
            timeOut: 3000,
            pauseOnHover: true,
            clickToClose: true
          });
          break;
        }
      }
    } else {
      this.notificationsService.error('Error updating Doctor', response, {
        timeOut: 3000,
        pauseOnHover: true,
        clickToClose: true
      });
    }
  }

  deleteDoctor(doctor: Doctor) {
    this.httpInterceptorService.doHTTPPostRequest(HttpConstants.DELETE_DOCTOR_URL, doctor, this.onReceiveDeleteDoctorResponse.bind(this));
  }

  onReceiveDeleteDoctorResponse(response) {
    if (response) {
      const doctor: Doctor = response as Doctor;
      for (let i = 0; i < this.doctors.length; i++) {
        if (this.doctors[i].id === doctor.id) {
          this.doctors.splice(i, 1);
          this.doctorsSubject.next(this.doctors.slice());
          this.notificationsService.success('Success', 'Doctor Deleted Successfully', {
            timeOut: 3000,
            pauseOnHover: true,
            clickToClose: true
          });
          break;
        }
      }
    } else {
      this.notificationsService.error('Error updating Doctor', response, {
        timeOut: 3000,
        pauseOnHover: true,
        clickToClose: true
      });
    }
  }

}
