import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-chat-seller',
  templateUrl: './chat-seller.component.html',
  styleUrls: ['./chat-seller.component.scss']
})
export class ChatSellerComponent implements OnInit {
  mensajes: any = [];
  mensaje: string;
  constructor(private chatService: ChatService, private authService: AuthService) { }

  @Output() onChatBot: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {
  }

  initOnDemand() {
    this.authService.getPerson().subscribe(
      data => {
        if ((data as any).status === 'OK') {
          this.mensajes = (data as any).result.questions;
          console.log('mensajes: ', this.mensajes);
        }
      });
  }

  enviarMensaje() {
    if (this.mensaje !== '') {
    const newMensaje = {content: this.mensaje, isFromCustomer: true};
    this.mensajes.push(newMensaje);
    this.chatService.sendMessage(this.mensaje, localStorage.getItem('token')).subscribe(
      (data) => {
        if ((data as any).status === 'OK') {
          const message =  (data as any).result;
          this.mensajes.push(message);
          this.mensaje = '';
        }
      });
    }
  }
}
