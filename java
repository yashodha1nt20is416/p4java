ackage com.example.dbhelper1;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
Button register,update,delete,display,login;
EditText uname,upass;
TextView res;
private static final String dbName="studentdb";
private static final String tbName="student";
private static final int dbVersion = 1;
dbhelper mydbhelper;
@SuppressLint("MissingInflatedId")
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
setContentView(R.layout.activity_main);
register=findViewById(R.id.register);
update=findViewById(R.id.update);
delete=findViewById(R.id.delete);
display=findViewById(R.id.display);
login=findViewById(R.id.login);
res=findViewById(R.id.res);
uname=findViewById(R.id.uname);
upass=findViewById(R.id.upass);
register.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
mydbhelper=new dbhelper(MainActivity.this,dbName,null,dbVersion);
long val=mydbhelper.adduser(uname.getText().toString(),upass.getText().toString());
if(val==-1){
Toast.makeText(MainActivity.this,"Registered",Toast.LENGTH_SHORT).show();
}
}
});
update.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
mydbhelper=new dbhelper(MainActivity.this,dbName,null,dbVersion);
mydbhelper.update(uname.getText().toString(),upass.getText().toString());
}
});
delete.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
mydbhelper=new dbhelper(MainActivity.this,dbName,null,dbVersion);
mydbhelper.delete(uname.getText().toString());
}
});
display.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
mydbhelper=new dbhelper(MainActivity.this,dbName,null,dbVersion);
String value=mydbhelper.Display(MainActivity.this);
res.setText(value);
}
});
}
}
...................................................................................................................


package com.example.dbhelper1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class dbhelper extends SQLiteOpenHelper {
private static final String dbName = "studentdb";
private static final String tbName = "student";
private static final int dbVersion = 1;
public dbhelper(@Nullable Context context, @Nullable String name, @Nullable
SQLiteDatabase.CursorFactory factory, int version) {
super(context, name, factory, version);
}
@Override
public void onCreate(SQLiteDatabase db) {
db.execSQL("CREATE TABLE " + tbName+ "(uname VARCHAR(10),passw VARCHAR(10))" +
";");
}
@Override
public void onUpgrade(SQLiteDatabase db, int prev_v, int new_v) {
db.execSQL("DROP TABLE IF EXISTS " + tbName);
onCreate(db);
}
public long adduser(String name, String pass) {
SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
ContentValues cv = new ContentValues();
cv.put("uname", name);
cv.put("passw ", pass);
long result = sqLiteDatabase.insert(tbName, null, cv);
sqLiteDatabase.close();
return result;
}
public void delete(String name){
SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
sqLiteDatabase.execSQL("DELETE FROM " + tbName+ " WHERE uname='" + name + "';");
sqLiteDatabase.close();
}
public void update(String name,String pass){
SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
sqLiteDatabase.execSQL("UPDATE " + tbName+ " SET passw='" + pass + "'" + " WHERE
uname='" + name + "';");
sqLiteDatabase.close();
}
public String Display(Context ctx){
SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM " + tbName, null);
String finalres=" ";
while (cursor.moveToNext()){
finalres+= cursor.getString(0)+":"+ cursor.getString(1);
}
return finalres;
}
}
...................................................................................................................................


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:orientation="vertical"
tools:context=".MainActivity">
<EditText
android:id="@+id/uname"
android:layout_width="match_parent"
android:layout_height="72dp"
android:ems="10"
android:inputType="textPersonName"
android:text="Name" />
<EditText
android:id="@+id/upass"
android:layout_width="match_parent"
android:layout_height="67dp"
android:ems="10"
android:inputType="textPersonName"
android:text="password" />
<Button
android:id="@+id/login"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="login" />
<Button
android:id="@+id/register"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="register" />
<Button
android:id="@+id/delete"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="delete" />
<Button
android:id="@+id/update"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="update" />
<Button
android:id="@+id/display"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="display" />
<TextView
android:id="@+id/res"
android:layout_width="match_parent"
android:layout_height="wrap_content"
android:text="TextView" />
</LinearLayout>
