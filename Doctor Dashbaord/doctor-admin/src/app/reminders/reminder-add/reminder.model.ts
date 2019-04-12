export class ReminderModel {
  public id: number;
  public priority: string;
  public year: string;
  public month: string;
  public date: string;
  public hours: string;
  public minutes: string;
  public description: string;
  public note: string;
  public patientId: number;
  public finished: string;
  public finishedOntime: string;
  public doctorId: number;


  constructor(id: number, priority: string, year: string, month: string, date: string, hours: string, minutes: string, description: string, note: string, patientId: number, finished: string, finishedOntime: string, doctorId: number) {
    this.id = id;
    this.priority = priority;
    this.year = year;
    this.month = month;
    this.date = date;
    this.hours = hours;
    this.minutes = minutes;
    this.description = description;
    this.note = note;
    this.patientId = patientId;
    this.finished = finished;
    this.finishedOntime = finishedOntime;
    this.doctorId = doctorId;
  }
}
