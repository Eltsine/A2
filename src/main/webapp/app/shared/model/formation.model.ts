import { IModule } from 'app/shared/model/module.model';
import { NomFormation } from 'app/shared/model/enumerations/nom-formation.model';

export interface IFormation {
  id?: number;
  nomFormation?: NomFormation;
  rapportId?: number;
  modules?: IModule[];
  inscriptionId?: number;
}

export class Formation implements IFormation {
  constructor(
    public id?: number,
    public nomFormation?: NomFormation,
    public rapportId?: number,
    public modules?: IModule[],
    public inscriptionId?: number
  ) {}
}
