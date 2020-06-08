import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent {

  books: any;
  isBooksPresent: boolean = false;

  constructor(private http: HttpClient) {
  }

  getAllBooks() {
    this.http.get("http://localhost:9090/books")
        .subscribe(response => {
          console.log(response);
          this.books = response;
          this.isBooksPresent = true;
        });
  }
}
