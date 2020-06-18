import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { MatCheckboxModule } from '@angular/material/checkbox';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { FormsModule }   from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BooksComponent } from './books/books.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { BorrowedBooksComponent } from './borrowed-books/borrowed-books.component';
import { HomeComponent } from './home/home.component';

import { BooksService } from './books.service';
import { LoginService } from './login.service';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    NavbarComponent,
    PageNotFoundComponent,
    BorrowedBooksComponent,
    HomeComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCheckboxModule,
    MatToolbarModule,
    MatButtonModule,
    MatGridListModule,
    MatCardModule,
    RouterModule,
    MatListModule,
    MatTooltipModule,
    MatTableModule,
    MatInputModule,
    MatProgressSpinnerModule,
    FormsModule
  ],
  providers: [
    BooksService,
    LoginService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
