package com.akshit.genedetectionapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class preventive_measures extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int number;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    List<String> diabetes;
    private int diabetes_count;
    List<String> migrane;
    private int migrane_count;
    List<String> hyperthyriod;
    private int hyperthyriod_count;
    List<String> hypothyriod;
    private int hypothyriod_count;
    List<String> congenital_heart_disease;
    private int congenital_heart_disease_count;
    List<String> thalassemia;
    private int thalassemia_count;
    List<String> rheumatoid_arthritis;
    private int rheumatoid_arthritis_count;
    private int no_match;





    public preventive_measures() {
        // Required empty public constructor
    }

    public static preventive_measures newInstance(String param1, String param2) {
        preventive_measures fragment = new preventive_measures();
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
        View v= inflater.inflate(R.layout.fragment_preventive_measures, container, false);
       //selected_symptoms=new ArrayList<>();

        diabetes= Arrays.asList(getResources().getStringArray(R.array.diabetes_symptoms));
        migrane= Arrays.asList(getResources().getStringArray(R.array.migrane_symptoms));
        hyperthyriod= Arrays.asList(getResources().getStringArray(R.array.hyperthyroid_symptoms));
        hypothyriod= Arrays.asList(getResources().getStringArray(R.array.hypothyroid_symptoms));
        congenital_heart_disease= Arrays.asList(getResources().getStringArray(R.array.congenital_heart_disease_symptoms));
        thalassemia= Arrays.asList(getResources().getStringArray(R.array.thalassemia_symptoms));
        rheumatoid_arthritis= Arrays.asList(getResources().getStringArray(R.array.rheumatoid_arthritis_symptoms));

       if(getArguments()!=null) {
           int user_no=getArguments().getInt("SentNo_Key");
           Toast.makeText(getActivity(), "Preventive_Measure  Args is +ve with userno = "+user_no, Toast.LENGTH_SHORT).show();
           switch (user_no) {
               case 0:
                   Toast.makeText(getActivity(), "No Disease Found..!", Toast.LENGTH_SHORT).show();
                   break;
               case 1:
                   Toast.makeText(getActivity(), "Symptoms founded for 1st person", Toast.LENGTH_SHORT).show();
                   Login.grandfather1 = checkProbability(Match_Symptoms.matched_symptoms_user1);
                   Login.percentage_gf1=new ArrayList<>();
                   for(int i = 0; i< Login.grandfather1.size(); i++){
                       Login.percentage_gf1.add(0.0);
                       Login.percentage_gf1.add(Login.grandfather1.get(i));
                   }
                   for(double d:Login.percentage_gf1){
                       Toast.makeText(getActivity(), "%gf1 for userid"+user_no+" is"+d, Toast.LENGTH_SHORT).show();
                   }
                   break;
               case 2:
                   Toast.makeText(getActivity(), "Symptoms founded for 2nd person", Toast.LENGTH_SHORT).show();
                   Login.grandmother1 = checkProbability(Match_Symptoms.matched_symptoms_user2);
                  // Toast.makeText(getActivity(), "size of returnedis "+grandmother1.size(), Toast.LENGTH_SHORT).show();
                   Login.percentage_gm1 = new ArrayList<>();
                   for(int i=0;i<Login.grandmother1.size();i++){
                       Login.percentage_gm1.add(0.0);
                       Login.percentage_gm1.add(Login.grandmother1.get(i));
                   }
                   for(double d:Login.grandmother1){
                       Toast.makeText(getActivity(), "gm1 is for userid"+user_no+" is "+d, Toast.LENGTH_SHORT).show();
                   }

                   break;
               case 3:
                   Toast.makeText(getActivity(), "Symptoms founded for 3rd person", Toast.LENGTH_SHORT).show();
                   Login.grandfather2 = checkProbability(Match_Symptoms.matched_symptoms_user3);
                   Login.percentage_gf2 = new ArrayList<>();

                   for(int i=0;i<Login.grandfather2.size();i++){
                       Login.percentage_gf2.add(0.0);
                       Login.percentage_gf2.add(Login.grandfather2.get(i));
                   }
                   break;
               case 4:
                   Toast.makeText(getActivity(), "Symptoms found for 4th person ", Toast.LENGTH_SHORT).show();
                   Login.grandmother2 = checkProbability(Match_Symptoms.matched_symptoms_user4);
                   Login.percentage_gm2 = new ArrayList<>();
                   for(int i=0;i<Login.grandmother2.size();i++){
                       Login.percentage_gm2.add(0.0);
                       Login.percentage_gm2.add(Login.grandmother2.get(i));
                   }
                   for(int i=0;i<Login.percentage_gm2.size();i++){
                       Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                   }
                   break;
               case 5:
                   Toast.makeText(getActivity(), "Symptoms found for 5th person ", Toast.LENGTH_SHORT).show();
                   Login.dad = checkProbability(Match_Symptoms.matched_symptoms_user5);
//                   if (Login.grandfather1.isEmpty())
//                       Login.grandfather1 = checkProbability(Match_Symptoms.matched_symptoms_user1);
//                   if (Login.grandmother1.isEmpty())
//                       Login.grandmother1 = checkProbability(Match_Symptoms.matched_symptoms_user2);
                   //Returned percentage is in (Disease1_expected,Disease1_actual,Disease2_expected,Disease2_actual
                   Login.percentage_dad=new ArrayList<>();
                   Toast.makeText(getActivity(), "dad and mom size arr is "+Login.grandfather1.size()+" --"+Login.grandmother1.size(), Toast.LENGTH_SHORT).show();
                   if(Login.grandfather1.size()>0&&Login.grandmother1.size()>0) {
                       Login.percentage_dad = tellPercentage(Login.dad, Login.grandfather1, Login.grandmother1, 5);
                   }
                   else
                       Toast.makeText(getActivity(), "Size of Login.Grandfather is null", Toast.LENGTH_SHORT).show();
                   break;
               case 6:
                   Toast.makeText(getActivity(), "Symptoms found for 6th", Toast.LENGTH_SHORT).show();
                   Login.mom = checkProbability(Match_Symptoms.matched_symptoms_user6);
                  Login.percentage_mom=new ArrayList<>();
//                   if (Login.grandfather2.isEmpty())
//                       Login.grandfather2 = checkProbability(Match_Symptoms.matched_symptoms_user3);
//                   if (Login.grandmother2.isEmpty())
//                       Login.grandmother2 = checkProbability(Match_Symptoms.matched_symptoms_user4);
                   Login.percentage_mom = tellPercentage(Login.mom, Login.grandfather2, Login.grandmother2, 6);
                   break;
               case 7:
                   Toast.makeText(getActivity(), "Symptoms found for 7th", Toast.LENGTH_SHORT).show();
                   Login.me = checkProbability(Match_Symptoms.matched_symptoms_user6);
//                   if (Login.dad.isEmpty())
//                       Login.dad = checkProbability(Match_Symptoms.matched_symptoms_user5);
//                   if (Login.mom.isEmpty())
//                       Login.mom = checkProbability(Match_Symptoms.matched_symptoms_user7);
                   Login.percentage_me = tellPercentage(Login.me, Login.dad, Login.mom, 7);

                   break;
               case 8:

                   if (Match_Symptoms.matched_symptoms_user8.size() <= 0) {
                       if (Login.me.isEmpty())
                           Login.me = checkProbability(Match_Symptoms.matched_symptoms_user7);
                       Login.percentage_child1female = tellPercentage(null, Login.me, null, 8);

                   } else {
                       Login.percentage_child1female = checkProbability(Match_Symptoms.matched_symptoms_user8);
                       if (Login.me.isEmpty())
                           Login.me = checkProbability(Match_Symptoms.matched_symptoms_user7);
                       //Here Single parent probability is expected and actual symptoms of child is actual returned symptom.
                       ArrayList<Double> child1female_percentage = tellPercentage(Login.child1female, Login.me);
                   }
                   Toast.makeText(getActivity(), "Symptoms found for 8th", Toast.LENGTH_SHORT).show();
                   break;
               case 9:
                   Toast.makeText(getActivity(), "Symptoms found for 9th", Toast.LENGTH_SHORT).show();
                   if (Match_Symptoms.matched_symptoms_user9.size() <= 0) {
                       if (Login.me.isEmpty())
                           Login.me = checkProbability(Match_Symptoms.matched_symptoms_user7);
                       ArrayList<Double> percentage_child2_by_me = tellPercentage(null, Login.me, null, 9);
                   } else {
                       Login.child2male = checkProbability(Match_Symptoms.matched_symptoms_user9);
                       if (Login.me.isEmpty())
                           Login.me = checkProbability(Match_Symptoms.matched_symptoms_user7);
                       //Here Single parent probability is expected and actual symptoms of child is actual returned symptom.
                       ArrayList<Double> child1female_percentage = tellPercentage(Login.child2male, Login.me);
                   }
                   Toast.makeText(getActivity(), "Symptoms found for 8th", Toast.LENGTH_SHORT).show();
                   break;

           }

       }

       //Connecting to UI...........








        return  v;
    }
    //*****************OVERLOADED tellPercentage 2 (For case of Child if not Born)***************************************

    private ArrayList<Double> tellPercentage(ArrayList<Double> child, ArrayList<Double> me) {
        double expected;
        double minechance;
        ArrayList<Double> percentage_ArrayList = new ArrayList<Double>();
        for(int i=0;i<7;i++) {
        expected=me.get(i)/2.0;
        minechance=child.get(i);
        percentage_ArrayList.add(expected);
        percentage_ArrayList.add(minechance);
        }
        return  percentage_ArrayList;
    }
    //*******************OVERLOADED tell_Percentage 1 (For Already Born People)............
    private ArrayList<Double> tellPercentage(ArrayList<Double> self, ArrayList<Double> father, ArrayList<Double> mother,int user_no) {
        String expected;
        String minechance;
        ArrayList<Double> percentage_ArrayList = new ArrayList<Double>();

        //*********formating decimal value upto 2 decimals
        DecimalFormat df = new DecimalFormat("0.00");

        if(user_no==5){
            for (int i = 0; i < 7; i++) {
                Toast.makeText(getActivity(), "fat(i) = " + Login.grandfather1.get(i), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "moth(i) = " + Login.grandmother1.get(i), Toast.LENGTH_SHORT).show();
                expected = df.format((Login.grandfather1.get(i) + Login.grandmother1.get(i)) / 2.0);
                Toast.makeText(getActivity(), "expected to be added is " + expected, Toast.LENGTH_SHORT).show();
                minechance = df.format(self.get(i));
                Toast.makeText(getActivity(), "minechance too be add is" + minechance, Toast.LENGTH_SHORT).show();
                percentage_ArrayList.add(Double.parseDouble(expected));
                percentage_ArrayList.add(Double.parseDouble(minechance));
            }
        }
        else if(user_no>7){
            if(Login.me==null&&mother!=null) {
                for(int i=0;i<7;i++) {
                    minechance=df.format((mother.get(i)/2.0));
                    percentage_ArrayList.add(Double.parseDouble(minechance));
                }
            }
                    else if(Login.me==null&&father!=null) {
                        for(int i=0;i<7;i++) {
                        minechance=df.format((father.get(i)/2.0));
                        percentage_ArrayList.add(Double.parseDouble(minechance));
                        }
                }
        }
        int ind=0;
        for(double d:percentage_ArrayList){

            Toast.makeText(getActivity(), "Returned per_Al at index  ind =  "+ind+" is  "+d, Toast.LENGTH_SHORT).show();
        ind++;
        }
        return percentage_ArrayList;
    }


    private ArrayList<Double> checkProbability(ArrayList<String> matched_symptoms_user) {
        ArrayList<Double> return_values= new ArrayList<Double>();

        for(String s:matched_symptoms_user){
            if(diabetes.contains(s))
                diabetes_count++;
            if(migrane.contains(s))
                migrane_count++;
            if(hypothyriod.contains(s))
                hypothyriod_count++;
            if(hyperthyriod.contains(s))
                hyperthyriod_count++;
            if(congenital_heart_disease.contains(s))
                congenital_heart_disease_count++;
            if(thalassemia.contains(s))
                thalassemia_count++;
            if(rheumatoid_arthritis.contains(s))
                rheumatoid_arthritis_count++;
            else
                no_match++;
        }
         DecimalFormat df = new DecimalFormat("0.00");
            //calculate the logic for their symptoms with matched symptoms.
            String per;
            per=df.format((diabetes_count*10.0));//1. diabetes has 10 disease specific symptoms.
            return_values.add(Double.parseDouble(per));
            per=df.format((migrane_count/6.0)*100);//2. migrane has 6 disease specific symptoms.
        return_values.add(Double.parseDouble(per));
            per=df.format((hyperthyriod_count/8.0)*100);//3. hyper-thyroid has 7 disease specific symptoms
        return_values.add(Double.parseDouble(per));
            per=df.format((hypothyriod_count/7.0)*100);//4. hypo-thyriod has 8 disease specific symptoms.
        return_values.add(Double.parseDouble(per));
            per=df.format((congenital_heart_disease_count/5.0)*100);//5.
        return_values.add(Double.parseDouble(per));
            per=df.format((thalassemia_count/7.0)*100);//.6
        return_values.add(Double.parseDouble(per));
            per=df.format((rheumatoid_arthritis_count/5.0)*100);//7.
        return_values.add(Double.parseDouble(per));

/*for(int j=0;j<return_values.size();j++){
    Toast.makeText(getActivity(), "return_values called and val is"+return_values.get(j), Toast.LENGTH_SHORT).show();
}*/
//            Toast.makeText(getActivity(), "Diabitic Count = " +diabetes_count, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "Migrane Count = " +migrane_count, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "hypothyriod Count = " +hypothyriod_count ,Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "hyper Count = " +hyperthyriod_count, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), " thala Count = " +thalassemia_count, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), "congential Count = " +congenital_heart_disease_count, Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), " RA Count = " +rheumatoid_arthritis_count, Toast.LENGTH_SHORT).show();
        return return_values;
    }


}