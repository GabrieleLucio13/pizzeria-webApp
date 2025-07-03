import { Component } from '@angular/core';
import { RouterModule} from '@angular/router';
import { CommonModule } from '@angular/common';
import { UnsplashService } from '../../services/unsplash.service';

@Component({
  selector: 'app-home',
  imports: [RouterModule, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  images: string[] = [];

  constructor(private unsplashService: UnsplashService) {}

  ngOnInit(): void {
    this.unsplashService.getPhotos('pizza', 10).subscribe(response => {
      
      this.images = response.results.map((img: any) => img.urls.regular);
    });
  }
}
