import { Telefono } from './Telefono';
export interface Contacto {
    id?: number;
    name: string;  
    telefonos: Telefono[];
}