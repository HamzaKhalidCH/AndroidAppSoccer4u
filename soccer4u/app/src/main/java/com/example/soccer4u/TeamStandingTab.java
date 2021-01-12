package com.example.soccer4u;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;

public class TeamStandingTab extends Fragment {

    public GridView grid;
    public ArrayList<Team> teams=new ArrayList<>();
    public Team_Standings_Adapter myAdapter;
    private Context context;
    final private String leagueName;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference teamList;


    public TeamStandingTab(Context context,String name) {
        this.context = context;
        this.leagueName = name;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        this.teamList = firestore.collection("leagues/"+this.leagueName+"/Teams");


        View root = inflater.inflate(R.layout.tab_standings, container, false);
        grid = root.findViewById(R.id.standingsList);

        this.teamList.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                teams = new ArrayList<Team>();
                if(!value.isEmpty())
                {
                    for (QueryDocumentSnapshot document : value) {

                        Team t1 = new Team(document.getString("name"));
                        t1.win = document.getLong("wins").intValue();
                        t1.lost = document.getLong("losses").intValue();
                        t1.tied = document.getLong("ties").intValue();
                        t1.played = document.getLong("played").intValue();
                        t1.gd = document.getLong("gd").intValue();
                        t1.points = document.getLong("points").intValue();

                        teams.add(t1);
                    }

                    teams.sort(new Comparator<Team>() {
                        @Override
                        public int compare(Team o1, Team o2) {
                            return o2.points - o1.points;
                        }
                    });

                    myAdapter = new Team_Standings_Adapter(getActivity(),teams);
                    grid.setAdapter(myAdapter);
                }
            }
        });


      /*  pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
