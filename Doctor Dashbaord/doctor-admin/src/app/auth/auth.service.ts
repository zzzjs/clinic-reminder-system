import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {catchError, map} from 'rxjs/operators';
import {DoctorAccount} from './doctor.account.model';
import {NetRequestDataModel} from '../shared/net.request.data.model';
import {throwError} from 'rxjs';
import {ReminderListData} from '../reminders/reminder-list.data.model';
import {ReminderService} from '../reminders/reminder.service';
import {Service_HostName, Service_Port} from '../shared/constants';

@Injectable()
export class AuthService {
  token: string;
  doctorAccount: DoctorAccount;
  netRequestData: NetRequestDataModel;
  netData: ReminderListData;

  constructor(private router: Router,
              private httpClient: HttpClient,
              private reminderService: ReminderService) {}

  signinUser(email: string, password: string) {
    console.log(email, password);
    this.doctorAccount = new DoctorAccount(0, '', '', email, password);
    this.netRequestData = new NetRequestDataModel('login', 100, 0, this.doctorAccount);
    return this.httpClient.post('http://localhost:8080/api/doctor/login', this.netRequestData);
  }
  logout() {
    this.router.navigate(['/signin']);
    this.token = null;
  }

  getReminderList(): any {
    this.doctorAccount = new DoctorAccount(0, '', '', 'aw@gmail.com', '12345');
    this.netRequestData = new NetRequestDataModel('login', 20, 0, this.doctorAccount);
    return this.httpClient.post(Service_HostName + ':' + Service_Port + '/api/doctor/login', this.netRequestData);
  }

  getReminderListNoResponse() {
    this.doctorAccount = new DoctorAccount(0, '', '', 'david@gmail.com', '12345');
    this.netRequestData = new NetRequestDataModel('login', 20, 0, this.doctorAccount);
    this.httpClient.post(Service_HostName + ':' + Service_Port + '/api/doctor/login', this.netRequestData)
      .subscribe(
      (data: ReminderListData) => {
        this.netData = data;
        console.log('getReminderList ' + data.reminderList);
        this.reminderService.setReminderListData(data);
        this.router.navigate(['/reminders']);
        this.token = '1024';
      }
    );
  }

  getReminderListWithPipe(): any {
    this.doctorAccount = new DoctorAccount(0, '', '', 'aw@gmail.com', '12345');
    this.netRequestData = new NetRequestDataModel('login', 20, 0, this.doctorAccount);
    this.httpClient.post(Service_HostName + ':' + Service_Port + '/api/doctor/login', this.netRequestData)
      .pipe(map((response) => {
      console.log('data ' + response);
      return response;
    }))
      .pipe(catchError(error => {
        return throwError('Something went wrong');
      }));
  }

  isAuthenticated() {
    return this.token != null;
  }
}
