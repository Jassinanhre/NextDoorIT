import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

import { NgxUiLoaderService } from 'ngx-ui-loader';

import { LoginComponent } from 'src/app/modules/auth/login/login.component';
import { SignupComponent } from 'src/app/modules/auth/signup/signup.component';
import { AuthService } from 'src/app/services/auth/auth.service';
import { GlobalConstants } from 'src/app/global-constants';
import { SnackbarService } from 'src/app/services/snackbar.service';
import { LocalStorageService } from 'src/app/services/local-storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  public isLogin: boolean = false;
  public responseMessage: any;

  constructor(
    private router: Router,
    private dialog: MatDialog,
    private authService: AuthService,
    private snackbarService: SnackbarService,
    private localStorageService: LocalStorageService,
    private ngxService: NgxUiLoaderService
  ) { }

  ngOnInit(): void {
    this.checkLoggedIn();
    this.authService.getEmitter().subscribe((status) => {
      this.isLogin = status;
    });
  }

  checkLoggedIn() {
    this.isLogin = this.localStorageService.getItem('isLoggedIn');
  }

  handleSignupAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(SignupComponent, dialogConfig);
  }

  handleLoginAction() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "550px";
    this.dialog.open(LoginComponent, dialogConfig);
  }

  handleLogoutAction() {
    // this.authService.logout().subscribe((response: any) => {
    //   this.ngxService.stop();
    this.localStorageService.clear();
    this.authService.setLoginStatus(false);
    this.router.navigate(['/']);
    //   this.responseMessage = response?.message;
    //   this.snackbarService.openSnackBar(this.responseMessage, "");
    //   this.router.navigate(['/']);
    // }, (error) => {
    //   this.ngxService.stop();
    //   if (error.error?.message) {
    //     this.responseMessage = error.error?.message;
    //   }
    //   else {
    //     this.responseMessage = GlobalConstants.genericError;
    //   }
    //   this.snackbarService.openSnackBar(this.responseMessage, GlobalConstants.error);
    // })
  }
}
