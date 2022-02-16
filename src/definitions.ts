export interface SimpleContactPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  getContacts(): Promise<{ result: {displayName: string; phoneNumber: string}[]}>
}
