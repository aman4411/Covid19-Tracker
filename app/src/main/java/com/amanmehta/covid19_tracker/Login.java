package com.amanmehta.covid19_tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    EditText mail,pass;
    Button loginBtn;
    TextView signupTv;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        loginBtn = findViewById(R.id.loginButton);
        signupTv = findViewById(R.id.signupText);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(this);
        signupTv.setOnClickListener(this);

        if(mAuth.getCurrentUser()!=null)
        {
            finish();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }

    }

    private void userLogin()
    {
        String email = mail.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if(email.isEmpty())
        {
            mail.setError("Email is required...");
            mail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mail.setError("Please enter a valid email.");
            mail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            pass.setError("Password is required...");
            pass.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            pass.setError("Minimum length of password should be 6.");
            pass.requestFocus();
            return;
        }

        progressDialog.setMessage("Logging In...");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {   finish();
                    Toast.makeText(getApplicationContext(),"Login Success",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }else
                {
                    progressDialog.cancel();
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view == loginBtn){
            userLogin();
        }
        if(view == signupTv){
            startActivity(new Intent(getApplicationContext(),Signup.class));
        }
    }
}
