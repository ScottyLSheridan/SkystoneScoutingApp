package com.masquerade.skystonescoutingapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.masquerade.skystonescoutingapp.LocalDB.Matches;
import com.masquerade.skystonescoutingapp.MainActivity;
import com.masquerade.skystonescoutingapp.MatchModel;
import com.masquerade.skystonescoutingapp.R;

import java.util.Objects;

import static com.masquerade.skystonescoutingapp.Fragments.DataActivity.updateMatch;
import static com.masquerade.skystonescoutingapp.Fragments.ScoutingActivity.matches;
import static com.masquerade.skystonescoutingapp.MainActivity.matchesDatabase;
import static com.masquerade.skystonescoutingapp.MainActivity.primaryKeys;


public class EditActivity extends Fragment implements View.OnClickListener {
    private Button update;
    private int matchesIndex;
    EditText tournamentEdit, matchNumEdit, teamNumEdit, autoSkystoneDeliveryEdit,
            autoStoneDeliveryEdit, autoFoundationStonesEdit, teleStoneDeliveryEdit, teleFoundationStonesEdit,
            teleSkyscraperHeightEdit, endCappingHeightEdit;
    CheckBox autoFoundationMoveEdit, autoParkEdit, endCapEdit, endFoundationMoveEdit, endParkEdit;
    InputMethodManager inputMethodManager;
    ViewGroup viewGroup;
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_activity, container, false);
        inputMethodManager = (InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);
        viewGroup = container;
        initView(view);
        matchesIndex = updateMatch.get(0).getMatchNum() - 1;
        update.setOnClickListener(v -> updateMatch());
        return view;
    }
    @Override
    public void onClick(View v) {

    }
    public boolean isClear() {
        return (tournamentEdit.getText().toString().equals("")  ||
                matchNumEdit.getText().toString().equals("") ||
                teamNumEdit.getText().toString().equals("") ||
                tournamentEdit.getText().toString().equals("") ||
                autoSkystoneDeliveryEdit.getText().toString().equals("") ||
                autoStoneDeliveryEdit.getText().toString().equals("") ||
                autoFoundationStonesEdit.getText().toString().equals("") ||
                teleStoneDeliveryEdit.getText().toString().equals("") ||
                teleFoundationStonesEdit.getText().toString().equals("") ||
                teleSkyscraperHeightEdit.getText().toString().equals("") ||
                endCappingHeightEdit.getText().toString().equals(""));
    }
    public  void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        MainActivity mainActivity = (MainActivity) getActivity();
        assert mainActivity != null;
        mainActivity.setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        hideKeyboard(view);

        update = view.findViewById((R.id.update));
        tournamentEdit = view.findViewById(R.id.tournament);
        matchNumEdit = view.findViewById(R.id.match_number);
        teamNumEdit = view.findViewById(R.id.team_number);
        autoSkystoneDeliveryEdit = view.findViewById(R.id.auto_sky_stones_moved);
        autoStoneDeliveryEdit = view.findViewById(R.id.auto_stones_moved);
        autoParkEdit = view.findViewById(R.id.auto_park);
        autoFoundationMoveEdit = view.findViewById(R.id.auto_move_foundation);
        autoFoundationStonesEdit = view.findViewById(R.id.auto_foundation_stones);
        teleStoneDeliveryEdit = view.findViewById(R.id.tele_stones_moved);
        teleFoundationStonesEdit = view.findViewById(R.id.tele_foundation_stones);
        teleSkyscraperHeightEdit = view.findViewById(R.id.tele_skyscraper_height);
        endCapEdit = view.findViewById(R.id.capped);
        endCappingHeightEdit  = view.findViewById(R.id.end_capping_height);
        endFoundationMoveEdit = view.findViewById(R.id.end_move_foundation);
        endParkEdit = view.findViewById(R.id.end_park);
        clear();
        tournamentEdit.setText(String.valueOf(updateMatch.get(0).getTournament()));
        matchNumEdit.setText(String.valueOf(updateMatch.get(0).getMatchNum()));
        teamNumEdit.setText(String.valueOf(updateMatch.get(0).getTeamNum()));
        autoSkystoneDeliveryEdit.setText(String.valueOf(updateMatch.get(0).getAutoSkystoneDelivery()));
        autoStoneDeliveryEdit.setText(String.valueOf(updateMatch.get(0).getAutoStoneDelivery()));
        autoFoundationStonesEdit.setText(String.valueOf(updateMatch.get(0).getAutoFoundationStones()));
        teleStoneDeliveryEdit.setText(String.valueOf(updateMatch.get(0).getTeleStoneDelivery()));
        teleFoundationStonesEdit.setText(String.valueOf(updateMatch.get(0).getTeleFoundationStones()));
        teleSkyscraperHeightEdit.setText(String.valueOf(updateMatch.get(0).getTeleSkyscraperHeight()));
        endCappingHeightEdit.setText(String.valueOf(updateMatch.get(0).getEndCappingHeight()));
        autoParkEdit.setChecked(updateMatch.get(0).isAutoPark());
        autoFoundationMoveEdit.setChecked(updateMatch.get(0).isAutoFoundationMove());
        endCapEdit.setChecked(updateMatch.get(0).isEndCap());
        endFoundationMoveEdit.setChecked(updateMatch.get(0).isEndFoundationMove());
        endParkEdit.setChecked(updateMatch.get(0).isEndPark());
    }
    public void clear() {
        matchNumEdit.setText("");
        teamNumEdit.setText("");
        autoSkystoneDeliveryEdit.setText("");
        autoStoneDeliveryEdit.setText("");
        autoFoundationStonesEdit.setText("");
        teleStoneDeliveryEdit.setText("");
        teleFoundationStonesEdit.setText("");
        teleSkyscraperHeightEdit.setText("");
        endCappingHeightEdit.setText("");
        autoParkEdit.setChecked(false);
        autoFoundationMoveEdit.setChecked(false);
        endCapEdit.setChecked(false);
        endFoundationMoveEdit.setChecked(false);
        endParkEdit.setChecked(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.app_bar_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            builder.setTitle("Delete Match").setMessage("Are you sure you want to delete this match? This action cannot be undone.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        deleteMatch();
                        Toast.makeText(getActivity(), "Deleted Successfully", Toast.LENGTH_SHORT).show();

                        matches.remove(updateMatch.get(0));
                        updateMatch.remove(0);
                        clear();
                        assert getFragmentManager() != null;
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, new DataActivity())
                                .commit();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void updateMatch() {
        if (!isClear()) {
            matches.set(matches.indexOf(updateMatch.get(0)),
                    new MatchModel(
                            tournamentEdit.getText().toString(),
                            Integer.parseInt(matchNumEdit.getText().toString()),
                            Integer.parseInt(teamNumEdit.getText().toString()),
                            Integer.parseInt(autoSkystoneDeliveryEdit.getText().toString()),
                            Integer.parseInt(autoStoneDeliveryEdit.getText().toString()),
                            Integer.parseInt(autoFoundationStonesEdit.getText().toString()),
                            Integer.parseInt(teleStoneDeliveryEdit.getText().toString()),
                            Integer.parseInt(teleFoundationStonesEdit.getText().toString()),
                            Integer.parseInt(teleSkyscraperHeightEdit.getText().toString()),
                            Integer.parseInt(endCappingHeightEdit.getText().toString()),
                            autoFoundationMoveEdit.isChecked(),
                            autoParkEdit.isChecked(),
                            endCapEdit.isChecked(),
                            endFoundationMoveEdit.isChecked(),
                            endParkEdit.isChecked())
            );

            Matches match = new Matches();
            match.setId(matchesDatabase.matchesDao().getMatch(updateMatch.get(0).getMatchNum()).get(0).getId());
            match.setTournament(tournamentEdit.getText().toString());
            match.setMatchNum(Integer.parseInt(matchNumEdit.getText().toString()));
            match.setTeamNum(Integer.parseInt(teamNumEdit.getText().toString()));
            match.setAutoSkystoneDelivery(Integer.parseInt(autoSkystoneDeliveryEdit.getText().toString()));
            match.setAutoStoneDelivery(Integer.parseInt(autoStoneDeliveryEdit.getText().toString()));
            match.setAutoFoundationStones(Integer.parseInt(autoFoundationStonesEdit.getText().toString()));
            match.setAutoFoundationMove(autoFoundationMoveEdit.isChecked());
            match.setAutoPark(autoParkEdit.isChecked());
            match.setTeleStoneDelivery(Integer.parseInt(teleStoneDeliveryEdit.getText().toString()));
            match.setTeleFoundationStones(Integer.parseInt(teleFoundationStonesEdit.getText().toString()));
            match.setTeleSkyscraperHeight(Integer.parseInt(teleSkyscraperHeightEdit.getText().toString()));
            match.setEndCap(endCapEdit.isChecked());
            match.setEndCappingHeight(Integer.parseInt(endCappingHeightEdit.getText().toString()));
            match.setEndFoundationMove(endFoundationMoveEdit.isChecked());
            match.setEndPark(endParkEdit.isChecked());

            matchesDatabase.matchesDao().updateMatch(match);

            Toast.makeText(getActivity(), "Updated successfully", Toast.LENGTH_SHORT).show();

            updateMatch.remove(0);
            clear();
            assert getFragmentManager() != null;
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new DataActivity())
                    .commit();
        }
    }
    public void deleteMatch() {
        Matches match = new Matches();
        try {match.setId(matchesDatabase.matchesDao().getMatch(updateMatch.get(0).getMatchNum()).get(0).getId());}
        catch (Exception e) {e.printStackTrace();}
        match.setTournament(updateMatch.get(0).getTournament());
        match.setMatchNumber(updateMatch.get(0).getMatchNum());
        match.setTeamNumber(updateMatch.get(0).getTeamNumber());
        match.setDepot(updateMatch.get(0).getDepot());
        match.setLander(updateMatch.get(0).getLander());
        match.setAutoDrop(updateMatch.get(0).isAutoDrop());
        match.setMarker(updateMatch.get(0).isMarker());
        match.setSample(updateMatch.get(0).isSample());
        match.setDoubleSample(updateMatch.get(0).isDoubleSample());
        match.setAutoPark(updateMatch.get(0).isAutoPark());
        match.setEndHang(updateMatch.get(0).isEndHang());
        match.setEndPartial(updateMatch.get(0).isEndPartial());
        match.setFullPark(updateMatch.get(0).isFullPark());

        matchesDatabase.matchesDao().deleteMatch(match);
        primaryKeys.add(match.getId());
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