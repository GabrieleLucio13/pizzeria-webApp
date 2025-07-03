import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Day } from '../models/day.model';

@Injectable({
  providedIn: 'root' 
})
export class CalendarService {
  private apiUrl = 'http://localhost:8080/api/calendar';  

  constructor(private http: HttpClient) {}

  getCalendar(): Observable<Day[]> {
    return this.http.get<Day[]>(this.apiUrl); 
  }
}