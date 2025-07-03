import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable(
    { providedIn: 'root' }
)
export class LoginService {

  constructor(private http: HttpClient) {}
  private apiUrl = 'http://localhost:8080/api/login';

  login(username: string, password: string) {
    console.log("Login attempt:", username, password);
    return this.http.post(this.apiUrl,
        { username, password },
        { withCredentials: true }
    );
   }
}