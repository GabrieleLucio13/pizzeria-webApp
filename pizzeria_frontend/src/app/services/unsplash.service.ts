
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UnsplashService {
  private accessKey = 'vGqvAl17u9GOPyCtHBDzyI9b7AdBo5lcbh7DpScnZ2A'; //ACCESS_KEY_UNSPLASH

  constructor(private http: HttpClient) {}

  getPhotos(query: string = 'food', perPage: number = 5): Observable<any> {
    const url = `https://api.unsplash.com/search/photos?query=${query}&per_page=${perPage}`;
    const headers = new HttpHeaders({
      Authorization: `Client-ID ${this.accessKey}`
    });
    return this.http.get(url, { headers });
  }
}