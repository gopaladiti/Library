import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BooksService } from '../books.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isBooksPresent: boolean;

  constructor(private router: Router, private booksService: BooksService) { }

  ngOnInit(): void {
    this.booksService.updatedBooksPresentValue(false);
    this.booksService.sharedBooks.subscribe(booksPresent => this.isBooksPresent = booksPresent);
  }

  goToBooks(url) {
    this.router.navigateByUrl(url);
  }
}
