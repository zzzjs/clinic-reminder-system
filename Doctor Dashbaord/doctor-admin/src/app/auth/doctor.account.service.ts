import {Injectable} from '@angular/core';
import {DoctorAccount} from './doctor.account.model';

@Injectable()
export class DoctorAccountService {
  private accounts: DoctorAccount[] = [];

  setAccount(account) {

  }

  getAccount() {
    return this.accounts.slice();
  }
}
