package com.akshit.genedetectionapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.akshit.genedetectionapp.Database.DatabaseClass;
import com.akshit.genedetectionapp.Database.EntityClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_set_reminder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_set_reminder extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBERlo
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton fab;
    EditText c,m,d,t,msg;
    Button set;
    Spinner r;
    String relation[];
    String username,userrelation;
    String timeToNotify;
    DatabaseClass databaseClass;
    ReminderAdapter reminderAdapter;
    RecyclerView recyclerView;


    public fragment_set_reminder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_set_reminder.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_set_reminder newInstance(String param1, String param2) {
        fragment_set_reminder fragment = new fragment_set_reminder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View mylayout=  inflater.inflate(R.layout.fragment_set_reminder, container, false);
        fab=mylayout.findViewById(R.id.floatingActionButton);
        recyclerView=mylayout.findViewById(R.id.recycler_view);
        databaseClass=DatabaseClass.getDatabase(getContext());
        if(getArguments()!=null) {
            username = getArguments().getString("UserName");
            userrelation = getArguments().getString("UserRelation");
            //Toast.makeText(getActivity(), "username="+username+" "+"userrelation="+userrelation, Toast.LENGTH_SHORT).show();
            if (username == null || userrelation == null) {
                Toast.makeText(getActivity(), "No reminder set", Toast.LENGTH_SHORT).show();
            } else {
                setReminderAdapter();
            }
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final Dialog dialog=new Dialog(getActivity());
                 dialog.setContentView(R.layout.custom_dialog);
                 r=dialog.findViewById(R.id.spinner);
                 d=dialog.findViewById(R.id.editTextDate);
                 t=dialog.findViewById(R.id.editTextTime);
                 m=dialog.findViewById(R.id.editTextMessage);
                 set=dialog.findViewById(R.id.button_set);



                    final int y,mm,dd,h,min;
                    Calendar ca=Calendar.getInstance();
                    y=ca.get(Calendar.YEAR);
                    mm=ca.get(Calendar.MONTH);
                    dd=ca.get(Calendar.DAY_OF_MONTH);
                    h=ca.get(Calendar.HOUR_OF_DAY);
                    min=ca.get(Calendar.MINUTE);

                relation=getResources().getStringArray(R.array.relation);
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,relation);
                r.setAdapter(adapter);

                d.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new DatePickerDialog(getActivity(),ld,y,mm,dd).show();
                    }
                });

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new TimePickerDialog(getActivity(),lt,h,min,false).show();
                    }
                });

                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (r.getSelectedItemPosition() > 0) {
                             String relation=r.getSelectedItem().toString();
                             String message=m.getText().toString().trim();
                            if(message.isEmpty()){
                                Toast.makeText(getActivity(), "Please, type message.", Toast.LENGTH_LONG).show();
                            }
                            else{
                                if(d.getText().toString().isEmpty() || t.getText().toString().isEmpty()){
                                    Toast.makeText(getActivity(), "Please,select date and time for reminder.", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    EntityClass entityClass = new EntityClass();
                                    String text=m.getText().toString().trim();
                                    String value = ("Relation: "+relation+"\nMessage: "+text);
                                    String date = (d.getText().toString().trim());
                                    String time = (t.getText().toString().trim());
                                    setAlarm(relation,text, date, time);
                                   // Toast.makeText(getActivity(), "values="+value+"date="+date+"time="+time, Toast.LENGTH_SHORT).show();
                                    entityClass.setEventdate(date);
                                    entityClass.setEventname(value);
                                    entityClass.setEventtime(time);
                                    databaseClass.evenDao().insertAll(entityClass);
                                    setAlarm(relation,value, date, time);
                                    setReminderAdapter();



                                    dialog.dismiss();

                                }
                            }



                        } else {
                          // set error message on spinner
                            TextView errorTextview = (TextView) r.getSelectedView();
                            errorTextview.setText("Invalid Choice");
                            errorTextview.setTextColor(Color.RED);
                        }

                    }


                });

                dialog.show();
            }

        });


        return  mylayout;
    }
    DatePickerDialog.OnDateSetListener ld=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
            d.setText(dayOfMonth+"/"+(month+1)+"/"+year);
        }
    };

    TimePickerDialog.OnTimeSetListener lt=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            timeToNotify=hourOfDay+ ":" +minute;
            t.setText(FormatTime(hourOfDay,minute));

        }
    };

    public String FormatTime(int hourOfDay, int minute) {
        String time="";
        String formattedMinute;

        if(minute/10==0)
            formattedMinute="0"+minute;
        else
            formattedMinute=""+minute;
        if(hourOfDay==0)
            time="12"+":"+formattedMinute+"AM";
        else if(hourOfDay<12)
            time=hourOfDay+":"+formattedMinute+"AM";
        else if(hourOfDay==12)
            time="12"+":"+formattedMinute+"PM";
        else{
            int temp=hourOfDay-12;
            time=temp+":"+formattedMinute+"PM";
        }
      return time;
    }

    private void setReminderAdapter(){
        List<EntityClass> classList=databaseClass.evenDao().getAllData();
        reminderAdapter=new ReminderAdapter(getContext(),classList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(reminderAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setReminderAdapter();
    }

    private void setAlarm(String relation,String text, String date, String time) {
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getActivity().getApplicationContext(), AlarmBroadcast.class);
        intent.putExtra("relation", relation);
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + timeToNotify;
        //Toast.makeText(getActivity(), "date and time="+dateandtime, Toast.LENGTH_SHORT).show();
        DateFormat formatter = new SimpleDateFormat("d/M/yyyy hh:mm", Locale.ENGLISH);
        try {

            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}