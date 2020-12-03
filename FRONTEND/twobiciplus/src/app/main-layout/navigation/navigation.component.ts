import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent implements OnInit {
  @ViewChild('sidenav', {static: true}) sidenav: ElementRef;

  @Output() productsRoute: EventEmitter<string> = new EventEmitter();

  clicked: boolean;

  constructor() {
    this.clicked = this.clicked === undefined ? false : true;
  }

  ngOnInit() {
  }

  setClicked(val: boolean): void {
    this.clicked = val;
  }

  products() {
    this.productsRoute.emit('pr');
  }

}
