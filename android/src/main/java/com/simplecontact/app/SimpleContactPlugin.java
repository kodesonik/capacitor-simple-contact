package com.simplecontact.app;


import android.Manifest;
import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@CapacitorPlugin(name = "SimpleContact", permissions = {
              @Permission(
                  alias = "contacts",
                  strings = { Manifest.permission.READ_CONTACTS }
                )
           })
//requestCodes={SimpleContactPlugin.REQUEST_CONTACTS
public class SimpleContactPlugin extends Plugin {
    protected static final int REQUEST_CONTACTS = 12345; // Unique request code

    private SimpleContact implementation = new SimpleContact();

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", implementation.echo(value));
        call.resolve(ret);
    }

   @PluginMethod()
    public void getContacts(PluginCall call) {
        // String value = call.getString("filter");
        // Filter based on the value if want
 
      contactsPermsCallback(call);
    }

    @PermissionCallback
    private void contactsPermsCallback(PluginCall call) {
        if (getPermissionState("contacts") == PermissionState.GRANTED) {
            loadContacts(call);
        } else {
            call.reject("Permission is required to read contacts");
        }
    }
 
    void loadContacts(PluginCall call) {
        ArrayList<Map> contactList = new ArrayList<>();
        ContentResolver cr = this.getContext().getContentResolver();
 
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                Map<String,String> map =  new HashMap<String, String>();
 
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
 
                map.put("displayName", name);
 
                String contactNumber = "";
 
                if (cur.getInt(cur.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    pCur.moveToFirst();
                    contactNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//                     Log.i("phoneNumber", "The phone number is "+ contactNumber);
                }
                if (contactNumber != "") {
                    contactNumber = contactNumber.replaceAll("\\s","");
                    contactNumber = contactNumber.replaceAll("-","");
                    map.put("phoneNumber", contactNumber);
                    contactList.add(map);
                }

            }
        }
        if (cur != null) {
            cur.close();
        }
 
        JSONArray jsonArray = new JSONArray(contactList);
        JSObject ret = new JSObject();
        ret.put("result", jsonArray);
        call.resolve(ret);
    }
}
