import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'libraryclient';
  books: any;
  isBooksPresent: boolean = false;

  constructor(private http: HttpClient, private router: Router) {
  }

  getAllBooks() {
      this.http.get("http://localhost:9090/books")
          .subscribe(response => {
            console.log(response);
            this.books = response;
            this.isBooksPresent = true;
          });
      this.router.navigate(['/books'], { queryParams: { books: this.books} });
  }
}
