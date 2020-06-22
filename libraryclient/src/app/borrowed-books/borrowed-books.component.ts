import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { BooksService } from '../books.service';
import { LoginService } from '../login.service';

@Component({
  selector: 'app-borrowed-books',
  templateUrl: './borrowed-books.component.html',
  styleUrls: ['./borrowed-books.component.css']
})
export class BorrowedBooksComponent implements OnInit {

  rentedBooks: any;
  userId: number;
  displayedColumns: string[] = ['select', 'title', 'borrowedDate', 'dueDate'];
  selectedBooks: number[] = [];
  token: string;
  headers: any;

  constructor(private http: HttpClient, private route: ActivatedRoute,
  private booksService: BooksService, private loginService: LoginService) { }

  ngOnInit(): void {
    //this.loginService.sharedToken.subscribe(data => this.token = data);
    this.token = localStorage.getItem('token');
    console.log(this.token);
    this.headers = this.loginService.getHeaders(this.token);
    console.log(this.headers);
    this.route.paramMap.subscribe(param => {
      this.userId = +param.get('userId');
    });
    this.getBooksRentedByUser(this.headers);
    //this.booksService.updatedBooksPresentValue(true);
  }

  getBooksRentedByUser(headers) {
    this.http.get('http://localhost:9090/books/user/'+this.userId, { headers, responseType: 'json'})
    .subscribe(response => {
      this.rentedBooks = response;
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

  returnBooks() {
    const options = {
      headers: new HttpHeaders({
        'Authorization': 'Bearer '+this.token
      })
    };
    this.selectedBooks.forEach(bookId => {
      this.http.delete<any>('http://localhost:9090/books/user/'+this.userId+'/'+bookId, options)
      .subscribe(response => {
        this.rentedBooks = response;
        this.selectedBooks = [];
      });
    });
  }
}
