import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  email: string;
  password: string;
  confirmPassword: string;

  constructor(private loginService: AuthService) {}

  register() {
    const user = { email: this.email, password: this.password };
    this.loginService.register(user).subscribe(data => {
      console.log(data);
    });
  }

  ngOnInit(): void {
  }

}
