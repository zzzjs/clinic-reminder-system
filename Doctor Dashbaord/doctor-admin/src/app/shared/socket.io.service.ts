import * as io from 'socket.io-client';
import Socket = SocketIOClient.Socket;
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {ReminderService} from '../reminders/reminder.service';
import {SocketIO_HostName, SocketIO_Port} from './constants';

@Injectable()
export class SocketIoService {
  private socket: Socket;
  public messages: Observable<String>;
  private socketObserver: any;

  constructor(private remindersService: ReminderService) {
    this.messages = Observable.create(observer => {
      this.socketObserver = observer;
    });
    this.init();
  }

  init() {
    this.socket = io(SocketIO_HostName + ':' + SocketIO_Port, {autoConnect: false});

    this.socket.on('connect', () => {
      console.log('***Socket Connected***');
      this.socket.emit('user_connect', {
        userId: this.remindersService.getDoctorAccount().id,
        userType: '1'
      });
    });

    this.socket.on('reconnecting', attempt => {
      console.log('***Socket Reconnecting***', attempt);
    });

    this.socket.on('reconnect_failed', () => {
      console.log('***Socket Reconnect failed***');
    });

    this.socket.on('disconnect', () => {
      console.log('***Socket Disconnected***');
    });

    this.socket.on('message', (msg) => {
      console.log(msg);
    });

    this.socket.on('goods_list_export_event', response => {
      const message: string = response;
      console.log(message);
      this.socketObserver.next(message);
    });
  }

  send(msg: string) {
    if (msg !== '') {
      console.log(msg);
      this.socket.emit('client_message', {
        msgContent: msg
      });
    }
  }

  disconnect() {
    this.socket.disconnect();
  }

  connect() {
    this.socket.connect();
  }


}
