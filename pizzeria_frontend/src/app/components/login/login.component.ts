import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LoginService } from '../../services/login.service';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  username : string = "";
  password : string = "";
  error : string = "";

  constructor(private loginService : LoginService, private router: Router) {};
  
  onSubmit() {
  this.loginService.login(this.username, this.password).subscribe({
    next: () => {
      window.location.href = 'http://localhost:8080/dashboard';
    },
    error: (err) => {
      alert("Login fallito. Verifica le credenziali.");
    }
  });
}
}
