package org.btssio.slam.facecast.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.btssio.slam.facecast.Class.ConnexionInternet;
import org.btssio.slam.facecast.Class.Offre;
import org.btssio.slam.facecast.MainActivity;
import org.btssio.slam.facecast.R;
import org.btssio.slam.facecast.Repositories.AccountRepository;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OffresEnCours#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffresEnCours extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView lvOffreEnCour;
    private View view;

    // Relation Offre
    private Offre offre;

    private final String URL = "http://10.0.2.2:3000/rest/offre/enCour/";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public OffresEnCours() {
        // Required empty public constructor
        this.offre = new Offre();
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OffresEnCours.
     */
    // TODO: Rename and change types and number of parameters
    public static OffresEnCours newInstance(String param1, String param2) {
        OffresEnCours fragment = new OffresEnCours();
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
        view = inflater.inflate(R.layout.fragment_offres_en_cours, container, false);
        lvOffreEnCour = (ListView) view.findViewById(R.id.lvOffreEnCour);
        return view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        ConnexionInternet connexionInternet = new ConnexionInternet(getActivity());
        if (!connexionInternet.YaConnexion()) {
            MainActivity.onTermine(getContext());
        } else {
            OffresEnCours offresEnCours = new OffresEnCours();
            AccountRepository arApiKey = new AccountRepository(getContext());
            String urlApiKey = URL + arApiKey.getApikey();
            offresEnCours.offre.sendRequestOffre(urlApiKey, this.getContext(), lvOffreEnCour,0 ,true);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
