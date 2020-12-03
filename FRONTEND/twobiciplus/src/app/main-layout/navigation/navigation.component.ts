import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  @ViewChild('sidenav', {static: true}) sidenav: ElementRef;

  @Output() onSidenav: EventEmitter<string> = new EventEmitter();

  clicked: boolean;
  isSeller = false;

  constructor() {
    this.clicked = this.clicked === undefined ? false : true;
  }

  ngOnInit() {
    this.isSeller = (localStorage.getItem('type') === 'seller');
  }

  setClicked(val: boolean): void {
    this.clicked = val;
  }

  products() {
    this.onSidenav.emit('pr');
  }

  cart() {
    this.onSidenav.emit('cart');
  }

  perfil() {
    this.onSidenav.emit('perfil');
  }

  logout() {
    this.onSidenav.emit('lg');
    localStorage.setItem('token', '');
    location.reload();
  }

  chatBot() {
    this.onSidenav.emit('cb');
  }

  chatSeller() {
    this.onSidenav.emit('cc');
  }

  report() {
    this.onSidenav.emit('rep');
  }

  about() {
    this.onSidenav.emit('ab');
  }

  complex() {
    this.onSidenav.emit('complex');
  }
  chatCustomer() {
    this.onSidenav.emit('cs');
  }

  // _________________________ Seller __________________________

  chatOfSeller() {
    this.onSidenav.emit('cos');
  }

  productsC() {
    this.onSidenav.emit('create');
  }

  productsD() {
    this.onSidenav.emit('delete');
  }

  productsR() {
    this.onSidenav.emit('showP');
  }

}
