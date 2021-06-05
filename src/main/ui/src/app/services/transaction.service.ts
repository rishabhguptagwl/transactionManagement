import { globalConfigService } from './globalConfig.service';
import { Transaction } from './../model/tranactionModel';
import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpParams } from '@angular/common/http';
import { Observable, from } from 'rxjs';

export interface datepar {
  from: string;
  to: string;
}



@Injectable()
export class TransactionService {

  public transactions: Transaction[];

  constructor(private _http: HttpClient,private globalConfig:globalConfigService) {

  }
  public getAllTransaction():Observable<Transaction[]> {

    return this._http.get<Transaction[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dashboard/getAll").pipe();

  }

  public SaveTransaction(transaction: Transaction): Observable<object> {
    return this._http.post((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dashboard/saveSingleRest", transaction).pipe();
  }


  public populateTransaction(){
    this._http.get<Transaction[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dashboard/getAll").pipe().subscribe(data=>{
    console.log("populate Transaction",data);
    this.transactions = data;

    });
  }




  public getTransactionBetweenDates(formdate, todate): Observable<Transaction[]> {
    let url = new HttpParams();
    let d;
    // console.log(formdate +" :"+ todate);
    url.set("from", formdate);
    url.set("to", todate);
    console.log(url.toString());

    d = {
      from: formdate,
      to: todate
    }
    return this._http.post<Transaction[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dashboard/getTransactionBetweenDates", d).pipe();

  }




}
