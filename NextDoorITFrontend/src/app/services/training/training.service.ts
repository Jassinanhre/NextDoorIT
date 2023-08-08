import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Training } from 'src/app/models/training.model';
import { environment } from 'src/environments/environment';
import { LocalStorageService } from '../local-storage.service';


@Injectable({
  providedIn: 'root'
})
export class TrainingService {
  url = `${environment.apiUrl}/training`;

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

  getAll() {
    return this.http.get<Training[]>(`${this.url}/all`, this.requestOptions);
  }

  getAllByCategory(categoryId: string) {
    return this.http.get<Training[]>(`${this.url}/byCategory?categoryId=${categoryId}`, this.requestOptions);
  }

  get(id: string) {
    return this.http.get(`${this.url}/getDetails?trainingId=${id}`, this.requestOptions);
  }

  enroll(data: any) {
    return this.http.post(`${this.url}/enroll`, data, this.requestOptions);
  }

  getReview(id: string) {
    return this.http.get(`${this.url}/reviewRatings?trainingId=${id}`, this.requestOptions);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${this.url}/${id}`, data, this.requestOptions);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${this.url}/${id}`, this.requestOptions);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(this.url, this.requestOptions);
  }

  findByTitle(title: any): Observable<Training[]> {
    return this.http.get<Training[]>(`${this.url}?title=${title}`);
  }
}