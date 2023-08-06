import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';
import { LocalStorageService } from '../../local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  url = `${environment.apiUrl}/order`;

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
  placeOrder(data: any) {
    return this.http.post(`${this.url}/placeOrder`, data, this.requestOptions);
  }

  getOrderInfo(userId: string) {
    return this.http.get(`${this.url}/info?userId=${userId}`, this.requestOptions);
  }
}
