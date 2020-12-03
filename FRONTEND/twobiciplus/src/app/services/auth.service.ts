import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'token/'; // 'api/';
  public token = '';
  private urlA = 'api/';
  constructor(private http: HttpClient, private cookie: CookieService) { }
  login(user: any): Observable<any> {
    const formHeaders = new HttpHeaders();
    formHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
    // formHeaders.append('Access-Control-Allow-Origin', 'http://localhost:4200');
    /* const formParams = new HttpParams()
      .set('username', user.username)
      .set('password', user.password);
    */
    const formData = new FormData();
    formData.append('username', user.email);
    formData.append('password', user.password);
    return this.http.post(this.url,  formData,
    {responseType: 'text',
    withCredentials: true
  }
    );
  }

  register(user: any): Observable<any> {
    const headers = new HttpHeaders().set('Authorization', `Bearer 123`);
    return this.http.post(this.urlA + 'person', user, {headers: headers});
  }

  setPropertieCookie(propName: string, value: string): void {
    this.cookie.set(propName, value);
  }

  getPropertieCookie(nameProp: any): string {
    return this.cookie.get(nameProp);
  }

  getPerson() {
    console.log('token people', this.token);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`);
    return this.http.get(`${this.urlA}me`, {
      headers: headers
    });
  }
}
