export interface simpleContactPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
