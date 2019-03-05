import {Component, OnDestroy, OnInit} from '@angular/core';
import {DoctorService} from '../../../services/doctor.service';
import {PatientService} from '../../../services/patient.service';
import {Subscription} from 'rxjs';
import {Doctor} from '../../../model/doctor';
import {Patient} from '../../../model/patient';
import {Reservation} from '../../../model/reservation';
import {ReservationService} from '../../../services/reservation.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit, OnDestroy {
  doctors: Doctor[];
  doctorsSubscription: Subscription;

  patients: Patient[];
  patientsSubscription: Subscription;

  reservation: Reservation;

  createReservationForm: FormGroup;
  fb: FormBuilder = new FormBuilder();

  constructor(private doctorService: DoctorService,
              private patientService: PatientService,
              private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.doctorsSubscription = this.doctorService.doctorsSubject
      .subscribe(
        (doctors: Doctor[]) => {
          console.log('called');
          this.doctors = doctors;
        }
      );
    this.doctors = this.doctorService.doctorsData();

    this.patientsSubscription = this.patientService.patientsSubject.subscribe(
      (patients: Patient[]) => {
        this.patients = patients;
      }
    );
    this.patients = this.patientService.patientsData();
    this.initForm();
  }

  initForm() {
    this.createReservationForm = this.fb.group({
      'doctor': this.fb.control('', Validators.required),
      'patient': this.fb.control('', Validators.required),
      'dateTime': this.fb.control(Date.now(), Validators.required),
      'breifComplain': this.fb.control('test', Validators.required)
    });
  }

  ngOnDestroy() {
    this.doctorsSubscription.unsubscribe();
    this.patientsSubscription.unsubscribe();
  }

  submitAction() {
    let value = this.createReservationForm.value;
    console.log(value);
    this.reservation = new Reservation(null, value.doctor,
      new Patient(value.patient, null, null, null),
      value.dateTime, value.breifComplain);
    this.reservationService.addReservation(this.reservation);
  }
}
