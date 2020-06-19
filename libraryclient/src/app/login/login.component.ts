import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { BooksService } from '../books.service';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  token: string;
  response: any;

  authRequest: any = {
    "username": this.username,
    "password": this.password
  };

  constructor(private loginService: LoginService, private booksService: BooksService,
  private router: Router) { }

  ngOnInit(): void {
    this.booksService.updatedBooksPresentValue(true);
  }

  login() {
    console.log(this.username);
    console.log(this.password);
    this.authRequest.username = this.username;
    this.authRequest.password = this.password;
    this.loginService.generateToken(this.authRequest)
        .subscribe( data => {
          console.log(data);
          this.token = data;
          this.loginService.updateTokenValue(data);
          this.router.navigateByUrl('home');
    });
  }

}
