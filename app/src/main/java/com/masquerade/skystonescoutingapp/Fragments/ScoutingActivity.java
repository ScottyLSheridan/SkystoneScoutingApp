package com.masquerade.skystonescoutingapp.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.masquerade.skystonescoutingapp.MainActivity;
import com.masquerade.skystonescoutingapp.MatchModel;
import com.masquerade.skystonescoutingapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Keval Kataria on 9/7/2019
 */
public class ScoutingActivity extends Fragment  implements View.OnClickListener{

    private EditText tournament, matchNum, teamNum, autoSkystoneDelivery,
            autoStoneDelivery, autoFoundationStones, teleStoneDelivery, teleFoundationStones,
            teleSkyscraperHeight, endCappingHeight;
    private CheckBox autoFoundationMove, autoPark, endCap, endFoundationMove, endPark;
    static List<MatchModel> matches = new ArrayList<>();
    private static List<EditText> fields = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scouting_activity,container);
        init(view);
        return view;
    }
    private void init(View view) {
        setToolBar(view);

        hideKeybaord(view);

        //getting values from user input
        tournament = view.findViewById(R.id.tournament);
        matchNum = view.findViewById(R.id.match_number);
        teamNum = view.findViewById(R.id.team_number);
        autoSkystoneDelivery = view.findViewById(R.id.auto_sky_stones_moved);
        autoStoneDelivery = view.findViewById(R.id.auto_stones_moved);
        autoFoundationStones = view.findViewById(R.id.auto_foundation_stones);
        teleStoneDelivery = view.findViewById(R.id.tele_stones_moved);
        teleFoundationStones = view.findViewById(R.id.tele_foundation_stones);
        teleSkyscraperHeight = view.findViewById(R.id.tele_skyscraper_height);
        endCappingHeight = view.findViewById(R.id.end_capping_height);
        autoFoundationMove = view.findViewById(R.id.auto_move_foundation);
        autoPark = view.findViewById(R.id.auto_park);
        endCap = view.findViewById(R.id.capped);
        endFoundationMove = view.findViewById(R.id.end_move_foundation);
        endPark = view.findViewById(R.id.end_park);
        Button stash = view.findViewById(R.id.stash);

        //Adding fields to List
        fields.add(autoSkystoneDelivery);
        fields.add(autoStoneDelivery);
        fields.add(autoFoundationStones);
        fields.add(teleStoneDelivery);
        fields.add(teleFoundationStones);
        fields.add(teleSkyscraperHeight);
        fields.add(endCappingHeight);

        //Stashing values
        stash.setOnClickListener(v -> {

            //Checking if any fields are blank and creating popup if true
            if (autoSkystoneDelivery.getText().toString().equals("") |
                autoStoneDelivery.getText().toString().equals("") |
                autoFoundationStones.getText().toString().equals("") |
                teleStoneDelivery.getText().toString().equals("") |
                teleFoundationStones.getText().toString().equals("") |
                teleSkyscraperHeight.getText().toString().equals("") |
                endCappingHeight.getText().toString().equals("")) createDialog(
            );

            else if (Integer.valueOf(autoSkystoneDelivery.getText().toString()) > 2) Toast.makeText(getActivity(), "Bruh, " +
                    "2 Skystones max", Toast.LENGTH_SHORT).show();

            else if (Integer.valueOf(autoSkystoneDelivery.getText().toString()) + Integer.valueOf(autoStoneDelivery.getText().toString()) > 4)
                Toast.makeText(getActivity(), "Bruh, 4 regular stones max", Toast.LENGTH_SHORT).show();

            else if (!endCap.isChecked() && !(endCappingHeight.getText().toString().equals("") || Integer.valueOf(endCappingHeight.getText().toString()) == 0))
                Toast.makeText(getActivity(), "Bruh, They didn't cap", Toast.LENGTH_SHORT).show();

            else if (isNotClear()) {
                    createMatch();
                    clear();
            }
            else if (teamNum.getText().toString().equals("")){
                Toast.makeText(getActivity(), "Don't forget to fill in the Team Number", Toast.LENGTH_SHORT).show();
            }
            else if (tournament.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Don't forget to fill in the Tournament Name", Toast.LENGTH_SHORT).show();
            }
            else if (matchNum.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Don't forget to fill in the Match Number", Toast.LENGTH_SHORT).show();
            }
        });

        if (matches.size()>0) {
            matchNum.setText(String.valueOf(matches.get(matches.size() - 1).getMatchNum() + 1));
            tournament.setText(matches.get(matches.size()-1).getTournament());
        }

        else {
            matchNum.setText(String.valueOf(1));
        }


    }
    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Empty Field").setMessage("Some fields are blank. Was this intentional?")
                .setPositiveButton("Yes", (dialog, which) -> {

                    for (EditText field : fields) {
                        if (field.getText().toString().equals("")) field.setText(0);
                    }
                    if (isNotClear()) {
                        createMatch();
                        clear();
                    }
                })
                .setNegativeButton("No", (dialog, which) -> Toast.makeText(getActivity(), "Bruh moment", Toast.LENGTH_SHORT).show())
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    private void setToolBar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setSupportActionBar(toolbar);
    }
    private void clear() {
        matchNum.setText(String.valueOf(Integer.parseInt(matchNum.getText().toString()) + 1));
        teamNum.setText("");
        autoSkystoneDelivery.setText("");
        autoStoneDelivery.setText("");
        autoFoundationStones.setText("");
        teleStoneDelivery.setText("");
        teleFoundationStones.setText("");
        teleSkyscraperHeight.setText("");
        endCappingHeight.setText("");
        autoFoundationMove.setChecked(false);
        autoPark.setChecked(false);
        endCap.setChecked(false);
        endFoundationMove.setChecked(false);
        endPark.setChecked(false);
    }
    private boolean isNotClear() {
        return (
                !tournament.getText().toString().equals("") &&
                !matchNum.getText().toString().equals("") &&
                !teamNum.getText().toString().equals("")
        );
    }
    private void createMatch() {
            MatchModel matchModel = new MatchModel(
                    tournament.getText().toString(),
                    Integer.parseInt(matchNum.getText().toString()),
                    Integer.parseInt(teamNum.getText().toString()),
                    Integer.parseInt(autoSkystoneDelivery.getText().toString()),
                    Integer.parseInt(autoStoneDelivery.getText().toString()),
                    Integer.parseInt(autoFoundationStones.getText().toString()),
                    Integer.parseInt(teleStoneDelivery.getText().toString()),
                    Integer.parseInt(teleFoundationStones.getText().toString()),
                    Integer.parseInt(teleSkyscraperHeight.getText().toString()),
                    Integer.parseInt(endCappingHeight.getText().toString()),
                    autoFoundationMove.isChecked(),
                    autoPark.isChecked(),
                    endCap.isChecked(),
                    endFoundationMove.isChecked(),
                    endPark.isChecked()
                    );
            matches.add(matchModel);

           /* Matches matches = new Matches();
            matches.setId(primaryKeys.get(primaryKeys.size() - 1));
            matches.setTournament(tournament.getText().toString());
            matches.setMatchNumber(Integer.parseInt(matchNum.getText().toString()));
            matches.setTeamNumber(Integer.parseInt(teamNum.getText().toString()));

            MainActivity.matchesDatabase.matchesDao().addMatch(matches);
            primaryKeys.add(primaryKeys.size()); */

            Toast.makeText(getActivity(), "Stashed successfully", Toast.LENGTH_SHORT).show();
    }
    private void hideKeybaord(View view) {
        InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getContext()).getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //We don't use this because we have more than one button so we create separate Listeners for when a button is clicker
    @Override
    public void onClick(View v) {

    }
}
