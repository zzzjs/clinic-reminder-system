import {Injectable} from "@angular/core";
import {AlertController} from "ionic-angular";

@Injectable()
export class ErrorHandleService {

  constructor(private alertCtrl: AlertController) {}

  public handleError(errorMessage: string) {
    const alert = this.alertCtrl.create({
      title: 'An error occurred!',
      message: errorMessage,
      buttons: ['Ok']
    });
    alert.present();
  }
}
