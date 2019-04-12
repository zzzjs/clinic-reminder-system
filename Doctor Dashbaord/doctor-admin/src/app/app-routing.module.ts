import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './core/home/home.component';
import {SignupComponent} from './auth/signup/signup.component';
import {SigninComponent} from './auth/signin/signin.component';
import {ReminderListComponent} from './reminders/reminder-list/reminder-list.component';
import {HistoryComponent} from './reminders/history/history.component';
import {ReminderAddComponent} from './reminders/reminder-add/reminder-add.component';

const appRoutes: Routes = [
  // { path: '', redirectTo: '/signin', pathMatch: 'full' },
  { path: 'reminders', component: ReminderListComponent },
  { path: 'reminders/add', component: ReminderAddComponent },
  { path: 'add', component: ReminderAddComponent},
  { path: 'history', component: HistoryComponent, children: [
      { path: ':id', component: HistoryComponent}
    ] },
  { path: 'signin', component: SigninComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
