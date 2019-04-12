import { Component } from '@angular/core';
import {AlertController, IonicPage, LoadingController, NavController, NavParams} from 'ionic-angular';
import {NgForm} from "@angular/forms";
import {NetService} from "../../service/net";
import {NetResponseDataModel} from "../../shared/net.response.data.model";
import {ErrorHandleService} from "../../service/error.handle";
import {PatientAccount} from "../../shared/patient.account.model";
import {RemindersPage} from "../reminders/reminders";

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
  account: PatientAccount;
  invalid: any;
  constructor(public navCtrl: NavController, public navParams: NavParams,
              private alertCtrl: AlertController, private loadingService: LoadingController,
              private net: NetService, private handleError: ErrorHandleService) {
  }


  onSignin(form: NgForm) {
    const loading = this.loadingService.create({
      content: 'Signing you in...'
    });
    loading.present();
    this.net.signinUser(form.value.email, form.value.password).subscribe(
      (data: NetResponseDataModel) => {
        loading.dismiss();
        if (data.resp_event === 20) {
          this.account = JSON.parse(data.respJson);
          this.navCtrl.push(RemindersPage, {account: this.account});
          this.net.setAccount(this.account);
          this.invalid = '';
        } else if (data.resp_event === 990) {
          this.invalid = 'Mail or Password Invalid!';
          console.log('Password Invalid');
        } else {
          this.invalid = 'Login failed!';
          this.handleError.handleError('Login failed!');
          }
    },
      (error1) => {
          loading.dismiss();
          this.handleError.handleError(error1.message);
      })
  }

}
