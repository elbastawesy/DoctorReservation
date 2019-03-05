import {Injectable} from '@angular/core';
import {Subject} from 'rxjs';
import {Reservation} from '../model/reservation';
import {HttpConstants} from './http-constants';
import {HttpInterceptorService} from './http-interceptor.service';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  reservations: Reservation[] = [];

  reservationsSubject = new Subject<Reservation[]>();
  reservationSubject = new Subject<Reservation>();

  constructor(private httpInterceptorService: HttpInterceptorService) {
  }

  private getAllReservations() {
    this.httpInterceptorService.doHTTPGetRequest(HttpConstants.GET_ALL_RESERVATIONS_URL, null, this.onReceiveGetReservationsResponse.bind(this));
  }

  onReceiveGetReservationsResponse(response) {
    this.reservations = response ? response as Reservation[] : [];
    this.reservationsSubject.next(this.reservations.slice());
  }

  reservationsData() {
    if (this.reservations.length === 0) {
      this.getAllReservations();
    }
    return this.reservations.slice();
  }

  addReservation(reservation: Reservation) {
    this.httpInterceptorService.doHTTPPostRequest(HttpConstants.CREATE_RESERVATION_URL, reservation, this.onReceiveAddReservationResponse.bind(this));
  }

  onReceiveAddReservationResponse(reservation) {
    if (reservation) {
      this.reservations.push(reservation as Reservation);
      this.reservationsSubject.next(this.reservations.slice());
    }
  }

  getReservation(id: number) {
    this.httpInterceptorService.doHTTPGetRequest(HttpConstants.GET_RESERVATION_BY_ID_URL + id, null, this.onReceiveGetReservation.bind(this));
  }

  onReceiveGetReservation(response) {
    const reservation: Reservation = response ? response as Reservation : null;
    this.reservationSubject.next(reservation);
  }

  updateReservation(index: number, reservation: Reservation) {
    this.reservations[index] = reservation;
    this.reservationsSubject.next(this.reservations);
  }
}
