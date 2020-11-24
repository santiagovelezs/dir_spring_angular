import { Contacto } from './../models/Contacto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DirectorioService {

  private URL_API = "localhost:8080/contactos";

  constructor(private http: HttpClient) {}  

  getContactos() {
    return this.http.get<Contacto[]>(this.URL_API);
  }
}
