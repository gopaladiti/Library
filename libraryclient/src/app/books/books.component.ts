import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BooksService } from '../books.service';

@Component({
  selector: 'books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books: any;
  rentedBooks: any;
  booksPresent: boolean = false;
  selectedBooks: number[] = [];
  requestBook: any;

  constructor(private http: HttpClient, private router: Router,
  private booksService: BooksService) {
  }

  ngOnInit(): void {
    this.getAllBooks();
  }

  getAllBooks() {
    return this.http.get("http://localhost:9090/books")
        .subscribe(response => {
          this.books = response;
          this.booksPresent = true;
          this.booksService.updatedBooksPresentValue(this.booksPresent);
    });
  }

  selectBook($event) {
    if($event.checked) {
      this.selectedBooks.push($event.source.id);
    } else {
      const index: number = this.selectedBooks.indexOf($event.source.id);
      if(index != -1) {
        this.selectedBooks.splice(index, 1);
      }
    }
  }

  getMyBooks(url, userId) {
    this.router.navigate([url, userId]);
  }

  rentBooks(url, userId) {
    this.requestBook = {
      userId: userId,
      listOfBooks: this.selectedBooks
    };
    this.http.post("http://localhost:9090/books/user", this.requestBook)
      .subscribe(response => {
         this.rentedBooks = response;
         this.router.navigate([url, userId]);
    });
  }
}
