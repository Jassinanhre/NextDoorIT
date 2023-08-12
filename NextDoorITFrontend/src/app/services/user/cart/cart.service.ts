import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Cart } from 'src/app/models/cart.model';
import { LocalStorageService } from '../../local-storage.service';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  url = `${environment.apiUrl}/cart`;

  constructor(
    private http: HttpClient,
    private localStorageService: LocalStorageService
  ) { }

  jwtToken: string = this.localStorageService.getItem('JWT')
  requestOptions: any = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin': '*',
      'Content-Type': 'application/json; application/x-www-form-urlencoded; charset=UTF-8',
      'Authorization': `Bearer ${this.jwtToken}`
    })
  }


  getAll(userId: string) {
    return this.http.get<Cart>(`${this.url}/all?userId=${userId}`, this.requestOptions)
  }


  removeOne(userId: string, productId: string) {
    return this.http.put(`${this.url}/removeProduct?userId=${userId}&productId=${productId}`, {}, this.requestOptions)
  }

}
