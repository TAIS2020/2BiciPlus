import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/services/product-service.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
 products: any = [];
  constructor(private productService: ProductServiceService) { }

  ngOnInit(): void {
    this.products = this.productService.cart;
  }

}
