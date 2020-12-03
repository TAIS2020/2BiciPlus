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
          this.loginService.getPerson().subscribe(
            (dataUser: any) => {
              if ((dataUser as any).status === 'OK') {
                const type = (dataUser as any).result.type;
                localStorage.setItem('type', type);
                this.logIn.emit('success');
                console.log('tipe: ', localStorage.getItem('type'));
                this.route.navigate(['/dashboard']);
              }
            }
          );
        }
      },
      error => {
        alert('Usuario y contrasena invalidos');
        console.log(error);
      });
  }

  ngOnInit(): void {
    this.logout();
  }

  logout() {
    localStorage.setItem('token', '');
  }
}
