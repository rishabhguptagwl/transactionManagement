import { dailyTransactionServices } from './../services/dailyTransaction.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-income-dialog',
  templateUrl: './income-dialog.component.html',
  styleUrls: ['./income-dialog.component.css']
})
export class IncomeDialogComponent implements OnInit {
  income: number
  updateincome: boolean
  addIncome: boolean
  showupdatebutton:boolean;
  constructor(private _httpdailyTransaction: dailyTransactionServices,  public dialogRef: MatDialogRef<IncomeDialogComponent>) { }

  ngOnInit() {
    this.income = null;
    


    var date = new Date();
    this._httpdailyTransaction.getIncomeOfUser((date.getMonth() + 1) + "", date.getFullYear() + "").subscribe(data => {
      console.log(data);
      var d= JSON.parse(JSON.stringify(data));
      if (data == null) {
        console.log("income not set")
        this.addIncome = true;
        this.updateincome = false;
        this.showupdatebutton = false;
      }
      else{
        // console.log(d);
        this.income = parseInt(d.income);
        this.addIncome = false;
        this.updateincome= true;
        this.showupdatebutton = false;

      }
    });

  }


  changeFlagToUpdate(){
    this.addIncome = ! this.addIncome;
    this.updateincome = ! this.updateincome;
    this.showupdatebutton = true;
  }
  saveIncome() {
    var date = new Date();
    console.log("income going to save "+ this.income+(date.getMonth() + 1) + "", date.getFullYear() + "");
    this._httpdailyTransaction.SaveIncomeOfUser((date.getMonth() + 1) + "",date.getFullYear() + "",this.income+"").subscribe(data=>{
      console.log(data);
    });
    this.dialogRef.close({"message":"saved","income":this.income});
  }

  updateIncome(){
    var date = new Date();
    this._httpdailyTransaction.updateIncomeOfUser((date.getMonth() + 1) + "",date.getFullYear() + "",this.income+"").subscribe(data=>{
      console.log(data);
      this.dialogRef.close({"message":"update","income":this.income});
    });
  }
}
