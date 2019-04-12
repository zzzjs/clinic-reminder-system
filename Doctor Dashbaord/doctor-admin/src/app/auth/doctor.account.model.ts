export class DoctorAccount {
  public id: number;
  public firstName: string;
  public lastName: string;
  public email: string;
  public password: string;

  constructor(id: number, first_name: string, last_name: string, email: string, password: string) {
    this.id = id;
    this.firstName = first_name;
    this.lastName = last_name;
    this.email = email;
    this.password = password;
  }
}
