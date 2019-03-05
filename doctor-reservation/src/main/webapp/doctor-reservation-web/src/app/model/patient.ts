export class Patient {
  public id: number;
  public name: string;
  public birthDate: Date;
  public gender: string;


  constructor(id: number, name: string, birthDate: Date, gender: string) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
    this.gender = gender;
  }
}
