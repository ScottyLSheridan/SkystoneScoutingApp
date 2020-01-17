package com.masquerade.skystonescoutingapp.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.masquerade.skystonescoutingapp.MainActivity;
import com.masquerade.skystonescoutingapp.MatchModel;
import com.masquerade.skystonescoutingapp.R;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.masquerade.skystonescoutingapp.Fragments.ScoutingActivity.matches;
import static com.masquerade.skystonescoutingapp.MainActivity.fragment;
import static com.masquerade.skystonescoutingapp.MainActivity.matchesDatabase;

/**
 * Created by Keval Kataria on 9/7/2019
 */

public class DataActivity extends Fragment implements View.OnClickListener {
    FileOutputStream outputStream;
    ScrollView scrollView;
    int score;
    String autoPark = "N", autoFoundationMove = "N", endCap = "N", endFoundationMove = "N", endPark = "N";
    int requestValue = 0;
    LinearLayout linearLayout;
    static List<MatchModel> updateMatch = new ArrayList<>();
    InputMethodManager inputMethodManager;
    ViewGroup viewGroup;
    String filename;
    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.data_activity, container, false);

        inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        viewGroup = container;

        initView(view);

        return view;
    }
    @Override
    public void onClick(View view) {}
    public void createExportButton(Button export) {
        export.setText(getText(R.string.export_button));
        export.setTextSize(25);
        export.setWidth(1000);
        export.setHeight(125);
        export.getBackground().setColorFilter(Color.parseColor("#DAA620"),
                PorterDuff.Mode.DARKEN);
        linearLayout.addView(export);
        export.setOnClickListener(v -> createCSV());
    }
    @SuppressLint("SetTextI18n")
    public void createButton(Button button, MatchModel match){
        button.setText ("Match #: " + match.getMatchNum() + ", Team #: " + match.getTeamNum() +
                ", Score: " + score);
        button.setId(match.getMatchNum());
        button.setTextSize(15);
        button.setWidth(1000);
        button.setHeight(100);
        button.getBackground().setColorFilter(Color.parseColor("#DAA520"),
                PorterDuff.Mode.DARKEN);
        linearLayout.addView(button);
        final MatchModel updateMatchEntry = match;
        button.setOnClickListener(v -> {
            updateMatch.add(updateMatchEntry);
            fragment = new EditActivity();
            loadFragment(new EditActivity());
        });
    }
    public void updateScore(MatchModel match) {
        score += match.getAutoStoneDelivery()*2;
        score += match.getAutoSkystoneDelivery()*10;
        score += match.getAutoFoundationStones()*4;
        score += match.getTeleStoneDelivery();
        score += match.getTeleSkyscraperHeight()*2;
        score += match.getEndCappingHeight();
        if (match.isAutoFoundationMove()) score += 10;
        if (match.isAutoPark()) score += 5;
        if (match.isEndCap()) score += 5;
        if (match.isEndFoundationMove()) score += 15;
        if (match.isEndPark()) score += 5;
    }
    public void createCSV() {
        if (matches.size()>0) {
            filename = "Scouting Data " + matches.get(0).getTournament() +
                    matches.get(matches.size() - 1).getMatchNum() + ".csv";

            File directoryDocument = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(directoryDocument, filename);
            if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestValue);
                requestValue += 1;
                try {Thread.sleep(5000);} catch(Exception e) {e.printStackTrace();}
            }
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, requestValue);
                requestValue += 1;
                try {Thread.sleep(5000);} catch(Exception e) {e.printStackTrace();}
            }
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(("Match Number,Team Number,Delivered Skystones, " +
                        "Delivered Stones, Under Skybridge, Moved Foundation, Stacked Stones," +
                        " Stones under Skybridge, Placed Stones, Skyscraper Bonus, Capped, " +
                        "Level Bonus, Moved Foundation, Parked" + System.lineSeparator()).getBytes());
                for (MatchModel match : matches) createRows(match);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity(), "Exported Successfully", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(), "No matches to export", Toast.LENGTH_SHORT).show();
        }
    }
    public void createRows(MatchModel match){
        if (match.isAutoFoundationMove()) autoFoundationMove = "Y";
        if (match.isAutoPark()) autoPark = "Y";
        if (match.isEndCap()) endCap = "Y";
        if (match.isEndFoundationMove()) endFoundationMove = "Y";
        if (match.isEndPark()) endPark = "Y";
        try {
            outputStream.write((match.getMatchNum() + ",").getBytes());
            outputStream.write((match.getTeamNum() + ",").getBytes());
            outputStream.write((match.getAutoSkystoneDelivery() + ",").getBytes());
            outputStream.write((match.getAutoStoneDelivery() + ",").getBytes());
            outputStream.write((autoPark + ",").getBytes());
            outputStream.write((autoFoundationMove + ",").getBytes());
            outputStream.write((match.getAutoFoundationStones() + ",").getBytes());
            outputStream.write((match.getTeleStoneDelivery() + ",").getBytes());
            outputStream.write((match.getTeleFoundationStones() + ",").getBytes());
            outputStream.write((match.getTeleSkyscraperHeight() + ",").getBytes());
            outputStream.write((endCap + ",").getBytes());
            outputStream.write((match.getEndCappingHeight() + ",").getBytes());
            outputStream.write((endFoundationMove + ",").getBytes());
            outputStream.write((endPark + System.lineSeparator()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void initView (View view) {
        view.setBackgroundColor(getResources().getColor(android.R.color.black));
        scrollView = view.findViewById(R.id.scrollView);
        linearLayout = view.findViewById(R.id.linearLayout);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        hideKeyboard(view);

        for (MatchModel match : matches) {
            score = 0;
            Button button = new Button(getContext());
            updateScore(match);
            createButton(button, match);
        }

        Button export = new Button(getContext());
        createExportButton(export);
        TextView textView = new TextView(getContext());
        textView.setHeight(150);
        linearLayout.addView(textView);

    }
    public void loadFragment (Fragment fragment) {
        assert getFragmentManager() != null;
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.app_bar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {

            case R.id.action_delete:
                if (matches.size()>0) {
                    AlertDialog.Builder builder = new AlertDialog
                            .Builder(Objects.requireNonNull(getActivity()));
                    builder.setTitle("Clear all matches").setMessage("Are you sure you want to clear all matches? This action cannot be undone.")
                            .setPositiveButton("Yes", (dialog, which) -> {
                                matches.clear();
                                int matchesCount = matchesDatabase.matchesDao().getAll().size();
                                for (int i = 0; i<matchesCount;i++) {
                                    try {
                                        matchesDatabase.matchesDao().deleteMatch(matchesDatabase.matchesDao().getMatch(i+1).get(0));
                                    } catch (Exception e) {e.printStackTrace();}
                                }
                                assert getFragmentManager()!= null;
                                getFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.fragment_container, new DataActivity())
                                        .commit();
                                Toast.makeText(getActivity(), "Matches Cleared", Toast.LENGTH_SHORT).show();
                            })
                            .setNegativeButton("No", (dialog, which) -> {})
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();}
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void hideKeyboard (View view) {
        InputMethodManager inputManager = (InputMethodManager) view
                .getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder binder = view.getWindowToken();
        inputManager.hideSoftInputFromWindow(binder,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
