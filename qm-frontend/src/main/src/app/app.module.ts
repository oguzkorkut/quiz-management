import {CdkTableModule} from '@angular/cdk/table';
import {
  MatAutocompleteModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
} from '@angular/material';
import { CategoryDetailComponent } from './category/category-detail/category-detail.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Routes, RouterModule } from '@angular/router';

import { SimpleNotificationsModule, NotificationsService } from 'angular2-notifications';

import { AppComponent } from './app.component';
import { CategoryComponent } from './category/category.component';
import { QuestionFilterPipe } from './category/category-question-detail/question-filter.pipe';
import { CategoryQuestionDetailComponent, AnswerDetailDialogPanel, QuestionDetailDialogPanel } from './category/category-question-detail/category-question-detail.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'quiz',
    pathMatch: 'full' // adres tam olarak path de belirtilen sekilde olmali anlaminda
  },
  {
    path: 'add-category',
    component: CategoryComponent // products path gelirse ProductComponent ac
  },
  {
    path: 'category-detail',
    component: CategoryDetailComponent
  },
  {
    path: 'category-question-detail',
    component: CategoryQuestionDetailComponent
  },
  {
    path: 'category-question-detail/:id',
    component: CategoryQuestionDetailComponent
  }
];
@NgModule({
  exports: [
    CdkTableModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
  ]
})
export class QuizMaterialModule {}

@NgModule({
  declarations: [
    AppComponent,
    CategoryComponent,
    CategoryDetailComponent,
    CategoryQuestionDetailComponent,
    QuestionFilterPipe,
    AnswerDetailDialogPanel,
    QuestionDetailDialogPanel



    
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    QuizMaterialModule,
    RouterModule.forRoot(appRoutes),
    SimpleNotificationsModule.forRoot()
  ],
  entryComponents: [CategoryQuestionDetailComponent, AnswerDetailDialogPanel, QuestionDetailDialogPanel],
  providers: [
    { provide: 'apiUrl', useValue: 'services/api' },
    NotificationsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
