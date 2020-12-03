import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email: string;
  address: string;
  phone: string;
  first: string;
  second: string;
  password: string;
  confirmPassword: string;

  constructor(private auth: AuthService) { }

  register() {
    if (this.password === this.confirmPassword) {
      const user = {
        email: this.email, password: this.password, address: this.address,
        phoneNumber: this.phone, cellNumber: this.phone, firstName: this.first, secondName: this.second, type: 'customer', 
      };
      this.auth.register(user).subscribe(data => {
        console.log(data);
      });
    }
    
  }

  ngOnInit(): void {
  }

}
