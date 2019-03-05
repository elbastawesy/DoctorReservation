import {Injectable} from '@angular/core';
import {Http, RequestMethod} from '@angular/http';
import {map} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {HttpConstants} from './http-constants';
import {NotificationsService} from 'angular2-notifications';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService {


  constructor(private http: Http, private notificationsService: NotificationsService) {
  }

  private doHTTPRequest(url: string, jsonString: any, requestMethod: RequestMethod): Observable<any> {
    return this.http.request(url, {
      method: requestMethod,
      headers: HttpConstants.HTTP_HEADER,
      body: jsonString
    }).pipe(
      map(
        response => {
          const jsonResponse = response.json();
          console.log('Reqeuest : ==========' + url + '===============');
          console.log(jsonResponse);
          return jsonResponse;
        }
      )
    );
  }

  handleError(error) {
    this.notificationsService.alert(error.json().httpStatus, error.json().message, {
      timeOut: 3000,
      pauseOnHover: true,
      clickToClose: true
    });
    console.log('Error Response is : >>>> ' + error.status);
    console.log(error.json());
    console.log('Error creating HTTP Request');
    return error.json();
  }


  doHTTPGetRequest(url: string, jsonString: any, onRecieveResponseCallBack: Function) {
    return this.doHTTPRequest(url, jsonString, HttpConstants.GET_METHOD).subscribe(
      jsonResponse => {
        onRecieveResponseCallBack(jsonResponse);
        return jsonResponse;
      },
      (error: Response) => {
        this.handleError(error);
      }
    );
  }

  doHTTPPostRequest(url: string, jsonString: any, onRecieveResponseCallBack: Function) {
    return this.doHTTPRequest(url, jsonString, HttpConstants.POST_METHOD).subscribe(
      jsonResponse => {
        onRecieveResponseCallBack(jsonResponse);
        return jsonResponse;
      },
      (error: Response) => {
        this.handleError(error);
      }
    );
  }


}
