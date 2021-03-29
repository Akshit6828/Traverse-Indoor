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
    TextView dos,dont,des;

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
       // des=mylayout.findViewById(R.id.textView27);
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
        //Toast.makeText(getActivity(), "choice: "+choice, Toast.LENGTH_SHORT).show();
        if(choice!=null){
           // String choice=bundle.getString("CHOICE_KEY2");
            if(choice.equals("asthma")){
                //des.setText("Asthma | Breathing Problem");
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
                //des.setText("Cold and Flu");
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
                //des.setText("Depression");
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
                //des.setText("Diabetes");
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
            else if(choice.equals("bloodpressure")){
                //des.setText("High Blood Pressure");
                Integer img1[]={R.drawable.citrus,R.drawable.fish_oil,R.drawable.swiss_chard,R.drawable.pumpkinseeds,R.drawable.broccoli,R.drawable.greek_yougut};
                String title1[]={"Citrus fruits","fatty fish","Swiss chard","Pumpkin seeds ","Broccoli","Greek yogurt"};
                Integer text1[]={R.string.Citrus_fruits,R.string.fatty_fish,R.string.Swiss,R.string.Pumpkin,R.string.Broccoli_blood,R.string.Greek_yogurt};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.pizza,R.drawable.pickle,R.drawable.sugar,R.drawable.alcohol,R.drawable.tomatopasta};
                String title2[]={"Frozen pizza","Pickle","Sugar","Alcohol","Canned tomato products"};
                Integer text2[]={R.string.Frozen_pizza,R.string.Pickles,R.string.Sugar,R.string.Alcohol_bp,R.string.tomato};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);
            }
            else if(choice.equals("migraine")){
                Integer img1[]={R.drawable.fish_oil,R.drawable.water,R.drawable.coffee,R.drawable.dark_chocolate,R.drawable.carrot,R.drawable.swiss_chard};
                String title1[]={"Omega-3 Fatty Acids","Stay Hydrated","Limited Caffeine","Dark Chocolate","Carrot","Collard greens"};
                Integer text1[]={R.string.omega3_mig,R.string.Water,R.string.Caffeine_yes,R.string.Dark_Chocolate,R.string.Carrots,R.string.Collard_greens};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.nuts,R.drawable.red_wine,R.drawable.cheese,R.drawable.caffeine};
                String title2[]={"Nuts and Certain Seeds","Red Wine","Aged Cheeses","Excessive caffeine"};
                Integer text2[]={R.string.Nuts_mig,R.string.red_wine,R.string.Cheeses,R.string.Excessive_Coffee};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);

            }
            else if(choice.equals("thyroid")){
                Integer img1[]={R.drawable.iodine,R.drawable.selenium,R.drawable.zinc};
                String title1[]={"Iodine-rich food","Selenium-rich food","Zinc-rich food"};
                Integer text1[]={R.string.iodine,R.string.selenium,R.string.Zinc};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.soy,R.drawable.cruciferous,R.drawable.glutin,R.drawable.fried_food,R.drawable.alcohol};
                String title2[]={"Foods With Soy","Cruciferous Vegetables","Gluten","Fatty Foods","Alcohol"};
                Integer text2[]={R.string.Soy,R.string.Cruciferous,R.string.Gluten,R.string.Fatty_Foods,R.string.Alcohol_thy};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);
            }
            else if(choice.equals("cholesterol")){
                Integer img1[]={R.drawable.legumes,R.drawable.avocados,R.drawable.nuts,R.drawable.grains,R.drawable.cocoa,R.drawable.soy};
                String title1[]={"Legumes","Avocodos","Nuts","Whole Grains","Cocoa","Soy Foods"};
                Integer text1[]={R.string.Legumes,R.string.avocados,R.string.nuts,R.string.Grains,R.string.Cocoa,R.string.Soy_chol};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.cheese,R.drawable.fried_food,R.drawable.desserts,R.drawable.shellfish};
                String title2[]={"Cheese","Fried Food","Desserts","Shellfish"};
                Integer text2[]={R.string.Cheeses,R.string.fried,R.string.Desserts,R.string.Shellfish};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);
            }
            else if(choice.equals("pinkeye")){
                Integer img1[]={R.drawable.juice,R.drawable.vitamin_a,R.drawable.vitamin_b,R.drawable.fruits};
                String title1[]={"Raw juices","Vitamin A-rich foods","Vitamin B2-rich Foods","Fresh Fruits"};
                Integer text1[]={R.string.juices,R.string.vitaminA,R.string.vitaminB2,R.string.fruit};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);


                Integer img2[]={R.drawable.banana,R.drawable.startch_sugar,R.drawable.salt,R.drawable.fried_food};
                String title2[]={"Avoid Banana","Starchy and Sugary foods","Salt","Fried Food"};
                Integer text2[]={R.string.Bananas,R.string.starchySugary,R.string.salts,R.string.fatty_food};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);
            }
            else if(choice.equals("diarrhea")){
                Integer img1[]={R.drawable.banana,R.drawable.grains,R.drawable.protein,R.drawable.coconut,R.drawable.zinc,R.drawable.sabudana,R.drawable.potatoes};
                String title1[]={"Banana","Grains","Protein-rich food","Coconut Water","Zinc-rich foods","Sabudana","Potatoes"};
                Integer text1[]={R.string.Banana,R.string.grains,R.string.Protein,R.string.water_dirr,R.string.zinc_supplements,R.string.Sabudana,R.string.potato};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.diary,R.drawable.spicyfood,R.drawable.coffee,R.drawable.cruciferous};
                String title2[]={"Dairy products","Spicy foods","Beverages","Avoid Cabbage and broccoli"};
                Integer text2[]={R.string.Diary,R.string.spicy_food,R.string.Beverages,R.string.green};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);
            }
            else if(choice.equals("insomnia")){
                Integer img1[]={R.drawable.kiwi,R.drawable.milk,R.drawable.fish_oil,R.drawable.nuts,R.drawable.rice};
                String title1[]={"Kiwi","Malted and Nighttime Milk","Fatty Fish","Nuts","Rice"};
                Integer text1[]={R.string.kiwi,R.string.milk,R.string.fatty_Fish,R.string.nuts_sleep,R.string.Rice_sleep};
                Adapter_Natural_Asthma adapter1 = new Adapter_Natural_Asthma(getActivity(), img1, title1, text1);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView1.setAdapter(adapter1);

                Integer img2[]={R.drawable.alcohol,R.drawable.sugary_beverages,R.drawable.fried_food,R.drawable.caffeine};
                String title2[]={"Alcohol","Sugary beverages","High Fat Foods","Caffeine"};
                Integer text2[]={R.string.alcohol_sleepless,R.string.sugary_beverages,R.string.High_Fat,R.string.Caffeine_sleep};
                Adapter_Natural_Asthma adapter2 = new Adapter_Natural_Asthma(getActivity(), img2, title2, text2);
                recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView2.setAdapter(adapter2);


            }


        }

        return  mylayout;
    }

}