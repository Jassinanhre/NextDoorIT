import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Payment } from 'src/app/models/payment.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  url = `${environment.apiUrl}/order`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<Payment[]> {
    return this.http.get<Payment[]>(`${this.url}/all`);
  }
}
