import {Injectable} from '@angular/core';
import {ReminderListData} from './reminder-list.data.model';
import {ReminderItem} from './reminder-item.model';
import {Subject} from 'rxjs';
import {PatientRemindersModel} from '../shared/patient.reminders.model';
import {ReminderModel} from './reminder-add/reminder.model';
import {DoctorAccount} from '../auth/doctor.account.model';

@Injectable()
export class ReminderService {
  remindersChanged = new Subject<ReminderListData>();
  private patientsReminders: PatientRemindersModel[];
  private patientReminders: PatientRemindersModel;
  private historyReminders: ReminderModel[];
  private doctorAccount: DoctorAccount;
  private reminderList: ReminderListData = new ReminderListData(0,
    '',
    '',
    '',
    [new ReminderItem(0, '', '', 0, 0, 0)]);

  getReminderListData() {
    return this.reminderList;
  }

  setReminderListData (reminderListData: ReminderListData) {
    this.reminderList = reminderListData;
    this.remindersChanged.next(this.reminderList);
  }

  setPatientsReminders (account: DoctorAccount, patientsReminders: PatientRemindersModel[]) {
    this.patientsReminders = patientsReminders;
    this.doctorAccount = account;
    this.generateRemindersList();
  }

  addReminder(reminder: ReminderModel) {
    this.patientsReminders.forEach((patientReminders, idx) => {
      if (patientReminders.patientId === reminder.patientId) {
        this.patientsReminders[idx].patientReminders.push(reminder);
      }
    });
    this.generateRemindersList();
  }

  generateRemindersList () {
    const reminderListData = new ReminderListData(this.doctorAccount.id, this.doctorAccount.firstName,
      this.doctorAccount.lastName, this.doctorAccount.email, []);
    const tempReminderList: ReminderItem[] = [];
    for (const reminders of this.patientsReminders) {
      const reminderListItem = new ReminderItem(reminders.patientId, reminders.firstName,
        reminders.lastName, 0, 0, 0);
      const tempReminders = reminders.patientReminders;
      for (const reminder of tempReminders) {
        if (reminder.finished === 'Y') { continue; }
        switch (reminder.priority) {
          case 'High': reminderListItem.addHigh(); break;
          case 'Middle': reminderListItem.addMiddle(); break;
          case 'Low': reminderListItem.addLow(); break;
        }
      }
      tempReminderList.push(reminderListItem);
    }
    tempReminderList.sort((r1: ReminderItem, r2: ReminderItem) => {
      if (r1.highNum > r2.highNum) { return -1; } else if (r1.highNum < r2.highNum) { return 1; }
      if (r1.middleNum > r2.middleNum) { return -1; } else if (r1.middleNum < r2.middleNum) { return 1; }
      if (r1.lowNum > r2.lowNum) { return -1; } else if (r1.lowNum < r2.lowNum) { return 1; }
      return 0;
    });
    reminderListData.reminderList = tempReminderList;
    this.setReminderListData(reminderListData);
  }

  getHistoryData () {
    const times: number[] = [0, 0, 0, 0, 0, 0, 0];
    const last7Days = this.getLast7Days();
    for (const reminder of this.historyReminders) {
      if (last7Days.includes(reminder.month + '/' + reminder.date)) {
        const i = last7Days.indexOf(reminder.month + '/' + reminder.date);
        times[i] = times[i] + 1;
      }
    }
    return times;
  }

  getLast7Days () {
    const arr = [];
    const now = new Date().getTime();
    const oneDayTime = 60 * 60 * 24 * 1000;    // 一天的秒数
    for (let i = 7; i >= 1; i--) {
      const dd = new Date(now - i * oneDayTime).getDate();
      const mm = new Date(now - i * oneDayTime).getMonth() + 1;
      arr.push((mm < 10 ? '0' + mm : mm + '') + '/' + (dd < 10 ? '0' + dd : dd + ''));
    }
    return arr;
  }

  setHistoryData(patientId: number) {
    for (const reminders of this.patientsReminders) {
        if (reminders.patientId === patientId) {
          this.patientReminders = reminders;
          this.historyReminders = reminders.patientReminders;
        }
    }
  }

  getHistoryPatient() {
    return this.patientReminders;
  }

  getDoctorAccount() {
    return this.doctorAccount;
  }

  updateReminder(reminder: ReminderModel) {
    this.patientsReminders.forEach((patientRemindersModel , idx) => {
      if (patientRemindersModel.patientId === reminder.patientId) {
        this.patientsReminders[idx].patientReminders.forEach( (reminderModel, iidx) => {
            if (reminderModel.id === reminder.id) {
              this.patientsReminders[idx].patientReminders[iidx] = reminder;
            }
        });
      }
    });
    this.generateRemindersList();
  }
}
