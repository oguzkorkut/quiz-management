import { Question } from "./Question";


export class Category {

    id: number;
    category: string;
    active: boolean;
    questionDtos: Question[] = [];
   
  
  }
  