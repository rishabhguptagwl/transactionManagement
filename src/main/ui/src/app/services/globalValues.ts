import { Injectable } from '@angular/core';

@Injectable()
export class globalValues{


    constructor(){

    }
    public expenseWeek:number;
    public expenseMonth: number;
    public expenseToday: number;
    public budgetMonth:number;

}