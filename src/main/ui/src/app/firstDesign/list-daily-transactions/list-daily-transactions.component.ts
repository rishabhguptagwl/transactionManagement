import { Component, OnInit } from '@angular/core';
import { dailyTransactionServices } from '../../services/dailyTransaction.service';
import { DailyTransaction } from '../../model/DailyTransaction.Model';
// import { totalmem } from 'os';
import * as d3 from 'd3';
import * as $ from 'jquery';
import { formatDate, DatePipe } from '@angular/common';

interface temp {
  date: string;
  trans: any;
}


@Component({
  selector: 'app-list-daily-transactions',
  templateUrl: './list-daily-transactions.component.html',
  styleUrls: ['./list-daily-transactions.component.css']
})
export class ListDailyTransactionsComponent implements OnInit {
  arrDailyTransaction: DailyTransaction[];
  arr: temp[];
  dt: any;
  total: any;
  iserror: boolean;
  error: any;
  pipe = new DatePipe('en-US');
  constructor(private _httpdailyTransaction: dailyTransactionServices) { }

  ngOnInit() {

    this.iserror = false;
    this.dt = [];
    this.arr = [];
    this.total = [];
    this._httpdailyTransaction.getAllTransaction().subscribe((data) => {
      this.arrDailyTransaction = data;
      var chartData = "";
      for (let i = 0; i < data.length; i++) {
        var date = (this.arrDailyTransaction[i].date.toString());
        var tran = JSON.parse(this.arrDailyTransaction[i].transaction + "");
        this.arr.push({
          date: date,
          trans: tran
        });
        var total = 0;
        // console.log(tran.transactions.length);
        for (let j = 0; j < tran.transactions.length; j++) {
          total += parseInt(tran.transactions[j].amount);
        }
        this.total.push({ "letter": date, "frequency": total });
        // chartData += '{"letter":'+date+',"frequency":'+total+'}';
      }
      chartData = JSON.stringify(this.total);
      // console.log(chartData);
      this.drawBarChart(this.total, this.arr);


      window.onresize = (e) => {
        //  / console.log("resized"+window.innerWidth+" "+window.innerHeight);
        this.drawBarChart(this.total, this.arr);
      };
    },
      error => {
        alert(error);
        this.iserror = true;
        this.error = error.message;
      }
    );

  }



  drawBarChart(data, expenses): void {
    console.log("expense",expenses);
    console.log("data",data);
    var div = d3.select("body").append("div")
      .attr("class", "tooltip")
      .style("padding", "6px")
      .style("background", "lightsteelblue")
      .style("border", "1px solid black")
      .style("border-radius", "5px")
      // .attr("style","padding:6px")
      // .attr("style","border:1px solid black")
      // .attr("style","background:lightsteelblue")
      .style("opacity", 0);
    $("#chart").html("");
    // console.log(alert(window.innerWidth));
    // width = window.innerWidth > 500 ? 500 : window.innerWidth;
    // console.log(width);
    width= (document.getElementById('chart-container').clientWidth);
    console.log(width);
    // width = width - (width*2)/100;
    console.log(width);
    var svg = d3.select("#chart").append("svg").attr("width", width).attr("height", "400").attr('viewBox', '0 0 ' + Math.min(500, 400) + ' ' + Math.min(500, 400))
      .attr('preserveAspectRatio', 'xMinYMin'),
      margin = { top: 100, right: 20, bottom: 100, left: 40 },
      width = +svg.attr("width") - margin.left - margin.right,
      height = +svg.attr("height") - margin.top - margin.bottom;

    var x = d3.scaleBand().rangeRound([0, width]).padding(0.1),
      y = d3.scaleLinear().rangeRound([height, 0]);

    var g = svg.append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
    // console.log(data.length);
    x.domain(data.map(function (d) { return d.letter; }));
    y.domain([0, d3.max(data, function (d) { return d.frequency; })]);

    g.append("g")
      .attr("class", "axis axis-x")
      .attr("transform", "translate(0," + height + ")")
      .call(d3.axisBottom(x)).selectAll("text")
      .style("text-anchor", "end")
      .attr("dx", "-.8em")
      .attr("dy", ".15em")
      .attr("transform", "rotate(-65)");

    g.append("g")
      .attr("class", "axis axis-y")
      .call(d3.axisLeft(y).ticks(10))
      .append("text")
      .attr("transform", "rotate(-90)")
      .attr("y", 6)
      .attr("dy", "0.71em")
      .attr("text-anchor", "end")
      .text("Frequency");

    g.selectAll(".bar")
      .data(data)
      .enter().append("rect")
      .attr("class", "bar").attr("fill", "steelblue")
      .attr("x", function (d) { return x(d.letter); })
      .attr("y", function (d) { return y(d.frequency); })
      .attr("width", x.bandwidth())
      .attr("height", function (d) { return height - y(d.frequency); }).on("mouseover", function (d, i) {
        div.transition()
          .duration(400)
          .style("opacity", 1);
        div.style("left", (d3.event.pageX) + "px")
          .style("top", (d3.event.pageY - 28) + "px");
        var exp = "";
        for (let k = 0; k < expenses[i].trans.transactions.length; k++) {
          exp += "<div>" + expenses[i].trans.transactions[k].name + "(" + expenses[i].trans.transactions[k].amount + ")" + "</div>"
        }
        div.html(exp);
        // console.log("hover in");
      }).on("mouseout", function (d, i) {
        div.style("opacity", 0);
        div.transition()
          .duration(200)
        // console.log("hover out");
      })
    // console.log(x);
    // console.log(d3.selectAll(".bar"));
    var tmp = 17;
    svg.selectAll("text.bar")
      .data(data)
      .enter().append("text")
      .attr("class", "t")
      .attr("font-size", "10px")
      .attr("text-anchor", "middle")
      .attr("x", function (d, i) {
        console.log("xxxxxxxxxxxxxxxxxxxxxxxxxx");
        console.log(($(".bar")[i]as any).x.animVal.value);
        console.log("xxxxxxxxxxxxxxxxxxxxxxxxxx");
       return (($(".bar")[i]as any).x.animVal.value + 55);       //---------------
        // console.log(i);
        // tmp = tmp + x.bandwidth();
        // return tmp;
      })
      .attr("y", function (d) {
        var tmp = y(d.frequency);
        return tmp == 0 ? (margin.top - 10) : (tmp + (margin.top - 10));
      })
      .text(function (d, i) { return data[i].frequency; });

    svg.append("text").text("Datewise expense").attr("transform", "translate(100,30)").attr("font-size", "30px");
  }


  filter(data: any): void {
    console.log(data.value.formDate + "::" + data.value.toDate);

    var form;
    var to;
    // const now = Date.now();
    form = this.pipe.transform(data.value.formDate, 'yyyy-MM-dd');
    to = this.pipe.transform(data.value.toDate, 'yyyy-MM-dd');
    // console.log(form+ "::" + to);
    this._httpdailyTransaction.getTransactionByDate(form,to).subscribe(data => {
      this.arrDailyTransaction = data;
      var chartData = "";
      this.arr = [];
      this.total = [];
      for (let i = 0; i < data.length; i++) {
        var date = (this.arrDailyTransaction[i].date.toString());
        var tran = JSON.parse(this.arrDailyTransaction[i].transaction + "");
        this.arr.push({
          date: date,
          trans: tran
        });
        var total = 0;
        // console.log(tran.transactions.length);
        for (let j = 0; j < tran.transactions.length; j++) {
          total += parseInt(tran.transactions[j].amount);
        }
        this.total.push({ "letter": date, "frequency": total });
        // chartData += '{"letter":'+date+',"frequency":'+total+'}';
      }
      chartData = JSON.stringify(this.total);
      // console.log(chartData);
      this.drawBarChart(this.total, this.arr);
    }
      ,
      error => {
        console.log(error);
      }
    );
  }

}
