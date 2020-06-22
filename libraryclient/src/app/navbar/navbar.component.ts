import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  username: string;

  constructor(private loginService: LoginService, private location: Location,
  private router: Router) { }

  ngOnInit(): void {
    this.loginService.sharedUser.subscribe(data => {
      if(data !== null)
        this.username = data.username;
    });
    //this.username = localStorage.getItem('username');
  }

  isHomePage() {
    return (this.location.path().endsWith('home'));
  }

  logout() {
    this.loginService.updateUserValue(null);
    //this.loginService.updateTokenValue(null);
    localStorage.clear();
    this.router.navigateByUrl('home');
  }

  isLoggedIn() {
    return this.loginService.isLoggedIn();
  }

}
