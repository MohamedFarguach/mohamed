import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { TypeVehiculeService } from 'app/Service/type-vehicule-service';
import { AjouterTypeLieuxComponent } from '../ajouter-type-lieux/ajouter-type-lieux.component';

@Component({
  selector: 'app-ajouter-type-vehicule',
  templateUrl: './ajouter-type-vehicule.component.html',
  styleUrls: ['./ajouter-type-vehicule.component.scss']
})
export class AjouterTypeVehiculeComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AjouterTypeVehiculeComponent>,private formBuilder: FormBuilder,public route:Router, private httpclient :HttpClient,private typeVehiculeService:TypeVehiculeService) { }

  FormTypeVehicule!:FormGroup
  ngOnInit(): void {
  this.FormTypeVehicule= this.formBuilder.group({

    type:['',Validators.required],


    })
  }
  TypeVehicule ={
    type:'',
  }

  closedialog(){
    this.dialogRef.close();
  }

  formSubmitted=false
  capitalize(value: string): string {
    if (!value) {
      return '';
    }
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
  }
  Ajouterlisttypevehicule(){
    this.formSubmitted=true
    const usernameValue = this.FormTypeVehicule.get('type').value;
    this.TypeVehicule.type = this.capitalize(usernameValue);       this.typeVehiculeService.ajoutelisttypevehicule(this.TypeVehicule).subscribe(
         {


             next :(data:any )=>{
              console.log("goog")
              this.dialogRef.close();
               },
              error:(err)=>{
                console.log(err.status);

             }
           });


     }
}

