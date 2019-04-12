import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {ReminderService} from '../reminder.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ReminderModel} from './reminder.model';
import {NetConnectService} from '../../shared/net.connect.service';

@Component({
  selector: 'app-reminder-add',
  templateUrl: './reminder-add.component.html',
  styleUrls: ['./reminder-add.component.css']
})
export class ReminderAddComponent implements OnInit {
  reminderForm: FormGroup;
  constructor(private reminderService: ReminderService, private router: Router,
              private route: ActivatedRoute, private netConnectService: NetConnectService) { }

  ngOnInit() {

    this.initForm();
  }

  onSubmit() {
    const newReminder = new ReminderModel(
      0,
      this.reminderForm.value['priority'],
      this.reminderForm.value['year'],
      this.reminderForm.value['month'] < 10 ? '0' + this.reminderForm.value['month'] : this.reminderForm.value['month'] + '',
      this.reminderForm.value['date'] < 10 ? '0' + this.reminderForm.value['date'] : this.reminderForm.value['date'] + '',
      this.reminderForm.value['hours'] < 10 ? '0' + this.reminderForm.value['hours'] : this.reminderForm.value['hours'] + '',
      this.reminderForm.value['minutes'] < 10 ? '0' + this.reminderForm.value['minutes'] : this.reminderForm.value['minutes'] + '',
      this.reminderForm.value['description'],
      this.reminderForm.value['note'],
      this.reminderForm.value['patient_id'],
      'N',
      'N',
      this.reminderService.getDoctorAccount().id
    );
    console.log(newReminder.priority);
    console.log(newReminder.year);
    console.log(newReminder.month);
    console.log(newReminder.date);
    console.log(newReminder.hours);
    console.log(newReminder.minutes);
    console.log(newReminder.description);
    console.log(newReminder.note);
    console.log(newReminder.patientId);
    console.log(newReminder.finished);
    console.log(newReminder.finishedOntime);
    console.log(newReminder.doctorId);
    this.netConnectService.storeReminder(newReminder);
    this.onCancel();
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  initForm() {
    let patient_id: number;
    let priority = '';
    let year: number;
    let month: number;
    let date: number;
    let hours: number;
    let minutes: number;
    let description = '';
    let note = '';
    this.reminderForm = new FormGroup({
      'patient_id': new FormControl(patient_id, Validators.required),
      'priority': new FormControl(priority, Validators.required),
      'year': new FormControl(year, Validators.required),
      'month': new FormControl(month, Validators.required),
      'date': new FormControl(date, Validators.required),
      'hours': new FormControl(hours, Validators.required),
      'minutes': new FormControl(minutes, Validators.required),
      'description': new FormControl(description, Validators.required),
      'note': new FormControl(note, Validators.required)
    });
  }

}
