import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { BotService } from 'src/app/services/bot.service';

@Component({
  selector: 'app-chat-bot',
  templateUrl: './chat-bot.component.html',
  styleUrls: ['./chat-bot.component.scss']
})
export class ChatBotComponent implements OnInit {

  mensajes: any = [];
  mensaje: string;
  constructor(private botService: BotService) { }

  @Output() onChatBot: EventEmitter<string> = new EventEmitter();

  ngOnInit(): void {

  }

  initOnDemand() {
  }

  enviarMensaje() {
    if (this.mensaje !== '') {
    const newMensaje = {content: this.mensaje, isFromCustomer: true};
    this.mensajes.push(newMensaje);
    this.mensaje = '';
    this.botService.sendBotMessage(this.mensaje, localStorage.getItem('token')).subscribe(
      (data) => {
        if ((data as any).status === 'OK') {
          const message = {content: (data as any).result, isFromCustomer: false};
          this.mensajes.push(message);
        }
      });
    }
  }


}
