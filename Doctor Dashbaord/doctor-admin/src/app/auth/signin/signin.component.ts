import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';
import {AuthService} from '../auth.service';
import {NetResponseDataModel} from '../../shared/net.response.data.model';
import {NetConnectService} from '../../shared/net.connect.service';
import {DoctorAccount} from '../doctor.account.model';
import {PatientRemindersModel} from '../../shared/patient.reminders.model';
import {ReminderService} from '../../reminders/reminder.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {
  invalid: string = '';
  constructor(private authService: AuthService, private netService: NetConnectService,
              private reminderService: ReminderService, private router: Router) { }

  ngOnInit() {
  }

  onSignin(form: NgForm) {
    const email = form.value.email;
    const password = form.value.password;
    this.authService.signinUser(email, password)
      .subscribe( (data: NetResponseDataModel) => {
        if (data.resp_event === 100) {
          const account: DoctorAccount = JSON.parse(data.respJson);
          this.netService.getPatientsReminders(account.id)
            .subscribe( (reminders: PatientRemindersModel[]) => {
              this.reminderService.setPatientsReminders(account, reminders);
              this.router.navigate(['/reminders']);
              this.authService.token = '1024';
              this.invalid = '';
            }
            );
        } else if (data.resp_event === 990) {
          this.invalid = 'Mail or Password Invalid!';
          console.log('Password Invalid');
        }  else {
          this.invalid = 'Login failed!';
          console.log('Login failed!');
        }
      }, error1 => {
        console.log(error1);
      });

  }
}
