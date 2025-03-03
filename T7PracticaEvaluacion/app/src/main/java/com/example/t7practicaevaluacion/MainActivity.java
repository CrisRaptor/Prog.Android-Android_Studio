package com.example.t7practicaevaluacion;

import static com.example.t7practicaevaluacion.Database.ContactProvider.CONTENT_URI;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.t7practicaevaluacion.ContactList.Contact;
import com.example.t7practicaevaluacion.ContactList.ListAdapter;
import com.example.t7practicaevaluacion.Database.ContactProvider;
import com.example.t7practicaevaluacion.Spinner.Avatar;
import com.example.t7practicaevaluacion.Spinner.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout contactLayout;
    private EditText etFindContact, etName, etPhone;
    private ImageButton btnAddContact;
    private TextView nameLabel, phoneLabel, avatarLabel;
    private Button btnAdd, btnModify, btnCancel;
    private Spinner spinnerAvatar;
    private ListView contactList;

    private ListAdapter listAdapter;

    private int selectedDrawableId;
    private long selectedContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Establece el toolbar*/
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Recupera los elementos*/
        contactLayout = findViewById(R.id.contactLayout);

        etFindContact = findViewById(R.id.etFindContact);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);

        btnAddContact = findViewById(R.id.btnAddContact);

        nameLabel = findViewById(R.id.nameLabel);
        phoneLabel = findViewById(R.id.phoneLabel);
        avatarLabel = findViewById(R.id.avatarLabel);

        btnAdd = findViewById(R.id.btnAdd);
        btnModify = findViewById(R.id.btnModify);
        btnCancel = findViewById(R.id.btnCancel);

        spinnerAvatar = findViewById(R.id.spinnerAvatar);
        contactList = findViewById(R.id.contactList);

        /*Registra el menu y carga los spinner*/
        registerForContextMenu(contactList);
        loadSpinner();
        loadContactList();

        /*Establecemos los listener*/
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactLayout.setVisibility(View.GONE);
                btnAdd.setVisibility(View.GONE);
                btnModify.setVisibility(View.GONE);
            }
        });

        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactLayout.setVisibility(View.VISIBLE);
                btnAdd.setVisibility(View.VISIBLE);
                btnModify.setVisibility(View.GONE);
                spinnerAvatar.setSelection(0);
                etName.setText("");
                etPhone.setText("");
            }
        });

        spinnerAvatar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDrawableId = ((Avatar) parent.getItemAtPosition(position)).getAvatar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty()){
                    ContentValues values = new ContentValues();
                    values.put(ContactProvider.Contacts.COL_NAME, name);
                    values.put(ContactProvider.Contacts.COL_PHONE, phone);
                    values.put(ContactProvider.Contacts.COL_AVATAR, selectedDrawableId);

                    getContentResolver().insert(CONTENT_URI, values);
                    loadContactList();

                    contactLayout.setVisibility(View.GONE);
                    btnAdd.setVisibility(View.GONE);

                    etName.setText("");
                    etPhone.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String phone = etPhone.getText().toString();

                if (!name.isEmpty() && !phone.isEmpty()){
                    ContentValues values = new ContentValues();
                    values.put(ContactProvider.Contacts.COL_NAME, name);
                    values.put(ContactProvider.Contacts.COL_PHONE, phone);
                    values.put(ContactProvider.Contacts.COL_AVATAR, selectedDrawableId);

                    getContentResolver().update(CONTENT_URI, values, ContactProvider.Contacts.COL_ID + " = ?", new String[]{String.valueOf(selectedContact)});
                    loadContactList();

                    contactLayout.setVisibility(View.GONE);
                    btnAdd.setVisibility(View.GONE);

                    etName.setText("");
                    etPhone.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Faltan datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadSpinner(){
        List<Avatar> avatars = new ArrayList<>();
        avatars.add(new Avatar(R.drawable.batman));
        avatars.add(new Avatar(R.drawable.capi));
        avatars.add(new Avatar(R.drawable.deadpool));
        avatars.add(new Avatar(R.drawable.furia));
        avatars.add(new Avatar(R.drawable.hulk));
        avatars.add(new Avatar(R.drawable.ironman));
        avatars.add(new Avatar(R.drawable.lobezno));
        avatars.add(new Avatar(R.drawable.spiderman));
        avatars.add(new Avatar(R.drawable.thor));
        avatars.add(new Avatar(R.drawable.wonderwoman));

        spinnerAvatar.setAdapter(new SpinnerAdapter(avatars, MainActivity.this, R.layout.spinner));
    }

    private void loadContactList(){
        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);
        ArrayList<Contact> contacts = new ArrayList<>();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                long idContact = cursor.getLong(cursor.getColumnIndexOrThrow("clave"));
                int avatar = cursor.getInt(cursor.getColumnIndexOrThrow("avatar"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));

                contacts.add(new Contact(idContact, avatar, name, phone));
            }
            cursor.close();
        }

        listAdapter = new ListAdapter(contacts, MainActivity.this, R.layout.element);
        contactList.setAdapter(listAdapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contact_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if(info != null) {
            Contact contacto = (Contact) contactList.getItemAtPosition(info.position);
            selectedContact = contacto.getContactId();
        }

        if (item.getItemId() == R.id.delete) {

            getContentResolver().delete(Uri.withAppendedPath(CONTENT_URI, String.valueOf(selectedContact)), null, null);
            loadContactList();

        } else if (item.getItemId() == R.id.update) {

            contactLayout.setVisibility(View.VISIBLE);
            btnAdd.setVisibility(View.GONE);
            btnModify.setVisibility(View.VISIBLE);

            Cursor cursor = getContentResolver().query(Uri.withAppendedPath(CONTENT_URI, String.valueOf(selectedContact)), null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
                int avatar = cursor.getInt(cursor.getColumnIndexOrThrow("avatar"));

                etName.setText(name);
                etPhone.setText(phone);

                for (int i = 0; i < spinnerAvatar.getCount(); i++) {
                    if (((Avatar) spinnerAvatar.getItemAtPosition(i)).getAvatar() == avatar) {
                        spinnerAvatar.setSelection(i);
                        break;
                    }
                }

                cursor.close();
            }

        }
        return super.onContextItemSelected(item);
    }
}