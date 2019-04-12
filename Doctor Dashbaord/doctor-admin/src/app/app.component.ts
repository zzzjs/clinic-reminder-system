import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'doctor-admin';

  constructor(private route: Router, private authService: AuthService) {}

  ngOnInit(): void {
    if (this.authService.isAuthenticated() === false) {
      this.route.navigate(['/signin']);
    }
  }
}
