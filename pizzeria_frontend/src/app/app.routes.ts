import { Routes } from '@angular/router';
import { FoodMenuComponent } from './components/food-menu/food-menu.component';
import { HomeComponent } from './components/home/home.component';
import { reservationComponent } from './components/reservation/reservation.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { LoginComponent } from './components/login/login.component';

export const routes: Routes = [
  { path: 'foods', component: FoodMenuComponent },
  { path: '', component: HomeComponent },
  { path: 'reservation', component: reservationComponent}, 
  { path: 'calendar', component : CalendarComponent},
  { path: 'login', component : LoginComponent}
];
