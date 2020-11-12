import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  products = [
    {
      name: 'Silla',
      description: 'Para sentarse bien',
      price: '$500000',
      image: 'https://contents.mediadecathlon.com/p1133140/k$933301a2f95f07c3e68f4ba3078635df/sillin-bicicleta-urbana-100.jpg?format=auto&f=400x400',
      id: 123
    },
    {
      name: 'Silla',
      description: 'Para sentarse bien',
      price: '$500000',
      image: 'https://contents.mediadecathlon.com/p1133140/k$933301a2f95f07c3e68f4ba3078635df/sillin-bicicleta-urbana-100.jpg?format=auto&f=400x400',
      id: 123
    },
    {
      name: 'Silla',
      description: 'Para sentarse bien',
      price: '$500000',
      image: 'https://contents.mediadecathlon.com/p1133140/k$933301a2f95f07c3e68f4ba3078635df/sillin-bicicleta-urbana-100.jpg?format=auto&f=400x400',
      id: 123
    },
    {
      name: 'Silla',
      description: 'Para sentarse bien',
      price: '$500000',
      image: 'https://contents.mediadecathlon.com/p1133140/k$933301a2f95f07c3e68f4ba3078635df/sillin-bicicleta-urbana-100.jpg?format=auto&f=400x400',
      id: 123
    }
  ];

  cartArticles = 0;
  constructor() { }

  ngOnInit(): void {
  }

  addCart(product: any) {
    //add to cart in back
    console.log(product);
    this.cartArticles++;
    
  }
}
