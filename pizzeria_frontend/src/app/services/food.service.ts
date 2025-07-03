import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Food } from '../models/food.model';

@Injectable({
  providedIn: 'root'  // Il service sarà disponibile in tutta l'app
})
export class FoodService {
  private apiUrl = 'http://localhost:8080/api/foods';  // URL del backend

  constructor(private http: HttpClient) {}

  getFoods(): Observable<Food[]> {
    return this.http.get<Food[]>(this.apiUrl); // Esegue una richiesta GET
  }
}


