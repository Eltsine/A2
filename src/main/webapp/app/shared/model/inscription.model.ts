import { Moment } from 'moment';
import { IFormation } from 'app/shared/model/formation.model';

export interface IInscription {
  id?: number;
  dateInscription?: Moment;
  montantApayer?: number;
  montantVerse?: number;
  resteApayer?: number;
  etatEtudiant?: string;
  periodeId?: number;
  formations?: IFormation[];
  etablissementId?: number;
  anneeScolaireId?: number;
}

export class Inscription implements IInscription {
  constructor(
    public id?: number,
    public dateInscription?: Moment,
    public montantApayer?: number,
    public montantVerse?: number,
    public resteApayer?: number,
    public etatEtudiant?: string,
    public periodeId?: number,
    public formations?: IFormation[],
    public etablissementId?: number,
    public anneeScolaireId?: number
  ) {}
}
