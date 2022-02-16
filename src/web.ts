import { WebPlugin } from '@capacitor/core';

import type { simpleContactPlugin } from './definitions';

export class simpleContactWeb extends WebPlugin implements simpleContactPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
