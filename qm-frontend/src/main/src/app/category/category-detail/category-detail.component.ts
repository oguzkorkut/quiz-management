import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category.service';
import { Category } from '../../models/Category';
import { ReturnModel } from '../../models/ReturnModel';

@Component({
  selector: 'app-category-detail',
  templateUrl: './category-detail.component.html',
  styleUrls: ['./category-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryDetailComponent implements OnInit {

  constructor(private categoryService: CategoryService) {}

  selectedCategory: Category;

  returnModel: ReturnModel;

  categories: Category[] = [];
  
  ngOnInit() {
    this.getCategories();
  }

  getCategories() {
    //this.categoryService.getCategories().subscribe(c => (this.returnModel = c));
    this.categoryService.getCategories().subscribe(
      response  => {
        console.log(response )
        this.categories = response.result as Category[];
      },
      error =>{
        console.log(error )
      });
  }

  onSelect(category?: Category) {
    if (category) {
      this.selectedCategory = category;
    } else {
      this.selectedCategory = null;
    }
  }

  addToCategory(){
    
  }

}
