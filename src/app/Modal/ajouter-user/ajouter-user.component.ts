import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from 'app/Service/user';

@Component({
  selector: 'app-ajouter-user',
  templateUrl: './ajouter-user.component.html',
  styleUrls: ['./ajouter-user.component.scss']
})
export class AjouterUserComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AjouterUserComponent>,private formBuilder: FormBuilder,
    private httpclient :HttpClient,public route:Router,private el: ElementRef,private userservice:User) { }

  ngOnInit(): void {

    this.llistRole();
    this.loginForm= this.formBuilder.group({
      username:['',Validators.required],
        prenom:['',Validators.required],
        nom:['',Validators.required],
        email:['',Validators.required],
        appRole:['',Validators.required],
        password:['',Validators.required],


        })
    }
    loginForm:FormGroup
    closedialog(){
      this.dialogRef.close();
    }

    Utilisateurs ={
      username: '',
      prenom: '',
      nom: '',
      email: '',
      password:'',
      appRole:{
        id:'',
      }

    }
    appRole={
      id:'',
      roleName:'',
    }
    listRole:any
  llistRole(){

     this.userservice.ListtypeRole().subscribe(
       (data: any)=>{
         console.log(data)
           this.appRole=data
         console.log("listRole"+JSON.stringify(this.appRole))
       },(err: any)=>{
         console.log(err)
       }
     )
   }
   capitalize(value: string): string {
    if (!value) {
      return '';
    }
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
  }
   formSubmitted=false
    userRegister(){
      this.formSubmitted=true
      const usernameValue = this.loginForm.get('username').value;
     this.Utilisateurs.username = this.capitalize(usernameValue);
     const usernameValue1 = this.loginForm.get('nom').value;
     this.Utilisateurs.nom = this.capitalize(usernameValue1);
     const usernameValue2 = this.loginForm.get('prenom').value;
     this.Utilisateurs.prenom = this.capitalize(usernameValue2);

              this.userservice.registerlistUser(this.Utilisateurs).subscribe(
           {
               next :(data:any )=>{
                this.closedialog();
              if(this.loginForm.valid){
                 console.log("goog"+this.Utilisateurs.username)
                }else{

                 }

                 },
                error:(err)=>{
                  console.log(err.status);

               }
             });


       }
  }
