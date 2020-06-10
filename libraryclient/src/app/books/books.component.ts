import { Component, OnInit } from '@angular/core';
//import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {

  books: any;
  /*isBooksPresent: boolean = false;*/

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.books = params['books'];
    });
  }

  /*getAllBooks() {
    this.http.get("http://localhost:9090/books")
        .subscribe(response => {
          console.log(response);
          this.books = response;
          this.isBooksPresent = true;
        });
  }*/
}
