import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
// import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  private url = 'api/';
  cart: any = [];
  constructor(private http: HttpClient) { }

  getProducts(token: any) {
    console.log('Bearer ' + token);
    const tok = 'Bearer ' + token;
    const formHeaders = new HttpHeaders();
    formHeaders.append('Content-Type', 'application/x-www-form-urlencoded');
    formHeaders.append('Access-Control-Allow-Origin', 'http://localhost:4200');
    formHeaders.append('Authorization', tok);
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.get(`${this.url}product`, {
      headers: headers
    });
  }

  addCart(product: any) {
    this.cart.push(product);
  }
}
