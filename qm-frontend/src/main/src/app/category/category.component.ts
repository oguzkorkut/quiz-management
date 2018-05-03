import { ReturnModel } from './../models/ReturnModel';
import { Component, OnInit } from '@angular/core';
import { CategoryService } from './category.service';
import { Category } from '../models/Category';
import { NgForm} from '@angular/forms';
import { NotificationsService } from 'angular2-notifications';
import { Router } from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
  providers: [CategoryService]

})
export class CategoryComponent implements OnInit {

  constructor(private categoryService: CategoryService,
              private notificationsService: NotificationsService,
              private router: Router) {}

  returnModel: ReturnModel;
   
  model: Category= new Category();

  ngOnInit() {
   
  }

  checkout(form: NgForm) {
      if(form.invalid) {
        return;
      } else {
        this.categoryService.saveCategory(this.model).subscribe(
          response  => {
            console.log(response);
            this.notificationsService.info('Successfull','Category is saved!');
            this.router.navigate([""]);
            window.location.reload();
        },
        error =>{
          console.log(error);
        });
      }
    }

  addToCategory(){
    
  }

}
