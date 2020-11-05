import { Component, OnInit } from '@angular/core';
import { ServerResponse } from '../interfaces/server-response';
import { LoginService } from '../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;

  constructor(private loginService: LoginService) { }

  login() {
    const user = {email: this.email, password: this.password};
    this.loginService.login(user).subscribe( 
      data => {
      console.log(data);
      const result = data as ServerResponse;
      this.loginService.setPropertieCookie('token', result.token);
      this.loginService.setPropertieCookie('username', result.userName);
    },
    error => {
      console.log(error);
    });
  }

  ngOnInit(): void {
  }

}
