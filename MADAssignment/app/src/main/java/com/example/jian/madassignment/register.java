package com.example.jian.madassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText etId, etPassword, etConfPass, etName, etIc, etEmail;
    Button register, cancel;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        etId = (EditText)findViewById(R.id.id);
        etPassword = (EditText)findViewById(R.id.password);
        etConfPass = (EditText)findViewById(R.id.confPassword);
        etName = (EditText)findViewById(R.id.name);
        etIc = (EditText)findViewById(R.id.ic);
        etEmail = (EditText)findViewById(R.id.email);
        register = (Button)findViewById(R.id.register);
        cancel = (Button)findViewById(R.id.cancel);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etId.getText().toString();
                String password = etPassword.getText().toString();
                String confPass = etConfPass.getText().toString();
                String name = etName.getText().toString();
                String ic = etIc.getText().toString();
                String email = etEmail.getText().toString();
                if(id.equals("")||password.equals("")||confPass.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();

                }else{
                    if(password.equals(confPass)){
                        Boolean checkId = db.checkId(id);
                        if(checkId == true){
                            Boolean insert = db.insert(id, password, name, ic, email);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"ID already exists.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Confirm password is not match with password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
