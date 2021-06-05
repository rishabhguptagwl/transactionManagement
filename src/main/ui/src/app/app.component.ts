import { dailyTransactionServices } from './services/dailyTransaction.service';
import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'TransactionManagement';
  page:string;

  constructor(private _weekExpense: dailyTransactionServices) { }

  ngOnInit(){
    this.setHeight();
    // this._weekExpense.monthly().subscribe(data=>{
    //   console.log(data);
    // });
  }


   setHeight() {
    ($("#page").height(window.innerHeight+"px"));
    

  }




}
