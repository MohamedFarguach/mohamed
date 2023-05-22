import { Component, ElementRef, Inject, OnInit, Optional } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { User } from 'app/Service/user';

@Component({
  selector: 'app-modefier-user',
  templateUrl: './modefier-user.component.html',
  styleUrls: ['./modefier-user.component.scss']
})
export class ModefierUserComponent implements OnInit {
  recievedID: any;


  constructor(public dialogRef: MatDialogRef<ModefierUserComponent>,private formBuilder: FormBuilder,
    public route:Router,private el: ElementRef,private userservice:User, @Optional() @Inject(MAT_DIALOG_DATA) public data: any ,) {{ this.recievedID = data } }
  ngOnInit(): void {
    this.affichevehicule();
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
  roleChange(role:any){
    this.Utilisateurs.appRole.id= role.target.value
  // alert(this.FicheMarque.marques.id)
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
  listUtilisateurs:any
   listUtilisateur(){
      this.userservice.ListtypeUtilisateurs().subscribe(
        (data: any)=>{
          console.log(data)
            this.listUtilisateurs=data

          console.log("listUtilisateurs"+JSON.stringify(this.listUtilisateurs))
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
    modefierUtilisateur(){
     this.formSubmitted=true
     const usernameValue = this.loginForm.get('username').value;
     this.Utilisateurs.username = this.capitalize(usernameValue);
     const usernameValue1 = this.loginForm.get('nom').value;
     this.Utilisateurs.nom = this.capitalize(usernameValue1);
     const usernameValue2 = this.loginForm.get('prenom').value;
     this.Utilisateurs.prenom = this.capitalize(usernameValue2);
     this.userservice.modefierUtilisateurs(this.Utilisateurs,this.recievedID.id).subscribe(
       {

           next :(daa:any )=>{
            console.log("goog")
            this.dialogRef.close();
             },
            error:(err)=>{
              console.log(err.status);

           }
         });


   }
   affichevehicule(){
    this.userservice.afficheUtilisateursbyid(this.recievedID.id).subscribe(
      (data: any)=>{

          this.Utilisateurs=data
      console.log(" this.Utilisateurs"+ JSON.stringify(this.Utilisateurs))
      },(err: any)=>{
        console.log(err)
      }
    )
  }
}
