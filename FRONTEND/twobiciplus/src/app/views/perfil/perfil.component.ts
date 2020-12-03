import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ShoppingCartServiceService } from 'src/app/services/shopping-cart-service.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.scss']
})
export class PerfilComponent implements OnInit {

  @Output() onPerfil: EventEmitter<string> = new EventEmitter();

  user = {};
  constructor(private sp: ShoppingCartServiceService) { }

  ngOnInit(): void {
  }

  initOnDemand() {
    this.sp.getHistory(localStorage.getItem('token')).subscribe(
      data => {
        const dat = data as any;
        console.log(dat);
        if (dat.status == 'OK') {
          this.user = dat.result;
        }
      }
    );
  }

}
