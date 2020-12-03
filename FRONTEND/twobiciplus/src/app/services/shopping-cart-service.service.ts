import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartServiceService {

  url = 'api/me/shoppingCart/product'

  constructor(private http: HttpClient) { }
  
  addToCart(product: any, token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url, product, {headers: headers});
  }
}
