import {Component, OnDestroy, OnInit} from '@angular/core';
import {AlertController, IonicPage, LoadingController, NavController, NavParams} from 'ionic-angular';
import {ReminderModel} from "../../shared/reminder.model";
import {PatientAccount} from "../../shared/patient.account.model";
import {NetService} from "../../service/net";
import {ErrorHandleService} from "../../service/error.handle";
import {SocketService} from "../../service/socket.service";

@IonicPage()
@Component({
  selector: 'page-reminders',
  templateUrl: 'reminders.html',
})
export class RemindersPage implements OnInit, OnDestroy{

  reminders: ReminderModel[] = [];
  account: PatientAccount;

  constructor(public navCtrl: NavController, public navParams: NavParams,
              private alertCtrl: AlertController, private loadingService: LoadingController,
              private net: NetService, private handleError: ErrorHandleService,
              private socketService: SocketService) {
  }

  ngOnInit(): void {
    this.account = this.navParams.get('account');
    this.init();
  }

  init() {
    this.socketService.connect();
    this.socketService.messages.subscribe((data: string) => {
      const reminder: ReminderModel = JSON.parse(data);
      console.log(reminder.date);
      this.reminders.push(reminder);
    }, error1 => {
      this.handleError.handleError(error1.message);
    });
  }


  ionViewDidEnter() {
    const loading = this.loadingService.create({
      content: 'Loading Reminders...'
    });
    loading.present();
    this.net.getReminders(this.account.patientId)
      .subscribe( (data: ReminderModel[]) => {
        loading.dismiss();
        this.reminders = data;
        },
        (error1) => {
          loading.dismiss();
          this.handleError.handleError(error1.message);
        });
  }

  onReminderFinished(reminder: ReminderModel) {
    const alert = this.alertCtrl.create({
      title : 'Done',
      message : 'Are you sure you have finished this reminder?',
      buttons : [
        {
          text: 'Yes',
          handler: () => {
            const loading = this.loadingService.create({
              content: 'Finishing...'
            });
            loading.present();
            this.net.finishReminder(reminder)
              .subscribe( () => {
                  loading.dismiss();
                  const position = this.reminders.findIndex( (reminderEl: ReminderModel) => {
                    return reminderEl.id == reminder.id;
                  });
                  this.reminders.splice(position, 1);
                },
                (error1) => {
                  loading.dismiss();
                  this.handleError.handleError(error1.message);
                });

          }
        },
        {
          text: 'No',
          role: 'cancel',
          handler: () => {
            console.log('Cancelled!');
          }
        }
      ]
    });
    alert.present();
  }

  ngOnDestroy(): void {
    this.socketService.disconnect();
  }

}
