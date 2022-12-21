import { Injectable } from '@angular/core';
import { delay, map, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Bill } from './bill';
import { Consumer } from './consumer';

@Injectable({
  providedIn: 'root',
})
export class BillServiceService {
  constructor(private http: HttpClient) {}

  getAllBillsConsumer(consumerId: number): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/consumer/getAllBills?consumerId=${consumerId}`
    );
  }

  getBillsByMonthConsumer(
    consumerId: number,
    month: string,
    year: number
  ): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/consumer/getBillsByMonth?consumerId=${consumerId}&month=${month}&year=${year}`
    );
  }

  getBillsByYearConsumer(consumerId: number, year: number): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/consumer/getBillsByYear?consumerId=${consumerId}&year=${year}`
    );
  }

  getAllBillsAdmin(userName: string, password: string): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/admin/getBills?userName=${userName}&password=${password}`
    );
  }

  getBillsByMonthAdmin(
    userName: string,
    password: string,
    month: string,
    year: number
  ): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/admin/getBillsByMonth?userName=${userName}&password=${password}&month=${month}&year=${year}`
    );
  }

  getBillsByYearAdmin(
    userName: string,
    password: string,
    year: number
  ): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/admin/getBillsByYear?userName=${userName}&password=${password}&year=${year}`
    );
  }

  getBillsByAreaAdmin(
    userName: string,
    password: string,
    area: string
  ): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/admin/getBillsByArea?userName=${userName}&password=${password}&area=${area}`
    );
  }

  getBillsByCityAdmin(
    userName: string,
    password: string,
    city: string
  ): Observable<Bill[]> {
    return this.http.get<Bill[]>(
      `http://localhost:8080/admin/getBillsByCity?userName=${userName}&password=${password}&city=${city}`
    );
  }

  addUnitsConsumed(
    userName: string,
    password: string,
    month: string,
    year: number,
    consumerId: number,
    unitsConsumed: number
  ): Observable<Bill> {
    // const headers = { 'content-type': 'application/json' };
    return this.http.post<Bill>(
      `http://localhost:8080/admin/addUnits?userName=${userName}&password=${password}&month=${month}&year=${year}&consumerId=${consumerId}&unitsConsumed=${unitsConsumed}`,
      ''
    );
  }
  registerConsumer(consumer: Consumer): Observable<Consumer> {
    const headers = { 'content-type': 'application/json' };
    const body = JSON.stringify(consumer);
    console.log(body);
    return this.http.post<Consumer>(
      'http://localhost:8080/consumer/register',
      body,
      { headers: headers }
    );
  }
}
