package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
    Integer[] img_disease ={R.drawable.diabetes2,R.drawable.migraine2,R.drawable.hyper_thyriod_png,R.drawable.hypo_thyriod,R.drawable.congential_heart_png,R.drawable.thalesemia_png,R.drawable.ra_png};
    String [] diseases ={"Diabetes","Migrane","Hyper Thyroid","Hypo Thyroid","Congenital Heart Disease","Thalassemia","Rheumatoid Arthritis"};
    public static RecyclerView recyclerView3;
    public RecyclerView.Adapter adapter3;
    public RecyclerView.LayoutManager layoutManager3;
    Button preventmeasures,close_preventive;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String gender_me;
    ArrayList<Double> default_zero;




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
        recyclerView3 =v.findViewById(R.id.recyclerView3);
        preventmeasures=v.findViewById(R.id.button_preventive_measures);
        default_zero=new ArrayList<Double>(Arrays.asList(0.00,0.00,0.00,0.00,0.00,0.00,0.00));
        close_preventive=v.findViewById(R.id.close_preventive);
        layoutManager3= new LinearLayoutManager(getActivity());
        recyclerView3.setLayoutManager(layoutManager3);

        diabetes= Arrays.asList(getResources().getStringArray(R.array.diabetes_symptoms));
        migrane= Arrays.asList(getResources().getStringArray(R.array.migrane_symptoms));
        hyperthyriod= Arrays.asList(getResources().getStringArray(R.array.hyperthyroid_symptoms));
        hypothyriod= Arrays.asList(getResources().getStringArray(R.array.hypothyroid_symptoms));
        congenital_heart_disease= Arrays.asList(getResources().getStringArray(R.array.congenital_heart_disease_symptoms));
        thalassemia= Arrays.asList(getResources().getStringArray(R.array.thalassemia_symptoms));
        rheumatoid_arthritis= Arrays.asList(getResources().getStringArray(R.array.rheumatoid_arthritis_symptoms));

        preferences=getActivity().getSharedPreferences("Local_Details", Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
        editor=preferences.edit();
        gender_me=preferences.getString("Gender_KEY","Not Specified");

       if(getArguments()!=null) {


           int user_no = getArguments().getInt("SentNo_Key");
           if(user_no>=0&&user_no<=9){
           switch (user_no) {
               case 0:
                   Toast.makeText(getActivity(), "No Disease Found..!", Toast.LENGTH_SHORT).show();
                   break;
               case 1:
                   //Toast.makeText(getActivity(), "Symptoms founded for 1st person", Toast.LENGTH_SHORT).show();
                   Login.grandfather1 = checkProbability(Match_Symptoms.matched_symptoms_user1);

                   Login.percentage_gf1 = Login.grandfather1;
                   Login.percentage_gf1_expected = default_zero;
                   adapter3 = new Results_Adapter(default_zero, Login.percentage_gf1, img_disease, diseases);
                   recyclerView3.setAdapter(adapter3);
                   break;
               case 2:
                   // Toast.makeText(getActivity(), "Symptoms founded for 2nd person", Toast.LENGTH_SHORT).show();
                   Login.grandmother1 = checkProbability(Match_Symptoms.matched_symptoms_user2);

                   Login.percentage_gm1 = Login.grandmother1;
                   Login.percentage_gm1_expected = default_zero;
                   adapter3 = new Results_Adapter(default_zero, Login.percentage_gm1, img_disease, diseases);
                   recyclerView3.setAdapter(adapter3);
                   break;
               case 3:
                   //  Toast.makeText(getActivity(), "Symptoms founded for 3rd person", Toast.LENGTH_SHORT).show();
                   Login.grandfather2 = checkProbability(Match_Symptoms.matched_symptoms_user3);

                   Login.percentage_gf2 = Login.grandfather2;
                   Login.percentage_gf2_expected = default_zero;
                   adapter3 = new Results_Adapter(default_zero, Login.percentage_gf2, img_disease, diseases);
                   recyclerView3.setAdapter(adapter3);


                   break;
               case 4:
                   //  Toast.makeText(getActivity(), "Symptoms found for 4th person ", Toast.LENGTH_SHORT).show();
                   Login.grandmother2 = checkProbability(Match_Symptoms.matched_symptoms_user4);

                   Login.percentage_gm2 = Login.grandmother2;
                   Login.percentage_gm2_expected = default_zero;

                   adapter3 = new Results_Adapter(default_zero, Login.percentage_gm2, img_disease, diseases);
                   recyclerView3.setAdapter(adapter3);
                   break;
               case 5:
                   // Toast.makeText(getActivity(), "Symptoms found for 5th person ", Toast.LENGTH_SHORT).show();
                   Login.dad = checkProbability(Match_Symptoms.matched_symptoms_user5);
                   Login.percentage_dad=Login.dad;

                   //********Checking Parents data is filled or not..
                   if (Login.grandfather1 != null && Login.grandmother1 != null) {
                       // Toast.makeText(getActivity(), "dad and mom size arr is "+Login.grandfather1.size()+" --"+Login.grandmother1.size(), Toast.LENGTH_SHORT).show();
                       if (Login.grandfather1.size() > 0 && Login.grandmother1.size() > 0) {
                           Login.percentage_dad_expected = tell_Expected(Login.grandfather1, Login.grandmother1);
                           //  Login.percentage_dad = tellPercentage(Login.dad, Login.grandfather1, Login.grandmother1, 5);
                           adapter3 = new Results_Adapter(Login.percentage_dad_expected, Login.dad, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       } else
                           Toast.makeText(getActivity(), "Please Refill his/her Parents Data Again", Toast.LENGTH_SHORT).show();

//                   for(int i=0;i<Login.percentage_dad.size();i++){
//                       Toast.makeText(getActivity(), "% dad alternate is "+Login.percentage_dad.get(i), Toast.LENGTH_SHORT).show();
//                   }
                   } else {
                       Toast.makeText(getActivity(), "Please Add Both of his/her Parents Data First", Toast.LENGTH_SHORT).show();
                   }

                   break;
               case 6:
                   //Toast.makeText(getActivity(), "Symptoms found for 6th", Toast.LENGTH_SHORT).show();
                   Login.mom = checkProbability(Match_Symptoms.matched_symptoms_user6);
                   Login.percentage_mom=Login.mom;

                   if (Login.grandfather2 != null && Login.grandmother2 != null) {
                     //  Toast.makeText(getActivity(), "dad and mom size arr is " + Login.grandfather2.size() + " --" + Login.grandmother2.size(), Toast.LENGTH_SHORT).show();
                       if (Login.grandfather2.size() > 0 && Login.grandmother2.size() > 0) {
                           Login.percentage_mom_expected = tell_Expected(Login.grandfather2, Login.grandmother2);
                           //Login.percentage_mom = tellPercentage(Login.mom, Login.grandfather2, Login.grandmother2, 6);
                           adapter3 = new Results_Adapter(Login.percentage_mom_expected, Login.mom, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       } else
                           Toast.makeText(getActivity(), "Please Refill his/her Parents Data Again", Toast.LENGTH_SHORT).show();

                   } else {
                       Toast.makeText(getActivity(), "Please Add Both of his/her Parents Data First", Toast.LENGTH_SHORT).show();
                   }



                   break;
               case 7:
                   // Toast.makeText(getActivity(), "Symptoms found for 7th", Toast.LENGTH_SHORT).show();
                   Login.me = checkProbability(Match_Symptoms.matched_symptoms_user7);
                   Login.percentage_me=Login.me;
                   if (Login.dad != null && Login.mom != null) {
                       //   Toast.makeText(getActivity(), "dad and mom size arr is "+Login.dad.size()+" --"+Login.mom.size(), Toast.LENGTH_SHORT).show();
                       if (Login.dad.size() > 0 && Login.mom.size() > 0) {
                           Login.percentage_me_expected = tell_Expected(Login.dad, Login.mom);
                           // Login.percentage_me= tellPercentage(Login.me, Login.dad, Login.mom, 7);
                           adapter3 = new Results_Adapter(Login.percentage_me_expected, Login.me, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       } else
                           Toast.makeText(getActivity(), "Please Refill his/her Parents Data Again", Toast.LENGTH_SHORT).show();

                   } else {
                       Toast.makeText(getActivity(), "Please Add Both of his/her Parents Data First", Toast.LENGTH_SHORT).show();
                   }
                   break;
               case 8:

                   if (Match_Symptoms.matched_symptoms_user8.size() <= 0) {
                       // Toast.makeText(getActivity(), "Symptoms found for 8th", Toast.LENGTH_SHORT).show();

                       if (Login.me != null) {
                           // Toast.makeText(getActivity(), "Size of Alone me is "+Login.me.size(), Toast.LENGTH_SHORT).show();
                           if (Login.me.size() > 0) {
                               Login.child1female = checkProbability(Match_Symptoms.matched_symptoms_user8);
                               Login.percentage_child1female=Login.child1female;
                               if (gender_me != null) {
                                   if (gender_me.equals("Male")) {
                                       // Login.percentage_child1female= tellPercentage(Login.child1female,Login.me,null,8);
                                       Login.percentage_child1female_expected = tell_Expected(Login.me, null);
                                       adapter3 = new Results_Adapter(Login.percentage_child1female_expected, Login.child1female, img_disease, diseases);
                                       recyclerView3.setAdapter(adapter3);
                                   } else if (gender_me.equals("Female")) {
                                       //Login.percentage_child1female= tellPercentage(Login.child1female,null,Login.me,8);
                                       Login.percentage_child1female_expected = tell_Expected(null, Login.me);
                                       adapter3 = new Results_Adapter(Login.percentage_child1female_expected, Login.child1female, img_disease, diseases);
                                       recyclerView3.setAdapter(adapter3);
                                   } else {
                                       Toast.makeText(getActivity(), "Your Gender is not specified for you.\n We Recommend you to fill your profile Either Male or Female for Tracing out the children Patterns Easily!", Toast.LENGTH_SHORT).show();
                                   }
                               }

                           }
                       } else {
                           Toast.makeText(getActivity(), "Please Add his/her Parent(s) Data First", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       //----if the child is not born.Thus, his symptoms are not selected.
                       if (Login.me != null) {
                           Login.child1female = checkProbability(Match_Symptoms.matched_symptoms_user8);
                           Login.percentage_child1female=Login.child1female;
                           if (Login.me.size() > 0) {
                               //Login.percentage_child1female = tellPercentage(Login.child1female);
                               if (gender_me.equals("Father"))
                                   Login.percentage_child1female_expected = tell_Expected(Login.me, null);
                               else if (gender_me.equals("Mother"))
                                   Login.percentage_child1female_expected = tell_Expected(null, Login.me);
                               else
                                   Toast.makeText(getActivity(), "Your Gender is not specified for you.\n We Recommend you to fill your profile Either Male or Female for Tracing out the children Patterns Easily!", Toast.LENGTH_SHORT).show();

                               adapter3 = new Results_Adapter(Login.percentage_child1female_expected, default_zero, img_disease, diseases);
                               recyclerView3.setAdapter(adapter3);
                           } else {
                               Toast.makeText(getActivity(), "Please fill his/ her Parent Data Again.", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(getActivity(), "Please Fill his/her Parent Data First..", Toast.LENGTH_SHORT).show();
                       }

                   }
                   break;
               case 9:
                   if (Match_Symptoms.matched_symptoms_user9.size() <= 0) {
                       //  Toast.makeText(getActivity(), "Symptoms found for 9th", Toast.LENGTH_SHORT).show();

                       if (Login.me != null) {
                           //  Toast.makeText(getActivity(), "Size of Alone me is "+Login.me.size(), Toast.LENGTH_SHORT).show();
                           if (Login.me.size() > 0) {
                               Login.child2male = checkProbability(Match_Symptoms.matched_symptoms_user9);
                               Login.percentage_child2male=Login.child2male;
                               if (gender_me != null) {
                                   if (gender_me.equals("Male")) {
                                       Login.percentage_child2male_expected = tell_Expected(Login.me, null);
                                       adapter3 = new Results_Adapter(Login.percentage_child2male_expected, Login.percentage_child2male, img_disease, diseases);
                                       recyclerView3.setAdapter(adapter3);
                                   } else if (gender_me.equals("Female")) {
                                       Login.percentage_child2male_expected = tell_Expected(null, Login.me);
                                       adapter3 = new Results_Adapter(Login.percentage_child2male_expected, Login.percentage_child2male, img_disease, diseases);
                                       recyclerView3.setAdapter(adapter3);
                                   } else {
                                       Toast.makeText(getActivity(), "Your Gender is not specified for you.\n We Recommend you to fill your profile Either Male or Female for Tracing out the children Patterns Easily!", Toast.LENGTH_SHORT).show();
                                   }
                               }

                           }
                       } else {
                           Toast.makeText(getActivity(), "Please Add his/her Parent(s) Data First", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       //----if the child is not born.Thus, his symptoms are not selected.
                       if (Login.me != null) {
                           Login.child2male = checkProbability(Match_Symptoms.matched_symptoms_user9);
                           Login.percentage_child2male=Login.child2male;
                           if (Login.me.size() > 0) {
                               // Login.percentage_child2male = tellPercentage(Login.child2male);
                               if (gender_me.equals("Male"))
                                   Login.percentage_child2male_expected = tell_Expected(Login.me, null);
                               else if (gender_me.equals("Female"))
                                   Login.percentage_child2male_expected = tell_Expected(null, Login.me);
                               else
                                   Toast.makeText(getActivity(), "Your Gender is not specified for you.\n We Recommend you to fill your profile Either Male or Female for Tracing out the children Patterns Easily!", Toast.LENGTH_SHORT).show();
                               adapter3 = new Results_Adapter(Login.percentage_child2male_expected, default_zero, img_disease, diseases);
                               recyclerView3.setAdapter(adapter3);
                           } else {
                               Toast.makeText(getActivity(), "Please fill his/ her Parent Data Again.", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(getActivity(), "Please Fill his/her Parent Data First..", Toast.LENGTH_SHORT).show();
                       }

                   }
                   break;

           }
       }
           else{
               switch (user_no) {
                   case 11: {
                       if(Login.percentage_gf1!=null) {
                           adapter3 = new Results_Adapter(default_zero, Login.percentage_gf1, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first..", Toast.LENGTH_SHORT).show();
                       }
                       break;

                   }
                   case 12: {
                       if(Login.percentage_gm1!=null) {
                           adapter3 = new Results_Adapter(default_zero, Login.percentage_gm1, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);

                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   }
                   case 13:
                       if(Login.percentage_gf2!=null) {
                           adapter3 = new Results_Adapter(default_zero, Login.percentage_gf2, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);

                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case 14:
                       if(Login.percentage_gm2!=null) {
                           adapter3 = new Results_Adapter(default_zero, Login.percentage_gm2, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case 15:
                       if(Login.percentage_dad!=null) {
                           adapter3 = new Results_Adapter(Login.percentage_dad_expected, Login.percentage_dad, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       }
                       else {
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case 16:
                       if(Login.percentage_mom!=null) {
                           adapter3 = new Results_Adapter(Login.percentage_mom_expected, Login.mom, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case 17:
                       if(Login.percentage_me!=null) {
                           adapter3 = new Results_Adapter(Login.percentage_me_expected, Login.me, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;

                   case 18:
                       if(Login.percentage_child1female!=null) {
                           adapter3 = new Results_Adapter(Login.percentage_child1female_expected, Login.child1female, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);

                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
                   case 19:
                       if(Login.percentage_child2male!=null) {
                           adapter3 = new Results_Adapter(Login.percentage_child2male_expected, Login.child2male, img_disease, diseases);
                           recyclerView3.setAdapter(adapter3);
                       }
                       else{
                           Toast.makeText(getActivity(), "Please fill symptoms first...", Toast.LENGTH_SHORT).show();
                       }
                       break;
               }
           }

       }

       //Connecting to UI...........

        preventmeasures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  backstack="PedigreeAnalysis";
                getActivity().getSupportFragmentManager().beginTransaction().
                        replace(R.id.fragment_container,new fragment_medication()).
                        addToBackStack(backstack).
                        commit();
            }
        });
       close_preventive.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String  backstack="PedigreeAnalysis";
               getActivity().getSupportFragmentManager().beginTransaction().
                       replace(R.id.fragment_container,new PedigreeAnalysis()).
                       addToBackStack(backstack).
                       commit();

           }
       });

        return  v;
    }
    private ArrayList<Double> tell_Expected(ArrayList<Double> father,ArrayList<Double> mother){

        ArrayList<Double> percentage_ArrayList = new ArrayList<Double>();
        DecimalFormat df = new DecimalFormat("0.00");
        String expected;

        if(father!=null&&mother!=null) {
            for (int i = 0; i < 7; i++) {
                expected = df.format((father.get(i) + mother.get(i)) / 2.0);
                percentage_ArrayList.add(Double.parseDouble(expected));
            }
        }
        else if(father!=null&&mother==null){
            for (int i = 0; i < 7; i++) {
                expected = df.format(father.get(i)  / 2.0);
                percentage_ArrayList.add(Double.parseDouble(expected));
            }

        }
        else if(father==null&&mother!=null){
            for (int i = 0; i < 7; i++) {
                expected = df.format(mother.get(i)  / 2.0);
                percentage_ArrayList.add(Double.parseDouble(expected));
            }
        }
        return percentage_ArrayList;
    }
    //*****************OVERLOADED tellPercentage 2 (For case of Child if not Born)***************************************

    private ArrayList<Double> tellPercentage( ArrayList<Double> me) {
        String expected;
        String minechance;
        ArrayList<Double> percentage_ArrayList = new ArrayList<Double>();
        DecimalFormat df = new DecimalFormat("0.00");
        for(int i=0;i<7;i++) {
        expected=df.format((me.get(i)/2.0));
        percentage_ArrayList.add(Double.parseDouble(expected));
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

        if(user_no>=5&&user_no<=7){
            for (int i = 0; i < 7; i++) {
            //    Toast.makeText(getActivity(), "fat(i) = " + father.get(i), Toast.LENGTH_SHORT).show();
             //   Toast.makeText(getActivity(), "moth(i) = " + mother.get(i), Toast.LENGTH_SHORT).show();
                expected = df.format((father.get(i) + mother.get(i)) / 2.0);
              //  Toast.makeText(getActivity(), "expected to be added is " + expected, Toast.LENGTH_SHORT).show();
                minechance = df.format(self.get(i));
              //  Toast.makeText(getActivity(), "minechance too be add is" + minechance, Toast.LENGTH_SHORT).show();
                percentage_ArrayList.add(Double.parseDouble(expected));
                percentage_ArrayList.add(Double.parseDouble(minechance));
            }
        }
        else if(user_no>7){
            if(Login.me!=null&&mother==null) {
                for(int i=0;i<7;i++) {
                    expected=df.format((father.get(i)/2.0));
                    minechance=df.format((self.get(i)));
                    percentage_ArrayList.add(Double.parseDouble(expected));
                    percentage_ArrayList.add(Double.parseDouble(minechance));
                }
            }
            else if(Login.me!=null&&father==null) {
                        for(int i=0;i<7;i++) {
                            expected=df.format((mother.get(i)/2.0));
                            minechance=df.format((self.get(i)));
                            percentage_ArrayList.add(Double.parseDouble(expected));
                            percentage_ArrayList.add(Double.parseDouble(minechance));
                        }
                }
        }
        int ind=0;
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
            per=df.format((hypothyriod_count/7.0)*100);//4. hypo-thyroid has 8 disease specific symptoms.
        return_values.add(Double.parseDouble(per));
            per=df.format((congenital_heart_disease_count/5.0)*100);//5.
        return_values.add(Double.parseDouble(per));
            per=df.format((thalassemia_count/7.0)*100);//.6
        return_values.add(Double.parseDouble(per));
            per=df.format((rheumatoid_arthritis_count/5.0)*100);//7.
        return_values.add(Double.parseDouble(per));

        return return_values;
    }


}