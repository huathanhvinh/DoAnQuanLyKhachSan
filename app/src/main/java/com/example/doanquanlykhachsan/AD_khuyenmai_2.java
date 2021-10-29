package com.example.doanquanlykhachsan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.doanquanlykhachsan.model.Khuyenmai;
import com.example.doanquanlykhachsan.model.Khuyenmai_adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AD_khuyenmai_2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AD_khuyenmai_2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private ArrayList<Khuyenmai> data = new ArrayList<>();
    private Khuyenmai_adapter adapter;
    private ListView listView;

    public AD_khuyenmai_2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AD_khuyenmai_2.
     */
    // TODO: Rename and change types and number of parameters
    public static AD_khuyenmai_2 newInstance(String param1, String param2) {
        AD_khuyenmai_2 fragment = new AD_khuyenmai_2();
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
        view = inflater.inflate(R.layout.fragment_khuyenmai_2, container, false);
        setControl();
        data.clear();
        Khuyenmai km1 = new Khuyenmai("123", "Kỉ niệm ngày thành lập khách sạn", "22 thg 9", "22 thg 10");


        data.add(km1);

        adapter = new Khuyenmai_adapter(getContext(), R.layout.item_khuyen_mai, data);
        listView.setAdapter(adapter);
        return view;
    }

    private void setControl() {
        listView = view.findViewById(R.id.list);
    }
}