import { Component, HostListener, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';

@Component({
  selector: 'app-auth-close',
  templateUrl: './auth-close.component.html',
  styleUrls: ['./auth-close.component.scss']
})
export class AuthCloseComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AuthCloseComponent>,public router :Router) { }

  ngOnInit(): void {
  }
  Deconnecter(){
    window.localStorage.clear();

    this.router.navigate(['/login']);
    this.dialogRef.close();

}
closedialog(){
  this.dialogRef.close();
}
controlExecutionTimeReport() {
  setTimeout(() => {
    this.closedialog();
  }, 1000); // 5000 milliseconds = 5 seconds
}
@HostListener('window:beforeunload')
   canUnload(): boolean {
     if (this.dialogRef && this.dialogRef.componentInstance) {
      console.log("re false")
      this.Deconnecter();
      return false;
     }
     console.log("re true")
     this.Deconnecter();

     return true;
   }
}
