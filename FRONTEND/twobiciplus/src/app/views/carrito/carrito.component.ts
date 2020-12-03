import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ShoppingCartServiceService } from 'src/app/services/shopping-cart-service.service';

declare var WidgetCheckout: any;

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.scss']
})
export class CarritoComponent implements OnInit {

  constructor(private sp: ShoppingCartServiceService) { }

  @Output() onCart: EventEmitter<string> = new EventEmitter();

  products = [];
  visibleButtons = true;

  ngOnInit(): void {
  }

  initOnDemand() {
    this.sp.getCartProducts(localStorage.getItem('token')).subscribe(
      data => {
        if ((data as any).status === 'OK') {
          this.products = (data as any).result.products;
          this.visibleButtons = this.products.length === 0;
        }
      }
    );
  }

  emptyCart() {
    this.sp.emptyCart(localStorage.getItem('token')).subscribe(
      data => {
        console.log(data);
        this.initOnDemand();
        this.onCart.emit('empty');
      }
    );
  }

  createOrder() {
    this.sp.createOrder(localStorage.getItem('token')).subscribe(
      data => {
        let res = data as any;
        if(res.status == 'OK') {
          this.emptyCart();
        }
      }
    );
  }

  buy() {
    const checkout = new WidgetCheckout({
      currency: 'COP',
      amountInCents: this.getTotal() * 100,
      reference: '2biciplus' + new Date().getTime(),
      publicKey: 'pub_test_dOlcXJkEd5lx9JOvUhafAyAzklDMpgt7',
    });
    checkout.open((result: { transaction: any; }) => {
      let transaction = result.transaction
      if (transaction.status == 'APPROVED') {
        this.createOrder();
      }
    });

  }

  getTotal(): number {
    let total = 0;
    this.products.forEach(el => {
      total += (el as any).quantity * (el as any).product.price;
    });
    return total;
  }
}
