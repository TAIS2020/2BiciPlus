import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  email: string;
  password: string;
  confirmPassword: string;

  constructor(private loginService: LoginService) {}

  register() {
    const user = { email: this.email, password: this.password };
    this.loginService.register(user).subscribe(data => {
      console.log(data);
    });
  }

  ngOnInit(): void {
  }

}
