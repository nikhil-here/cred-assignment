package com.assignment.cred;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.cred.Adapter.Adapter_Emi;
import com.assignment.cred.Entity.Emi;

import java.text.NumberFormat;
import java.util.ArrayList;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class MainActivity extends AppCompatActivity implements Adapter_Emi.InterfaceEmi, CircularSeekBar.OnCircularSeekBarChangeListener {



    private TextView tvCredit;
    private RecyclerView rvEmis;
    private MotionLayout motionLayout;
    private ImageView ivCreditContainer;
    private CircularSeekBar circularSeekBar;

    private Adapter_Emi adapter_emi;
    private ArrayList<Emi> pojoEmiArrayList;
    private NumberFormat amountFormat ;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        getEmiPlans();
        setEmiPlans();
    }


    @Override
    public void onEmiSelected(int selectedPlanIndex) {
        //for de-selecting other emi plans
        for (int i = 0; i < pojoEmiArrayList.size(); i++) {
            if (i == selectedPlanIndex) {
                pojoEmiArrayList.get(i).setSelected(true);
                continue;
            }
            pojoEmiArrayList.get(i).setSelected(false);
        }
        //notifying adapter
        adapter_emi.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed() {
        if (motionLayout.getCurrentState() == R.id.secondView)
        {
            // secondView => firstView
            motionLayout.transitionToState(R.id.firstView);

        } else if (motionLayout.getCurrentState() == R.id.thirdView)
        {
            // thirdview => secondview
            motionLayout.transitionToState(R.id.secondView);

        }else {
            // if firstview exit
            super.onBackPressed();
        }
    }


    @Override
    public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
        //formatting float progress to amount ₹ i.e adding commas and grouping
        amountFormat = NumberFormat.getInstance();
        tvCredit.setText("₹"+amountFormat.format(Math.round(progress)));
    }

    @Override
    public void onStopTrackingTouch(CircularSeekBar seekBar) {

    }

    @Override
    public void onStartTrackingTouch(CircularSeekBar seekBar) {

    }

    private void setEmiPlans() {
        rvEmis.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter_emi = new Adapter_Emi(pojoEmiArrayList, this, this);
        rvEmis.setAdapter(adapter_emi);
        rvEmis.hasFixedSize();
    }

    private void getEmiPlans() {
        pojoEmiArrayList = new ArrayList<>();
        pojoEmiArrayList.add(new Emi("₹4,247", "for 12 months", R.color.vintageBlue, true));
        pojoEmiArrayList.add(new Emi("₹5,580", "for 9 months", R.color.vintageBrown, false));
        pojoEmiArrayList.add(new Emi("₹8,247", "for 6 months", R.color.vintageViolet, false));
        pojoEmiArrayList.add(new Emi("₹12,247", "for 4 months", R.color.vintageBlue, false));
    }

    private void initViews() {
        rvEmis = findViewById(R.id.rv_emi);
        motionLayout = findViewById(R.id.root);
        circularSeekBar = findViewById(R.id.cs_credit);
        tvCredit = findViewById(R.id.tv_credit_amount);
        ivCreditContainer = findViewById(R.id.iv_arrow_down_credit);
    }

    private void initListeners() {
        circularSeekBar.setOnSeekBarChangeListener(this);
    }
}