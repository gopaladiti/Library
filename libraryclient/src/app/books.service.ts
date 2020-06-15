import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BooksService {

  private booksPresent = new BehaviorSubject(false);
  sharedBooks = this.booksPresent.asObservable();

  constructor() { }

  updatedBooksPresentValue(booksPresent: boolean) {
    this.booksPresent.next(booksPresent);
  }

}
