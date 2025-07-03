import { Component } from '@angular/core';
import { CommonModule} from '@angular/common';
import { RouterModule } from '@angular/router';
import { Day } from '../../models/day.model';
import { CalendarService } from '../../services/calendar.service';

@Component({
  selector: 'app-calendar.component',
  imports: [CommonModule, RouterModule],
  templateUrl: './calendar.component.html',
  styleUrl: './calendar.component.css'
})
export class CalendarComponent {
  calendar: Day[] = []; 

   constructor(private calendarService: CalendarService) {}

   ngOnInit() {
    this.calendarService.getCalendar().subscribe(data => {
      this.calendar = data; 
    });
  }

  getMesiDistinct(): string[] {
  const mesi = this.calendar.map(day => day.mese);
  return [...new Set(mesi)];
}

getGiorniPerMese(mese: string): Day[] {
  return this.calendar.filter(day => day.mese === mese);
}
}
