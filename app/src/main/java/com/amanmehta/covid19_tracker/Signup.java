package com.amanmehta.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity implements View.OnClickListener{

    EditText mail,pass,confirmPass;
    Button signupBtn;
    TextView loginTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        confirmPass = findViewById(R.id.passCnf);
        signupBtn = findViewById(R.id.signupButton);
        loginTv = findViewById(R.id.loginText);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(this);
        loginTv.setOnClickListener(this);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }

    private void registerUser(){

        String email = mail.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String confirmPassword = confirmPass.getText().toString().trim();

        if(email.isEmpty())
        {
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty())
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(!password.equals(confirmPassword)){
            Toast.makeText(this,"Password did not match",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //user successfully registered.
                            finish();
                            Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        } else
                        {
                            Toast.makeText(getApplicationContext(),task.getException().toString(),Toast.LENGTH_LONG).show();
                            progressDialog.cancel();
                        }
                    }
                })
        ;
    }

    @Override
    public void onClick(View view) {
        if(view == signupBtn){
            registerUser();
        }
        if(view == loginTv){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
    }
}
