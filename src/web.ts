import { WebPlugin } from '@capacitor/core';

import type { SimpleContactPlugin } from './definitions';

export class SimpleContactWeb extends WebPlugin implements SimpleContactPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  async getContacts(): Promise<{ result: {displayName: string; phoneNumber: string}[]}> {
    return{
      result:  [
        {
          displayName: 'Kode Sonik',
          phoneNumber: '92942601'
        }
      ]
    }
  }

}
