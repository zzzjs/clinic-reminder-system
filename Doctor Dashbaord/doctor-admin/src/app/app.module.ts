import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SigninComponent } from './auth/signin/signin.component';
import { SignupComponent } from './auth/signup/signup.component';
import { HeaderComponent } from './core/header/header.component';
import { HomeComponent } from './core/home/home.component';
import {AppRoutingModule} from './app-routing.module';
import { ReminderListComponent } from './reminders/reminder-list/reminder-list.component';
import { HistoryComponent } from './reminders/history/history.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from './auth/auth.service';
import { HttpClientModule} from '@angular/common/http';
import {ReminderService} from './reminders/reminder.service';
import { ReminderItemComponent } from './reminders/reminder-list/reminder-item/reminder-item.component';
import { ReminderAddComponent } from './reminders/reminder-add/reminder-add.component';
import {NetConnectService} from './shared/net.connect.service';
import {ChartsModule} from 'ng2-charts';
import {SocketIoService} from './shared/socket.io.service';


@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    SignupComponent,
    HeaderComponent,
    HomeComponent,
    ReminderListComponent,
    HistoryComponent,
    ReminderItemComponent,
    ReminderAddComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    ChartsModule
  ],
  providers: [AuthService, ReminderService, NetConnectService, SocketIoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
