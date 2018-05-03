import { Answer } from './../../models/Answer';
import { Question } from './../../models/Question';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Category } from './../../models/Category';
import { CategoryService } from './../category.service';
import { Component, OnInit, Inject } from '@angular/core';
import { NotificationsService } from 'angular2-notifications';
import { ActivatedRoute } from '@angular/router';
import { AppPager } from '../../AppPager';
import { AppPage } from './../../../../e2e/app.po';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-category-question-detail',
  templateUrl: './category-question-detail.component.html',
  styleUrls: ['./category-question-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryQuestionDetailComponent implements OnInit {

  constructor(private categoryService: CategoryService, 
    private notificationsService: NotificationsService, private activatedRoute: ActivatedRoute,
    public dialog: MatDialog) { }

  questions: Question[] = [];

  filterText: string = '';

  pager: AppPager = new AppPager();

  categoryId: number = 0;

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
     if(params['id']){
        this.categoryId = params['id'];
        this.getQuestionsByCategoryId(params['id']);
      }
    });
  }

  getQuestionsByCategoryId(id: number){
    this.categoryService.getQuestionsByCategoryId(id).subscribe( response  => {
      console.log(response );
      this.questions = response.result as Question[];

      this.pager = this.getPager(this.questions.length,1,3);
    },
    error =>{
      console.log(error )
    });
  }

  addQuestion(question: Question){
    this.categoryService.saveQuestion(question).subscribe(
      response  => {
        console.log(response )
        this.notificationsService.info('Successfull','Question is saved!');
        this.getQuestionsByCategoryId(this.categoryId);
    },
    error =>{
      console.log(error )
    });
  }

  openQuestionDialog(id: number): void {
    let dialogRef = this.dialog.open(QuestionDetailDialogPanel, {
      width: '400px',
      data: { categoryId: id, questionType: 'SINGLE_CHOICE', question: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.addQuestion(result);
    });
  }

  openAnswerDialog(questionId: number,question: string, answerDtos:Answer[]): void {
   
    let dialogRef = this.dialog.open(AnswerDetailDialogPanel, {
      width: '500px',
      data: { questionId: questionId,question: question, answerDtos: answerDtos}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.questions = result;
      //this.addQuestion(result);
      this.addAnswers(result.answerDtos)
    });
  }

  addAnswers(answerDtos: Answer[]){
    this.categoryService.saveAnswers(answerDtos).subscribe(
      response  => {
        console.log(response )
        this.notificationsService.info('Successfull','İşlem başarılı.');
        this.getQuestionsByCategoryId(this.categoryId);
    },
    error =>{
      console.log(error )
    });
  }

  getPager(totalItems: number, currentPage: number, pageSize: number=10): AppPager{
    let totalPage = Math.ceil(totalItems/pageSize);

    let pages: Array<number>= [];
    for(let i=0 ; i<totalPage; i++){
      pages.push(i+1);
    }

    var pager = new AppPager();

    pager.pageSize= pageSize;
    pager.currentPage = currentPage;
    pager.pageList = pages;

    return pager;
  }

  setPage(page:number){
    this.pager.currentPage= page;
  }
  
}


@Component({
  selector: 'question-detail-dialog-panel',
  templateUrl: 'question-dialog-panel.html',
  styleUrls: ['question-dialog-panel.css']
})
export class QuestionDetailDialogPanel {

  constructor(
    public dialogRef: MatDialogRef<QuestionDetailDialogPanel>,
    private notificationsService: NotificationsService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onNoClick(): void {
      this.dialogRef.close();
    }
  
    checkout() {
      console.log("dwddwdw  ");
      if(this.data.question) {
         this.dialogRef.close(this.data);
      } else {
        this.notificationsService.warn('Warning','Soru alanı boş geçilemez.');
      }
    }

}

@Component({
  selector: 'answer-detail-dialog-panel',
  templateUrl: 'answer-dialog-panel.html',
  styleUrls: ['answer-dialog-panel.css']
})
export class AnswerDetailDialogPanel {

  constructor(
    public dialogRef: MatDialogRef<AnswerDetailDialogPanel>,
    private notificationsService: NotificationsService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onNoClick(): void {
      this.dialogRef.close();
    }
  
    checkout() {
      console.log("checkout answers");
      if(this.data.answerDtos.length > 0) {
         
        let control = false;

        let count: number = 0;

        for (let i = 0; i < this.data.answerDtos.length; i++) {
          const element = this.data.answerDtos[i];
          if (element.result && !element.deleted) {
            control = true;
            count++;
          }
          
        }

        if (control) {
          if (count > 1) {
            this.notificationsService.warn('Warning','Birden fazla doğru cevap seçilemez.');
          }else{
            this.dialogRef.close(this.data);
          }
          
        }else{
          this.notificationsService.warn('Warning','Sorunun doğru cevabını giriniz.');
        }
        
      } else {
        this.notificationsService.warn('Warning','Cevap girişi zorunludur.');
      }
    }

    changeResult(answer: Answer) {
      answer.result = !answer.result;
    }

    delete(answer: Answer, index: number) {
      answer.deleted = !answer.deleted;
      if(!answer.id){
        this.data.answerDtos.splice(index, 1);
      }
      
    }
    

    addAnswer(questionId:number ,inputText: HTMLInputElement){

      if (inputText.value) {
        let tempAnswer: Answer = new Answer();
  
        tempAnswer.answer = inputText.value;
        tempAnswer.result = false;
        tempAnswer.active = true;
        tempAnswer.questionId = questionId;
     
        this.data.answerDtos.push(tempAnswer);

        this.notificationsService.success('Bilgi Mesajı','Cevap eklendi.');

        inputText.value = '';
        /* 
        this.categoryService.saveAnswer(tempAnswer).subscribe(
          response  => {
            console.log(response )
            this.notificationsService.info('Successfull','Answer is saved!');
            this.getQuestionsByCategoryId(this.categoryId);
        },
        error =>{
          console.log(error )
        });*/
      } else {
        this.notificationsService.warn('Hata','Boş kayıt eklenemez.')
      }    
    }

}