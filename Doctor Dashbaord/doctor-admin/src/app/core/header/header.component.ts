import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../auth/auth.service';
import {SocketIoService} from '../../shared/socket.io.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  constructor(private authService: AuthService, private socketIoService: SocketIoService) { }

  ngOnInit() {

  }

  onLogout() {
    this.authService.logout();
    this.socketIoService.disconnect();
  }

  onAddReminder() {

  }
}
