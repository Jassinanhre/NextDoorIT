import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { ServiceCategory } from 'src/app/models/serviceCategory.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  url = environment.apiUrl;

  constructor(private http: HttpClient) { }

  getAll(): Observable<ServiceCategory[]> {
    return this.http.get<ServiceCategory[]>(`${this.url}/allCategories`);
  }
}
