import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  products: any = [
    /*
    {
      name: 'Silla',
      description: 'Para sentarse bien',
      price: '$500000',
      image: 'https://contents.mediadecathlon.com/p1133140/k$933301a2f95f07c3e68f4ba3078635df/sillin-bicicleta-urbana-100.jpg?format=auto&f=400x400'
    }
    */
  ];
  constructor(private productService: ProductServiceService) {
    console.log('viene');
    productService.getProducts().subscribe((data) => {
      console.log(data);
      this.products = data.result as any;
    });
  }

  ngOnInit(): void {
  }
  addCart(product: any) {
    this.productService.cart.push(product);
  }
}
