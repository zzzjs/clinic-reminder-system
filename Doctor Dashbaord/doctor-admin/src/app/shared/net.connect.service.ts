import {Injectable} from '@angular/core';
import {ReminderModel} from '../reminders/reminder-add/reminder.model';
import {HttpClient } from '@angular/common/http';
import {NetRequestDataModel} from './net.request.data.model';
import {NetResponseDataModel} from './net.response.data.model';
import {ReminderService} from '../reminders/reminder.service';
import {Service_HostName, Service_Port} from '../shared/constants';

@Injectable()
export class NetConnectService {
  constructor(private httpClient: HttpClient, private reminderService: ReminderService) {}

  storeReminder(reminder: ReminderModel) {
    const netRequestData = new NetRequestDataModel('login', 110, 0, reminder);
    this.httpClient.post(Service_HostName + ':' + Service_Port + '/api/reminders', netRequestData)
      .subscribe(
        (response: NetResponseDataModel) => {
          if (response.resp_event === 110) {
            console.log(response);
            this.reminderService.addReminder(reminder);
          } else {
            console.log('connection error!');
          }
        }, error1 => {
          console.log(error1);
        }
      );
  }

  getPatientsReminders(doctorId: number) {
    return this.httpClient.get(Service_HostName + ':' + Service_Port + '/api/reminders/doctor/' + doctorId);
  }
}
