import { DirectorioService } from './../../services/directorio.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directorio',
  templateUrl: './directorio.component.html',
  styleUrls: ['./directorio.component.css']
})
export class DirectorioComponent implements OnInit {

  contactos = [];

  constructor(private directorioService: DirectorioService) { }

  ngOnInit(): void {

    this.directorioService.getContactos()
        .subscribe(
          resp => {
            this.contactos = resp;
          },
          err => {
            console.log(err)
          }
        )
    }
}
