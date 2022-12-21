import { Consumer } from './consumer';

export class Bill {
  billId: number;
  consumerId: Consumer;
  unitsConsumed: number;
  year: number;
  month: string;
  totalAmount: number;

  constructor(
    billId: number,
    consumerId: Consumer,
    unitsConsumed: number,
    year: number,
    month: string,
    totalAmount: number
  ) {
    this.billId = 0;
    this.consumerId = new Consumer(0, '', '', '', '');
    this.unitsConsumed = 0;
    this.year = 0;
    this.month = '';
    this.totalAmount = 0;
  }
}
