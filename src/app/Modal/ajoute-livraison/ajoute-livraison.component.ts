import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { LivraisonSerive } from 'app/Service/livraison-serive';
import { ChaufeurService } from 'app/Service/chaufeur-service';
import { VehiculeService } from 'app/Service/vehicule-service';
import { HttpClient } from '@angular/common/http';
import { LieuxService } from 'app/Service/lieux-service';
import { TypeVehiculeService } from 'app/Service/type-vehicule-service';
import { ResponsableService } from 'app/Service/responsable-service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { Observable, Subscription, map, startWith } from 'rxjs';

@Component({
  selector: 'app-ajoute-livraison',
  templateUrl: './ajoute-livraison.component.html',
  styleUrls: ['./ajoute-livraison.component.scss']
})
export class AjouteLivraisonComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<AjouteLivraisonComponent>,private formBuilder: FormBuilder,private livraisonservice:LivraisonSerive,private chaufeurService:ChaufeurService,private vehiculeService:VehiculeService,private lieuxService:LieuxService,private typeVehiculeService:TypeVehiculeService,private responsableService:ResponsableService,
    private http: HttpClient) {


     }
  selectedFile: File;
  formSubmitted = false;
  listlieux:any
  myControl = new FormControl('');
  options: any[] = [];
  filteredOptions: any[] = [];
filteredOptionsSubscription: Subscription;
  selectedOption: any;
  lieuChargeControl = new FormControl();
  filteredListlieux: Observable<any[]>;
  listlieuxlivraisone=[
  ]
  ngOnInit(): void {
    this.filteredDataToSearch = this.unfilteredDataToSearch.map(w => {
      return {
        text: w.designation ,
        value: w.id
      };
    });
    this.filteredDataToSearch2 = this.listlieuxlivraisone.map(w => {
      return {
        text: w.designation ,
        value: w.id
      };
    });
    this.filteredListlieux = this.lieuChargeControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filterListlieux(value))
    );
    this.filteredOptionsSubscription = this.myControl.valueChanges
    .pipe(
      startWith(''),
      map(value => this._filter(value || ''))
    )
    .subscribe(options => {
      this.filteredOptions = options;
    });
    this.listLieuxlivraison();
    this.MlistResponsables();
    this.listtypeLieux();
    this.listtypevehicules();
   this.listLieux();
    this.listchaufeurs();
   this.loginForm= this.formBuilder.group({
    idlivraison:['',Validators.required],
    datePrevuCharge:['',Validators.required],
    lieucharge:['',Validators.required],
    lieuxlivraison:['',Validators.required],
    responsableCharge:['',Validators.required],
    heurePrevuCharge:['',Validators.required],
    dateLivraison:['',Validators.required],
    heureLivraison:['',Validators.required],
    dateDemande:['',Validators.required],
    heureDemande:['',Validators.required],
    quantite:['',Validators.required],
    note:['',Validators.required],
    designation:['',Validators.required],
    unite:['',Validators.required],
    statut:['',Validators.required],
    data:['',Validators.required],
    vehicule:['',Validators.required],
    chauffeur:['',Validators.required],
    chantier:['',Validators.required],
    typeVehicule:['',Validators.required],

      })
  }

  loginForm:FormGroup
    selectedLieu: any;
    name = "Angular";
    listLieux(){
      this.lieuxService.ListLieux().subscribe(
        (data: any)=>{
          console.log(data)
            this.listlieux=data
                 this.options=data
                 this.unfilteredDataToSearch = data.map(item => {
                  return {
                    id: item.id,
                    designation: item.designation
                  };
                });
                this.filteredDataToSearch = this.unfilteredDataToSearch;
        },(err: any)=>{
          console.log(err)
        }
      )
    }
    listLieuxx() {
      this.lieuxService.ListLieux().subscribe(
        (data: any) => {
          console.log(data);
         /*  this.listlieuxlivraison = data.map(item => {
            return {
              id: item.id,
              designation: item.designation
            };
          });
          this.filteredDataToSearch2 = this.listlieuxlivraison; */
        },
        (err: any) => {
          console.log(err);
        }
      );
    }









    unfilteredDataToSearch =[
      ]

   /*  unfilteredDataToSearch: any[] = [
      {
        id:'' ,
        Code:'',
        designation: '',
        type:'',
        adresse:'',
        statut:'',
        typelieux:{
          id:''
        }
      }


    ]; */
    lookup(e) {
      const searchTerm = e.toLowerCase();
      this.filteredDataToSearch = this.unfilteredDataToSearch.filter(
        item => item.designation.toLowerCase().includes(searchTerm)
      );
    }
    lookup2(e) {
      const searchTerm = e.toLowerCase();
      this.filteredDataToSearch2 = this.listlieuxlivraisone.filter(
        item => item.designation.toLowerCase().includes(searchTerm)
      );
    }
    clean(t){
      t.value = '';
      this.lookup(t.value);
    }

    filteredDataToSearch: any;
    filteredDataToSearch2: any;


    public beComponentForm: FormGroup = new FormGroup({
      slct_cntrl: new FormControl("")
    });
    getSelectedOptionLabel(): string {
      const selectedOption = this.filteredOptions.find(option => option.id === this.myControl.value);
      alert(selectedOption)
      return selectedOption ? `${selectedOption.id} - ${selectedOption.designation}` : '';
    }
  private _filterListlieux(value: string): any[] {
    const filterValue = value.toLowerCase();
    return this.listlieux.filter(lieu => lieu.designation.toLowerCase().includes(filterValue));
  }
  private _filter(value: string): any[] {
    const filterValue = value ? value.toLowerCase() : '';

    return this.options.filter(option => {
      const optionString = option && option.designation ? option.designation.toString().toLowerCase() : '';

      return optionString.includes(filterValue);
    });
  }

  optionSelected(event: MatAutocompleteSelectedEvent): void {
    this.FicheLivraison.lieucharge.id = event.option.value.id;

    console.log(this.FicheLivraison.lieucharge.id ,'Selected option:', event.option.value.id);
  }
  ChauffeurChange(chaufeur:any){
    this.FicheLivraison.chauffeur.id= chaufeur.target.value
   }
   lieuxChange(lieu:any){
    this.FicheLivraison.lieucharge.id= lieu.target.value
    alert(this.FicheLivraison.lieucharge.id)
   }
   vehiculesChange(vehicule:any){
    this.FicheLivraison.vehicule.id= vehicule.target.value
   }
   typevehiculesChange(vehicule:any){
    this.FicheLivraison.typeVehicule.id= vehicule.target.value
   this.listvehiculeBytypeIds( this.FicheLivraison.typeVehicule.id);

   }

   lieuxlivraisonChange(lieu:any){
    this.FicheLivraison.lieuxlivraison.id= lieu.target.value
   }
   typelieuxChargeChange(lieu:any){
    this.FicheLivraison.lieucharge.id= lieu.target.value
   }
   lieucharge(lieux:any){
    this.FicheLivraison.lieucharge= lieux.target.value
   }
   responsablechange(responsable:any){
    this.FicheLivraison.responsableCharge.id= responsable.target.value
   }
   onFileSelected(event) {
    this.selectedFile = event.target.files[0];
  }
  FicheLivraison =
    {
      datePrevuCharge: '',
      heurePrevuCharge:'',
      dateLivraison:'',
      heureLivraison:'',
      dateDemande:'',
      heureDemande:'',
      quantite:'',
      designation:'',
      unite:'',
      note:'',
      statut:'',
      vehicule: {
          id:  '0',
        },
        chauffeur: {
          id: '0',

        },
        lieuxlivraison: {
         id:'0',
         designation:''
         },
         filelivraison: {
          id:'0',

          },
          typeVehicule:{
            id:'',
          },
          lieucharge:{
            id:'',
          },
          responsableCharge:{
            id:''
          }

    }



    onLieuChargeSelected(selectedLieu: any) {
      // Handle the selected lieu here
      console.log(selectedLieu);
    }

  closedialog(){
    this.dialogRef.close();
  }
  listchaufeur:any
   listchaufeurs(){
      this.chaufeurService.Listchaufeur().subscribe(
        (data: any)=>{
          console.log(data)
            this.listchaufeur=data

          console.log("listchaufeur"+JSON.stringify(this.listchaufeur))
        },(err: any)=>{
          console.log(err)
        }
      )
    }
    listResponsables:any
   MlistResponsables(){
      this.responsableService.ListResponsable().subscribe(
        (data: any)=>{
          console.log(data)
            this.listResponsables=data

          console.log("listResponsables"+JSON.stringify(this.listResponsables))
        },(err: any)=>{
          console.log(err)
        }
      )
    }

    listLieuxlivraison(){
       this.lieuxService.ListLieux().subscribe(
         (data: any)=>{
           console.log(data)
           this.listlieuxlivraisone = data.map(item => {
            return {
              id: item.id,
              designation: item.designation
            };
          });
          this.filteredDataToSearch2 = this.listlieuxlivraisone;

           console.log("listlieuxlivraison"+JSON.stringify(this.listlieuxlivraisone))
         },(err: any)=>{
           console.log(err)
         }
       )
     }
    listtypelieux:any
    listtypeLieux(){
       this.lieuxService.ListTypeLieux().subscribe(
         (data: any)=>{
           console.log(data)
             this.listtypelieux=data

           console.log("listtypelieux"+JSON.stringify(this.listlieux))
         },(err: any)=>{
           console.log(err)
         }
       )
     }
    listvehicule:any
    listvehicules(){
       this.vehiculeService.ListVehicule().subscribe(
         (data: any)=>{
           console.log(data)
             this.listvehicule=data

           console.log("listvehicule"+JSON.stringify(this.listvehicule))
         },(err: any)=>{
           console.log(err)
         }
       )
     }
     listvehiculeBytypeId:any
     listvehiculeBytypeIds(id:any){
        this.vehiculeService.ListVehiculeByTypeId(id).subscribe(
          (data: any)=>{
            console.log(data)
              this.listvehiculeBytypeId=data

            console.log("listvehiculeBytypeId"+JSON.stringify(this.listvehiculeBytypeId))
          },(err: any)=>{
            console.log(err)
          }
        )
      }
     listtypevehicule:any
     listtypevehicules(){
        this.typeVehiculeService.GetTypeVehicule().subscribe(
          (data: any)=>{
            console.log(data)
              this.listtypevehicule=data

            console.log("listvehicule"+JSON.stringify(this.listvehicule))
          },(err: any)=>{
            console.log(err)
          }
        )
      }
      changedate(){
        if (this.FicheLivraison.dateLivraison < this.FicheLivraison.dateDemande) {
          this.FicheLivraison.dateLivraison = ""; // Clear the input
        }
      }
      checkDates() {
        if (this.FicheLivraison.dateLivraison < this.FicheLivraison.dateDemande) {
          this.FicheLivraison.dateLivraison = ""; // Clear the input
        }
      }
      checkDates2() {
        if (this.FicheLivraison.dateLivraison < this.FicheLivraison.datePrevuCharge) {
          this.FicheLivraison.dateLivraison = ""; // Clear the input
        }
      }

     Message=false
     MessageDate=false
     compareNumbers() {
        if(this.FicheLivraison.dateDemande  <= this.FicheLivraison.dateLivraison) {
          this.ajouterlistlivraison()
        }else{
          this.MessageDate=true

        }


    }
    Messagevehicule=false
    Messagelieux=false
    compareString(){
      if(this.FicheLivraison.lieucharge.id == this.FicheLivraison.lieuxlivraison.id ) {

       this.Messagelieux=true

      }else{
                this.compareNumbers();
                this.Messagelieux=false


      }

      }

  ajouterlistlivraison(){
    console.log("tojsoo"+JSON.stringify(this.FicheLivraison))
    this.formSubmitted= true
       this.livraisonservice.ajoutelistlivraison(this.FicheLivraison).subscribe(
         {


             next :(data:any )=>{

              this.dialogRef.close();


              if(this.loginForm.valid){
               console.log("goog"+this.FicheLivraison)
                  }else{

                }

               },
              error:(err)=>{
                console.log(err.status);

             }
           });


     }


    controlExecutionTimeReport() {
      setTimeout(() => {
        this.compareString();
      }, 1000);
    }
    onUploadfile() {
      this.livraisonservice.uploadFile(this.selectedFile)
        .subscribe(res => {
          console.log(res);
        });
    }

     }
