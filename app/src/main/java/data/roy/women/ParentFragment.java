package data.roy.women;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ParentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ImageView send;
    ImageView contact;
    ImageView location;
    ImageView about;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ParentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParentFragment newInstance(String param1, String param2) {
        ParentFragment fragment = new ParentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_parent, container, false);
        send=root.findViewById(R.id.send);
        contact=root.findViewById(R.id.contact);
        about=root.findViewById(R.id.about);
        send.setOnClickListener(View->{
            Intent intent=new Intent(requireContext(), ShareActivity.class);
            startActivity(intent);
        });
        contact.setOnClickListener(View->{
            Intent intent=new Intent(requireContext(), VehicleActivity.class);
            startActivity(intent);
        });
about.setOnClickListener(View->{
    Intent intent=new Intent(requireContext(), About.class);
    startActivity(intent);
});
        return root;
    }
}