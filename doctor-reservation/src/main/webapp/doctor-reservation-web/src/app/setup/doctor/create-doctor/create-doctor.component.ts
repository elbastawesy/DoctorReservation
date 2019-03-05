import {Component, OnDestroy, OnInit} from '@angular/core';
import {Doctor} from '../../../model/doctor';
import {DoctorService} from '../../../services/doctor.service';
import {NgForm} from '@angular/forms';
import {Subscription} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-create-doctor',
  templateUrl: './create-doctor.component.html',
  styleUrls: ['./create-doctor.component.css']
})
export class CreateDoctorComponent implements OnInit, OnDestroy {

  doctor: Doctor;
  doctors: Doctor[];
  doctorsSubscription: Subscription;
  selectedIdForEdit = 0;
  selectedDoctor: Doctor;

  constructor(private doctorService: DoctorService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.doctor = new Doctor(null, null, null, null, null);
  }

  ngOnInit() {
    console.log('calling CreateDoctorComponent on init');
    this.doctorsSubscription = this.doctorService.doctorsSubject.subscribe(
      (doctors: Doctor[]) => {
        this.doctors = doctors;
      }
    );
    this.doctors = this.doctorService.doctorsData();
  }

  onSubmitDoctorForm(createDoctorForm: NgForm) {
    this.doctorService.addDoctor(this.doctor);
    createDoctorForm.resetForm();
  }

  onEditDoctor(doctor: Doctor) {
    this.selectedIdForEdit = doctor.id;
    this.selectedDoctor = new Doctor(doctor.id, doctor.name, doctor.speciality, doctor.address, doctor.education);
  }

  onCancelEditDoctor() {
    this.selectedIdForEdit = 0;
    this.selectedDoctor = null;
  }

  applyEditDoctor() {
    this.doctorService.updateDoctor(this.selectedDoctor);
    this.selectedIdForEdit = 0;
    this.selectedDoctor = null;
  }

  deleteDoctor(doctor: Doctor) {
    this.doctorService.deleteDoctor(doctor);
    this.selectedIdForEdit = 0;
    this.selectedDoctor = null;
  }

  ngOnDestroy(): void {
    this.doctorsSubscription.unsubscribe();
  }

}
