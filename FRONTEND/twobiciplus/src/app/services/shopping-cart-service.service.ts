import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartServiceService {

  url = 'api/me/shoppingCart';

  constructor(private http: HttpClient) { }

  addToCart(product: any, token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url + '/product', product, {headers: headers});
  }

  getCartProducts(token: any){
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.get(this.url, {headers: headers});
  }

  emptyCart(token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.delete(this.url, {headers: headers});
  }

  createOrder(token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url + '/order', {headers: headers});
  }

  getHistory(token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.get('api/me', {headers: headers});
  }
}
