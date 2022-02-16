export interface SimpleContactPlugin {
  getContacts(): Promise<{ result: {displayName: string; phoneNumber: string}[]}>
}
