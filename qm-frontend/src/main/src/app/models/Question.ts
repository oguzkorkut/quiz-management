import { Answer } from "./Answer";

export class Question {

    id: number;
    question: string;
    questionType: string;
    puan: number;
    orderNo: number;
    active: boolean;
  //  deleted: boolean;
    answerDtos: Answer[] = [];

    
}