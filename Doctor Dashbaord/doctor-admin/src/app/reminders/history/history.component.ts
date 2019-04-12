import { Component, OnInit } from '@angular/core';
import {ReminderService} from '../reminder.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ReminderModel} from '../reminder-add/reminder.model';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  constructor(private reminderService: ReminderService, private route: ActivatedRoute, private router: Router) { }
  reminders: ReminderModel[];
  last7Days: string[] = ['01/04', '01/05', '01/06', '01/07', '01/08', '01/09', '01/10'];
  chartData = [{
    label: 'not on-time',
    data: [2, 2, 5, 7, 3, 10, 3],
    borderWidth: 1
  }];

  chartOptions = {
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        },
        width: 1
      }]
    }
  };

  colors = [
    {
      backgroundColor: 'rgba(30, 169, 224, 0.8)'
    }
  ];
  title: string;

  ngOnInit() {

    this.last7Days = this.reminderService.getLast7Days();
    this.chartData = [
      {
        label: 'not on-time',
        data: this.reminderService.getHistoryData(),
        borderWidth: 1
      }
    ];
    this.title = this.reminderService.getHistoryPatient().firstName + ' didn\'t finish on-time in last 7 days.';
  }

  onChartClick(event) {
    console.log(event);
  }

}
