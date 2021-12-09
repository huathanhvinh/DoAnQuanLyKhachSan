package com.example.doanquanlykhachsan.nhanvien_thungan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.doanquanlykhachsan.R;
import com.example.doanquanlykhachsan.adapter.*;
import com.example.doanquanlykhachsan.helpers.StaticConfig;
import com.example.doanquanlykhachsan.model.DoiPhong;
import com.example.doanquanlykhachsan.model.PhongDaDat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NVTN_ThongBao_DoiPhong#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NVTN_ThongBao_DoiPhong extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private View view;
    private static ArrayList<PhongDaDat> data = new ArrayList<>();
    private static custom_NVTN_thongbao_doiphong apdater;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NVTN_ThongBao_DoiPhong() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NVTN_ThongBao_DatPhong.
     */
    // TODO: Rename and change types and number of parameters
    public static NVTN_ThongBao_DoiPhong newInstance(String param1, String param2) {
        NVTN_ThongBao_DoiPhong fragment = new NVTN_ThongBao_DoiPhong();
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
        view = inflater.inflate(R.layout.fragment_n_v_t_n__thong_bao__dat_phong, container, false);
        // Inflate the layout for this fragment
        setControl();
        return view;
    }

    private void setControl() {
        listView = view.findViewById(R.id.list);
        apdater = new custom_NVTN_thongbao_doiphong(getContext(), R.layout.listview_nvtn_thongbao_datphong, data);
        listView.setAdapter(apdater);
        khoitao();
    }

    public static void khoitao() {
        StaticConfig.mDoiPhong.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    DoiPhong dp = ds.getValue(DoiPhong.class);
                    StaticConfig.mRoomRented.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                PhongDaDat da = ds.getValue(PhongDaDat.class);
                                if(dp.getMaPhieu().equals(da.getMaFB())&&da.getXacnhan().equals("Đã Xác Nhận")){
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
                apdater.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}