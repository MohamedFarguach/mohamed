import { Component, OnInit } from '@angular/core';
import { LegendItem, ChartType } from '../lbd/lbd-chart/lbd-chart.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AjouteLivraisonComponent } from 'app/Modal/ajoute-livraison/ajoute-livraison.component';
import { ModefierLivraisonComponent } from 'app/Modal/modefier-livraison/modefier-livraison.component';
import { Router } from '@angular/router';
import { LivraisonSerive } from 'app/Service/livraison-serive';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    public emailChartType: ChartType;
    public emailChartData: any;
    public emailChartLegendItems: LegendItem[];

    public hoursChartType: ChartType;
    public hoursChartData: any;
    public hoursChartOptions: any;
    public hoursChartResponsive: any[];
    public hoursChartLegendItems: LegendItem[];

    public activityChartType: ChartType;
    public activityChartData: any;
    public activityChartOptions: any;
    public activityChartResponsive: any[];
    public activityChartLegendItems: LegendItem[];

  constructor(public dialog:MatDialog,public route:Router,private livraisonSerive:LivraisonSerive) { }



  searchTerm: string = '';

  ngOnInit() {
      this.listlivraisons();


this.countn=5;
    }

    p : number=1;
    countn!:number
    itemsPerPage:number=this.countn
    FicheLivraison =
    {
      idlivraison: '',
      datePrevuCharge: '',
      lieucharge: '',
      responsablechargement: '',
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
          id:  '',
        },
        chauffeur: {
          id: '',

        },
        chantier: {
         id:'',

         },
         filelivraison: {
          id:'0',

          },

    }



    changea(n:any){
     this.countn=n.target.value
     console.log(this.countn)
      console.log(n.target.value)
    }
    test(){
      alert("test")
      this.route.navigate(['/test']);
    }
    opendialog(){
    // this.dialog.open(AjouteLivraisonComponent)
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = true; // Disable click outside of the dialog area to close
   //  this.dialog.open(AjouteLivraisonComponent, dialogConfig);
     const dialogRef=this.dialog.open(AjouteLivraisonComponent, dialogConfig);
     dialogRef.afterClosed().subscribe(() => {
      this.listlivraisons();
    });

      }
      //showButton = false;
      opendialogconsultation(id:number){
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
       const dialogRef= this.dialog.open(ModefierLivraisonComponent,{

         data: {id:id,showButton: true},

         disableClose: true ,

        });

        dialogRef.afterClosed().subscribe(result => {
          console.log(`Dialog result: ${result}`);
          this.listlivraisons();
        });


         }

      opendialogmodefier(id:number,fileid:number){
     const dialogConfig = new MatDialogConfig();
     dialogConfig.disableClose = true;


    const dialogRef= this.dialog.open(ModefierLivraisonComponent,{

      data: {id:id,fileid:fileid},

      disableClose: true ,

     });

     dialogRef.afterClosed().subscribe(result => {
       console.log(`Dialog result: ${result}`);
       this.listlivraisons();
     });


      }
      listlivraison:any[] =  [];

      listlivraison2 : any[] = [];
      listlivraisons(){
         this.livraisonSerive.Listlivraison().subscribe(
           (data: any)=>{
             console.log(data)
               this.listlivraison=data
               this.listlivraison2=data
               return this.listlivraison=data ;
           },(err: any)=>{
             console.log(err)
           }
         )
       }



       deletlivraison(fich:any) {
         this.livraisonSerive.deletlivraison(fich).subscribe({
          next:()=>{
           this.listlivraisons()
          },
         error:err=>{

         }

         })

        }
        download(fich:any) {
          this.livraisonSerive.getData(fich).subscribe(response => {
            const blob = new Blob([response], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'data.pdf';
            a.click();
          });
        }
        getfile(fich:any) {

           this.livraisonSerive.getBytes(fich).subscribe({
            next:()=>{

            },
           error:err=>{

           }

           })

          }

       deletFile(fich:any) {
         this.livraisonSerive.deletfile(fich).subscribe({
          next:()=>{

          },
         error:err=>{

         }

         })

        }

        controlExecutionTimeDeleteFile(fiche:any) {

          setTimeout(() => {
            this.deletFile(fiche);
          }, 1000);
        }
        filterData(searchTerm: string) {
          this.listlivraison = this.listlivraison2.filter((item) => item.lieucharge.designation.toLowerCase().includes(searchTerm));

        }


}
