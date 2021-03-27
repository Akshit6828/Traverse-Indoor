 package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Natural_Medi_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Natural_Medi_Fragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String choice;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    RecyclerView recyclerView1,recyclerView2;
    TextView dos,dont;

    public Natural_Medi_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Natural_Medi_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Natural_Medi_Fragment newInstance(String param1, String param2) {
        Natural_Medi_Fragment fragment = new Natural_Medi_Fragment();
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
          // choice =getArguments().getString("CHOICE_KEY2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mylayout= inflater.inflate(R.layout.fragment_natural__medi_, container, false);
        recyclerView1=mylayout.findViewById(R.id.recyclerView_dos);
        recyclerView2=mylayout.findViewById(R.id.recyclerView_dosnt);
        dos=mylayout.findViewById(R.id.textView25);
        dont=mylayout.findViewById(R.id.textView26);
        //Natural_Medi_Fragment layout=new Natural_Medi_Fragment();
        //Bundle bundle=layout.getArguments();
       /* try {
            choice = getArguments().getString("CHOICE_KEY2");
        }
        catch (Exception e){
            Toast.makeText(getActivity(), "Exception ", Toast.LENGTH_SHORT).show();
        }
       if(choice==null){
           Toast.makeText(getActivity(), "choice of frag2 is null", Toast.LENGTH_SHORT).show();

       }
       else{
           Toast.makeText(getActivity(), "choice of frag2 is OK", Toast.LENGTH_SHORT).show();
           //choice=bundle.getString("CHOICE_KEY2");
       }*/

        //pref fetch string
        preferences=getActivity().getSharedPreferences("Local_Details", Context.MODE_PRIVATE);//Mode private as with it the file can only be accessed using calling application
        editor=preferences.edit();
        choice= preferences.getString("Choice",null);
        Toast.makeText(getActivity(), "choice: "+choice, Toast.LENGTH_SHORT).show();
        if(choice!=null){
           // String choice=bundle.getString("CHOICE_KEY2");
            if(choice.equals("asthma")){
                Integer img1[]={R.drawable.caffeine,R.drawable.garlic,R.drawable.ginger,R.drawable.fish_oil,R.drawable.juice};
                String title1[]={"Caffeinated tea or coffee","Garlic","Ginger","Omega-3 Acid","Lemon Juice"};
                Integer text1[]={R.string.caffeine,R.string.garlic,R.string.ginger,R.string.Omega,R.string.LemonJuice};
                Adapter_Natural_Asthma adapter1= new Adapter_Natural_Asthma(getActivity(),img1,title1,text1);

                Integer img2[]={R.drawable.allergens,R.drawable.sulfites,R.drawable.fried_food};
                String title2[]={"Allergens | Allergic food","Sulfites","Foods that cause gas"};
                Integer text2[]={R.string.allergens,R.string.Sulfites,R.string.gas};
                Adapter_Natural_Asthma adapter2= new Adapter_Natural_Asthma(getActivity(),img2,title2,text2);

                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView2.setAdapter(adapter2);

                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView1.setAdapter(adapter1);
            }
            else if(choice.equals("cold")){
                Integer img1[]={R.drawable.gindertea,R.drawable.water,R.drawable.broccoli,R.drawable.spinach,R.drawable.apple};
                String title1[]={"Ginger Tea","Water","Broccoli","Spinach","Apple"};
                Integer text1[]={R.string.gingerTea,R.string.water,R.string.broccoli,R.string.Spinach,R.string.apple};
                Adapter_Natural_Asthma adapter1= new Adapter_Natural_Asthma(getActivity(),img1,title1,text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.caffeine,R.drawable.alcohol,R.drawable.jagged,R.drawable.fried_food,R.drawable.pickled};
                String title2[]={"Caffeinated drinks","Alcohol","Hard or crunchy foods","Fatty foods","Sour,pickled or brined foods"};
                Integer text2[]={R.string.Caffeine,R.string.alcohol,R.string.jagged_foods,R.string.Fatty_foods,R.string.SourOrPickled};
                Adapter_Natural_Asthma adapter2= new Adapter_Natural_Asthma(getActivity(),img2,title2,text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                recyclerView2.setAdapter(adapter2);

            }
            else if(choice.equals("depression")) {
                Integer img1[] = {R.drawable.protein, R.drawable.vitamin, R.drawable.selenium, R.drawable.fish_oil};
                String title1[] = {"Protein-Rich Foods", "Get Enough Vitamin D", "Selenium-Rich Foods", "Omega-3 Fatty Acids"};
                Integer text1[] = {R.string.protine, R.string.vitamin, R.string.Selenium, R.string.Omega3};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[] = {R.drawable.alcohol, R.drawable.soya, R.drawable.fried_food, R.drawable.toast, R.drawable.soda};
                String title2[] = {"Alcohol", "Soy Sauce", "Processed Food", "Toast(white bread)", "Soda"};
                Integer text2[] = {R.string.Alcohol, R.string.Soy_Sauce, R.string.fried_food, R.string.Toast, R.string.soda};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);
            }
            else if(choice.equals("diabetes")){
                Integer img1[]={R.drawable.spinach,R.drawable.avocados,R.drawable.chia,R.drawable.greek_yougut,R.drawable.nuts,R.drawable.broccoli};
                String title1[]={"Leafy Greens","Avocados","Chia Seeds","Greek Yogurt","Nuts","Broccoli"};
                Integer text1[]={R.string.leafy_green,R.string.Avocados,R.string.Chia_Seeds,R.string.Greek_Yogurt,R.string.Nuts,R.string.Broccoli};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.drink,R.drawable.toast,R.drawable.flav_yougut,R.drawable.rice};
                String title2[]={"Sugar-sweetened beverages","White bread","Fruit-flavored yogurt","Rice"};
                Integer text2[]={R.string.beverages,R.string.White_bread,R.string.flavored_yogurt,R.string.rice};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);

            }


        }

        return  mylayout;
    }

}