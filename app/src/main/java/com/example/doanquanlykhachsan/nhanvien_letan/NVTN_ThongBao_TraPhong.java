package com.example.doanquanlykhachsan.nhanvien_letan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.custom_NVTN_thongbao_datphong;
import com.example.doanquanlykhachsan.adapter.custom_NVTN_thongbao_traPhong;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NVTN_ThongBao_TraPhong#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NVTN_ThongBao_TraPhong extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private View view;
    private ArrayList<PhongDaDat> data = new ArrayList<>();
    private custom_NVTN_thongbao_traPhong apdater;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NVTN_ThongBao_TraPhong() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NVTN_ThongBao_TraPhong.
     */
    // TODO: Rename and change types and number of parameters
    public static NVTN_ThongBao_TraPhong newInstance(String param1, String param2) {
        NVTN_ThongBao_TraPhong fragment = new NVTN_ThongBao_TraPhong();
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
        view = inflater.inflate(R.layout.fragment_n_v_t_n__thong_bao__tra_phong, container, false);
        // Inflate the layout for this fragment
        setControl();
        return view;
    }

    private void setControl() {
        listView = view.findViewById(R.id.list);
        apdater = new custom_NVTN_thongbao_traPhong(getContext(), R.layout.listview_nvtn_thongbao_datphong, data);
        listView.setAdapter(apdater);
        khoitao();
    }
    private void khoitao() {
        StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    PhongDaDat da = ds.getValue(PhongDaDat.class);
                    if (da.getXacnhan().equals("Trả Phòng")) {
                        data.add(da);
                    }
                }
                apdater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}