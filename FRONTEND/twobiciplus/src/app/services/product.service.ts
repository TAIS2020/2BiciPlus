import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  url = 'api/';
  constructor(private http: HttpClient) { }

  create(product: any, token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url + '/product', product, {headers: headers});
  }

  getProducts(token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.get(`${this.url}product`, {
      headers: headers
    });
  }

  delete(id: any, token: any) {
    const tok = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.delete(`${this.url}product/${id}`, {
      headers: headers
    });
  }
}
