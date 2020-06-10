import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
/*import { PageNotFoundComponent } from './page-not-found/page-not-found.component';*/

const routes: Routes = [
     {
        path: 'books',
        component: BooksComponent
     },
     /*{
        path: '**',
        component: PageNotFoundComponent
     }*/
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
