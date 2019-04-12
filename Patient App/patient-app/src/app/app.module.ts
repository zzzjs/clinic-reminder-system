import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { HomePage } from '../pages/home/home';
import {NetService} from "../service/net";
import {ErrorHandleService} from "../service/error.handle";
import {LoginPage} from "../pages/login/login";
import {HttpClientModule} from "@angular/common/http";
import {RemindersPage} from "../pages/reminders/reminders";
import {SocketService} from "../service/socket.service";

@NgModule({
  declarations: [
    MyApp,
    HomePage,
    LoginPage,
    RemindersPage
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    HomePage,
    LoginPage,
    RemindersPage
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    NetService,
    ErrorHandleService,
    SocketService
  ]
})
export class AppModule {}
