import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FoodService } from '../../services/food.service';
import { Food } from '../../models/food.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-food-menu',
  imports : [CommonModule, RouterModule],
  templateUrl: './food-menu.component.html',
  styleUrl: './food-menu.component.css'
})

export class FoodMenuComponent implements OnInit {

  allFoods: Food[] = []; 
  types: string[] = ['Pizze', 'Arancini', 'Insalate', 'Bevande'];
  typeSelected = 'Pizze';

  constructor(private foodService: FoodService) {}  // Iniettiamo il servizio

  ngOnInit() {
    this.foodService.getFoods().subscribe(data => {
      this.allFoods = data;  // Quando arrivano i dati, li salviamo in `allFoods`
    });
  }

  get filteredFood(): Food[] {
    return this.allFoods.filter(cibo => cibo.type === this.typeSelected);
  }

  selezionaTipo(tipo: string): void {
    this.typeSelected = tipo;
  }
}