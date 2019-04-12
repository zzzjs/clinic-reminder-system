import * as io from 'socket.io-client';
import Socket = SocketIOClient.Socket;
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {NetService} from "./net";
import {SocketIO_HostName, SocketIO_Port} from "../shared/constants";

@Injectable()
export class SocketService {
  private socket: Socket;
  public messages: Observable<String>;
  private socketObserver: any;

  constructor(private netService: NetService) {
    this.messages = Observable.create(observer => {
      this.socketObserver = observer;
    });
    this.init();
  }

  init() {
    this.socket = io(SocketIO_HostName + ':' + SocketIO_Port, {autoConnect: false});

    this.socket.on("connect", () => {
      console.debug('***Socket Connected***');
      this.socket.emit('user_connect', {
        userId: this.netService.getAccount().patientId,
        userType: '2'
      });
    });

    this.socket.on("reconnecting", attempt => {
      console.debug('***Socket Reconnecting***', attempt);
    });

    this.socket.on("reconnect_failed", () => {
      console.debug('***Socket Reconnect failed***');
    });

    this.socket.on('disconnect', () => {
      console.debug('***Socket Disconnected***');
    });

    this.socket.on('goods_list_export_event', response => {
      let message: string = response;
      console.log(message);
      this.socketObserver.next(message);
    })
  }

  send(msg) {
    if(msg != ''){
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
