export class ReminderItem {
  public patientId: number;
  public firstName: string;
  public lastName: string;
  public highNum: number;
  public middleNum: number;
  public lowNum: number;

  constructor(patientId: number, firstName: string, lastName: string, highNum: number, middleNum: number, lowNum: number) {
    this.patientId = patientId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.highNum = highNum;
    this.middleNum = middleNum;
    this.lowNum = lowNum;
  }

  addHigh() { this.highNum++; }
  addMiddle() { this.middleNum++; }
  addLow() { this.lowNum++; }

}
