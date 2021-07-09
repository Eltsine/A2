export interface ISpecialite {
  id?: number;
  libSpecialite?: string;
  formateurId?: number;
}

export class Specialite implements ISpecialite {
  constructor(public id?: number, public libSpecialite?: string, public formateurId?: number) {}
}
