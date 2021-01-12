package com.example.soccer4u;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;

public class LiveMatchesTab extends Fragment {

    public GridView grid;
    ArrayList<Match> matches=new ArrayList<>();
    Live_Matches_Adapter myAdapter;
    Context context;
    final private String name;
    private boolean updated = false;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();

    private CollectionReference matchList;

    public LiveMatchesTab(Context context, String name) {
        this.context = context;
        this.name = name;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        this.matchList = firestore.collection("leagues/" + name + "/matches");

        View root = inflater.inflate(R.layout.tab_livematches, container, false);
        grid = root.findViewById(R.id.matchesList);

        matchList.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!value.isEmpty()) {
                    matches = new ArrayList<Match>();
                    updated = true;
                    for (QueryDocumentSnapshot document : value) {

                        if(document.getLong("status").intValue()==1)
                        {
                            String team1Name = document.getString("team1Name");
                            String team2Name = document.getString("team2Name");

                            String id = document.getId();

                            Match m1 = new Match(id, team1Name, team2Name);

                            m1.team1Score = document.getLong("team1Score").intValue();
                            m1.team2Score = document.getLong("team2Score").intValue();
                            m1.team1Passes = document.getLong("team1Passes").intValue();
                            m1.team2Passes = document.getLong("team2Passes").intValue();
                            m1.team1Passing = document.getLong("team1Passing").intValue();
                            m1.team2Passing = document.getLong("team2Passing").intValue();
                            m1.team1Poss = document.getString("team1Poss");
                            m1.team2Poss = document.getString("team2Poss");
                            m1.venue = document.getString("venue");
                            m1.matchStatus = document.getLong("status").intValue();

                            Timestamp time = document.getTimestamp("Time");
                            m1.timeStamp = time;

                            String timeComplete = time.toDate().toString();
                            m1.matchDate = timeComplete.substring(0, 11);
                            m1.matchTime = timeComplete.substring(11, 19);

                            matches.add(m1);
                        }
                    }

                    matches.sort(new Comparator<Match>() {
                        @Override
                        public int compare(Match o1, Match o2) {
                            return o1.timeStamp.compareTo(o2.timeStamp);
                        }
                    });

                    myAdapter = new Live_Matches_Adapter(getActivity(),matches);
                    grid.setAdapter(myAdapter);
                }
            }
        });

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Match match = matches.get(position);

                Intent intent = new Intent(getActivity(),MatchTabbedActivity.class);
                intent.putExtra("leagueName",name);
                intent.putExtra("id",match.Id);
                intent.putExtra("team1Name",match.team1Name);
                intent.putExtra("team2Name",match.team2Name);
                intent.putExtra("team1Score",match.team1Score);
                intent.putExtra("team2Score",match.team2Score);

                startActivity(intent);
            }
        });
        grid.setAdapter(myAdapter);
        return root;
    }
}
