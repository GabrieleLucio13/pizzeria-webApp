import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/Booking.model';

@Injectable({
  providedIn: 'root'  
})
export class ReservationService {
  private apiUrl = 'http://localhost:8080/api/reservation';  

  constructor(private http: HttpClient) {}

  getReservationByCode(code: String): Observable<Booking> {
    return this.http.get<Booking>(`${this.apiUrl}/${code}`); 
  }

  addReservation(booking: Booking): Observable<string> {
  return this.http.post(this.apiUrl, booking, { responseType: 'text' });
}

  deleteReservation(code : String) : Observable<any> {
     return this.http.delete<Booking>(`${this.apiUrl}/${code}`);
  }

  updateReservation(code: string, reservation: Booking): Observable<Booking> {
  return this.http.put<Booking>(`${this.apiUrl}/${code}`, reservation);
}
}