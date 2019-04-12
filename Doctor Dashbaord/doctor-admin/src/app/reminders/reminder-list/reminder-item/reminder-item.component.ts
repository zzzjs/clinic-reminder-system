import {Component, Input, OnInit} from '@angular/core';
import {ReminderItem} from '../../reminder-item.model';

@Component({
  selector: 'app-reminder-item',
  templateUrl: './reminder-item.component.html',
  styleUrls: ['./reminder-item.component.css']
})
export class ReminderItemComponent implements OnInit {
  @Input() reminder: ReminderItem;
  @Input() index: number;
  constructor() { }

  ngOnInit() {
    console.log('Item oninit ' + this.index , this.reminder);
  }

}
