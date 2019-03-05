export class Doctor {
  public id: number;
  public name: string;
  public speciality: string;
  public address: string;
  public education: string;


  constructor(id: number, name: string, speciality: string, address: string, education: string) {
    this.id = id;
    this.name = name;
    this.speciality = speciality;
    this.address = address;
    this.education = education;
  }
}
