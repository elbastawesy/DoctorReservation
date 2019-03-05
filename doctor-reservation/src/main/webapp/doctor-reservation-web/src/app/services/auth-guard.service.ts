import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from './auth.service';
import {NotificationsService} from 'angular2-notifications';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private authService: AuthService, private  router: Router, private notificationsService: NotificationsService) {
  }

  /**
   *  this method either return Observable or Promise in case of asynchronous request or return direct boolean  in case of synchronously
   * as you might have client code that runs synchronously
   */
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService.isAuthinticated().then(
      (authinticated: boolean) => {
        if (!authinticated) {
          this.notificationsService.warn('', 'Please login first', {
            timeOut: 2000,
            pauseOnHover: true,
            clickToClose: false
          });
          this.router.navigate(['/']);
        }
        return authinticated;
      }
    );
  }
}
