import { Moment } from 'moment';

export interface ISession {
  id?: number;
  dateDebut?: Moment;
  dateFin?: Moment;
  nbreParticipant?: number;
  nbreHeure?: number;
  heureDebut?: number;
  heureFin?: number;
  contenuFormation?: string;
  moduleId?: number;
}

export class Session implements ISession {
  constructor(
    public id?: number,
    public dateDebut?: Moment,
    public dateFin?: Moment,
    public nbreParticipant?: number,
    public nbreHeure?: number,
    public heureDebut?: number,
    public heureFin?: number,
    public contenuFormation?: string,
    public moduleId?: number
  ) {}
}
