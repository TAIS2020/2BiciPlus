import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  url = 'api/me/question';

  constructor(private http: HttpClient) { }

  sendMessage(mensaje: string, token: string | null) {
    const tok = 'Bearer ' + token;
    const question = {question: mensaje};
    console.log('enviando: ', question);
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url, question, {headers: headers});
  }

  sendAnswer(mensaje: string, token: string | null) { // TODO
    const tok = 'Bearer ' + token;
    const question = {question: mensaje};
    console.log('enviando: ', question);
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url, question, {headers: headers});
  }

}
