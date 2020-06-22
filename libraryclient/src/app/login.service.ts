import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  /*private token = new BehaviorSubject(null);
  sharedToken = this.token.asObservable();*/

  private user = new BehaviorSubject(null);
  sharedUser = this.user.asObservable();

  constructor(private http: HttpClient) { }

  /*updateTokenValue(token: string) {
      this.token.next(token);
  }*/

  updateUserValue(user: any) {
    this.user.next(user);
  }

  generateToken(request) {
    return this.http.post<string>("http://localhost:9090/authenticate", request,
    { responseType: 'text' as 'json' });
   }

   getHeaders(token) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return headers;
   }

   /*welcome(token) {
       let tokenStr = 'Bearer ' + token;
       const headers = new HttpHeaders().set('Authorization', tokenStr);
       return this.http.get("http://localhost:9090/welcome", { headers, responseType: 'text' as 'json' });
      }*/

  getUserInfo(token, username) {
    const headers = this.getHeaders(token);
     return this.http.get<any>("http://localhost:9090/user/" + username, {
      headers, responseType: 'json' });
  }

  isLoggedIn() {
      return localStorage.getItem('token');
  }

}
