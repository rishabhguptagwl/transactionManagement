import { RouterModule, Router } from '@angular/router';
import { KPICalculationService } from './../services/KPICalculation.service';
import { browser } from 'protractor';
import { globalValues } from './../services/globalValues';
import { IncomeDialogComponent } from './../income-dialog/income-dialog.component';
import { Transaction } from './../model/tranactionModel';
import { DatePipe } from '@angular/common';
import { dailyTransactionServices } from './../services/dailyTransaction.service';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar, MatDialog } from '@angular/material';
import { NoTransactionEntryDialogComponent } from '../no-transaction-entry-dialog/no-transaction-entry-dialog.component';
import { BudgeDialogComponent } from '../budge-dialog/budge-dialog.component';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  pipe = new DatePipe('en-US');
  constructor(private _httpdailyTransaction: dailyTransactionServices,
              private snackBar: MatSnackBar,
              private dialog: MatDialog,
              private globalValue: globalValues,
              public KPIValues: KPICalculationService,
              public KPICalculationService: KPICalculationService,
              public router:Router) { }

  isMobile: boolean;
  exp_today: number;
  exp_week: number;
  exp_month: number;
  avg_exp: number;
  income: number;
  budget: number;

  showMobileView: string
  ngOnInit() {
    if (window.innerWidth < 768) {
      this.isMobile = true;
      this.showMobileView = "collapse"
    }
    else {
      this.isMobile = false;
      this.showMobileView = "";
    }
    this.exp_today = 0;
    var date = new Date();
    this._httpdailyTransaction.getIncomeOfUser((date.getMonth() + 1) + "", date.getFullYear() + "").subscribe(data => {
      if (data == null) {
        this.income = 0
      }
      else {
        var tmp = JSON.parse(JSON.stringify(data));
        this.income = tmp.income;
        console.log("income", this.income);
      }
    })


    this._httpdailyTransaction.getBudgetOfUser((date.getMonth() + 1) + "", date.getFullYear() + "").subscribe(data => {
      console.log(data);
      // var tmp = JSON.parse(JSON.stringify(data));
      if (data == null)
        this.budget = 0;
      else
        this.budget =data.budget
      this.globalValue.budgetMonth = this.budget;
    });


    this._httpdailyTransaction.getTransactionToday(this.pipe.transform(Date.now(), 'yyyy-MM-dd')).subscribe(form => {
      var t;
      if (form != null) {
        t = JSON.parse(JSON.stringify(form));
        t = t.Result;
        if (t === "No data found") {
          this.openSnackBar("No transaction for today", "Error");
          this.dialog.open(NoTransactionEntryDialogComponent, { data: { "isMobile": this.isMobile } }).afterClosed().subscribe(data => {
            console.log(data);
            if (data == undefined || data == false) {
              this.openSnackBar("No transaction added", "");
            }
            else {
              // document.location.reload();
              this.router.navigate(['dashboard'])
              // document.location.href = "/dashboard";
            }
          });
        }
        else{
          console.log(t);
        for (var i = 0; i < t.length; i++) {
          this.exp_today += (parseInt(t[i].amount));
          console.log(this.exp_today)
        }
        this.globalValue.expenseToday = this.exp_today;
        }
      }
      else {
        this.openSnackBar("No transaction for today", "Error");
        this.dialog.open(NoTransactionEntryDialogComponent, { data: { "isMobile": this.isMobile } }).afterClosed().subscribe(data => {
          console.log(data);
          if (data == undefined || data == false) {
            this.openSnackBar("No transaction added", "");
          }
          else {
            document.location.reload();
            // document.location.href = "/dashboard";
          }
        });
      }

    });

    this._httpdailyTransaction.getTransactionWeekly(this.pipe.transform(Date.now(), 'yyyy-MM-dd')).subscribe(form => {
      var t;
      this.exp_week = 0
      if (form != null) {
        for (var j = 0; j < form.length; j++) {
          t = JSON.parse(form[j].transaction + "");
          for (var i = 0; i < t.transactions.length; i++) {
            this.exp_week += (parseInt(t.transactions[i].amount));
          }
        }
        this.globalValue.expenseWeek = this.exp_week;
        // console.log(this.exp_week);
      }
      else {
        this.openSnackBar("No transaction for week", "Error");
      }
    });

    this._httpdailyTransaction.getAllTransaction().subscribe(form => {
      // console.log("Called for calculating sum of monthly transaction",form);
      var t;
      this.exp_month = 0;

      if (form != null) {
        for (var j = 0; j < form.length; j++) {
          t = JSON.parse(form[j].transaction + "");
          var tmp = 0
          for (var i = 0; i < t.transactions.length; i++) {
            tmp += parseInt(t.transactions[i].amount);
            this.exp_month += (parseInt(t.transactions[i].amount));
          }
        }
        this.globalValue.expenseMonth = this.exp_month;
        this.avg_exp = this.exp_month / parseInt((this.pipe.transform(Date.now(), 'dd')));
        var a = this.avg_exp;
        this.avg_exp = parseInt(a.toPrecision(5));
        console.log(this.avg_exp);
      }
      else {
        this.openSnackBar("No transaction for month", "Error");
      }
    });



    this.KPICalculationService.UpdateKPIValues(new Date().getMonth() + 1, new Date().getFullYear() + "");
  }

  openIncomeDialog() {
    this.dialog.open(IncomeDialogComponent, { width: "300px", height: "200px" }).afterClosed().subscribe(data => {
      if (data != null)
        this.openSnackBar("Your income is " + data.message, data.message);
      console.log("afterclose", data);
      this.income = parseInt(data.income);
    });
  }


  openBudgeDialog() {
    this.dialog.open(BudgeDialogComponent, { width: "300px", height: "200px" }).afterClosed().subscribe(data => {
      if (data != null)
        this.openSnackBar("Your bugdet is " + data.message, data.message);
      console.log("afterclose", data);
      this.budget = parseInt(data.budget);
    });
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 2000,
    });
  }
}
