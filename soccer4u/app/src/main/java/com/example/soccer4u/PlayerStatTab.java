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

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;

public class PlayerStatTab extends Fragment {

    public GridView gridGoals;
    public ArrayList<Player> players=new ArrayList<>();
    public Player_Stat_Adapter myAdapter;
    Context context;
    final public String name;

    private FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private CollectionReference playerList;

    public PlayerStatTab(Context context,String name) {
        this.context = context;
        this.name = name;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.playerList = firestore.collection("leagues/" + name + "/players");

        View root = inflater.inflate(R.layout.tab_stats, container, false);
        gridGoals = root.findViewById(R.id.topGoalsList);

        playerList.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (!value.isEmpty()) {
                    players = new ArrayList<Player>();
                    for (QueryDocumentSnapshot document : value) {

                        String name = document.getString("name");
                        int goals = document.getLong("goals").intValue();
                        int assists = document.getLong("assists").intValue();
                        Player p1 = new Player(name,"CDM",goals,assists);
                        players.add(p1);
                    }
                    players.sort(new Comparator<Player>() {
                        @Override
                        public int compare(Player o1, Player o2) {
                            return o2.goals - o1.goals;
                        }
                    });
                    myAdapter = new Player_Stat_Adapter(context,players);
                    gridGoals.setAdapter(myAdapter);
                }
            }
        });

        return root;
    }
}
