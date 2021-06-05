import { Transaction } from './../model/tranactionModel';
import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../services/transaction.service';
import * as d3 from 'd3';
import * as $ from 'jquery';

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent implements OnInit {

  constructor(private _transactionService : TransactionService) { }
  Saving : number[];
  Amount : number[];
  date : String[];
  trans : Transaction[]; 
  ngOnInit() {
    this.Saving = [];
    this.Amount = [];
    this.date = [];
    this.trans = [];
    this.Alltransaction();
  }

  Alltransaction():void{
    var date = [];
    var y = "";
    this._transactionService.getAllTransaction().subscribe((data)=>{
      this.trans = data;
    

        for(let i=0;i<data.length;i++)
        {
          y = y+(data[i].save+":");
          date.push(data[i].date);
                
        }
        this.drawChart(y,"chart1"); 
        
    });
     
  }

  drawChart(y,element):void{
    //console.log("called y");
    //console.log(y);
    y = y.split(":");
    //console.log(y);
    let minScale= 0;
    let maxScale= 50000;
   // console.log(element);
			console.log(element);
			$("#" + element).html("");
			// 2. Use the margin convention practice 
			var margin = {
				top : 50,
				right : 50,
				bottom : 50,
				left : 50
			}, width = 500 - margin.left - margin.right // Use the window's width 
			, height = 300 - margin.top - margin.bottom; // Use the window's height

			// The number of datapoints
			var n = y.length-1;

			// 5. X scale will use the index of our data
			var xScale = d3.scaleLinear().domain([ 0, n - 1 ]) // input
			.range([ 0, width ]); // output

			// 6. Y scale will use the randomly generate number 
			var yScale = d3.scaleLinear().domain([ minScale, maxScale]) // input 
			.range([ height, 0 ]); // output 

			// 7. d3's line generator
			var line = d3.line().x(function(d, i) {
				return xScale(i);
			}) // set the x values for the line generator
			.y(function(d) {
				return yScale(d.y);
			}) // set the y values for the line generator 
			.curve(d3.curveMonotoneX) // apply smoothing to the line

			// 8. An array of objects of length N. Each object has key -> value pair, the key being "y" and the value is a random number
			var dataset = d3.range(n).map(function(d, i) {
        var v = d3.randomUniform(1)();
				return {
					"y" : y[i]
				}
			})

			// 1. Add the SVG to the page and employ #2
			var svg = d3.select("#" + element).append("svg").attr("width",
					width + margin.left + margin.right).attr("height",
					height + margin.top + margin.bottom).append("g").attr(
					"transform",
					"translate(" + margin.left + "," + margin.top + ")");

			// 3. Call the x axis in a group tag
			svg.append("g").attr("class", "x axis").attr("transform",
					"translate(0," + height + ")").call(d3.axisBottom(xScale)); // Create an axis component with d3.axisBottom

			// 4. Call the y axis in a group tag
			svg.append("g").attr("class", "y axis").call(d3.axisLeft(yScale)); // Create an axis component with d3.axisLeft

			// 9. Append the path, bind the data, and call the line generator 
			svg.append("path").datum(dataset) // 10. Binds data to the line 
			.attr("class", "line").attr("style","fill: none;stroke: #ffab00;stroke-width:3") // Assign a class for styling 
			.attr("d", line); // 11. Calls the line generator 
          }

}
