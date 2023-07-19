import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Training } from 'src/app/models/training.model';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class TrainingService {
  url = `${environment.apiUrl}/training`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Training[]> {
    return this.http.get<Training[]>(`${this.url}/all`);
  }

  getAllByCategory(categoryId: string): Observable<Training[]> {
    return this.http.get<Training[]>(`${this.url}/category?categoryId=${categoryId}`);
  }

  get(id: any): Observable<Training> {
    return this.http.get(`${this.url}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.url, data);
  }

  update(id: any, data: any): Observable<any> {
    return this.http.put(`${this.url}/${id}`, data);
  }

  delete(id: any): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }

  deleteAll(): Observable<any> {
    return this.http.delete(this.url);
  }

  findByTitle(title: any): Observable<Training[]> {
    return this.http.get<Training[]>(`${this.url}?title=${title}`);
  }
}