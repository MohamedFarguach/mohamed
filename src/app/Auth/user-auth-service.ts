import { HostListener, Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {
  private inactivityTimer: any;

  constructor(private router : Router) { }

   public setRoles(role: []){
    localStorage.setItem('roles',JSON.stringify(role))
  }
  public getRoles(): []{
    return JSON.parse(localStorage.getItem('roles')!);
  }
  public setToken(authenticatedUser:string){
    localStorage.setItem('authenticatedUser',authenticatedUser);
  }
  public getToken(): string {
    return localStorage.getItem('authenticatedUser')!;
  }
  public clear(){
    localStorage.clear();
  }
  public isLoggedIn(){
    return this.getRoles() && this.getToken();
  }



  logout(): void {

    localStorage.removeItem('authenticatedUser');
    localStorage.clear();
    window.location.reload()
    this.router.navigate(['login'])
  }

}
