import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { BorrowedBooksComponent } from './borrowed-books/borrowed-books.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
     {
        path: 'login',
        component: LoginComponent
     },
     {
        path: 'books',
        component: BooksComponent
     },
     {
        path: 'books/user/:userId',
        component: BorrowedBooksComponent
     },
     {
        path: 'home',
        component: HomeComponent
      },
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
     {
        path: '**',
        component: PageNotFoundComponent
     }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
