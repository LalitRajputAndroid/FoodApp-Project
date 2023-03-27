package com.example.foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class login_Fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    public login_Fragment() {

    }

    public static login_Fragment newInstance(String param1, String param2) {
        login_Fragment fragment = new login_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextInputEditText email, pass;
    Button loginbtn;
    TextView forgetpassword;

    ProgressDialog dialog;

    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_, container, false);

        loginbtn = view.findViewById(R.id.loginbtn_id);
        email = view.findViewById(R.id.login_email_id);
        pass = view.findViewById(R.id.login_password_id);
        forgetpassword = view.findViewById(R.id.forgotPass_id);

        dialog = new ProgressDialog(this.getActivity());
        dialog.setTitle("Login");
        dialog.setCanceledOnTouchOutside(false);

        auth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                macthinfo();
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setTitle("Sending Mail");
                dialog.show();
                String Email = email.getText().toString();
                auth.sendPasswordResetEmail(Email).addOnSuccessListener(getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Email sent", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(getActivity(), "Feild"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        return view;
    }

    private void macthinfo() {

        String Email = email.getText().toString();
        String Pass = pass.getText().toString();

        if (Email.isEmpty() || Pass.isEmpty()) {
            email.setError("Enter a Email");
            pass.setError("Enter a Password");
        } else {
            dialog.show();
            auth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog.dismiss();
                    if (task.isSuccessful()) {

                        Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity().getApplication(), Home_Activity3.class));
                        email.setText("");
                        pass.setText("");
                        getActivity().finish();
                    }
                    else {
                        Toast.makeText(getActivity(), "Worng Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Log.d("Error", e.getMessage());
                    Toast.makeText(getActivity(), "Log in Faild", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}