import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {PatientAccount} from "../shared/patient.account.model";
import {NetRequestDataModel} from "../shared/net.request.data.model";
import {ReminderModel} from "../shared/reminder.model";
import {Service_HostName, Service_Port} from "../shared/constants";

@Injectable()
export class NetService {
  account: PatientAccount;
  constructor(private http: HttpClient) {}

  signinUser(email: string, password: string, ) {
    this.account = new PatientAccount(0, email, password, 0);
    const netRequestData = new NetRequestDataModel('login', 20, 0, this.account);
    return this.http.post(Service_HostName + ':'+ Service_Port + '/api/patient/login', netRequestData);
  }

  setAccount(account: PatientAccount) {
    this.account = account;
  }

  getAccount() {
    return this.account;
  }

  getReminders(patientId: number) {
    return this.http.get(Service_HostName + ':'+ Service_Port + '/api/reminders/' + patientId);
  }

  finishReminder(reminder: ReminderModel) {
    reminder.finished = 'Y';
    const data = new Date();
    const year = data.getFullYear();
    const month = data.getMonth() + 1;
    const date = data.getDate();
    const hours = data.getHours();
    const mins = data.getMinutes();
    let ontime: string;
    const t = reminder.year +''+ reminder.month +''+ reminder.date +''+ reminder.hours +''+ reminder.minutes;
    const currentTime: number = year*10000*10000 + month*10000*100 + date*10000 + hours *100 + mins;
    const reminderTime: number = +t;
    if (currentTime<=reminderTime) {ontime = 'Y';}
    else {ontime = 'N'}
    reminder.finishedOntime = ontime;
    const netRequestData = new NetRequestDataModel('reminderFinished', 30, 0, reminder);
    return this.http.put(Service_HostName + ':'+ Service_Port + '/api/reminders/', netRequestData);
  }
}
