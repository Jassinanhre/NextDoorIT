import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Cart } from 'src/app/models/cart.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  url = `${environment.apiUrl}/cart`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Cart[]> {
    return this.http.get<Cart[]>(`${this.url}/all`);
  }
}
