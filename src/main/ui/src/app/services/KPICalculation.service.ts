import { DatePipe } from '@angular/common';
import { dailyTransactionServices } from './dailyTransaction.service';
import { globalValues } from './globalValues';
import { Injectable } from "@angular/core";

@Injectable()
export class KPICalculationService {
    constructor(private globalValues: globalValues, private _httpdailyTransaction: dailyTransactionServices) {
        this.expenseToday = 0;
        this.expenseWeek = 0;
        this.expenseMonth = 0;
        this.expenseAverage = 0;
        this.monthlyBudge = 0;
        this.monthlyIncome = 0;
    }
   public expenseToday: number;
   public expenseWeek: number;
   public expenseMonth: number;
   public expenseAverage: number;
   public monthlyBudge: number;
   public monthlyIncome: number;
    pipe = new DatePipe('en-US');




    public UpdateKPIValues(month: number, year: string) {

            /* Today Transaction calcualtion */
        this._httpdailyTransaction.getTransactionToday(this.pipe.transform(Date.now(), 'yyyy-MM-dd')).subscribe(form => {
            var t;
            if (form != null) {
                t = JSON.parse(JSON.stringify(form));
                t = t.Result;
                console.log(t);
                for (var i = 0; i < t.length; i++) {
                    this.expenseToday += (parseInt(t[i].amount));
                    console.log(this.expenseToday)
                }
                // console.log(this.exp_today);
            }

        });

            /* week Transaction calcualtion */
        this._httpdailyTransaction.getTransactionWeekly(this.pipe.transform(Date.now(), 'yyyy-MM-dd')).subscribe(form => {
            var t;
            this.expenseWeek = 0
            if (form != null) {
                for (var j = 0; j < form.length; j++) {
                    t = JSON.parse(form[j].transaction + "");
                    for (var i = 0; i < t.transactions.length; i++) {
                        this.expenseWeek += (parseInt(t.transactions[i].amount));
                    }
                }
                // console.log(this.exp_week);
            }
            else {
            }
        });

                /*month Transaction calcualtion */
                
        this._httpdailyTransaction.getMonthlyTransaction((month-1)+"",year).subscribe(form => {

            var form_=JSON.parse(JSON.stringify(form)).transaction;
            var t;
            this.expenseMonth = 0;
            console.log("Called for calculating sum of monthly transaction",form_);

            if (form_ != null) {
                for (var j = 0; j < form_.length; j++) {
                    console.log(form_[j].Transactions);
                    t = form_[j];
                    var tmp = 0
                    for (var i = 0; i < t.Transactions.length; i++) {
                        tmp += parseInt(t.Transactions[i].amount);
                        this.expenseMonth += (parseInt(t.Transactions[i].amount));
                    }
                }
                console.log("Expense month",this.expenseMonth);
                this.expenseAverage = this.expenseMonth / parseInt((this.pipe.transform(Date.now(), 'dd')));
                var a = this.expenseAverage;
                this.expenseAverage = parseInt(a.toPrecision(5));
            }
            else {
            }
        });

        this._httpdailyTransaction.getIncomeOfUser((month)+"",year+"").subscribe(data=>{
            if(data == null){
              this.monthlyIncome =0
            }
            else
            {
              var tmp = JSON.parse(JSON.stringify(data));
              this.monthlyIncome= tmp.income;
              console.log("income",this.monthlyIncome);
            }
          })


        // this.expenseMonth = 6000;
        // this.expenseAverage = 240;
        // this.monthlyBudge = 6000;
        // this.monthlyIncome = 12000;


        this._httpdailyTransaction.getBudgetOfUser((month)+"",year).subscribe(data=>{
            console.log(data);
            var tmp = JSON.parse(JSON.stringify(data));
            if(data == null)
              this.monthlyBudge = 0;
            else
            this.monthlyBudge = tmp.budget;
          });
    }

}