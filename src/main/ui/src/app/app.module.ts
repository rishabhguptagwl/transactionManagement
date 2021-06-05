import { KPICalculationService } from './services/KPICalculation.service';
import { dailyTransactionServices } from './services/dailyTransaction.service';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnalyticsComponent } from './analytics/analytics.component';
import { TransactionService } from './services/transaction.service';
import { FormsModule } from '@angular/forms';
import { DailyTransactionComponent } from './daily-transaction/daily-transaction.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { TodayExpenseComponent } from './today-expense/today-expense.component';
import { WeekExpenseComponent } from './week-expense/week-expense.component';
import { UnclassComponent } from './unclass/unclass.component';
import { MonthExpenseComponent } from './month-expense/month-expense.component';
import { IncomeDialogComponent } from './income-dialog/income-dialog.component';
import { NoTransactionEntryDialogComponent } from './no-transaction-entry-dialog/no-transaction-entry-dialog.component';
import { BudgeDialogComponent } from './budge-dialog/budge-dialog.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { globalConfigService } from './services/globalConfig.service';
import { globalValues } from './services/globalValues';
import { GoogleChartsModule } from 'angular-google-charts';
import { EditTodayTransactionComponent } from './edit-today-transaction/edit-today-transaction.component';
import { AngularMaterialModule } from './angular-material.module';

@NgModule({
  declarations: [
    AppComponent,
    AnalyticsComponent,
    DailyTransactionComponent,
    UserDashboardComponent,
    TodayExpenseComponent,
    WeekExpenseComponent,
    UnclassComponent,
    MonthExpenseComponent,
    IncomeDialogComponent,
    NoTransactionEntryDialogComponent,
    BudgeDialogComponent,
    EditTodayTransactionComponent,
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserModule,
    GoogleChartsModule.forRoot(),
    BrowserAnimationsModule,
    AngularMaterialModule
  ],
  providers: [
    TransactionService,
    dailyTransactionServices,
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    },
    globalConfigService,
    globalValues,
    KPICalculationService
  ],
  bootstrap: [AppComponent],
  entryComponents:[
    IncomeDialogComponent,
    NoTransactionEntryDialogComponent,
    BudgeDialogComponent,
    EditTodayTransactionComponent
  ]
})
export class AppModule { }
