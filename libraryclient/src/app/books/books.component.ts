import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BooksService } from '../books.service';
import { LoginService } from '../login.service';

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
  token: string;
  headers: any;
  userId: number;

  constructor(private http: HttpClient, private router: Router,
  private booksService: BooksService, private loginService: LoginService) {
  }

  ngOnInit(): void {
    //this.loginService.sharedToken.subscribe(data => this.token = data);
    this.token = localStorage.getItem('token');
    console.log(this.token);
    this.headers = this.loginService.getHeaders(this.token);
    console.log(this.headers);
    this.loginService.sharedUser.subscribe(value => this.userId = value.userId);
    //this.userId = +localStorage.getItem('userId');
    this.getAllBooks(this.headers);
  }

  getAllBooks(headers) {
    return this.http.get("http://localhost:9090/books", { headers, responseType: 'json' })
        .subscribe(response => {
          this.books = response;
          this.booksPresent = true;
          //this.booksService.updatedBooksPresentValue(this.booksPresent);
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

  getMyBooks(url) {
    this.router.navigate([url, this.userId]);
  }

  rentBooks(url) {
    this.requestBook = {
      userId: this.userId,
      listOfBooks: this.selectedBooks
    };
    this.http.post<any>("http://localhost:9090/books/user", this.requestBook,
    { headers: this.headers, responseType: 'json' })
      .subscribe(response => {
         this.rentedBooks = response;
         this.router.navigate([url, this.userId]);
    });
  }

  isUnavailable(book) {
    if(book.available === 1) {
      return true;
    } else {
      return false;
    }
  }
}
