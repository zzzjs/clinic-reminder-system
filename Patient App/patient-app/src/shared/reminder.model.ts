export interface ReminderModel {

  id: number;
  priority: string;
  year: number;
  month: number;
  date: number;
  hours: number;
  minutes: number;
  description: string;
  note: string;
  patientId: number;
  finished: string;
  finishedOntime: string;
  doctorId: number;

}
