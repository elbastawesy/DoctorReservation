import {Headers, RequestMethod} from '@angular/http';

export class HttpConstants {
  public static readonly HTTP_HEADER = new Headers({'Content-Type': 'application/json; charset=utf-8'});
  public static readonly GET_METHOD = RequestMethod.Get;
  public static readonly POST_METHOD = RequestMethod.Post;
  public static readonly PUT_METHOD = RequestMethod.Put;
  public static readonly DELETE_METHOD = RequestMethod.Delete;

  public static readonly BASE_URL = 'http://localhost:8080/';
  public static readonly CREATE_PATIENT_URL = HttpConstants.BASE_URL + 'patient/create';
  public static readonly UPDATE_PATIENT_URL = HttpConstants.BASE_URL + 'patient/update';
  public static readonly GET_PATIENT_BY_ID_URL = HttpConstants.BASE_URL + 'patient/';
  public static readonly GET_ALL_PATIENTS_URL = HttpConstants.BASE_URL + 'patient/all';

  public static readonly CREATE_DOCTOR_URL = HttpConstants.BASE_URL + 'doctor/create';
  public static readonly UPDATE_DOCTOR_URL = HttpConstants.BASE_URL + 'doctor/update';
  public static readonly DELETE_DOCTOR_URL = HttpConstants.BASE_URL + 'doctor/delete';
  public static readonly GET_DOCTOR_BY_ID_URL = HttpConstants.BASE_URL + 'doctor/';
  public static readonly GET_ALL_DOCTORS_URL = HttpConstants.BASE_URL + 'doctor/all';

  public static readonly CREATE_RESERVATION_URL = HttpConstants.BASE_URL + 'reservation/create';
  public static readonly UPDATE_RESERVATION_URL = HttpConstants.BASE_URL + 'reservation/update';
  public static readonly GET_RESERVATION_BY_ID_URL = HttpConstants.BASE_URL + 'reservation/';
  public static readonly GET_ALL_RESERVATIONS_URL = HttpConstants.BASE_URL + 'reservation/all';
}
