import { TransactionService } from '../../services/transaction.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-transaction-view',
  templateUrl: './transaction-view.component.html',
  styleUrls: ['./transaction-view.component.css']
})
export class TransactionViewComponent implements OnInit {

  transactionArr:any;

  constructor(private _transactionService:TransactionService) { }

  ngOnInit() {
    // this._transactionService.populateTransaction();
    // this.transactionArr = this._transactionService.transactions;
    this._transactionService.getAllTransaction().subscribe(data=>{
      this.transactionArr = data;
      // console.log("daily Transactions ",data);
    });
  }


  OnSave(){
    console.log("transaction saved");
  }

}
