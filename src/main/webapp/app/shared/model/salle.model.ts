export interface ISalle {
  id?: number;
  nomSalle?: string;
  capacite?: string;
  sessionId?: number;
}

export class Salle implements ISalle {
  constructor(public id?: number, public nomSalle?: string, public capacite?: string, public sessionId?: number) {}
}
