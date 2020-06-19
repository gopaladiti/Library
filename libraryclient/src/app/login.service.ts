import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private token = new BehaviorSubject(null);
  sharedToken = this.token.asObservable();

  constructor(private http: HttpClient) { }

  updateTokenValue(token: string) {
      this.token.next(token);
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

   welcome(token) {
       let tokenStr = 'Bearer ' + token;
       const headers = new HttpHeaders().set('Authorization', tokenStr);
       return this.http.get("http://localhost:9090/welcome", { headers, responseType: 'text' as 'json' });
      }
}
