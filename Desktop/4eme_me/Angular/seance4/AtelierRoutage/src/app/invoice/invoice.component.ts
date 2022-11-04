import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { error } from 'console';
import { InvoiceListComponent } from '../invoice-list/invoice-list.component';
import { Inovice } from '../model/inovice';
@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {
   ngg=new InvoiceListComponent();
   id!: any;
   active!: any;
   i!:any ;

  constructor(private activatedroute : ActivatedRoute)//injection de service activatedroute
   {
    this.activatedroute.paramMap.subscribe((params) => {
      this.id = params.get('idFacture');
      this.active = params.get('active');
     console.log(this.id);
     console.log(this.active);
     //console.log(this.ngg.list.find(item => item.idFacture === Number(this.id)));
    this.i=(this.ngg.list.find(item => item.idFacture === Number(this.id)));
     console.log(this.i);
 });
   }

  ngOnInit(): void {
  
  
  }

 

}
