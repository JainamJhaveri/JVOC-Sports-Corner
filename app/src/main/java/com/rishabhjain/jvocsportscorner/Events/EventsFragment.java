package com.rishabhjain.jvocsportscorner.Events;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Events_AdVh.ContentAdapter;
import com.rishabhjain.jvocsportscorner.AdaptersViewHolders.Events_AdVh.ItemModel;
import com.rishabhjain.jvocsportscorner.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.rishabhjain.jvocsportscorner.General.Constants.ADD_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_ADDEVENTTITLE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.DEFAULT_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.EDIT_EVENT_REQ_CODE;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_EDITED;
import static com.rishabhjain.jvocsportscorner.General.Constants.EVENT_NOT_ADDED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ADDEVENTTITILE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EDITFLAG;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EDIT_POSITION;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_EVENTVENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_ENDDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_ENDTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_EVENTNAME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_EVENTS;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_ISNOTIFIED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_ISNOTIFIEDFORSCHEDULE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_NOOFPARTICIPANTS;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_STARTDATE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_SUCCESS;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_JSON_VENUE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_NOTIFIED;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_NOTIFIED_FOR_SCHEDULE;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_STARTTIME;
import static com.rishabhjain.jvocsportscorner.General.Constants.TAG_UNIQUEPARTICIPANTS;
import static com.rishabhjain.jvocsportscorner.General.Constants.URL_GETALLEVENTS;
import static com.rishabhjain.jvocsportscorner.General.Constants.getSpecificTime;
import static com.rishabhjain.jvocsportscorner.General.Constants.reversedate;

public class EventsFragment extends Fragment {

    private static EventsFragment event_fragment_instance;
    private final String TAG = this.getClass().getSimpleName();
    private static RecyclerView recyclerView;
    private static ContentAdapter adapter;
    private List<ItemModel> models = null;
    private String[] eventnames, venues, dates, times, participants, isnotifiedforschedule, isnotified ;
    private ProgressDialog progressDialog;

    public EventsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        event_fragment_instance = this;
        initializeList();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_event, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_new_event_menu) {
            Intent i = new Intent(this.getActivity(), AddEvent.class);
            i.putExtra(TAG_EVENTNAME, DEFAULT_EVENTNAME);
            i.putExtra(TAG_EVENTVENUE, DEFAULT_EVENTVENUE);
            i.putExtra(TAG_EVENTDATE, DEFAULT_EVENTDATE);
            i.putExtra(TAG_STARTTIME, DEFAULT_STARTTIME);
            i.putExtra(TAG_ENDTIME, DEFAULT_ENDTIME);
            i.putExtra(TAG_ADDEVENTTITILE, DEFAULT_ADDEVENTTITLE);
            i.putExtra(TAG_EDITFLAG, false);    // add mode on
            startActivityForResult(i, ADD_EVENT_REQ_CODE);
            return true;
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_EVENT_REQ_CODE || requestCode == EDIT_EVENT_REQ_CODE) {

            if (resultCode == EVENT_NOT_ADDED) return;

            Bundle extras = data.getExtras();
            String eventname = extras.getString(TAG_EVENTNAME);
            String venue = extras.getString(TAG_EVENTVENUE);
            String date = extras.getString(TAG_EVENTDATE);
            String starttime = extras.getString(TAG_STARTTIME);
            String endtime = extras.getString(TAG_ENDTIME);

            if (resultCode == EVENT_ADDED) {
                Log.e(TAG, "onActivityResult: event added");
                // when a new event is added no of participants will be 0, isnotified = false and isnotifiedforschedule = false
                ItemModel itemModel = new ItemModel(eventname, venue, date, starttime + " to " + endtime, "0", "0", "0");
                addRVItem(itemModel);
            } else if (resultCode == EVENT_EDITED) {
                int edit_position = extras.getInt(TAG_EDIT_POSITION);
                String no_of_participants = extras.getString(TAG_UNIQUEPARTICIPANTS);
                // as events will only be editable if they are not notified and not notified of their schedule, their default value will be 0
                String is_notified = extras.getString(TAG_NOTIFIED, "0");
                String is_notified_for_schedule= extras.getString(TAG_NOTIFIED_FOR_SCHEDULE, "0");

                ItemModel itemModel = new ItemModel(eventname, venue, date, starttime + " to " + endtime,
                        no_of_participants, is_notified, is_notified_for_schedule);
                editRVItem(edit_position, itemModel);
            }
        }
    }

    private void initializeList() {     // TODO: This list will be populated from the GetAllEvents.php
        models = new ArrayList<>();
/*
        eventnames = getContext().getResources().getStringArray(R.array.eventNames);
        venues = getContext().getResources().getStringArray(R.array.eventVenues);
        dates = getContext().getResources().getStringArray(R.array.eventDates);
        times = getContext().getResources().getStringArray(R.array.eventTimes);
        participants = getContext().getResources().getStringArray(R.array.eventTotalParticipants);
        isnotified = getContext().getResources().getStringArray(R.array.isNotifiedArray);
        isnotifiedforschedule = getContext().getResources().getStringArray(R.array.isNotifiedForScheduleArray);

        for (int i = 0; i < eventnames.length; i++) {
            models.add(new ItemModel(eventnames[i], venues[i], dates[i], times[i],
                    participants[i], isnotified[i], isnotifiedforschedule[i]));
        }

*/
        /*

      "eventName": "Indoor Tournament",
      "startDate": "2016-01-11",
      "endDate": "2015-01-10",
      "startTime": "08:25:00",
      "endTime": "16:00:00",
      "venue": "1st floor",
      "noOfParticipants": "25",
      "isNotified": "0",
      "isNotifiedforSchedule": "0"
        * */

        String urlGetallevents = URL_GETALLEVENTS;
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, urlGetallevents, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int success = response.getInt(TAG_JSON_SUCCESS);
                    System.out.println("success: " + success);
                    if (success != 1) {
                        Log.e(TAG, "getProductsDataFromJson: no products found, success = 0");
                        return;
                    }
                    JSONArray events = response.getJSONArray(TAG_JSON_EVENTS);
                    if( events.length() <= 0) {
                        Log.e(TAG, "onResponse: No events found ");
                        return;
                    }
                    models.clear();
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject event = events.getJSONObject(i);

                        String eventName = event.getString(TAG_JSON_EVENTNAME);
                        String startDate = event.getString(TAG_JSON_STARTDATE);
                        String endDate = event.getString(TAG_JSON_ENDDATE);
                        String startTime = event.getString(TAG_JSON_STARTTIME);
                        String endTime = event.getString(TAG_JSON_ENDTIME);
                        String venue = event.getString(TAG_JSON_VENUE);
                        String noOfParticipants = event.getString(TAG_JSON_NOOFPARTICIPANTS);
                        String isNotified = event.getString(TAG_JSON_ISNOTIFIED);
                        String isNotifiedforSchedule = event.getString(TAG_JSON_ISNOTIFIEDFORSCHEDULE);

                        startDate = reversedate(startDate);
                        startTime = getSpecificTime(startTime);
                        endTime = getSpecificTime(endTime);

                        System.out.println( eventName +  " " +venue + " " +startDate + " " +endDate + " " +startTime + " "
                                +endTime + " " +venue + " " + noOfParticipants + " " +isNotified + " " +isNotifiedforSchedule);
                        addRVItem(new ItemModel(eventName, venue, startDate,
                                startTime + " to " +endTime, noOfParticipants, isNotified, isNotifiedforSchedule ));
                    }
                    progressDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(EventsFragment.this.getContext(), "Error getting events data from internet", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        progressDialog = new ProgressDialog(this.getContext());
        progressDialog.setMessage("Fetching The List Data....");
        progressDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        adapter = new ContentAdapter(models);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    public static Context getEventsInstance() {
        return event_fragment_instance.getActivity();
    }


    public static void startEditAddEventAc(String event_name_str, String venue_str, String date_str, String time_str, String no_of_participants_str, int edit_position) {
        Intent i = new Intent(getEventsInstance(), AddEvent.class);
        i.putExtra(TAG_EVENTNAME, event_name_str);
        i.putExtra(TAG_EVENTVENUE, venue_str);
        i.putExtra(TAG_EVENTDATE, date_str);
        String startTimeString = time_str.substring(0, 8);
        String endTimeString = time_str.substring(12);
        i.putExtra(TAG_STARTTIME, startTimeString);
        i.putExtra(TAG_ENDTIME, endTimeString);
        i.putExtra(TAG_UNIQUEPARTICIPANTS, no_of_participants_str);
        i.putExtra(TAG_ADDEVENTTITILE, event_name_str);
        i.putExtra(TAG_EDIT_POSITION, edit_position);
        i.putExtra(TAG_EDITFLAG, true);
        event_fragment_instance.startActivityForResult(i, EDIT_EVENT_REQ_CODE);
    }

    private void editRVItem(int edit_position, ItemModel itemModel) {   // TODO: UpdateEvent.php will be called from here, check for duplication on server side before updation
        ContentAdapter.replaceItem(edit_position, itemModel);
        adapter.notifyDataSetChanged();
    }

    private void addRVItem(ItemModel itemModel) {// TODO: AddEvent.php will be called from here, check for duplication on server side before insertion

        ContentAdapter.addItem(itemModel);
        adapter.notifyDataSetChanged();
    }

    public static void deleteRVItemAt(int position) {                   // TODO: DeleteEvent.php will be called from here
        ContentAdapter.deleteItem(position);
        adapter.notifyDataSetChanged();
    }
}