import {ReminderModel} from '../reminders/reminder-add/reminder.model';

export interface PatientRemindersModel {
  patientId: number;
  firstName: string;
  lastName: string;
  patientReminders: ReminderModel[];
}
