package com.rishabhjain.jvocsportscorner.Events;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.rishabhjain.jvocsportscorner.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.AdaptersViewHolders.General.Constants.*;

public class AddSubEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {


    CheckBox CB_age_senior, CB_age_junior, CB_gender_male, CB_gender_female;
    Spinner gameSpinner, teamOfSpinner;
    String selectedGame, selectedTeamMembers, selectedAgeGroup, selectedGender;
    RadioButton Radio_openForBoth, Radio_exclusive;
    RadioGroup RG_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        getReferences();
        setUpSpinners();
        setUpRadioListeners();
        setTitle("Add Subevent(s)");
    }

    private void setUpRadioListeners() {
        RG_gender.setOnCheckedChangeListener(this);
    }

    private void setUpSpinners() {
        List<String> game_list = new ArrayList<>();
        List<String> no_of_mems_list = new ArrayList<>();

        String[] games_array = getResources().getStringArray(R.array.games);
        String[] no_of_mems_array = getResources().getStringArray(R.array.no_of_team_members);

        Collections.addAll(no_of_mems_list, no_of_mems_array);
        Collections.addAll(game_list, games_array);

        ArrayAdapter<String> game_spinner_adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, game_list);
        ArrayAdapter<String> no_of_mems_spinner_adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_spinner_item, no_of_mems_list);

        game_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        no_of_mems_spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gameSpinner.setAdapter(game_spinner_adapter);
        teamOfSpinner.setAdapter(no_of_mems_spinner_adapter);

        gameSpinner.setOnItemSelectedListener(this);
        teamOfSpinner.setOnItemSelectedListener(this);
    }

    private void getReferences() {
        CB_gender_male = (CheckBox) findViewById(R.id.CB_gender_male);
        CB_gender_female = (CheckBox) findViewById(R.id.CB_gender_female);
        gameSpinner = (Spinner) findViewById(R.id.gameSpinner);
        teamOfSpinner = (Spinner) findViewById(R.id.teamOfSpinner);
        RG_gender = (RadioGroup) findViewById(R.id.RG_gender);
        Radio_openForBoth = (RadioButton) findViewById(R.id.Radio_openForBoth);
        Radio_exclusive = (RadioButton) findViewById(R.id.Radio_exclusive);
        CB_age_senior = (CheckBox) findViewById(R.id.CB_age_senior);
        CB_age_junior = (CheckBox) findViewById(R.id.CB_age_junior);
    }

    public void subEventDoneClicked(View view) {
        if (oneAgeGroupSelected() && properGenderCBSelected()) {
            Toast.makeText(AddSubEvent.this,
                    selectedGame + " " + selectedTeamMembers + " " +selectedAgeGroup + " " +selectedGender,
                    Toast.LENGTH_SHORT).show();
            setResult(SUB_EVENT_ADDED);
            finish();
        }
    }

    private boolean properGenderCBSelected() {
        if (Radio_exclusive.isChecked()) {
            if (CB_gender_male.isChecked() || CB_gender_female.isChecked()) {
                setSelectedGenders();
                return true;
            }
            Toast.makeText(AddSubEvent.this, "Please select atleast one gender if you select exclusive for option", Toast.LENGTH_SHORT).show();
            return false;
        }
        setSelectedGenders();
        return true;
    }

    private void setSelectedGenders() {
        selectedGender = "";
        if (Radio_exclusive.isChecked()) {
            if (CB_gender_male.isChecked()){
                selectedGender += "Male";
            }
            if(CB_gender_female.isChecked()){
                selectedGender += "Female";
            }
            return;
        }
        selectedGender += "Open";
    }

    private boolean oneAgeGroupSelected() {
        if (CB_age_junior.isChecked() || CB_age_senior.isChecked()) {
            setSelectedAgeGroups();
            return true;
        }
        Toast.makeText(AddSubEvent.this, "Select atleast one age group checkbox", Toast.LENGTH_SHORT).show();
        return false;
    }

    private void setSelectedAgeGroups() {
        selectedAgeGroup = "";
        if(CB_age_junior.isChecked())
            selectedAgeGroup += "Juniors";
        if(CB_age_senior.isChecked())
            selectedAgeGroup += "Seniors";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ){
                setResult(SUB_EVENT_NOT_ADDED);
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        setResult(SUB_EVENT_NOT_ADDED);
        finish();
    }

    private void setupActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int parentId = parent.getId();

        switch (parentId) {
            case R.id.gameSpinner:
                selectedGame = gameSpinner.getItemAtPosition(position).toString();
                break;
            case R.id.teamOfSpinner:
                selectedTeamMembers = teamOfSpinner.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()){
            case R.id.RG_gender:
                if( Radio_exclusive.isChecked() ){
                    CB_gender_male.setEnabled(true);
                    CB_gender_female.setEnabled(true);
                    CB_gender_male.setChecked(true);
                    CB_gender_female.setChecked(true);
                }
                if( Radio_openForBoth.isChecked() ){
                    CB_gender_male.setChecked(false);
                    CB_gender_female.setChecked(false);
                    CB_gender_male.setEnabled(false);
                    CB_gender_female.setEnabled(false);
                }
        }
    }
}
