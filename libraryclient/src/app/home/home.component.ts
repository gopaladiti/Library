import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BooksService } from '../books.service';
import { Location } from '@angular/common';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  //isBooksPresent: boolean;

  constructor(private router: Router, private location: Location) { }

  ngOnInit(): void {
    //this.booksService.updatedBooksPresentValue(false);
    //this.booksService.sharedBooks.subscribe(booksPresent => this.isBooksPresent = booksPresent);
    //localStorage.setItem('homePage', 'true');
  }

  isHomePage() {
     return (this.location.path().endsWith('home'));
  }

  goToBooks() {
    this.router.navigateByUrl("books");
  }
}
