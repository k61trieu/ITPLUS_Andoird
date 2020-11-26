package com.example.sharedfprefencendemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText  txtvUserName, txtvPassword;
CheckBox chkLuuTT;
Button btnLogin, btnHuy;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor = sharedPreferences.edit();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();

        sharedPreferences = getSharedPreferences("login.xml",MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();

            }

            private void login() {

            }
        });
    }

    private void init() {
        sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                String username =sharedPreferences.getString("username","");
        String password =sharedPreferences.getString("password","");
        boolean checked = sharedPreferences.getBoolean("checked",false);
        txtvUserName.setText(username);
        txtvPassword.setText(password);
        chkLuuTT.setChecked(checked);
    }

    public void login(View view){
        String username =txtvUserName.getText().toString();
        String passwword= txtvPassword.getText().toString();
        if(username!= null  && username.equals("admin")&& passwword!= null && passwword.equals("123456")){
            Toast.makeText(this,"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();
            if(chkLuuTT.isChecked()){


                        editor.putString("username", username);
                        editor.putString("password",passwword);
                        editor.putBoolean("cheked", chkLuuTT.isChecked());
                        editor.commit();
            } else {
                editor.remove("username");
                editor.remove("username");
                editor.remove("cheked");
                editor.commit();
            }


        }
    else {

        }
    }
    private void mapping() {
        txtvUserName =findViewById(R.id.txtvUserName);
        txtvPassword = findViewById(R.id.txtvPassWord);
        chkLuuTT =findViewById(R.id.chkLuuTT);
        btnLogin = findViewById(R.id.btnLogin);
        btnHuy =findViewById(R.id.btnHuy);
    }
}