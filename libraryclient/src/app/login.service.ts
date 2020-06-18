import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  generateToken(request) {
    return this.http.post<string>("http://localhost:9090/authenticate", request,
    { responseType: 'text' as 'json' });
   }

   /*getHeaders(token) {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return headers;
   }*/

   welcome(token) {
       let tokenStr = 'Bearer ' + token;
       const headers = new HttpHeaders().set('Authorization', tokenStr);
       return this.http.get("http://localhost:9090/welcome", { headers, responseType: 'text' as 'json' });
      }
}
