package com.gabys.ps_tema2.model.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gabys.ps_tema2.model.DB.DBConnection;
import com.gabys.ps_tema2.model.Property;
import com.gabys.ps_tema2.model.Rent;
import com.gabys.ps_tema2.model.User;

import java.util.ArrayList;

public class RentPersistence {

    private SQLiteDatabase database;

    public RentPersistence(Context context) {
        this.database = DBConnection.getInstance(context).getConnection();
    }

    public ArrayList<Property> getProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        Cursor cursor = database.query("Properties", null, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String location = cursor.getString(cursor.getColumnIndexOrThrow("location"));
                int rooms = cursor.getInt(cursor.getColumnIndexOrThrow("roomsNo"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                float price = cursor.getFloat(cursor.getColumnIndexOrThrow("price"));
                int isAvailable = cursor.getInt(cursor.getColumnIndexOrThrow("isAvailable"));
                String imageURL = cursor.getString(cursor.getColumnIndexOrThrow("imageURL"));

                properties.add(new Property(id, title, location, rooms, type, price, isAvailable == 1, imageURL));

                cursor.moveToNext();
            }
        }

        cursor.close();
        return properties;
    }

    public Rent getRent(int id) {
        Rent rent = null;
        Cursor cursor = database.query("Rents", null, "id = " + id, null, null, null, null);

        if (cursor != null){
            rent = new Rent();
            cursor.moveToFirst();
        }


        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int clientId = cursor.getInt(cursor.getColumnIndexOrThrow("id_client"));
                int propertyId = cursor.getInt(cursor.getColumnIndexOrThrow("id_property"));
                rent = new Rent(id, clientId, propertyId);

                cursor.moveToNext();
            }
        }

        cursor.close();
        return rent;
    }

    public long addRent(Rent rent) {
        ContentValues values = new ContentValues();

        values.put("id_client", rent.getClientId());
        values.put("id_property", rent.getPropertyId());

        return database.insert("Rents", null, values);
    }

    public void updateProperty(Property property) {
        ContentValues values = new ContentValues();

        values.put("title", property.getTitle());
        values.put("location", property.getLocation());
        values.put("roomsNo", property.getRoomsNo());
        values.put("type", property.getType());
        values.put("price", property.getPrice());
        values.put("isAvailable", property.isAvailable() ? 1 : 0);
        values.put("imageURL", property.getImageURL());

        String[] whereArgs = {String.valueOf(property.getId())};
        database.update("Properties", values, "id = ?", whereArgs);
    }

    public int deleteProperty(int id) {
        String[] whereArgs = {String.valueOf(id)};
        return database.delete("Properties", "id = ?", whereArgs);
    }

    public void createPropertyTable() {
        String query = "CREATE TABLE IF NOT EXISTS Rents (" +
                "id INTEGER primary key autoincrement, " +
                "id_client INTEGER, " +
                "id_property INTEGER);"
                ;
        database.execSQL(query);
    }

    public void deleteTable(){
        database.execSQL("DROP TABLE IF EXISTS Rents");
    }

    public void insertDummyValues(){
        this.addRent(new Rent());
        this.addRent(new Rent());
        this.addRent(new Rent());
    }


    public void addRent(Property property, User client) {
        ContentValues values = new ContentValues();

        values.put("id_client", client.getId());
        values.put("id_property", property.getId());

        database.insert("Rents", null, values);
    }
}
