import { Moment } from 'moment';

export interface IPeriode {
  id?: number;
  duree?: string;
  dateDebut?: Moment;
  dateFin?: Moment;
}

export class Periode implements IPeriode {
  constructor(public id?: number, public duree?: string, public dateDebut?: Moment, public dateFin?: Moment) {}
}
