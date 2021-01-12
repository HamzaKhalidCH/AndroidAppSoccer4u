package com.example.soccer4u;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MatchStatsTab extends Fragment{

    private Context context;
    private String leagueName;
    private String matchId;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private DocumentReference documentRef;

    public MatchStatsTab(Context context,String leagueName,String matchId) {
        this.context = context;
        this.leagueName = leagueName;
        this.matchId = matchId;
    }

    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        documentRef = firestore.collection("leagues/"+this.leagueName+"/matches").document(this.matchId);

        final View root = inflater.inflate(R.layout.tab_match_stat, container, false);
        context = this.getContext();

        final TextView team1Goals = root.findViewById(R.id.team1Goals);
        final TextView team2Goals = root.findViewById(R.id.team2Goals);
        final TextView team1Poss = root.findViewById(R.id.team1Poss);
        final TextView team2Poss = root.findViewById(R.id.team2Poss);
        final TextView team1Passing = root.findViewById(R.id.team1Passing);
        final TextView team2Passing = root.findViewById(R.id.team2Passing);
        final TextView team1Passes = root.findViewById(R.id.team1Passes);
        final TextView team2Passes = root.findViewById(R.id.team2Passes);
        final TextView venue = root.findViewById(R.id.venue);


        documentRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot document, @Nullable FirebaseFirestoreException error) {
               if(document.exists())
               {
                   Match m1 = new Match(document.getId(),document.getString("team1Name"),document.getString("team2Name"));

                   m1.team1Score = document.getLong("team1Score").intValue();
                   m1.team2Score =  document.getLong("team2Score").intValue();
                   m1.team1Passes =  document.getLong("team1Passes").intValue();
                   m1.team2Passes =  document.getLong("team2Passes").intValue();
                   m1.team1Passing = document.getLong("team1Passing").intValue();
                   m1.team2Passing = document.getLong("team2Passing").intValue();
                   m1.team1Poss = document.getString("team1Poss");
                   m1.team2Poss = document.getString("team2Poss");
                   m1.venue = document.getString("venue");

                   Timestamp time = document.getTimestamp("Time");
                   m1.timeStamp = time;

                   String timeComplete = time.toDate().toString();
                   m1.matchDate = timeComplete.substring(0, 11);
                   m1.matchTime = timeComplete.substring(11, 19);

                   team1Goals.setText(String.valueOf(m1.team1Score));
                   team2Goals.setText(String.valueOf(m1.team2Score));
                   team1Poss.setText(m1.team1Poss);
                   team2Poss.setText(m1.team2Poss);
                   team1Passing.setText(String.valueOf(m1.team1Passing));
                   team2Passing.setText(String.valueOf(m1.team2Passing));
                   team1Passes.setText(String.valueOf(m1.team1Passes));
                   team2Passes.setText(String.valueOf(m1.team2Passes));
                   venue.setText(m1.venue);

               }
            }
        });

        return root;
    }

}
