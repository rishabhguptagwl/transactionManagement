import { TransactionService } from '../../services/transaction.service';
import { Transaction } from '../../model/tranactionModel';
import { Component, OnInit } from '@angular/core';
import * as d3 from "d3";
import * as $ from "jquery"

@Component({
	selector: 'app-analytics-dash-board',
	templateUrl: './analytics-dash-board.component.html',
	styleUrls: ['./analytics-dash-board.component.css']
})
export class AnalyticsDashBoardComponent implements OnInit {

	_transaction: Transaction[];
	display: boolean;
	filter: boolean;
	scaleMin: number;
	scaleMax: number;


	constructor(private _httpService: TransactionService) {
		this._transaction = [];
	}

	ngOnInit() {
		this.getTransaction();
		this.filter = false;
		this.display = false;
		this.scaleMax = 0;
		this.scaleMin = 0;
		window.onresize = (e) => {
			this.getTransaction();
		}
	}



	getTransaction(): void {
		var saving = "";
		var trans = "";
		this._httpService.getAllTransaction().subscribe((data) => {

			for (let i = 0; i < data.length; i++) {
				this._transaction.push({
					id: data[i].id,
					date: data[i].date,
					amount: data[i].amount,
					save: data[i].save
				});
				saving = saving + data[i].save + ":"
				trans = trans + data[i].amount + ":"
			}
			console.log(trans);
			this.drawChart(saving, "chartSaving");
			this.drawChart(trans, "chartExpense");

		});
	}


	updateCharts(data) {
		var saving = "";
		var trans = "";
		this._transaction = [];
		for (let i = 0; i < data.length; i++) {
			console.log(data[i]);
			this._transaction.push({
				id: data[i].id,
				date: data[i].date,
				amount: data[i].amount,
				save: data[i].save
			});
			saving = saving + data[i].save + ":";
			trans = trans + data[i].amount + ":";
		}
		this.drawChart(saving, "chartSaving");
		this.drawChart(trans, "chartExpense");
	}

	drawChart(y, element): void {
		//console.log("called y");
		y = y.slice(0, y.length - 1);
		y = y.split(":");
		// console.log();
		console.log(y);
		let minScale = parseInt(d3.min(y)) - 1000;
		let maxScale = parseInt(d3.max(y)) + 1000;
		if (this.scaleMin != 0 || this.scaleMax != 0) {
			minScale = this.scaleMin;
			maxScale = this.scaleMax;
		}

		// console.log(minScale);
		// console.log(maxScale);

		// console.log(element);
		// console.log(element);
		$("#" + element).html("");
		// 2. Use the margin convention practice
		var margin = {
			top: 50,
			right: 50,
			bottom: 50,
			left: 50
		},
			width = 500 - margin.left - margin.right // Use the window's width
			, height = 300 - margin.top - margin.bottom; // Use the window's height


		width = window.innerWidth > 550 ? 500 : (window.innerWidth - 100);
		// The number of datapoints
		var n = y.length;

		// 5. X scale will use the index of our data
		var xScale = d3.scaleLinear().domain([0, n]) // input
			.range([0, width - 50]); // output

		console.log(width);
		// 6. Y scale will use the randomly generate number
		var yScale = d3.scaleLinear().domain([minScale, maxScale]) // input
			.range([height, 0]); // output

		// 7. d3's line generator
		var line = d3.line().x(function (d, i) {
			return xScale(i);
		}) // set the x values for the line generator
			.y(function (d) {
				return yScale(d.y);
			}) // set the y values for the line generator
			.curve(d3.curveMonotoneX) // apply smoothing to the line

		// 8. An array of objects of length N. Each object has key -> value pair, the key being "y" and the value is a random number
		var dataset = d3.range(n).map(function (d, i) {
			var v = d3.randomUniform(1)();
			return {
				"y": y[i]
			}
		})

		// 1. Add the SVG to the page and employ #2

		// var svg = d3.select("#" + element).append("svg").attr("width",
		// 	width + margin.left + margin.right).attr("height",
		// 		height + margin.top + margin.bottom).append("g").attr(
		// 			"transform",
		// 			"translate(" + margin.left + "," + margin.top + ")");

		var svg = d3.select("#" + element).append("svg").attr("width",
			width).attr("height",
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
			.attr("class", "line").attr("style", "fill: none;stroke: #ffab00;stroke-width:3") // Assign a class for styling
			.attr("d", line); // 11. Calls the line generator
	}


	filterinverse() {
		this.filter = !this.filter;
	}

	displayinverse() {
		this.display = !this.display;
	}


	applyfilter(form: any) {
		this.scaleMin = form.value.sclmin;
		this.scaleMax = form.value.sclmax;
		// this.getTransaction();
		console.log(form.value);
		this._httpService.getTransactionBetweenDates(form.value.fromdate, form.value.todate).subscribe((data) => {
			this.updateCharts(data);
		}, (error) => {
			console.error("Error");
			console.error(error);
		});
	}


}


