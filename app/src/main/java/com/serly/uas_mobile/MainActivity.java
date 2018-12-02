package com.serly.uas_mobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CheckBox chkRemember;
    private Button btnLogin;
    private Button btnRegistrasi;
    private EditText txtName;
    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.checkSavedCredentials();

        this.initComponents();
    }

    private void initComponents(){
        this.chkRemember = this.findViewById(R.id.chk_remember);
        this.btnLogin = this.findViewById(R.id.btn_login);
//        this.btnRegistrasi = this.findViewById(R.id.btn_registrasi);
        this.txtName = this.findViewById(R.id.name);
        this.txtEmail = this.findViewById(R.id.email);
        this.txtPassword = this.findViewById(R.id.password);
    }

    public void button_onClick(View view){
        this.login();
    }

    private void login(){
        String username = this.txtName.getText().toString();
        String email = this.txtEmail.getText().toString();
        String password = this.txtPassword.getText().toString();

        boolean loginCorrect = this.checkCredentials(username,email,password);

        if(loginCorrect){
            boolean remember = this.chkRemember.isChecked();
            if(remember){
                this.saveCredentials();
            }
            this.openHome(username);
        }
        else{
            Toast.makeText(this.getApplicationContext(),"Invalid username , Email atau password!",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCredentials(String username, String email, String password){
        if(username.equals("rinduaji") && email.equals("rinduaji@gmail.com") && password.equals("rinduaji"))
            return true;
        else
            return false;
    }

    private void checkSavedCredentials(){
        SharedPreferences handler = this.getSharedPreferences("simpanPrefLogin", Context.MODE_PRIVATE);

        String username = handler.getString("username", "");
        String email = handler.getString("email","");
        String password = handler.getString("password","");

        boolean loginCorrect = this.checkCredentials(username,email,password);

        if(loginCorrect)
            this.openHome(username);
    }

    private void saveCredentials(){
        SharedPreferences handler = this.getSharedPreferences("simpanPrefLogin",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username",this.txtName.getText().toString());
        editor.putString("email",this.txtEmail.getText().toString());
        editor.putString("password",this.txtPassword.getText().toString());

        editor.apply();
    }

    private void openHome(String username){
        Intent i = new Intent(this.getApplicationContext(),MainDashboard.class);
        i.putExtra("username",username);
        this.startActivity(i);
    }
}
