import { RouterModule, Router } from '@angular/router';
import { dailyTransactionServices } from './../services/dailyTransaction.service';
import { Component, OnInit } from '@angular/core';
import { stringify } from 'querystring';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-daily-transaction',
  templateUrl: './daily-transaction.component.html',
  styleUrls: ['./daily-transaction.component.css']
})
export class DailyTransactionComponent implements OnInit {

  constructor(private _dailyTransactionService: dailyTransactionServices,
              private snackBar: MatSnackBar,
              private router:Router) { }

  DailyTransaction: number;
  todayDate:Date;
  defaultDateValue:string;

  ngOnInit() {
    this.todayDate = new Date();
    this.DailyTransaction = 1;
    this.defaultDateValue = this.todayDate.getFullYear()+"-"+this.todayDate.getMonth()+"-"+this.todayDate.getDate();
    this._dailyTransactionService.getAllTransaction().subscribe((data) => {
      console.log(data);
    }, (error) => {
      console.log(error);
    });
  }

  // Increase the count of transcation
  incDailyTransaction() {
    this.DailyTransaction++;
  }


  //Decrease the count of transaction
  decDailyTransaction() {
    if (this.DailyTransaction != 1)
      this.DailyTransaction--;
  }


  createRange(number) {
    var items: number[] = [];
    for (var i = 1; i <= number; i++) {
      items.push(i);
    }
    return items;
  }

  // showAlert(currentElementIndex) {
  //   alert(currentElementIndex);
  // }

  //To save the transaction created by user
  saveDailyTransaction(form: any) {
    var date  = this.todayDate.getFullYear()+"-"+(this.todayDate.getMonth()>9?(this.todayDate.getMonth()+1)+"":("0"+(this.todayDate.getMonth()+1)))+"-"+(this.todayDate.getDate()<10? "0"+this.todayDate.getDate():this.todayDate.getDate());
    form.value.transDate=date;
    console.log(date);
    this._dailyTransactionService.saveTransaction(form.value, this.DailyTransaction).subscribe((data) => {

      if (stringify(data).split("=")[1] === "Success") {
        this.openSnackBar("Transaction save","Success");
        this.DailyTransaction = 0;
        setTimeout(() => {
          this.DailyTransaction=1;
        }, 20);
        // this.router.navigate(['/']);
      }
    });
  }

  //To show the message to user
  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }

}
