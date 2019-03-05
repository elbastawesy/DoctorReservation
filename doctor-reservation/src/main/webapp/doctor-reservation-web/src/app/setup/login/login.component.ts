import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = 'admin';
  password = 'admin';

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }

  login() {
    console.log('username is : ' + this.username);
    console.log('password is : ' + this.password);
    if (this.username === 'admin' && this.password === 'admin') {
      this.authService.login();
      this.router.navigate(['/doctor']);
    } else {
      alert('Invalid username or password');
    }
  }

}
