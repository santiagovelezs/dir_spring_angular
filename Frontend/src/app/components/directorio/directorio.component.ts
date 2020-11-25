import { Contacto } from './../../models/Contacto';
import { DirectorioService } from './../../services/directorio.service';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';


@Component({
  selector: 'app-directorio',
  templateUrl: './directorio.component.html',
  styleUrls: ['./directorio.component.css']
})
export class DirectorioComponent implements OnInit {

  contactos: Contacto[];
  form: FormGroup;

  constructor(private directorioService: DirectorioService) {
    this.builtForm();
  }

  private builtForm() {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      telefonos: new FormArray([
        new FormControl('', [Validators.required])
      ])
    });
  }

  ngOnInit(): void {
    this.getContactos();
  }

  getContactos() {
    this.directorioService.getContactos()
      .subscribe(
        resp => {
          this.contactos = resp;
          console.log(this.contactos[1].telefonos)
        },
        err => {
          console.log("Error: ", err)
        }
      )
  }

  onAddPhone() {
    (<FormArray>this.form.controls['telefonos'])
      .push(new FormControl('', [Validators.required]));
  }

  onRemovePhone(index) {
    (<FormArray>this.form.controls['telefonos'])
      .removeAt(index);
  }

  get f() {
    return this.form.controls;
  }

  createContacto() {
    if (this.form.status !== 'VALID') {
      console.log(this.form.value);
      return;
    }
    console.log(this.form.value);
    this.directorioService.createContacto(this.form.value)
      .pipe(first())
      .subscribe(
        res => {
          console.log(res)
          this.getContactos();
        },
        error => console.log(error)
      )
  }
}
