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
