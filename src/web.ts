import { WebPlugin } from '@capacitor/core';

import type { SimpleContactPlugin } from './definitions';

export class SimpleContactWeb extends WebPlugin implements SimpleContactPlugin {
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
