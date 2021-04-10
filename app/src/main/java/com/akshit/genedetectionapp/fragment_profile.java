package com.akshit.genedetectionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static kotlinx.coroutines.flow.FlowKt.collect;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String code;

    public fragment_profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_profile newInstance(String param1, String param2) {
        fragment_profile fragment = new fragment_profile();
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
        code="+91";
        HashMap<String, String> mymap = new HashMap<>();
        mymap.put ("Afghanistan", "+93");
                mymap.put("Albania", "+355");
                mymap.put("Algeria", "+213");
                mymap.put("American Samoa", "+1 (684)");
                mymap.put("Andorra", "+376");
                mymap.put("Angola", "+244");
                mymap.put("Argentina", "+54");
                mymap.put("Armenia", "+374");
                mymap.put("Aruba", "+297");
                mymap.put("Australia", "+61");
                mymap.put("Australian Capital Territory", "+672");
                mymap.put("Austria", "+43");
                mymap.put("Azerbaijan", "+994");
                mymap.put("Bahrain", "+973");
                mymap.put("Bangladesh", "+880");
                mymap.put("Bhutan", "+975");
                mymap.put("Bolivia", "+591");
                mymap.put("Botswana", "+267");
                mymap.put("Brazil", "+5");
                mymap.put("Brunei", "+673");
                mymap.put("Bulgaria", "+359");
                mymap.put("Burkina Faso", "+226");
                mymap.put("Burma", "+9");
                mymap.put("Burundi", "+257");
                mymap.put("Cambodia", "+855");
                mymap.put("Cameroon", "+237");
                mymap.put("Canada", "+1");
                mymap.put("Cape Verde", "+238");
                mymap.put("Chad", "+235");
                mymap.put("Chile", "+5");
                mymap.put("China", "+8");
                mymap.put("Colombia", "+57");
                mymap.put("Comoros", "+269");
                mymap.put("Congo, Democratic Republic of the", "+243");
                mymap.put("Congo, Republic of the", "+242");
                mymap.put("Cook Islands", "682");
                mymap.put("Costa Rica", "+506");
                mymap.put("Croatia", "+385");
                mymap.put("Cuba", "+53");
                mymap.put("Cyprus", "+357");
                mymap.put("Czech Republic", "+420");
                mymap.put("Denmark", "+45");
                mymap.put("Diego Garcia", "+246");
                mymap.put("Egypt", "+20");
                mymap.put("El Salvador", "+503");
                mymap.put("Equatorial Guinea", "+240");
                mymap.put("Eritrea", "+291");
                mymap.put("Estonia", "+372");
                mymap.put("Ethiopia", "+251");
                mymap.put("Faroe Islands", "+298");
                mymap.put("Fiji", "+679");
                mymap.put("Finland", "+358");
                mymap.put("France", "+33");
                mymap.put("French Guiana", "+594");
                mymap.put("French Polynesia", "+689");
                mymap.put("Gabon", "+241");
                mymap.put("Gambia, The", "+220");
                mymap.put("Georgia", "+995");
                mymap.put("Germany", "+49");
                mymap.put("Ghana", "+233");
                mymap.put("Gibraltar	", "+350");
                mymap.put("Greece", "+	30");
                mymap.put("Greenland", "+299");
                mymap.put("Guadeloupe", "+590");
                mymap.put("Guatemala", "+502");
                mymap.put("Guinea", "+224");
                mymap.put("Guyana", "+592");
                mymap.put("Haiti", "+509");
                mymap.put("Honduras", "+504");
                mymap.put("Hong Kong", "+852");
                mymap.put("Hungary", "+	36");
                mymap.put("Iceland", "+354");
                mymap.put("India", "+91");
                mymap.put("Indonesia", "+	62");
                mymap.put("Iran", "+98");
                mymap.put("Iraq", "+964");
                mymap.put("Ireland", "+353");
                mymap.put("Israel", "+972");
                mymap.put("Italy", "+	39");
                mymap.put("Japan", "+	81");
                mymap.put("Jordan", "+962");
                mymap.put("Kazakhsta", "+7");
                mymap.put("Kenya", "+254");
                mymap.put("Kiribati", "+686");
                mymap.put("Korea, North", "+850");
                mymap.put("Korea, South", "+82");
                mymap.put("Kuwait", "+965");
                mymap.put("Kyrgyzstan", "+996");
                mymap.put("Laos", "+856");
                mymap.put("Latvia", "+371");
                mymap.put("Lebanon", "+961");
                mymap.put("Lesotho", "+266");
                mymap.put("Liberia", "+231");
                mymap.put("Libya", "+218");
                mymap.put("Liechtenstein", "+423");
                mymap.put("Lithuania", "+370");
                mymap.put("Luxembourg", "+352");
                mymap.put("Macau", "+853");
                mymap.put("Madagascar", "+261");
                mymap.put("Malawi", "+265");
                mymap.put("Malaysia", "+60");
                mymap.put("Maldives", "+960");
                mymap.put("Mali", "+223");
                mymap.put("Malta", "+356");
                mymap.put("Martinique", "+596");
                mymap.put("Mauritania", "+222");
                mymap.put("Mauritius", "+230");
                mymap.put("Mayotte", "+269");
                mymap.put("Mexico", "+52");
                mymap.put("Moldova", "+373");
                mymap.put("Monaco", "+377");
                mymap.put("Mongolia", "+976");
                mymap.put("Morocco", "+212");
                mymap.put("Mozambique", "+258");
                mymap.put("Namibia", "+264");
                mymap.put("Nauru", "+674");
                mymap.put("Nepal", "+977");
                mymap.put("Netherlands", "+31");
                mymap.put("Netherlands Antilles", "+599");
                mymap.put("New Caledonia", "+687");
                mymap.put("New Zealand", "+64");
                mymap.put("Nicaragua", "+505");
                mymap.put("Niger", "+227");
                mymap.put("Nigeria", "+234");
                mymap.put("Niue", "+683");
                mymap.put("Norway", "+47");
                mymap.put("Oman", "+968");
                mymap.put("Pakistan", "+92");
                mymap.put("Palau", "+680");
                mymap.put("Panama", "+507");
                mymap.put("Paraguay", "+595");
                mymap.put("Peru", "+51");
                mymap.put("Philippines", "+63");
                mymap.put("Poland", "+48");
                mymap.put("Portugal", "+351");
                mymap.put("Qatar", "+974");
                mymap.put("Reserved", "+970");
                mymap.put("Romania", "+40");
                mymap.put("Russia", "+7");
                mymap.put("Rwanda", "+250");
                mymap.put("Samoa", "+685");
                mymap.put("San Marino", "+378");
                mymap.put("Saudi Arabia", "+966");
                mymap.put("Senegal", "+221");
                mymap.put("Serbia", "+381");
                mymap.put("Seychelles", "+248");
                mymap.put("Sierra Leone", "+232");
                mymap.put("Singapore", "+65");
                mymap.put("Slovakia", "+421");
                mymap.put("Slovenia", "+386");
                mymap.put("Solomon Islands", "+677");
                mymap.put("Somalia", "+252");
                mymap.put("South Africa", "+27");
                mymap.put("Spain", "+34");
                mymap.put("Sri Lanka", "+94");
                mymap.put("Sudan", "+249");
                mymap.put("Suriname", "+597");
                mymap.put("Swaziland", "+268");
                mymap.put("Sweden", "+46");
                mymap.put("Switzerland", "+41");
                mymap.put("Syria", "+963");
                mymap.put("Taiwan", "+886");
                mymap.put("Tajikistan", "+992");
                mymap.put("Tanzania", "+255");
                mymap.put("Thailand", "+66");
                mymap.put("Togo", "+228");
                mymap.put("Tokelau", "+690");
                mymap.put("Tonga", "+676");
                mymap.put("Turkey", "+90");
                mymap.put("Turkmenistan", "+993");
                mymap.put("Tuvalu", "+688");
                mymap.put("Uganda", "+256");
                mymap.put("Ukraine", "+380");
                mymap.put("United Kingdom", "+44");
                mymap.put("United States", "+1");
                mymap.put("Uzbekistan", "+998");
                mymap.put("Vanuatu", "+678");
                mymap.put("Venezuela", "+58");
                mymap.put("Vietnam", "+84");
                mymap.put("Yemen", "+967");
                mymap.put("Zambia", "+260");
                mymap.put("Zimbabwe", "+263");

        /*Map<String,String> mymap2=new HashMap<>();
        mymap2.put("+91","India");
        "+92","Pakistan");
        "+1","United States"
        "+44","United Kingdom"*/

        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_profile, container, false);
        preferences=getActivity().getSharedPreferences("Local_Details",Context.MODE_PRIVATE);
        editor=preferences.edit();
        String get_name=preferences.getString("username_key","Akshit");
        String get_phone=preferences.getString("userphone_in_sharedpreference","9465675515");
        int get_age=preferences.getInt("Age_user",20);
        String get_blood_group=preferences.getString("Bloodgroup_user","A+");
        TextView name=myview.findViewById(R.id.tv_name);
        TextView age=myview.findViewById(R.id.tv_age);
        TextView bloodgroup=myview.findViewById(R.id.tv_blood);
        TextView contact=myview.findViewById(R.id.tv_phone);
        TextView country=myview.findViewById(R.id.tv_address);

        name.setText(get_name);
        contact.setText(get_phone);
        age.setText(get_age+" ");
        bloodgroup.setText(get_blood_group);
        for(Map.Entry<String, String> entry: mymap.entrySet()) {

            // if give value is equal to value from entry
            // print the corresponding key
            if(entry.getValue().equals(code)) {
                //Toast.makeText(getActivity(), ""+ entry.getKey(), Toast.LENGTH_SHORT).show();
                country.setText(entry.getKey());
                break;
            }
        }


        return myview;


    }
}