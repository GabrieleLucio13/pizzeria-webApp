import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Booking } from '../models/Booking.model';

@Injectable({
  providedIn: 'root'  // Il service sarà disponibile in tutta l'app
})
export class ReservationService {
  private apiUrl = 'http://localhost:8080/api/reservation';  

  constructor(private http: HttpClient) {}

  getReservationByCode(code: String): Observable<Booking> {
    return this.http.get<Booking>(`${this.apiUrl}/${code}`); 
  }

  addReservation(booking: Booking): Observable<Booking> {
    return this.http.post<Booking>(this.apiUrl, booking);
  }

  deleteReservation(code : String) : Observable<any> {
     return this.http.delete<Booking>(`${this.apiUrl}/${code}`);
  }

  updateReservation(code: string, reservation: Booking): Observable<Booking> {
  return this.http.put<Booking>(`${this.apiUrl}/${code}`, reservation);
}
}