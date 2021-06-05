import { RouterModule } from '@angular/router';
import { globalConfigService } from './globalConfig.service';
import { DailyTransaction } from './../model/DailyTransaction.Model';
import { HttpClient } from '@angular/common/http';
import { Injectable, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class dailyTransactionServices{

    constructor(private _http:HttpClient,
                private globalConfig:globalConfigService,
                private Router:RouterModule){
    }

    public getAllTransaction():Observable<DailyTransaction[]>{
        return this._http.get<DailyTransaction[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/monthtilldate").pipe();
    }

    public saveTransaction(data,length):Observable<string>{
        var d = {"data":data,"length":length};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dailytransaction/savetest",d).pipe();
    }
// dailytransaction/updateDailyTransaction

public updateTransaction(data,date):Observable<string>{
    var d = {"data":data,"date":date};
    console.log(d)
    return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dailytransaction/updateDailyTransaction",d).pipe();
}


    public getTransactionByDate(form:string ,to:string):Observable<DailyTransaction[]>{
        var tmp = "{\"form\":\""+form+"\",\"to\":\""+to+"\"}";
        return this._http.post<DailyTransaction[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/getdailytransactionbydate",tmp).pipe();
    }

    public getTransactionWeekly(date:string):Observable<DailyTransaction[]>{
        var tmp = "{\"date\":\""+date+"\"}";
        return this._http.post<DailyTransaction[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/WeeklyTransaction",tmp).pipe();
    }

    public getTransactionToday(date:string):Observable<String>{
        var tmp = "{\"date\":\""+date+"\"}";
        return this._http.post<String>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/todaytransaction",tmp).pipe();
    }

    public getUnclassTransaction():Observable<object[]>{
        return this._http.get<object[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/unclasstransaction").pipe();
    }


    public saveUnclassTransaction(data:string,id:string,size:number):Observable<string>{
        var d = {"id":id,"data":data,"size":size};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/unclass/save",d).pipe();
    }

    public getTransactionByClass(date:string):Observable<Object[]>{
        var tmp = "{\"date\":\""+date+"\"}";
        // alert("service called");
        var tmpe:Observable<Object[]>;
        tmpe =  this._http.post<Object[]>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dailytransaction/Transactionbyclass",tmp).pipe();
        // alert("service return data");
        return tmpe;
    }

    public getMonthlyTransaction(month:string,year:string):Observable<string>{
        var load = {"month":month,"year":year};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dailytransaction/transactionMonthly",load).pipe();
    }


    public monthly():Observable<string>{
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/monthlytransaction",{role:1}).pipe();
    }

    public getMonthlyStats(month:string,year:string):Observable<string>{
        var load = {"month":month,"year":year};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dailytransaction/transactionmonthlystats",load).pipe();
    }


    public getIncomeOfUser(month:string,year:string):Observable<string>{
        var load = {"month":month,"year":year};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/income/getuserincome",load);
        // return this._http.post<string>("/income/getuserincome",load);
    }


    public SaveIncomeOfUser(month:string,year:string,income:string):Observable<string>{
        var load = {"month":month,"year":year,"income":income};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/income/saveincome",load);
    }

    public updateIncomeOfUser(month:string,year:string,income:string):Observable<string>{
        var load = {"month":month,"year":year,"income":income};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/income/updateincome",load);

    }

    public getBudgetOfUser(month:string,year:string):Observable<{id:number,month:number,year:number,userid:number,budget:number}>{
        var load = {"month":month,"year":year};
        return this._http.post<{id:number,month:number,year:number,userid:number,budget:number}>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/budget/getuserbudget",load);
    }

    public saveBudgetOfUser(month:string,year:string,budget:string):Observable<string>{
        var load = {"month":month,"year":year,"budget":budget};
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/budget/savebudget",load);
    }

    public deleteTransaction(date:string):Observable<string>{
        alert(date +"deleted");
        // return null;
        var load = {"date":date};
        console.log(load);
        return this._http.post<string>((this.globalConfig.isDev?this.globalConfig.devHostName:this.globalConfig.prodHostName)+"/dailytransaction/delete",load);
    }

}
