import {Doctor} from './doctor';
import {Patient} from './patient';

export class Reservation {
  public id: number;
  public doctor: Doctor;
  public patient: Patient;
  public dateTime: Date;
  public breifComplain: string;

  constructor(id: number, doctor: Doctor, patient: Patient, dateTime: Date, breifComplain: string) {
    this.id = id;
    this.doctor = doctor;
    this.patient = patient;
    this.dateTime = dateTime;
    this.breifComplain = breifComplain;
  }
}
