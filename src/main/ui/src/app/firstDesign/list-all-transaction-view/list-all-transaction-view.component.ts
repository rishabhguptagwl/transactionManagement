import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../model/tranactionModel';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-list-all-transaction-view',
  templateUrl: './list-all-transaction-view.component.html',
  styleUrls: ['./list-all-transaction-view.component.css']
})
export class ListAllTransactionViewComponent implements OnInit {
  @Input() transactions :any;

  constructor(private _transactionService:TransactionService) { }
  ngOnInit() {

    // this.transactions = this._transactionService.transactions
  }






}
