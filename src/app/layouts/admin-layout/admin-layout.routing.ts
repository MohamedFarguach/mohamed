import { Routes } from '@angular/router';

import { HomeComponent } from '../../Livraison/home.component';
import { UserComponent } from '../../Vehicule/user.component';
import { TablesComponent } from '../../Chaufeurs/tables.component';
import { TypographyComponent } from '../../Users/typography.component';
import { IconsComponent } from '../../Marque/icons.component';
import { MapsComponent } from '../../Lieu/maps.component';
import { NotificationsComponent } from '../../TypeLieux/notifications.component';
import { UpgradeComponent } from '../../TypeVehicule/upgrade.component';
import { TestComponent } from 'app/ResponsableChargement/test.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'Livraisons',      component: HomeComponent },
    { path: 'Vehicules',           component: UserComponent },
    { path: 'Chauffeurs',          component: TablesComponent },
    { path: 'Utilisateurs',     component: TypographyComponent },
    { path: 'Marques',          component: IconsComponent },
    { path: 'Lieux',           component: MapsComponent },
    { path: 'Types-des-Lieux',  component: NotificationsComponent },
    { path: 'Types-des-Véhicules',        component: UpgradeComponent },
    { path: 'Responsable',        component: TestComponent },
];
