import { registerPlugin } from '@capacitor/core';

import type { SimpleContactPlugin } from './definitions';

const SimpleContact = registerPlugin<SimpleContactPlugin>('SimpleContact', {
  web: () => import('./web').then(m => new m.SimpleContactWeb()),
});

export * from './definitions';
export { SimpleContact };
