import { Component, OnInit } from '@angular/core';
import { Inovice } from '../model/inovice';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.css']
})
export class InvoiceListComponent implements OnInit {

  public list!:Inovice[];
  constructor() {
    this.list=[
      {
        idFacture:1,
        montantFacture:120, 
        montantRemise:10, 
        dateFacture:"12/12/2021",
        active:true
      },
      {
        idFacture:2,
        montantFacture:1020,
        montantRemise:90,
        dateFacture:"22/11/2020",
        active:true
      },
      {
        idFacture:3,
        montantFacture:260, 
        montantRemise:30, 
        dateFacture:"18/10/2020",
        active:false
      },
      {
        idFacture:4,
        montantFacture:450,
        montantRemise:40,
        dateFacture:"14/12/2020",
        active:true
      },
    ]
    

   }
  
  ngOnInit(): void {
  }

  getInovice(index:number) {
    let i =new Inovice();
    console.log(this.list.find(e=>e.idFacture === index));
    return this.list.find(e=>e.idFacture === index);
   }
 

}
