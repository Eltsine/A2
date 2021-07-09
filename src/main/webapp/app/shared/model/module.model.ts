import { ISession } from 'app/shared/model/session.model';
import { NomModule } from 'app/shared/model/enumerations/nom-module.model';

export interface IModule {
  id?: number;
  nomModule?: NomModule;
  prix?: number;
  sessions?: ISession[];
  formationId?: number;
}

export class Module implements IModule {
  constructor(
    public id?: number,
    public nomModule?: NomModule,
    public prix?: number,
    public sessions?: ISession[],
    public formationId?: number
  ) {}
}
