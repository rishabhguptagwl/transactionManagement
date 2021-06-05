import { UnclassComponent } from './unclass/unclass.component';
import { MonthExpenseComponent } from './month-expense/month-expense.component';
import { Routes, RouterModule } from '@angular/router';
import { WeekExpenseComponent } from './week-expense/week-expense.component';
import { TodayExpenseComponent } from './today-expense/today-expense.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ListDailyTransactionsComponent } from './firstDesign/list-daily-transactions/list-daily-transactions.component';
import { AnalyticsDashBoardComponent } from './firstDesign/analytics-dash-board/analytics-dash-board.component';
import { DailyTransactionComponent } from './daily-transaction/daily-transaction.component';
import { TransactionViewComponent } from './firstDesign/transaction-view/transaction-view.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  { path:"", redirectTo:"/dashboard/month", pathMatch:'full'},
  { path: "analytics", component: DailyTransactionComponent },
  {path:"frist", loadChildren:'./firstDesign/fristDesign.module#fristDesignModule' },
  {
    path: "dashboard", component: UserDashboardComponent, children: [
      { path: "today", component: TodayExpenseComponent },
      { path: "week", component: WeekExpenseComponent },
      { path: "month", component: MonthExpenseComponent },
      { path: "", component:MonthExpenseComponent}

    ]
  },
  { path: "unclassed", component: UnclassComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
