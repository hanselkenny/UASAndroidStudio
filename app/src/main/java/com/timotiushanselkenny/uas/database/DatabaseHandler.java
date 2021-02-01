package com.timotiushanselkenny.uas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "UAS";

    // table name
    private static final String TABLE_TALL = "TrOrder";

    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PRICE = "price";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_IMAGE = "image";
    private static DatabaseHandler sInstance;

    // ...

    public static synchronized DatabaseHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TALL + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"+ KEY_PRICE + " TEXT,"
                + KEY_QUANTITY + " TEXT," +KEY_DESCRIPTION +" TEXT," +KEY_IMAGE +" TEXT " +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // on Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TALL);
        onCreate(db);
    }
    public void addRecord(ProductTransaction userModels){

        SQLiteDatabase db  = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userModels.getName());
        values.put(KEY_PRICE, userModels.getPrice());
        values.put(KEY_QUANTITY, userModels.getQuantity());
        values.put(KEY_DESCRIPTION, userModels.getDescription());
        values.put(KEY_IMAGE, userModels.getImage());

        db.insert(TABLE_TALL, null, values);
        db.close();
    }
    public ProductTransaction getProduct(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TALL, new String[] { KEY_ID,
                        KEY_NAME, KEY_PRICE,KEY_QUANTITY,KEY_DESCRIPTION ,KEY_IMAGE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ProductTransaction product = new ProductTransaction(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(4),Double.parseDouble(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),cursor.getString(5));
        // return contact
        return product;
    }
    // get All Record
    public List<ProductTransaction> getAllRecord() {
        List<ProductTransaction> contactList = new ArrayList<ProductTransaction>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TALL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ProductTransaction userModels = new ProductTransaction();
                userModels.setId(Integer.parseInt(cursor.getString(0)));
                userModels.setmName(cursor.getString(1));
                userModels.setmPrice(Double.parseDouble(cursor.getString(2)));
                userModels.setmQuantity(Integer.parseInt(cursor.getString(3)));
                userModels.setmDescription(cursor.getString(4));
                userModels.setmImage(cursor.getString(5));
                contactList.add(userModels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public int getProductCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TALL;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    public int updateProduct(ProductTransaction contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PRICE, contact.getPrice());
        values.put(KEY_QUANTITY, contact.getQuantity());
        values.put(KEY_DESCRIPTION, contact.getDescription());
        values.put(KEY_IMAGE, contact.getImage());

        // updating row
        return db.update(TABLE_TALL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
    }
    public void deleteModel(ProductTransaction contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TALL, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }
}
