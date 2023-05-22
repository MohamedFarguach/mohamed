import { Component, HostListener, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UserAuthService } from 'app/Auth/user-auth-service';
import { AuthService } from 'app/AuthServic/auth-service';
import { AuthCloseComponent } from 'app/Modal/auth-close/auth-close.component';
import { LoginService } from 'app/Service/login-service';
import { Login } from 'app/class/login';
import jwt_decode from 'jwt-decode';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  ngOnInit(): void {

     if(this.userAuthService.isLoggedIn()){
       this.router.navigateByUrl('/dashboard')
   }
  }
  test : Date = new Date();
  username!:string
  password!:string
  message:any
  user:Login = new Login();


  constructor(public router :Router, private userAuthService: UserAuthService, private userService:LoginService,private authService: AuthService,public dialog:MatDialog,)
    {  }
    private loginTime: number;
    private logoutTimeout: any;

    getDecodedAccessToken(token: string): any {
      try {
        return jwt_decode(token);
      } catch(Error) {
        return null;
      }
    }


    logout(): void {
      this.userAuthService.logout();
    }
    private tokenExpirationTimer: any;
    expiresIn:any

  doLoogin() {
    this.userService.login(this.user).subscribe(
        (response: any) => {
          window.localStorage.setItem("role",response.role)
          this.userAuthService.setRoles(this.getDecodedAccessToken(JSON.stringify(response)).roles);
          this.userAuthService.setToken(response.accessToken);


          const role= this.getDecodedAccessToken(JSON.stringify(response)).roles[0]
          this.loginTime = new Date().getTime();
          this.setLogoutTimeout();
       //   alert(this.loginTime)
           this.router.navigate(['/transport']);

       },
        (error) => {
        console.log(error);
       }
      );
   }
   isTokenExpired(): boolean {
    const token = this.userAuthService.getToken();
    if (!token) {
      return true; // Token not found, consider it expired
    }
    const expirationDate = new Date().getTime(); // Replace with the actual expiration timestamp as a number
    return expirationDate <= new Date().getTime();
  }

  logoute(): void {
    clearTimeout(this.logoutTimeout);
  }

  private setLogoutTimeout(): void {
    const logoutTime = this.loginTime + (280 * 60 * 1000);
    const currentTime = new Date().getTime();
    const timeRemaining = logoutTime - currentTime;
    if (timeRemaining <= 0) {
      return;
    }
    this.logoutTimeout = setTimeout(() => {
      localStorage.clear();
            this.opendialog();
    }, timeRemaining);
  }
  opendialog(){
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = true;
     const dialogRef=this.dialog.open(AuthCloseComponent, dialogConfig);
   dialogRef.afterClosed().subscribe(() => {
  });
   }


 }





