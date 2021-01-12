package com.example.soccer4u;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    ArrayList<String> league_names = new ArrayList<String>();

    ListView Leagues_list;
    final int REQUEST_CODE = 1;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference leagueRef= db.collection("leagues");
   // ListenerRegistration leaguesListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        leagueRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot query : queryDocumentSnapshots)
                {
                   league_names.add(query.getString("name"));
                }
                Load_List();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

// Add a new document with a generated ID
       /* db.collection("leagues")
                .add(leagues)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d( TAG,"DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });*/



        super.onCreate(savedInstanceState);
    }

    public void Load_List()
    {
        setContentView(R.layout.activity_main);

        Leagues_list = findViewById(R.id.Leagues_list);
        leagues_list_adapter adapter = new leagues_list_adapter(MainActivity.this,league_names);
        Leagues_list.setAdapter(adapter);

        Leagues_list.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //getting id + adding 1 in it to start id from 1 index
                String person_name = league_names.get(position);   //getting the name of the person whom i want to chat with
                //passing data to another Activity
                Intent intent = new Intent(getApplicationContext(), TabbedActivity.class);
                intent.putExtra("Name", person_name);
                startActivityForResult(intent, REQUEST_CODE);

            }
        }));
    }

    @Override
    protected void onStart() {
        super.onStart();
        leagueRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        //leaguesListener.remove();
    }

}