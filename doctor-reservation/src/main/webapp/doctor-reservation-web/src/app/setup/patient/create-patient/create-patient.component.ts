import {Component, OnDestroy, OnInit} from '@angular/core';
import {Patient} from '../../../model/patient';
import {PatientService} from '../../../services/patient.service';
import {NgForm} from '@angular/forms';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-create-patient',
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.css']
})
export class CreatePatientComponent implements OnInit, OnDestroy {

  patient: Patient;
  patients: Patient[];
  patientsSubscription: Subscription;

  constructor(private patientService: PatientService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.patientsSubscription = this.patientService.patientsSubject.subscribe(
      (patients: Patient[]) => {
        this.patients = patients;
      }
    );
    this.patients = this.patientService.patientsData();
  }

  onSubmitPatientForm(formValues: NgForm) {
    console.log(formValues);
    const patientName = formValues.value.name;
    const gender = formValues.value.gender;
    const patient = new Patient(0, patientName, formValues.value.birthDate, gender);
    const patientJson = JSON.parse(JSON.stringify(formValues.value));
    // pass patient or patientJson, both work fine
    this.patientService.addPatient(patient);
    formValues.reset();
  }

  onEditBtnClick(patientId: number) {
    this.router.navigate([patientId], {relativeTo: this.route});
  }

  ngOnDestroy(): void {
    this.patientsSubscription.unsubscribe();
  }
}
