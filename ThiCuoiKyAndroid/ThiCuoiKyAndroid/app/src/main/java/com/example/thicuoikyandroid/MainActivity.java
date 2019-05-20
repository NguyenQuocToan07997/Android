package com.example.thicuoikyandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Model.UserModel;
import com.example.Adapter.ContactAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int RC_SIGN_IN;
    GoogleSignInClient mGoogleSignInClient;
    RecyclerView recyclerView;






    static List<UserModel> models;

    static FirebaseDatabase db;
    static String userID;
    static String userEmail;
    static String userName;

    static ContactAdapter adapter;

    EditText edtAddcredits;
    EditText edtAdddescription;
    EditText edtAddsubject_code;
    EditText edtAddsubject_name;
    Button btnAdd;
    static UserModel data = new UserModel();
    String credits = "";
    String description = "";
    String subject_code = "";
    String subject_name = "";
    static DatabaseReference myRef;
    static DatabaseReference post;
    @Override
    protected void onResume() {
        super.onResume();
     //   adapter.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
//
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();
        OnInIt();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        db = FirebaseDatabase.getInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onValidateForm()) {
                    AddProduct();
                    onClearForm();
                }
            }
        });
        myRef = db.getReference("AdvancedAndroidFinalTest");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                models = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    UserModel m = dataSnapshot1.getValue(UserModel.class);
                    models.add(m);
                }

                if (models.isEmpty()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        UserModel value = ds.getValue(UserModel.class);
                        models.add(value);
                    }

                }
 //               adapter.notifyDataSetChanged();
                adapter = new ContactAdapter(models, R.layout.custom_recyclerview, MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void OnInIt() {
        recyclerView = findViewById(R.id.recycleview);
        edtAddcredits = findViewById(R.id.edt_credits);
        edtAdddescription = findViewById(R.id.edt_description);
        edtAddsubject_code = findViewById(R.id.edt_subject_code);
        edtAddsubject_name = findViewById(R.id.edt_subject_name);
        btnAdd = findViewById(R.id.btnAdd);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            userID = account.getId();
            userEmail = account.getEmail();
            userName = account.getDisplayName();
        } catch (ApiException e) {
            finish();
        }
    }
    void AddProduct() {
        myRef = db.getReference("AdvancedAndroidFinalTest");
        data.setEmail(userEmail);
        data.setUsername(userName);
        data.setUserId(userID);

        data.setCredits(Integer.parseInt(edtAddcredits.getText().toString()));
        data.setDescription(edtAdddescription.getText().toString());
        data.setSubject_code(edtAddsubject_code.getText().toString());
        data.setSubject_name(edtAddsubject_name.getText().toString());
        post = myRef.child(data.getSubject_code());
        post.setValue(data);
    }

    private boolean onValidateForm()
    {
        credits = edtAddcredits.getText().toString();
        description = edtAdddescription.getText().toString();
        subject_code = edtAddsubject_code.getText().toString();
        subject_name = edtAddsubject_name.getText().toString();
        if(credits.equals(""))
        {
            edtAddcredits.setError("Can be null");
            return false;
        }
        if(description.equals(""))
        {
            edtAdddescription.setError("Can be null");
            return false;
        }
        if(subject_code.equals(""))
        {
            edtAddsubject_code.setError("Can be null");
            return false;
        }
        if(subject_name.equals(""))
        {
            edtAddsubject_name.setError("Can be null");
            return false;
        }
        return true;
    }

    private void onClearForm()
    {
        edtAddcredits.setText("");
        edtAdddescription.setText("");
        edtAddsubject_code.setText("");
        edtAddsubject_name.setText("");
    }
}
