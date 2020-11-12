import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url: string = 'api/'

  constructor(private http: HttpClient, private cookie: CookieService) { }

  login(user: any): Observable<any> {
    return this.http.post(this.url + 'login', user);
  }

  register(user: any): Observable<any> {
    return this.http.post(this.url + 'register', user);
  }

  setPropertieCookie(propName: string, value: string): void {
    this.cookie.set(propName, value);
  }

  getPropertieCookie(nameProp: any): string {
    return this.cookie.get(nameProp);
  }
}
