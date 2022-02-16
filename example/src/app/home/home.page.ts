import { Component } from '@angular/core';
import { SimpleContact } from 'simple-contact';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  contacts: { displayName: string; phoneNumber: string }[];

  constructor() {
    this.getContacts();
  }

  async getContacts() {
    try {
      this.contacts =  (await (SimpleContact.getContacts())).result
      console.log(this.contacts);
    } catch(err ) {
      console.log(err.message);
    }

  }
}
