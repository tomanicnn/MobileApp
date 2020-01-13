package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.Food;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FoodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodFragment extends Fragment {

    private static final String ARG_REST = "rest";

    private String rest;

    private OnFragmentInteractionListener mListener;

    public FoodFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FoodFragment newInstance(String rest) {
        FoodFragment fragment = new FoodFragment();
        Bundle args = new Bundle();
        args.putString(ARG_REST, rest);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rest = getArguments().getString(ARG_REST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_food, container, false);

        LinearLayout listOfChoose = v.findViewById(R.id.foodLayoutView);
        fillingWithDatas(rest, listOfChoose);
        return v;
    }

    @SuppressLint("HandlerLeak")
    private void fillingWithDatas(String rest, final LinearLayout linearLayout){
        linearLayout.removeAllViews();
        if(rest != null) {
            ApiHandler.getJSON("http://10.0.2.2:5000/json/" + rest, new ReadDataHandler() {
                @Override
                public void handleMessage(Message msg) {
                    String odgovor = getJson();

                    try{
                        JSONArray array = new JSONArray(odgovor);
                        final ArrayList<Food> foods = Food.parseJSONArray(array);

                        for(final Food f : foods){

                            final LinearLayout item = (LinearLayout)getLayoutInflater().inflate(R.layout.food, null);
                            ImageView icon = item.findViewById(R.id.foodImg);
                            icon.setImageResource(R.drawable.food1);
                            TextView foodName = item.findViewById(R.id.foodName);
                            TextView foodPrice = item.findViewById(R.id.foodPrice);
                            TextView foodDesc = item.findViewById(R.id.foodDesc);

                            foodName.setText("Food name : " + f.getName());
                            foodPrice.setText("Food price : " + f.getPrice());
                            foodDesc.setText("About meal : " + f.getDesc());
                            foodName.setKeyListener(null);
                            foodPrice.setKeyListener(null);
                            foodDesc.setKeyListener(null);

                            item.setTag(f.getId());

                            linearLayout.addView(item);
                        }
                    }
                    catch(Exception e){

                    }
                }

            });
        }
        else{
            TextView msg = new TextView(getContext());
            msg.setText("Choose restourant");
            msg.setTextSize(18);
            linearLayout.addView(msg);
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
