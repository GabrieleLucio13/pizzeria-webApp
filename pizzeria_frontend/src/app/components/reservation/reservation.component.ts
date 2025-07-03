import { Component } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { Booking } from '../../models/Booking.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-reservation',
  imports: [ FormsModule, RouterModule, CommonModule],
  templateUrl: "./reservation.component.html",
  styleUrl: './reservation.component.css'
})
export class reservationComponent {

  azione: string = 'aggiungi';
  
  prenotazione: Booking = {
    name: '',
    contact: '',
    date: '',
    time: '',
    number: 1
  };
  
  prenotazione_da_modificare: Booking = {
      name: '',
      contact: '',
      date: '',
      time: '',
      number: 1
      };

  showDetails : boolean = false;
  modifyDetails : boolean = false;
  randomCode: string = '';

  constructor(private reservationService: ReservationService, private router: Router) {}

  addReservation() {
    this.reservationService.addReservation(this.prenotazione).subscribe(() => {
      alert('Prenotazione aggiunta con successo!');
      this.router.navigate(['/']);
    });
  }

  getReservation(){
    this.reservationService.getReservationByCode(this.randomCode).subscribe({
      next: (res) => {
        this.prenotazione_da_modificare = res;
        this.showDetails = true;
      },
      error: () => {
        alert('Codice non valido o prenotazione non trovata');
        this.showDetails = false;
        this.modifyDetails = false;
      }
  })
}
  modReservation(){
    if (confirm('Sei sicuro di voler modificare questa prenotazione?')) {
      this.reservationService.updateReservation(this.randomCode, this.prenotazione).subscribe(() => {
        alert('Prenotazione modificata.');
        this.router.navigate(['/']);
      });
    }
  }

  rmvReservation(){
    this.modifyDetails = false;
    if (confirm('Sei sicuro di voler cancellare questa prenotazione?')) {
      this.reservationService.deleteReservation(this.randomCode).subscribe(() => {
        alert('Prenotazione cancellata.');
        this.router.navigate(['/']);
      });
    }
  }

  showForm(){
    this.modifyDetails = true;
  }
}
