import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'app/Auth/user-auth-service';
import { Login } from 'app/class/login';
import { URLAPP } from 'app/URLApplications';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private tokenExpirationTimer: any;

  private apiUrl = `${URLAPP}`;

    USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
    public username?: String |null;
    public password!: String |null;


 //PATH_OF_API = 'http://localhost:8079';
 requestHeader1 = new HttpHeaders({ 'No-Auth': 'True' });
 requestHeader = new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' });
   constructor(private httpClient :HttpClient,private router: Router,private userAuthService:UserAuthService) {

   }
   token:any
   expiresIn:any
     public login(user:Login) {
     //  this.logine(this.token, this.expiresIn)
       return this.httpClient.post(this.apiUrl+'authenticate', user,{headers:this.requestHeader1}
          );
     }




     public forUser() {
       //console.log("ana f forUser() hhhh")
       return this.httpClient.get(this.apiUrl+'forUser', {
         responseType: 'text',
       });
     }
     public forAdmin() {
       console.log("ana f forAdmin hhhh")
       return this.httpClient.get(this.apiUrl+'forAdmin', {
         responseType: 'text',
       });
     }

     logout() {
       sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
       this.username  = null;
       this.password = null;

       localStorage.removeItem('authenticatedUser');
        clearTimeout(this.tokenExpirationTimer);
        window.localStorage.clear();
        this.router.navigate(['/login']);
     }

     isUserLoggedIn() {
       let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
       if (user === null) return false
       console.log("isUserloggIN")
       return true
     }
     GetToken(){
       return localStorage.getItem('authenticatedUser')!=null?localStorage.getItem('authenticatedUser'):'';

     }

     getLoggedInUserName() {
       let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
       if (user === null) return ''
       console.log("getLoggedInUserName")
       return user
     }
     roleMatch(allwoedRoles: string|any[]):any|undefined{

       let isMatch =false;
       const userRoles:any = this.userAuthService.getRoles();
     //  console.log(userRoles+"hahowa")
       if (userRoles != null && userRoles) {
       for(let i= 0 ;i < userRoles.length;i++){
         if(userRoles[i]===allwoedRoles[i]){
           isMatch=true;

           return isMatch;
         }else{
           return isMatch;
         }

         }
       }
       }


      getToken(): string | null {
        return localStorage.getItem('authenticatedUser');
      }



     }








