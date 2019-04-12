import {Component, OnDestroy, OnInit} from '@angular/core';
import {ReminderService} from '../reminder.service';
import {ReminderListData} from '../reminder-list.data.model';
import {Subscription} from 'rxjs';
import {ReminderItem} from '../reminder-item.model';
import {ActivatedRoute, Router} from '@angular/router';
import {SocketIoService} from '../../shared/socket.io.service';

import Socket = SocketIOClient.Socket;
import {DoctorAccount} from '../../auth/doctor.account.model';


@Component({
  selector: 'app-reminder-list',
  templateUrl: './reminder-list.component.html',
  styleUrls: ['./reminder-list.component.css']
})
export class ReminderListComponent implements OnInit, OnDestroy {
  private socket: Socket;
  reminderListData: ReminderListData;
  doctorAccount: DoctorAccount;
  reminders: ReminderItem[];
  columns = ['patientId', 'firstName', 'lastName', 'highNum', 'middleNum', 'lowNum'];
  subscription: Subscription;
  constructor(private reminderService: ReminderService,
              private router: Router,
              private route: ActivatedRoute,
              private socketIoService: SocketIoService) {
  }

  ngOnInit() {
    this.subscription = this.reminderService.remindersChanged
      .subscribe(
        (reminderListData: ReminderListData) => {
          this.reminderListData = reminderListData;
          this.reminders = this.reminderListData.reminderList;
        }
      );
    this.reminderListData = this.reminderService.getReminderListData();
    this.reminders = this.reminderListData.reminderList;
    this.doctorAccount = this.reminderService.getDoctorAccount();
      this.init();
  }

  init() {
    this.socketIoService.connect();
    this.socketIoService.messages.subscribe( (data: string) => {
      const reminder = JSON.parse(data);
      this.reminderService.updateReminder(reminder);
    });
  }


  ionViewWillEnter() {
    console.log('ionViewWillEnter');
    // this.socketIoService.connect();
  }

  ionViewWillLeave() {
    console.log('ionViewWillLeave');
    this.socketIoService.disconnect();
  }

  ionViewWillUnload() {
    console.log('ionViewWillUnload');
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  gotoHistoryPage(patientId: number) {
    console.log('patientId ' + patientId);
    this.reminderService.setHistoryData(patientId);
    this.router.navigate(['/history', patientId]);

  }

  onNewReminder() {
    this.router.navigate(['add'], {relativeTo: this.route});
  }

}
