import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { BooksService } from '../books.service';

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

  constructor(private http: HttpClient, private route: ActivatedRoute, private booksService: BooksService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(param => {
      this.userId = +param.get('userId');
    });
    this.getBooksRentedByUser();
    this.booksService.updatedBooksPresentValue(true);
  }

  getBooksRentedByUser() {
    this.http.get('http://localhost:9090/books/user/'+this.userId).subscribe(response => {
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
    this.selectedBooks.forEach(bookId => {
      this.http.delete('http://localhost:9090/books/user/'+this.userId+'/'+bookId)
      .subscribe(response => {
        this.rentedBooks = response;
        this.selectedBooks = [];
      });
    });
  }
}
