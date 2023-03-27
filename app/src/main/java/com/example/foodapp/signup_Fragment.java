package com.example.foodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodapp.Adapters.All_Modals.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class signup_Fragment extends Fragment {


    public signup_Fragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    TextInputEditText email, password, c_password;
    Button signup;
    String EV = "[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+";
    ProgressDialog dialog;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_, container, false);

        email = view.findViewById(R.id.signup_email_id);
        password = view.findViewById(R.id.signup_password_id);
        c_password = view.findViewById(R.id.signup_confirmpassword_id);
        signup = view.findViewById(R.id.signupbtn_id);

        dialog = new ProgressDialog(this.getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Creating Account");
        dialog.setMessage("We`re Creating Your Account");

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        signup.setOnClickListener(v -> {

            String Email = Objects.requireNonNull(email.getText()).toString();
            String Pass = Objects.requireNonNull(password.getText()).toString();
            String C_pass = Objects.requireNonNull(c_password.getText()).toString();

            if (Email.isEmpty() || Pass.isEmpty() || C_pass.isEmpty()) {
                email.setError("Fill the fild");
                password.setError("Fill the fild");
                c_password.setError("Fill the fild");
//            Toast.makeText(getActivity(), "Fill the fild", Toast.LENGTH_SHORT).show();
            } else
            {
               try {
                   checkemail();
               }
               catch (Exception e)
               {
                   Toast.makeText(getActivity(), "Error:"+e, Toast.LENGTH_SHORT).show();
               }

            }
        });

        return view;
    }

    private void checkinfo() {

        String Email = Objects.requireNonNull(email.getText()).toString();
        String Pass = Objects.requireNonNull(password.getText()).toString();
        String C_pass = Objects.requireNonNull(c_password.getText()).toString();

        if (Email.isEmpty() || Pass.isEmpty() || C_pass.isEmpty()) {
            email.setError("Fill the fild");
            password.setError("Fill the fild");
            c_password.setError("Fill the fild");
//            Toast.makeText(getActivity(), "Fill the fild", Toast.LENGTH_SHORT).show();
        } else if (!Email.matches(EV)) {
            email.setError("Invalid Email");
//            Toast.makeText(getActivity(), "Invalid Email", Toast.LENGTH_SHORT).show();
        } else if (!Pass.equals(C_pass)) {
            c_password.setError("Password not match");
//            Toast.makeText(getActivity(), "Password not match", Toast.LENGTH_SHORT).show();
        } else if (Pass.length() <= 8) {
            password.setError("Password must be 8 character");
//            Toast.makeText(getActivity(), "Password must be 8 character", Toast.LENGTH_SHORT).show();
        } else {
            dialog.show();
            auth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    dialog.dismiss();
                    if (task.isSuccessful()){
                        Toast.makeText(getActivity(), "Succesfully Registration", Toast.LENGTH_SHORT).show();
                        String uid = task.getResult().getUser().getUid().toString();
                        Users users = new Users(Email,Pass,uid);
                        reference.child("Users").child(uid).setValue(users);
                        email.setText("");
                        password.setText("");
                        c_password.setText("");

//                        startActivity(new Intent(getActivity().getApplication(),login_Fragment.class))
                    }
                }
            }).addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void checkemail() {
        String e = email.getText().toString();
        auth.fetchSignInMethodsForEmail(e).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                try {
                    boolean check = task.getResult().getSignInMethods().isEmpty();
                    if (check) {
                        checkinfo();
                    } else {
                        dialog.dismiss();
                        email.setError("Email already exist");
                        Toast.makeText(getActivity(), "Email already exist", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Registration Faild", Toast.LENGTH_SHORT).show();
                    Log.d("Error",e.getMessage());
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getActivity(), e+"Sign up Faild", Toast.LENGTH_SHORT).show();
            }
        });
    }

}