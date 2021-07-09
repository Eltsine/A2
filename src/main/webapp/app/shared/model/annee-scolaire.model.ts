import { IInscription } from 'app/shared/model/inscription.model';

export interface IAnneeScolaire {
  id?: number;
  libelleAnnee?: string;
  inscriptions?: IInscription[];
}

export class AnneeScolaire implements IAnneeScolaire {
  constructor(public id?: number, public libelleAnnee?: string, public inscriptions?: IInscription[]) {}
}
