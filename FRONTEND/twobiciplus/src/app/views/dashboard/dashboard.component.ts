import { Component, OnInit } from '@angular/core';
import { ServerResponse } from 'src/app/interfaces/server-response';
import { ProductServiceService } from 'src/app/services/product-service.service';
import { ShoppingCartServiceService } from 'src/app/services/shopping-cart-service.service';

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

  cartArticles = 0;
  item = 1;
  navTittle = 'Productos Destacados';

  constructor(private productService: ProductServiceService,
              private spService: ShoppingCartServiceService) {
  }

  ngOnInit(): void {
    this.productService.getProducts(localStorage.getItem('token')).subscribe((data) => {
      console.log(data);
      this.products = (data as ServerResponse).result ;
    });
  }
  addCart(product: any) {
    this.spService.addToCart(product, localStorage.getItem('token')).subscribe(
      data => {
        console.log('in cart', data);
        let cartItems = 0;
        (data as any).result.products.forEach((it: { quantity: number; }) => {
          cartItems += it.quantity;
        });
        localStorage.setItem('cart', cartItems + '');
        this.cartArticles = +localStorage.getItem('cart')!;
      },
      error => {
        console.log(error);
        
      }
    );
  }

  showCart() {
    this.item = 2;
    this.navTittle = 'Carrito';
  }
}
