import { NgModule } from '@angular/core';
import { MatCardModule,
  MatInputModule,
  MatDatepickerModule,
  MatNativeDateModule,
  MatRippleModule,
  MatExpansionModule,
  MatSidenavModule,
  MatListModule,
  MatDividerModule,
  MatSnackBarModule,
  MatTooltipModule,
  MatSelectModule,
  MatButtonModule,
} from '@angular/material';

@NgModule({
  imports:[
    MatCardModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRippleModule,
    MatExpansionModule,
    MatSidenavModule,
    MatListModule,
    MatDividerModule,
    MatSnackBarModule,
    MatSelectModule,
    MatButtonModule,
    MatTooltipModule
  ],
  exports:[
    MatCardModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatRippleModule,
    MatExpansionModule,
    MatSidenavModule,
    MatListModule,
    MatDividerModule,
    MatSnackBarModule,
    MatSelectModule,
    MatButtonModule,
    MatTooltipModule
  ],
  providers:[
    MatDatepickerModule
  ]
})
export class AngularMaterialModule{

}
