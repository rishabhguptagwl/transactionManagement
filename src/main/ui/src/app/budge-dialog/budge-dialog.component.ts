import { MatDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import { dailyTransactionServices } from '../services/dailyTransaction.service';

@Component({
  selector: 'app-budge-dialog',
  templateUrl: './budge-dialog.component.html',
  styleUrls: ['./budge-dialog.component.css']
})
export class BudgeDialogComponent implements OnInit {
  noBudget:boolean;
  isUpdate:boolean;
  budget:string;
  constructor(private _httpserivce : dailyTransactionServices,public dialogRef: MatDialogRef<BudgeDialogComponent>) { }

  ngOnInit() {
    this.isUpdate = false;
    var date = new Date(); 
    this._httpserivce.getBudgetOfUser((date.getMonth() + 1) + "", date.getFullYear() + "").subscribe(data=>{
      console.log(data);
      if(data == null){
        this.noBudget = true;
      }
      else{
        var tmp = JSON.parse(JSON.stringify(data));
        this.budget = tmp.budget;
      }
    });
  }


  saveBudget(){
    var date = new Date(); 
    this._httpserivce.saveBudgetOfUser((date.getMonth() + 1) + "", date.getFullYear() + "",this.budget).subscribe(data=>{
      console.log("update",data);
      this.dialogRef.close({"message":"saved","budget":this.budget});
    })
  }

  changeFlagToUpdate(){
    this.noBudget = true;
    this.isUpdate = true;
  }

}
