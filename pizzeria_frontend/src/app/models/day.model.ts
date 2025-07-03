export class Day {
  data: string;  
  giorno: string;
  mese : string;
  apertura: string;
  chiusura: string;
  chiuso: boolean;


constructor(
    data: string,
    giorno: string,
    mese: string,
    apertura: string,
    chiusura: string,
    chiuso: boolean
  ) {
    this.data = data;
    this.giorno = giorno,
    this.mese = mese,
    this.apertura = apertura;
    this.chiusura = chiusura;
    this.chiuso = chiuso;
  }
}