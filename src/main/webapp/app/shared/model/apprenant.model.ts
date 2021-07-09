export interface IApprenant {
  id?: number;
  photoContentType?: string;
  photo?: any;
  nom?: string;
  prenom?: string;
  statut?: string;
  niveau?: string;
  contact?: string;
  email?: string;
  addParent?: string;
  inscriptionId?: number;
  formateurId?: number;
}

export class Apprenant implements IApprenant {
  constructor(
    public id?: number,
    public photoContentType?: string,
    public photo?: any,
    public nom?: string,
    public prenom?: string,
    public statut?: string,
    public niveau?: string,
    public contact?: string,
    public email?: string,
    public addParent?: string,
    public inscriptionId?: number,
    public formateurId?: number
  ) {}
}
