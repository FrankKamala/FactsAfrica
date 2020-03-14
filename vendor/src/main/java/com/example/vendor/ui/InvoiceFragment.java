package com.example.vendor.ui;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vendor.R;
import com.example.vendor.models.Invoice;

import org.parceler.Parcels;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvoiceFragment extends Fragment implements View.OnClickListener {


    //BindViews
    @BindView(R.id.date_today)
    TextView mPickDate;


    private Invoice mInvoice;
    private String title;
    private int page;
    private View rootView;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //Calender
   private Calendar myCal = Calendar.getInstance();

   private int year =myCal.get(Calendar.YEAR);
   private int month = myCal.get(Calendar.MONTH);
   private int day = myCal.get(Calendar.DAY_OF_WEEK);



    public InvoiceFragment() {
        // Required empty public constructor
    }

    public static InvoiceFragment newInstance(int page, String title){
        InvoiceFragment invoiceFragment = new InvoiceFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        invoiceFragment.setArguments(args);
        return invoiceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;

                String date = month  + "/" + day + "/" + year;


                mPickDate.setText(date);


            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_invoice, container, false);
        ButterKnife.bind(this, rootView);

        mPickDate.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        DatePickerDialog dialog = new DatePickerDialog(
                getActivity(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year,month,day);
        if(v==mPickDate){
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        }

    }

    //date picker


}
