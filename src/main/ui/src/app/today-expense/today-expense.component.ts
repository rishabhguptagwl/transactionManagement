import { EditTodayTransactionComponent } from './../edit-today-transaction/edit-today-transaction.component';
import { MatDialog } from '@angular/material';
import { DatePipe } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';
import { dailyTransactionServices } from '../services/dailyTransaction.service';
import * as d3 from 'd3';
import * as $ from 'jquery';
@Component({
  selector: 'app-today-expense',
  templateUrl: './today-expense.component.html',
  styleUrls: ['./today-expense.component.css']
})
export class TodayExpenseComponent implements OnInit {
  @Input() date: string;
  fulldate: string
  pipe = new DatePipe('en-US');
  TransactionType: any;
  Expenses: any;
  isData: boolean;

  constructor(private _httpTransactionServices: dailyTransactionServices, private MatDialog:MatDialog) { }

  ngOnInit() {
    this.isData = true;
    // alert(window.innerWidth);
    var expense = [];
    var chartdata = [];
    this.fulldate = Date.now().toString();
    console.log("date before condition check", this.date);
    if (this.date == undefined)
      this.date = this.pipe.transform(Date.now(), 'dd-MM-yyyy');



    var tmp = this.date.split("-");
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));  
    this.getTransaction(this.pipe.transform(date1, 'yyyy-MM-dd'));
  }



  getTransaction(date: string) {
    console.log("Value passing for date ", date);
    var expdist = [];
    var expenses = [];
    this._httpTransactionServices.getTransactionToday(date).subscribe(
      data => {
        if (data != null) {
          this.isData = true;
          var result = JSON.parse(JSON.stringify(data));
          // //console.log(result);
          var tmp = result.Result;
          var essential = 0;
          var lifestyle = 0;
          for (let i = 0; i < tmp.length; i++) {
            expenses.push({ "letter": tmp[i].name, "frequency": tmp[i].amount, "type": tmp[i].value });
            if (tmp[i].value === "Essentials")
              essential += tmp[i].amount;
            else
              lifestyle += tmp[i].amount
          }
          expdist.push({ "region": "essentials", "count": essential });
          expdist.push({ "region": "Life Style", "count": lifestyle });
          this.TransactionType = JSON.stringify(expdist);
          this.Expenses = (expenses);
          var t = { "value": expdist };
          // expdist.push({"value":})
          this.drawBarChart(this.Expenses);
          this.pie(t, "piechart");
        }
        else {
          //console.log("No data found");
          this.isData = false;
          $("#piechart").html("");
          $("#barchart").html("");
          this.Expenses = [];
        }
      }
    );
  }

  increaseDate() {
    var tmp = this.date.split("-");
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));
    date1.setDate(date1.getDate() + 1);
    if(date1> new Date()){
      alert("you can't see in future");
    }
    else{
      this.date = this.pipe.transform(date1, "dd-MM-yyyy");
      this.getTransaction(this.pipe.transform(date1, 'yyyy-MM-dd'));
    }
  }

  decreaseDate() {
    var tmp = this.date.split("-");
    var date1 = new Date(this.pipe.transform(tmp[1] + "-" + tmp[0] + "-" + tmp[2]));
    var dayinMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    date1.setDate(date1.getDate() - 1)
    if (date1.getDate() === 0)
      date1.setDate((dayinMonths[date1.getMonth()]));
    this.date = this.pipe.transform(date1, "dd-MM-yyyy");
    this.getTransaction(this.pipe.transform(date1, 'yyyy-MM-dd'));
  }

  drawBarChart(data): void {
    console.log(data);
    // data = JSON.parse(data);
    var div = d3.select("body").append("div")
      .attr("class", "tooltip")
      .style("padding", "6px")
      .style("background", "lightsteelblue")
      .style("border", "1px solid black")
      .style("border-radius", "5px")
      .style("opacity", 0);
    $("#barchart").html("");
    width = (document.getElementById('barchart').clientWidth);
    height = 270;
    if (width <= 400)
      height = 220;
    // console.log("Width " + width + ": Height " + height);
    var svg = d3.select("#barchart").append("svg").attr("width", width).attr("height", height)
      .attr('preserveAspectRatio', 'xMinYMin'),
      margin = { top: 30, right: 20, bottom: 70, left: 40 },
      width = +svg.attr("width") - margin.left - margin.right,
      height = +svg.attr("height") - margin.top - margin.bottom;
    var x = d3.scaleBand().rangeRound([0, width]).padding(0.1),
      y = d3.scaleLinear().rangeRound([height, 0]);
    var g = svg.append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
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
      .attr("class", "bar").attr("fill", "steelblue").attr("cursor", "pointer")
      .attr("x", function (d) { return x(d.letter); })
      .attr("y", function (d) { return y(d.frequency); })
      .attr("width", x.bandwidth())
      .attr("height", function (d) { return height - y(d.frequency); }).on("mouseover", function (d, i) {
        console.log(data[i]);
        div.transition()
          .duration(400)
          .style("opacity", 1);
        div.style("left", (d3.event.pageX) + "px")
          .style("top", (d3.event.pageY - 28) + "px");
        div.html(data[i].type);
      }).on("mouseout", function (d, i) {
        div.style("opacity", 0);
        div.transition()
          .duration(400)
      })
    var tmp = 17;
    svg.selectAll("text.bar")
      .data(data)
      .enter().append("text")
      .attr("class", "t")
      .attr("font-size", "10px")
      .attr("text-anchor", "middle")
      .attr("x", function (d, i) {
        // ($(".bar")[i]as any).x.animVal.value)
        return (($(".bar")[i] as any).x.animVal.value + 55);       //---------------
      })
      .attr("y", function (d) {
        var tmp = y(d.frequency);
        return tmp == 0 ? (margin.top - 10) : (tmp + (margin.top - 10));
      })
      .text(function (d, i) { return data[i].frequency; });
  }
  pie(data, chartPlace) {
    //console.log(data);
    const width = $("#" + chartPlace).width();
    var height = $("#" + chartPlace).width();
    height = 270;
    if (width <= 400)
      height = 220;
    const radius = (Math.min(width, height) / 2) - 10;
    d3.select("#" + chartPlace).html("");
    const svg = d3.select("#" + chartPlace)
      .append("svg").attr("id", "chart-pie")
      .attr("width", width)
      .attr("height", height)
      .append("g")
      .attr("transform", `translate(${(width / 2) - 50}, ${(height / 2) + 10})`);

    var div = d3.select("body").append("div")
      .attr("class", "tooltip")
      .style("padding", "6px")
      .style("background", "lightsteelblue")
      .style("border", "1px solid black")
      .style("border-radius", "5px")
      .style("opacity", 0);
    const color = d3.scaleOrdinal(["#4682b4", "#91b3d0"]);
    const pie = d3.pie()
      .value(d => d.count)
      .sort(null);
    const arc = d3.arc()
      .innerRadius(0)
      .outerRadius(radius);
    function type(d) {
      d.value = Number(d.value);
      return d;
    }
    function arcTween(a) {
      const i = d3.interpolate(this._current, a);
      this._current = i(1);
      return (t) => arc(i(t));
    }
    const path = svg.selectAll("path")
      .data(pie(data["value"]));
    path.transition().duration(500).attrTween("d", arcTween);
    path.enter().append("path")
      .attr("fill", (d, i) => color(i))
      .attr("d", arc)
      .attr("cursor", "pointer")
      .attr("id", "pie")
      .attr("stroke", "white")
      .attr("stroke-width", "2px")
      .each(function (d) {
        this._current = d;
      }).on("mouseover", function (d, i) {
        div.transition()
          .duration(400)
          .style("opacity", 1);
        div.style("left", (d3.event.pageX) + "px")
          .style("top", (d3.event.pageY - 28) + "px");
        div.html(d.data.region + ":<b>" + d.value + "</b>");
      }).on("mouseout", function (d, i) {
        div.transition()
          .duration(400)
          .style("opacity", 0);
      });
    var group = d3.select("#chart-pie").append("g");
    for (let i = 0; i < data.value.length; i++) {
      var group1 = group.append('g').attr("transform", "translate(" + (width - ((width * 30) / 100)) + "," + (100 + (15 * i)) + ")")
      group1.append("rect").attr("width", "10").attr("height", "10").attr("fill", color(i));
      group1.append("text").text(data.value[i].region + "(" + data.value[i].count + ")").attr("fill", "black").attr("transform", "translate(15,10)");
    }
  }

  deleteTransaction(){
    if(confirm("Delete transaction"+ this.date)){
      this._httpTransactionServices.deleteTransaction(this.date).subscribe(data=>{
        var result = JSON.parse(JSON.stringify(data));
        if(result.result==="success"){
          this.decreaseDate();
          this.getTransaction(this.date);
        }
        else
          alert("fail");
      });
    }
  }

  editToday():void{
    this.MatDialog.open(EditTodayTransactionComponent,{
      width:window.innerWidth+"px",
      height:(window.innerHeight-10)+"px",
      data:{
        date:this.date
      }
    });
  }

}
