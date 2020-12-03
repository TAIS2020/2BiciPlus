import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-products-crud',
  templateUrl: './products-crud.component.html',
  styleUrls: ['./products-crud.component.scss']
})
export class ProductsCRUDComponent implements OnInit {

  constructor(private productService: ProductService) { }

  type: number;
  name: string;
  price: number;
  details: string;
  photoURL: string;

  products = [];

  ngOnInit(): void {
  }

  initOnDemand(type: string) {
    switch (type) {
      case 'c':
        this.type = 1;
        break;
      case 'r':
        this.type = 2;
        this.productService.getProducts(localStorage.getItem('token')).subscribe((data) => {
          console.log(data);
          this.products = (data as any).result;
        });
        break;
      case 'u':
        this.type = 3;
        break;
      case 'd':
        this.type = 4;
        this.productService.getProducts(localStorage.getItem('token')).subscribe((data) => {
          console.log(data);
          this.products = (data as any).result;
        });
        break;
      default:
        break;
    }
  }
  crear() {
    const prod = {
      name: this.name,
      categories: {
        id: 1
      },
      price: this.price,
      details: this.details,
      photoURL: this.photoURL,
      type: 'simple'
    };
    this.productService.create(prod, localStorage.getItem('token')).subscribe(
      data => {
        console.log(data);
        if((data as any).status == 'OK') {
          this.name = '';
          this.price = 0;
          this.details = '';
          this.photoURL = '';
        }
      }
    )
  }

  delete(product: any) {
    this.productService.delete(product.id, localStorage.getItem('token')).subscribe(
      data => {
        console.log(data);
        
        this.initOnDemand('d');
      }
    );
  }
}
