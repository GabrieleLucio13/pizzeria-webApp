export class Booking {
    name: string;
    contact: string;
    date : string;
    time : string;
    number: number;
  
    constructor(
      name: string,
      contact: string,
      number: number,
      date : string,
      time : string,
    ) {
      this.name = name;
      this.contact = contact;
      this.date = date;
      this.time = time;
      this.number = number;
    }
  }