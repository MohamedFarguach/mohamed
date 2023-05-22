import { Component, ElementRef, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';

declare const $: any;
declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/Vehicules', title: 'Vehicules',  icon:'pe-7s-car', class: '' },
    { path: '/Marques', title: 'Marques',  icon:'pe-7s-shield', class: '' },
    { path: '/Chauffeurs', title: 'Chauffeurs',  icon:'pe-7s-user', class: '' },
     { path: '/Lieux', title: 'Lieux',  icon:'pe-7s-home', class: '' },
    // { path: '/typography', title: 'Utilisateurs',  icon:'pe-7s-users', class: '' },
    { path: '/Types-des-Lieux', title: 'Types des Lieux',  icon:'pe-7s-help1', class: '' },
  { path: '/Types-des-Véhicules', title: 'Types des Véhicules',  icon:'pe-7s-timer',  class:"" },
  { path: '/Responsable', title: 'Responsable Charge',  icon:'pe-7s-more',  class:"" },

];
export const ROUTA: RouteInfo[] = [


  { path: '/Livraisons', title: 'Livraisons',  icon: 'pe-7s-note2', class: '' },

  // { path: '/upgrade', title: '',  icon:'', class: 'active-pro' },
];
export const ROUTE: RouteInfo[] = [
  { path: '/Utilisateurs', title: 'Utilisateurs',  icon:'pe-7s-users', class: '' },


 // { path: '/upgrade', title: '',  icon:'', class: 'active-pro' },
];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html'
})
export class SidebarComponent implements OnInit {
  menuItems: any[];
  menuItem: any[];
  menuIte: any[];

  constructor(public router :Router,private elementRef: ElementRef) { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    this.menuItem = ROUTE.filter(menuItem => menuItem);
    this.menuIte = ROUTA.filter(menuItem => menuItem);


  }
  isMobileMenu() {
      if ($(window).width() > 991) {
          return false;
      }
      return true;
  };
  LogOut(){
    window.localStorage.clear();
    this.router.navigate(['/login']);
  }
  margin: boolean = false

  entre(){

    if( this.margin==false){
      this.marginn2=false
      this.margin=true


    }else{
      this.margin=false
    }
  }
  marginn2: boolean = false
  margin2(){

    if( this.marginn2==false){
      this.marginn2=true
      this.margin=false


    }else{
      this.marginn2=false
    }
  }
  margin3: boolean = false
  margin32(){

    if( this.margin3==false){
      this.marginn2=false
      this.margin=false


    }else{
      this.marginn2=false
    }
  }
  margin34(){

    if( this.marginn2==true){
      this.marginn2=false
      this.margin=false
      this.margin3=false


    }else {
      this.marginn2=false
      this.margin=false
      this.margin3=false

    }
  }
  margin33(){

    if( this.margin==true){
      this.marginn2=false
      this.margin=false
      this.margin3=false


    }else {
      this.marginn2=false
      this.margin=false
      this.margin3=false

    }
  }
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent) {
    if (!this.elementRef.nativeElement.contains(event.target)) {
      this.marginn2 = false;
      this.margin = false;
    }
  }
}
