import { ListDailyTransactionsComponent } from './list-daily-transactions/list-daily-transactions.component';
import { AnalyticsDashBoardComponent } from './analytics-dash-board/analytics-dash-board.component';
import { TransactionViewComponent } from './transaction-view/transaction-view.component';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
    { path: "view", component: TransactionViewComponent },
    { path: "viewdata", component: AnalyticsDashBoardComponent },
    { path: "dailytransaction", component: ListDailyTransactionsComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class fristDesignRoutingModule { }
