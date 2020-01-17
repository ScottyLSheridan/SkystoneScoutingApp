package com.masquerade.skystonescoutingapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.masquerade.skystonescoutingapp.Fragments.DataActivity;
import com.masquerade.skystonescoutingapp.Fragments.ScoutingActivity;
import com.masquerade.skystonescoutingapp.LocalDB.MatchesDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static MatchesDatabase matchesDatabase;
    public static List<Integer> primaryKeys = new ArrayList<>();
    public static Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment = null;

        switch (item.getItemId()) {
            case R.id.scout:
                while (updateMatch.size() != 0) updateMatch.remove(0);
                fragment = new ScoutingActivity();
                break;
            case R.id.data:
                while (updateMatch.size() != 0) updateMatch.remove(0);
                fragment = new DataActivity();
                break;
            case R.id.analyze:
                while (updateMatch.size() != 0) updateMatch.remove(0);
                fragment = new AnalyzeActivity();
                break;
            default:
                break;
        }

        return loadFragment(fragment);
    }
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    public void createDb () {
        primaryKeys.add(0);

        matchesDatabase = Room.databaseBuilder(getApplicationContext(),
                MatchesDatabase.class, "matchesdb").allowMainThreadQueries()
                .build();

        loadMatches();

    }
    public void loadMatches() {
        int matchesCount = matchesDatabase.matchesDao().getAll().size();

        matches.clear();
        for (int i = 0; i < matchesCount; i++) {

            try {
                Matches match = matchesDatabase.matchesDao().getMatch(i + 1).get(0);
                matches.add(
                        new ScoutingModel(
                                match.getTournament(),
                                match.getMatchNumber(),
                                match.getTeamNumber(),
                                match.getDepot(),
                                match.getLander(),
                                match.isAutoDrop(),
                                match.isMarker(),
                                match.isAutoPark(),
                                match.isSample(),
                                match.isDoubleSample(),
                                match.isEndHang(),
                                match.isEndPartial(),
                                match.isFullPark()));
                primaryKeys.add(primaryKeys.size());
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }
}
