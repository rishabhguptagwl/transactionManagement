import { DatePipe } from '@angular/common';
import { dailyTransactionServices } from './../services/dailyTransaction.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Component, OnInit, Inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-today-transaction',
  templateUrl: './edit-today-transaction.component.html',
  styleUrls: ['./edit-today-transaction.component.css']
})
export class EditTodayTransactionComponent implements OnInit {

  pipe = new DatePipe('en-US');
  dailyTransactionArr:any;

  constructor(public dialogRef: MatDialogRef<EditTodayTransactionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dailyTransactionServices:dailyTransactionServices,
    private router:Router
    ) { }

  ngOnInit() {
    this.loadTodayTransactionData(this.data.date);
  }

  loadTodayTransactionData(date:string):void{
    var tmp = date.split("-");
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));
    this.dailyTransactionServices.getTransactionToday(this.pipe.transform(date1, 'yyyy-MM-dd')).subscribe(data=>{
      var result = JSON.parse(JSON.stringify(data));
      this.dailyTransactionArr = result.Result;
    });
  }

  increaseDate() {
    var tmp = this.data.date.split("-");
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));
    date1.setDate(date1.getDate() + 1);
    if(date1> new Date()){
      alert("you can't see in future");
    }
    else{
      this.data.date = this.pipe.transform(date1, "dd-MM-yyyy");
      this.loadTodayTransactionData(this.data.date);
    }
  }

  decreaseDate() {
    var tmp = this.data.date.split("-");
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));
    var dayinMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    date1.setDate(date1.getDate() - 1)
    if (date1.getDate() === 0)
      date1.setDate((dayinMonths[date1.getMonth()]));
    this.data.date = this.pipe.transform(date1, "dd-MM-yyyy");
    // this.getTransaction(this.pipe.transform(date1, 'yyyy-MM-dd'));
    this.loadTodayTransactionData(this.data.date);
  }

  addTransaction():void{
    this.dailyTransactionArr.push({"amount":"","name":"","value":""});
  }

  saveTransaction():void{
    var tmp = this.data.date.split("-");
    console.log("updating transaction with date ",tmp);
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));
    this.dailyTransactionServices.updateTransaction(this.dailyTransactionArr,this.pipe.transform(date1, 'yyyy-MM-dd')).subscribe(data=>{
      var result = JSON.parse(JSON.stringify(data));
      if(result.status==="success"){
        this.dialogRef.close({"status":"success"})
        // this.router.navigate(['/']);
      }
      else{
        alert("Error");
      }
    });
  }

  remove(i:any):void{
    this.dailyTransactionArr.splice(i,1);
  }

}
