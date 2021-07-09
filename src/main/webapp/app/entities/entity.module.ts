import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'annee-scolaire',
        loadChildren: () => import('./annee-scolaire/annee-scolaire.module').then(m => m.AzimutAnneeScolaireModule),
      },
      {
        path: 'periode',
        loadChildren: () => import('./periode/periode.module').then(m => m.AzimutPeriodeModule),
      },
      {
        path: 'formation',
        loadChildren: () => import('./formation/formation.module').then(m => m.AzimutFormationModule),
      },
      {
        path: 'module',
        loadChildren: () => import('./module/module.module').then(m => m.AzimutModuleModule),
      },
      {
        path: 'rapport',
        loadChildren: () => import('./rapport/rapport.module').then(m => m.AzimutRapportModule),
      },
      {
        path: 'session',
        loadChildren: () => import('./session/session.module').then(m => m.AzimutSessionModule),
      },
      {
        path: 'salle',
        loadChildren: () => import('./salle/salle.module').then(m => m.AzimutSalleModule),
      },
      {
        path: 'formateur',
        loadChildren: () => import('./formateur/formateur.module').then(m => m.AzimutFormateurModule),
      },
      {
        path: 'specialite',
        loadChildren: () => import('./specialite/specialite.module').then(m => m.AzimutSpecialiteModule),
      },
      {
        path: 'apprenant',
        loadChildren: () => import('./apprenant/apprenant.module').then(m => m.AzimutApprenantModule),
      },
      {
        path: 'etablissement',
        loadChildren: () => import('./etablissement/etablissement.module').then(m => m.AzimutEtablissementModule),
      },
      {
        path: 'inscription',
        loadChildren: () => import('./inscription/inscription.module').then(m => m.AzimutInscriptionModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class AzimutEntityModule {}
