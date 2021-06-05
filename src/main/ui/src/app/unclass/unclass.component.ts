import { Component, OnInit } from '@angular/core';
import { dailyTransactionServices } from '../services/dailyTransaction.service';
import { DailyTransaction } from '../model/DailyTransaction.Model';

interface dailyExp{
  id:number;
  transaction:string;
}


@Component({
  selector: 'app-unclass',
  templateUrl: './unclass.component.html',
  styleUrls: ['./unclass.component.css']
})
export class UnclassComponent implements OnInit {

  constructor(private _unclass:dailyTransactionServices) { }
  // d : DailyTransaction[];
  d:dailyExp[];


  ngOnInit() {
    // this.d=[];
    // this._unclass.getUnclassTransaction().subscribe(data=>{
    //   for(let i=0;i<data.result.length;i++){
    //     this.d.push({id:data.result[i].id,transaction:(JSON.parse(data.result[i].transaction))});
    //   }
    //   console.log(this.d);
    // });
    this.populateData();
  }


  populateData(){
    this.d=[];
    this._unclass.getUnclassTransaction().subscribe(data=>{


      console.log("xxxxxxxxxxxxxxxxxxxxxxxxxx");
      console.log(JSON.parse(JSON.stringify(data)));
      var tmp = JSON.parse(JSON.stringify(data));
      console.log("xxxxxxxxxxxxxxxxxxxxxxxxxx");
      for(let i=0;i<tmp.result.length;i++){
        this.d.push({id:tmp.result[i].id,transaction:(JSON.parse(tmp.result[i].transaction))});
      }
      console.log(this.d);
    });
  }


  save(form:any,id:any,index:any){
    // console.log(index);
    this._unclass.saveUnclassTransaction(form.value,id,parseInt(index)).subscribe(data=>{
      if(data==="success")
        {
          console.log("success");
          // this.populateData();
        }
        else{
          alert("Server error");
        }
    })
  }

}
