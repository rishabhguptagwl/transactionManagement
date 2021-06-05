import { fristDesignRoutingModule } from './fristDesign-routing.module';
import { TransactionViewComponent } from './transaction-view/transaction-view.component';
import { ListDailyTransactionsComponent } from './list-daily-transactions/list-daily-transactions.component';
import { ListAllTransactionViewComponent } from './list-all-transaction-view/list-all-transaction-view.component';
import { InsertTransactionComponent } from './insert-transaction/insert-transaction.component';
import { FormsModule } from '@angular/forms';
import { AnalyticsDashBoardComponent } from './analytics-dash-board/analytics-dash-board.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from '../angular-material.module';

@NgModule({
  declarations:[
    AnalyticsDashBoardComponent,
    InsertTransactionComponent,
    ListAllTransactionViewComponent,
    ListDailyTransactionsComponent,
    TransactionViewComponent
  ],
  imports:[
    FormsModule,
    CommonModule,
    AngularMaterialModule,
    fristDesignRoutingModule
  ]
})
export class fristDesignModule{

}
