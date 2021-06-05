import { Transaction } from '../../model/tranactionModel';
import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { transformAll } from '@angular/compiler/src/render3/r3_ast';
import { TransactionService } from '../../services/transaction.service';
import { MatSnackBar } from '@angular/material';



@Component({
  selector: 'app-insert-transaction',
  templateUrl: './insert-transaction.component.html',
  styleUrls: ['./insert-transaction.component.css']
})
export class InsertTransactionComponent implements OnInit {
  @Input() transactionArr :any;
  @Output() onSave = new EventEmitter();

  transaction: Transaction;
  TransactionDate:Date;
  amount: number;
  saving:number;
  maxDate:Date;

  constructor(private _transactionService: TransactionService,
              private snackBar: MatSnackBar) {
  }
  ngOnInit() {
    this.TransactionDate = new Date();
    this.maxDate = new Date();

  }
  save(form: any) {
    console.log("get month:"+this.TransactionDate.getMonth()+" value:" +(this.TransactionDate.getMonth()<=9) )
    this.transaction = {
      id: 0,
      amount: this.amount,
      save: this.saving,
      date: this.TransactionDate.getFullYear()+"-"+(this.TransactionDate.getMonth()<=9?"":"0")+(this.TransactionDate.getMonth()+1)+"-"+this.TransactionDate.getDate()
    };
    console.log();
    console.log("this is a transcation date",this.transaction);
    this._transactionService.SaveTransaction(this.transaction).subscribe(data => {
      console.log(data);
      if (data){
        this.openSnackBar("Your transaction is saved", "Success");
        this._transactionService.getAllTransaction().subscribe(data=>{
          this.transactionArr = data
          console.log(data);
        }
          );
    }
      else
        this.openSnackBar("Some error occured", "Error");
      this.clearControls(form);
    },error=>{
      this.openSnackBar("Some error occured", "Error");
    });

    this.onSave.emit();
  }
  clearControls(form: any) {
    form.controls.amount.value = "";
    form.controls.saving.value = "";
    form.controls.date.value = "";
  }
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }


}
