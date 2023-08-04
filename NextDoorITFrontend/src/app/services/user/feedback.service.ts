import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { LocalStorageService } from '../local-storage.service';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  url = `${environment.apiUrl}/feedback`;

  constructor(
    private http: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  jwtToken: string = this.localStorageService.getItem('JWT')
  requestOptions: any = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.jwtToken}`
    })
  }

  createServiceRating(data: any) {
    return this.http.post(`${this.url}/save`, data, this.requestOptions);
  }

  createProductRating(data: any) {
    return this.http.post(`${this.url}/save/productReview`, data, this.requestOptions);
  }

}
