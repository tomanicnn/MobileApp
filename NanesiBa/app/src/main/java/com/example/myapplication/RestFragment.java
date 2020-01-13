package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myapplication.apis.ApiHandler;
import com.example.myapplication.apis.ReadDataHandler;
import com.example.myapplication.model.Restaurant;
import org.json.JSONArray;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RestFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RestFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;

    public RestFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RestFragment newInstance() {
        RestFragment fragment = new RestFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = (View) inflater.inflate(R.layout.fragment_rest, container, false);
        ApiHandler.getJSON("http://10.0.2.2:5000/restourans.json", new ReadDataHandler(){
            @Override
            public void handleMessage(Message msg) {
                String odgovor = getJson();

                try {
                    JSONArray array = new JSONArray(odgovor);
                    //odgovor spakujemo u JSONArray, i onda parsiramo da bismo dobili linked list
                    ArrayList<Restaurant> restaurants = Restaurant.parseJSONArray(array);
                    //postavljamo dobijeni povratni tekst kao tekst labele (ručno formatirano)

                    LinearLayout listRest = (LinearLayout) v.findViewById(R.id.restLayout);

                    for (final Restaurant s : restaurants) {

                        LinearLayout item = (LinearLayout) inflater.inflate(R.layout.rest, null);

                        TextView restName = item.findViewById(R.id.restName);
                        restName.setKeyListener(null);
                        restName.setText("Name : " + s.getName());

                        TextView restAddress = item.findViewById(R.id.restAddress);
                        restAddress.setKeyListener(null);
                        restAddress.setText("Address : " + s.getAddress());

                        TextView restPhone = item.findViewById(R.id.restPhone);
                        restPhone.setKeyListener(null);
                        restPhone.setText("Phone : " + s.getPhone());

                        TextView restMinWait = item.findViewById(R.id.restMinWait);
                        restMinWait.setKeyListener(null);
                        restMinWait.setText("Min Wait : " + s.getMinWait());


                        item.setTag(s.getId());
                        item.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mListener.odabraniRestoran((s.getId()));
                            }
                        });

                        listRest.addView(item);
                    }

                } catch (Exception e) {

                }
            }
        });

        return v;
    }

    public void onButtonPressed(Uri uri) {

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

    @Override
    public void onClick(View v) {
        String rest = (String)v.getTag();

        if(mListener != null){
            mListener.odabraniRestoran(rest);
        }
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
        void odabraniRestoran(String rest);
    }
}
