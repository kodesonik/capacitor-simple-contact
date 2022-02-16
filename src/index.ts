import { registerPlugin } from '@capacitor/core';

import type { simpleContactPlugin } from './definitions';

const simpleContact = registerPlugin<simpleContactPlugin>('simpleContact', {
  web: () => import('./web').then(m => new m.simpleContactWeb()),
});

export * from './definitions';
export { simpleContact };
