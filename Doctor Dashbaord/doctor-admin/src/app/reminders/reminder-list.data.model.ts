import {ReminderItem} from './reminder-item.model';

export class ReminderListData {
  public id: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public reminderList: ReminderItem[];

  constructor(id: number, firstName: string, lastName: string, email: string, reminderList: ReminderItem[]) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.reminderList = reminderList;
  }
}
