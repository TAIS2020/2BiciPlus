import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BotService {

  url = 'api/bot/question';

  constructor(private http: HttpClient) { }

  sendBotMessage(mensaje: string, token: any) {
    const tok = 'Bearer ' + token;
    const question = {question: mensaje};
    console.log('enviando: ', question);
    const headers = new HttpHeaders().set('Authorization', tok );
    return this.http.post(this.url, question, {headers: headers});
  }

}
