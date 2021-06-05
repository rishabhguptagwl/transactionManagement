import { KPICalculationService } from './../services/KPICalculation.service';
import { MatDialog } from '@angular/material';
import { globalValues } from './../services/globalValues';
import { dailyTransactionServices } from './../services/dailyTransaction.service';
import { DatePipe } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';
import * as d3 from 'd3';
import * as $ from 'jquery';
import { TodayExpenseComponent } from '../today-expense/today-expense.component';

@Component({
  selector: 'app-month-expense',
  templateUrl: './month-expense.component.html',
  styleUrls: ['./month-expense.component.css'],
})
export class MonthExpenseComponent implements OnInit {
  month: string[] = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
  monthDayCount: number[] = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  currentMonth: number;
  year: number;
  pipe = new DatePipe('en-US');
  max: string;
  min: string;
  maxDate: string;
  minDate: string;
  expectBudgetBurndown: any = [];
  acctualBudgetBurndown: any = [];
  isTransactionNotFound: boolean = false;

  constructor(private _dailyTransaction: dailyTransactionServices, private globalValues:globalValues, private dialog:MatDialog, private KPIValues: KPICalculationService) { }

  ngOnInit() {
    var date = new Date()
    this.currentMonth = date.getMonth();
    this.year = date.getFullYear();

    this.getTransaction();
    this.expectBudgetBurndown = [];
    console.log(this.KPIValues.expenseAverage);
    console.log(this.KPIValues.expenseMonth);
    console.log(this.KPIValues.expenseWeek);
    console.log(this.KPIValues.expenseToday);
    console.log(this.KPIValues.monthlyBudge);
    this.KPIValues.UpdateKPIValues(this.currentMonth+1,this.year+"");
    console.log(this.KPIValues.expenseAverage);
    console.log(this.KPIValues.expenseMonth);
    console.log(this.KPIValues.expenseWeek);
    console.log(this.KPIValues.expenseToday);
    console.log(this.KPIValues.monthlyBudge);
  }

  getTransaction() {
    this.isTransactionNotFound = false;
    this.expectBudgetBurndown = [];
    this.acctualBudgetBurndown = [];
    this._dailyTransaction.getMonthlyTransaction((this.currentMonth) + "", this.year + "").subscribe(data => {
      var expense = [];
      var SendData = [];
      var lifestyle = 0;
      var essential = 0;
      var acctualBurnDownCounter = 0;
      var transaction = JSON.parse(JSON.stringify(data));
      if(transaction.transaction.length<=0){
        this.isTransactionNotFound= true;
        return;
      }
      for (let i = 0; i < transaction.transaction.length; i++) {
        var date = transaction.transaction[i].date;
        var trans = transaction.transaction[i].Transactions;
        var sum = 0;
        for (let j = 0; j < trans.length; j++) {
          sum += parseInt(trans[j].amount);
          if (trans[j].value == "Essentials") {
            essential += parseInt(trans[j].amount);
          }
          else {
            lifestyle += parseInt(trans[j].amount);
          }
        }
        acctualBurnDownCounter += sum;
        var tdate = date.split("-");
        tdate = new Date(tdate[2],(tdate[1]-1),tdate[0]);
        this.acctualBudgetBurndown.push({ "date": tdate, "amount": acctualBurnDownCounter });
        expense.push({ "letter": date, "frequency": sum });
        var tmp = [];
        tmp.push({ "transactions": trans });
        SendData.push({ "date": date, "trans": tmp[0] });
      }
      console.log('acctual burndown',this.acctualBudgetBurndown);
      this.drawBarChart(expense, SendData);
      var tmp = [];
      tmp.push({ "region": "Essesntials", "count": essential });
      tmp.push({ "region": "Lifestyle", "count": lifestyle });
      var t = [];
      t.push({ "value": tmp });
      this.pie(t[0], "piechart");
    });

    this._dailyTransaction.getMonthlyStats((this.currentMonth) + "", this.year + "").subscribe(data => {
      var jsonData = JSON.parse(JSON.stringify(data));
      this.min = jsonData.minimum_trans;
      this.max = jsonData.maximum_trans;
      this.maxDate = jsonData.maximum_trans_date;
      this.minDate = jsonData.minimum_trans_date;
    });

    /*leap year code is to be added*/
    this._dailyTransaction.getBudgetOfUser((this.currentMonth + 1) + "", this.year + "").subscribe(data => {
      var budget = JSON.parse(JSON.stringify(data)).budget;
      var perDayExpense = budget / this.monthDayCount[this.currentMonth];
      var spend = 0;
      for (let i = 0; i < this.monthDayCount[this.currentMonth]; i++) {
        spend += perDayExpense;
        this.expectBudgetBurndown.push({ "amount": spend, "date": new Date(this.year, this.currentMonth, (i + 1)) });
      }
      
      this.drawLineChart(this.expectBudgetBurndown, "linechart", this.globalValues.budgetMonth, this.acctualBudgetBurndown);
    });

    /*on resize of window adjustment of chart data*/ 
    window.onresize = (e) => {
      this.getTransaction();
    };
  }

  previousdate() {
    this.currentMonth = (this.currentMonth--) === 0 ? 11 : this.currentMonth;
    this.year = this.currentMonth == 11 ? --this.year : this.year;
    this.getTransaction();
    this.KPIValues.UpdateKPIValues(this.currentMonth+1,this.year+"");
  }

  nextDate() {
    this.currentMonth = (++this.currentMonth) === 12 ? 0 : this.currentMonth;
    this.year = this.currentMonth == 0 ? ++this.year : this.year;
    this.getTransaction();
    this.KPIValues.UpdateKPIValues(this.currentMonth+1,this.year+"");
  }


  drawBarChart(data, expenses): void {
    var div = d3.select("body").append("div")
      .attr("class", "tooltip")
      .style("padding", "6px")
      .style("background", "lightsteelblue")
      .style("border", "1px solid black")
      .style("border-radius", "5px")
      .style("opacity", 0);
    $("#chart").html("");
    width = (document.getElementById('chart-container').clientWidth);
    var svg = d3.select("#chart").append("svg").attr("width", width).attr("height", "300")
      .attr('preserveAspectRatio', 'xMinYMin'),
      margin = { top: 50, right: 20, bottom: 100, left: 40 },
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
      }).on("mouseout", function (d, i) {
        div.style("opacity", 0);
        div.transition()
          .duration(200)
      }).on("click",(data)=>{
        // console.log("called for letter",data.letter);
        this.openDialogForSingleDay(data.letter);
      });
    var tmp = 55;
    var fontSize;
    if (window.innerWidth <= 768) {
      tmp = 45;
      fontSize = 6 + "px";
    }
    else {
      fontSize = 10 + "px";
    }
    svg.selectAll("text.bar")
      .data(data)
      .enter().append("text")
      .attr("class", "t")
      .attr("font-size", fontSize)
      .attr("text-anchor", "middle")
      .attr("x", function (d, i) {
        return (($(".bar")[i] as any).x.animVal.value + tmp);   
      })
      .attr("y", function (d) {
        var tmp = y(d.frequency);
        return tmp == 0 ? (margin.top - 10) : (tmp + (margin.top - 10));
      })
      .text(function (d, i) { return data[i].frequency; });
  }
  
  openDialogForSingleDay(date:any):void{
    console.log("called with date",date);
    this.dialog.open(TodayExpenseComponent,{data:{date:date}});
  }

  pie(data, chartPlace) {
    const width = $("#" + chartPlace).width();
    var height = $("#" + chartPlace).width();
    height = 250;
    if (width <= 400)
      height = 250;
    const radius = (Math.min(width, height) / 2) - 10;
    d3.select("#" + chartPlace).html("");
    const svg = d3.select("#" + chartPlace)
      .append("svg").attr("id", "chart-pie")
      .attr("width", width)
      .attr("height", height + 50)
      .append("g")
      .attr("transform", `translate(${(width / 2)}, ${(height / 2) + 10})`);

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
      var group1 = group.append('g').attr("transform", "translate(" + ((width / 2) - 50) + "," + ((height + 10) + (15 * i)) + ")")
      group1.append("rect").attr("width", "10").attr("height", "10").attr("fill", color(i));
      group1.append("text").text(data.value[i].region + "(" + data.value[i].count + ")").attr("fill", "black").attr("transform", "translate(15,10)");
    }
  }

  drawLineChart(y, element, budget, burndown): void {
    console.log("drawLineChart| invoked");
    
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart(){
      var data = new google.visualization.DataTable();
      data.addColumn('date', 'X');
      data.addColumn('number', 'Acctual');
      data.addColumn('number', 'Expected');
      for(let i=0;i<y.length;i++){
        data.addRow([y[i].date,burndown[i]==undefined?null:burndown[i].amount,y[i].amount]);
      }
      var options = {
        hAxis: {
          title: 'Date'
        },
        vAxis: {
          title: 'INR'
        },
        colors: ['rgb(70, 130, 180)','rgb(145, 179, 208)'],
        crosshair: {
          color: '#000',
          trigger: 'selection'
        },
        curveType: 'function',
        ChartLegendPosition:'bottom'
      };
      var chart = new google.visualization.LineChart(document.getElementById(element));
      chart.draw(data,options);
      chart.setSelection([{row: 38, column: 1}]);
    }
      console.log("drawLineChart| exits");
  }
}

