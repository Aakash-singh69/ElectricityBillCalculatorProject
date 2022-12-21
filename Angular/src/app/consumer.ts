export class Consumer {
  consumerId: number;
  consumerName: string;
  area: string;
  city: string;
  connectionType: string;

  constructor(
    consumerId: number,
    consumerName: string,
    area: string,
    city: string,
    connectionType: string
  ) {
    this.consumerId = 0;
    this.consumerName = '';
    this.area = '';
    this.city = '';
    this.connectionType = '';
  }
}
