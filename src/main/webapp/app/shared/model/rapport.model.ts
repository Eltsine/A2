export interface IRapport {
  id?: number;
  formationId?: number;
}

export class Rapport implements IRapport {
  constructor(public id?: number, public formationId?: number) {}
}
