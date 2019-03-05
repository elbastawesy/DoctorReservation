import {Component} from '@angular/core';
import {AuthService} from './services/auth.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'doctor-reservation-web';


  constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) {
  }

  isAuthinticated() {
    return this.authService.isLoggedIn();
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }


}
