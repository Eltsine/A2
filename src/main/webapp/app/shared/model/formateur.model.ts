import { ISpecialite } from 'app/shared/model/specialite.model';
import { IApprenant } from 'app/shared/model/apprenant.model';

export interface IFormateur {
  id?: number;
  photoContentType?: string;
  photo?: any;
  cnib?: string;
  nom?: string;
  prenom?: string;
  statut?: string;
  contact?: string;
  email?: string;
  salaireHoraire?: number;
  salaireMensuel?: number;
  specialites?: ISpecialite[];
  personnes?: IApprenant[];
  etablissementId?: number;
}

export class Formateur implements IFormateur {
  constructor(
    public id?: number,
    public photoContentType?: string,
    public photo?: any,
    public cnib?: string,
    public nom?: string,
    public prenom?: string,
    public statut?: string,
    public contact?: string,
    public email?: string,
    public salaireHoraire?: number,
    public salaireMensuel?: number,
    public specialites?: ISpecialite[],
    public personnes?: IApprenant[],
    public etablissementId?: number
  ) {}
}
