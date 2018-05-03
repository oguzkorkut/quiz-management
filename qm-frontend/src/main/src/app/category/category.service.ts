import { Question } from './../models/Question';
import { Injectable, Inject } from '@angular/core';
import {Http, Headers, Response, RequestOptions, RequestOptionsArgs} from '@angular/http';
import {Observable} from 'rxjs/Observable'; // asenkron  servis bağlantıları
import 'rxjs/add/operator/map'; // gelen response data'yi istenilen bir nesneye map etmek icin kullanilir
import 'rxjs/add/operator/do'; // asenkron operasyon bittiginde yapilmasi istenen islemi anlatir.
import 'rxjs/add/operator/catch'; // Bir hata oldugunda yapilmasi istenen sey yazilir
import { Category } from '../models/Category';
import { ReturnModel } from '../models/ReturnModel';
import { Answer } from '../models/Answer';


@Injectable()
export class CategoryService {

  constructor(private http: Http,  @Inject('apiUrl') private apiUrl) { }

  url: string = this.apiUrl;

  
    
    
  cerateHeader(){
    let headers = new Headers();

    headers.append('Access-Control-Allow-Origin','*');
    headers.append('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, DELETE, PUT');
    headers.append('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
   
    headers.append('Accept','application/json');
    headers.append('content-type','application/json');

    return headers;
  }

  getCategories(): Observable<ReturnModel> {

    return this.http.get(this.url + '/category/getCategories').map(response => response.json());
  }

  saveCategory(category: Category): Observable<ReturnModel>{

    let headers = this.cerateHeader();
    
    var requestOptions =new RequestOptions({headers:headers});

    return this.http.post(this.url + '/category/save', category, requestOptions).map(response => response.json());
  }

  updateCategory(category: Category): Observable<ReturnModel>{
    return this.http.put(this.url + '/category/update', category).map(response => response.json());
  }

  getQuestionsByCategoryId(id: number): Observable<ReturnModel>{
    let headers = new Headers();
        
    headers.append('Accept','application/json');
    headers.append('Content-Type','application/json');

    var requestOptions =new RequestOptions({headers:headers});

    return this.http.get(this.url + '/category/getQuestionsByCategoryId/' + id).map(response => response.json());
  }

  saveQuestion(question: Question): Observable<ReturnModel>{

    let headers = this.cerateHeader();

    var requestOptions =new RequestOptions({headers:headers});

    return this.http.post(this.url + '/category/saveQuestion', question, requestOptions).map(response => response.json());
  }

  saveAnswer(answer: Answer): Observable<ReturnModel>{

    let headers = this.cerateHeader();

    var requestOptions =new RequestOptions({headers:headers});

    return this.http.post(this.url + '/category/saveAnswer', answer, requestOptions).map(response => response.json());
  }

  saveAnswers(answers: Answer[]): Observable<ReturnModel>{

    let headers = this.cerateHeader();

    var requestOptions =new RequestOptions({headers:headers});

    return this.http.post(this.url + '/category/saveAnswers', answers, requestOptions).map(response => response.json());
  }
}

