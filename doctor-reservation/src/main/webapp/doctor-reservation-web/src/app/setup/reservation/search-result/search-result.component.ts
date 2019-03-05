import {Component, OnDestroy, OnInit} from '@angular/core';
import {Reservation} from '../../../model/reservation';
import {ReservationService} from '../../../services/reservation.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-search-result',
  templateUrl: './search-result.component.html',
  styleUrls: ['./search-result.component.css']
})
export class SearchResultComponent implements OnInit, OnDestroy {

  reservations: Reservation[] = [];
  reservationsSubscription: Subscription;

  constructor(private reservationService: ReservationService) {
  }

  ngOnInit() {
    this.reservationsSubscription = this.reservationService.reservationsSubject.subscribe(
      (data: Reservation[]) => this.reservations = data
    );
    this.reservations = this.reservationService.reservationsData();
  }

  ngOnDestroy() {
    this.reservationsSubscription.unsubscribe();
  }

}
