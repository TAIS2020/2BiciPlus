import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
// import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  private url = 'api/'; // 'api/';
  cart: any = [];
  constructor(private http: HttpClient, private authService: AuthService) { }

  getProducts() {
    console.log('token:', this.authService.token);
    
    const formHeaders = new HttpHeaders();
    formHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
    formHeaders.append('Access-Control-Allow-Origin', 'http://localhost:4200');
    formHeaders.append('Authorization', `Bearer ${this.authService.token}`);
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.token}`);
    return this.http.get(`${this.url}product`, {
      headers: headers
    });
  }
}
