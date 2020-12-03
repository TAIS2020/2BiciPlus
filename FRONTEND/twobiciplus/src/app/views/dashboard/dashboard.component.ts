import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ServerResponse } from 'src/app/interfaces/server-response';
import { ProductServiceService } from 'src/app/services/product-service.service';
import { ShoppingCartServiceService } from 'src/app/services/shopping-cart-service.service';
import { CarritoComponent } from '../carrito/carrito.component';
import { ChatBotComponent } from '../chat-bot/chat-bot.component';
import { ChatSellerComponent } from '../chat-seller/chat-seller.component';
import { LoginComponent } from '../login/login.component';
import { PerfilComponent } from '../perfil/perfil.component';

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
  navTittle = 'Productos';

  @ViewChild('carrito') carrito: CarritoComponent;
  @ViewChild('perfil') perfil: PerfilComponent;
  @ViewChild('login') login: LoginComponent;
  @ViewChild('chatBot') chatBot: ChatBotComponent;
  @ViewChild('chatSeller') chatSeller: ChatSellerComponent;

  constructor(private productService: ProductServiceService,
    private spService: ShoppingCartServiceService,
    private route: Router) {
  }

  ngOnInit(): void {
    this.initOnDemand();
    setInterval(() => {
      if (localStorage.getItem('dash') == '1') {
        this.initOnDemand();
        localStorage.setItem('dash', '0');
      }
    }, 1000);

  }

  initOnDemand() {
    this.productService.getProducts(localStorage.getItem('token')).subscribe((data) => {
      console.log(data);
      this.products = (data as ServerResponse).result;
    });
    this.spService.getCartProducts(localStorage.getItem('token')).subscribe(
      data => {
        let cartItems = 0;
        (data as any).result.products.forEach((it: { quantity: number; }) => {
          cartItems += it.quantity;
        });
        localStorage.setItem('cart', cartItems + '');
        this.cartArticles = +localStorage.getItem('cart')!;
      }
    );
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
    this.carrito.initOnDemand();
  }

  onCart(event: any) {
    switch (event) {
      case 'empty':
        this.cartArticles = 0;;
        break;
    }
  }

  onSidenav(event: any) {
    switch (event) {
      case 'pr':
        this.item = 1;
        this.navTittle = 'Productos';
        break;
      case 'cart':
        this.item = 2;
        this.navTittle = 'Carrito';
        this.carrito.initOnDemand();
        break;
      case 'perfil':
        this.item = 3;
        this.navTittle = 'Perfil';
        this.perfil.initOnDemand();
        break;
      case 'cb':
        this.item = 4;
        this.navTittle = 'Chat Bot';
        this.chatBot.initOnDemand();
        break;
      case 'cc':
        this.item = 5;
        this.navTittle = 'Chat Vendedor';
        this.chatSeller.initOnDemand();
        break;
      case 'lg':
        this.route.navigate(['/']);
        break;
      default:
        break;
    }
  }
}
