import { Component, OnInit } from '@angular/core';
import { LoginService } from '../login.service';

@Component({
  selector: 'user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  user: any;
  editView: boolean = false;
  token: string;
  username: string;
  //disabled: boolean;
  constructor(private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.sharedUser.subscribe(data => this.user = data);
    /*this.token = localStorage.getItem('token');
    this.username = localStorage.getItem('username');
    this.getUserDetails(this.token, this.username);*/
  }

  /*getUserDetails(token, username) {
    this.loginService.getUserInfo(token, username)
        .subscribe(response => {
          console.log(response);
          this.user = response;
        });
  }*/

  isEditView() {
    //this.disabled = false;
    return this.editView;
  }

  editDetails() {
    this.editView = true;
    console.log(this.editView);
  }

  save() {
  }

  cancel() {

  }
}
