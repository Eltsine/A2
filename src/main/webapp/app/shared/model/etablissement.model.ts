import { IInscription } from 'app/shared/model/inscription.model';
import { IFormateur } from 'app/shared/model/formateur.model';

export interface IEtablissement {
  id?: number;
  nom?: string;
  adresse?: string;
  inscriptions?: IInscription[];
  formateurs?: IFormateur[];
}

export class Etablissement implements IEtablissement {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string,
    public inscriptions?: IInscription[],
    public formateurs?: IFormateur[]
  ) {}
}
