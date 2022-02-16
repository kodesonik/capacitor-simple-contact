# simple-contact

Capacitor plugins to easily phones contacts

## Install

```bash
npm install simple-contact
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`getContacts()`](#getcontacts)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### getContacts()

```typescript
getContacts() => Promise<{ result: { displayName: string; phoneNumber: string; }[]; }>
```

**Returns:** <code>Promise&lt;{ result: { displayName: string; phoneNumber: string; }[]; }&gt;</code>

--------------------

</docgen-api>
