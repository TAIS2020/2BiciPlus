import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @Output() logIn: EventEmitter<string> = new EventEmitter();
  email: string;
  password: string;

  constructor(private loginService: AuthService,
              private route: Router) { }

  login() {
    const user = { email: this.email, password: this.password };
    this.loginService.login(user).subscribe(
      data => {
        if (data !== 'no token found') {
          localStorage.setItem('token', data);
          localStorage.setItem('username', user.email);
          this.logIn.emit('success');
          this.route.navigate(['/dashboard']);
        }
      },
      error => {
        alert('Usuario y contrasena invalidos')
        console.log(error);
      });
  }

  ngOnInit(): void {
  }

  logout() {
    localStorage.setItem('token', '');
  }
}
