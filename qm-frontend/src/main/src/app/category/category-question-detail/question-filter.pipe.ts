import { Question } from './../../models/Question';
import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'questionFilter'
})
export class QuestionFilterPipe implements PipeTransform {

  transform(value: Question[], filterText?: string): Question[] {

    filterText = filterText ? filterText.toLocaleLowerCase() : null;

    return filterText ? value.filter((p: Question) => p.question.toLocaleLowerCase().indexOf(filterText) !== -1 ) : value;
  }

}
