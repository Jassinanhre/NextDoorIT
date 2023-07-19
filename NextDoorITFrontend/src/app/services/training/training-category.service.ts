import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { TrainingCategory } from 'src/app/models/trainingCategory.model';
import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class TrainingCategoryService {
  url = `${environment.apiUrl}/categories`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<TrainingCategory[]> {
    return this.http.get<TrainingCategory[]>(`${this.url}/allCategories`);
  }
}
