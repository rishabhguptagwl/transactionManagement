import { MatDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-no-transaction-entry-dialog',
  templateUrl: './no-transaction-entry-dialog.component.html',
  styleUrls: ['./no-transaction-entry-dialog.component.css']
})
export class NoTransactionEntryDialogComponent implements OnInit {
  landingOption:boolean;
  addTransaction:boolean;
  constructor( public dialogRef: MatDialogRef<NoTransactionEntryDialogComponent>) { }

  ngOnInit() {
    this.landingOption= true;
  }

  addtransaction(){
    this.landingOption= false;
    $("#cdk-overlay-1").height(500);
    this.addTransaction = true;
  }


  close(){
    this.dialogRef.close(this.addtransaction);
  }


  closeWithoutAddingTransaction(){
    this.dialogRef.close();
  }

}
