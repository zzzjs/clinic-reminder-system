export class PatientAccount{
  public id: number;
  public email: string;
  public password: string;
  public patientId: number;


  constructor(id: number, email: string, password: string, patient_id: number) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.patientId = patient_id;
  }
}
