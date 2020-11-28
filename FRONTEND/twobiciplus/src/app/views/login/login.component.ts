import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ServerResponse } from 'src/app/interfaces/server-response';
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
        console.log(data);
        if (true /* here goes the validation*/) {
          const result = data as ServerResponse;
          this.loginService.setPropertieCookie('token', result.token!);
          this.loginService.setPropertieCookie('username', result.userName!);
          this.logIn.emit('success');
          this.route.navigate(['/dashboard']);
        }
      },
      error => {
        console.log(error);
      });
  }

  ngOnInit(): void {
  }

}
