import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Patient} from '../../../model/patient';
import {PatientService} from '../../../services/patient.service';
import {Observable, Subscription} from 'rxjs';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CanComponentDeactivate} from '../../../services/can-deactivate-guard';

@Component({
  selector: 'app-edit-patient',
  templateUrl: './edit-patient.component.html',
  styleUrls: ['./edit-patient.component.css']
})
export class EditPatientComponent implements OnInit, OnDestroy, CanComponentDeactivate {

  editPatientForm: FormGroup;

  patient: Patient;
  patientSubscription: Subscription;
  formSubmitted = false;

  constructor(private patientService: PatientService,
              private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.patientSubscription = this.patientService.patientSubject.subscribe(
      (patient: Patient) => {
        this.patient = patient;
        console.log(this.patient);
        if (this.formSubmitted) {
          this.router.navigate(['/patient']);
        } else {
          this.initForm();
        }
      }
    );
    this.patientService.getPatient(this.route.snapshot.params['id']);
    this.initForm();
  }

  initForm() {
    this.editPatientForm = this.formBuilder.group({
      'patientName': this.formBuilder.control(this.patient ? this.patient.name : '', Validators.required),
      'gender': this.formBuilder.control(this.patient ? this.patient.gender : '', Validators.required),
      'birthDate': this.formBuilder.control(this.patient ? this.patient.birthDate : null, Validators.required)
    });
  }

  onSubmitForm() {
    this.patient = new Patient(this.patient.id,
      this.editPatientForm.value['patientName'],
      this.editPatientForm.value['birthDate'],
      this.editPatientForm.value['gender']);
    this.patientService.updatePatient(this.patient);
    this.formSubmitted = true;
  }

  ngOnDestroy(): void {
    this.patientSubscription.unsubscribe();
  }

  canDeactivate(): Observable<boolean> | Promise<boolean> | boolean {
    if (this.patient.name !== this.editPatientForm.value['patientName'] && !this.formSubmitted) {
      return confirm('Are you sure you want to discard changes');
    } else {
      return true;
    }
  }

}
