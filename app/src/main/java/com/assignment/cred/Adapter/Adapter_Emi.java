package com.assignment.cred.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.assignment.cred.Entity.Emi;
import com.assignment.cred.R;

import java.util.ArrayList;

public class Adapter_Emi extends RecyclerView.Adapter<Adapter_Emi.ViewHolder> {

    private ArrayList<Emi> pojoEmiArrayList;
    private Emi emi;
    private Context context;
    private InterfaceEmi anInterface;
    private static final String TAG = "Adapter_Emi";


    public Adapter_Emi(ArrayList<Emi> pojoEmiArrayList, Context context, InterfaceEmi anInterface) {
        this.pojoEmiArrayList = pojoEmiArrayList;
        this.context = context;
        this.anInterface = anInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_emi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        emi = pojoEmiArrayList.get(position);
        holder.tvEmiAmount.setText(emi.getAmount());
        holder.tvEmiDuration.setText(emi.getDuration());
        holder.clMain.setCardBackgroundColor(ContextCompat.getColor(context, emi.getColorId()));
        holder.cbEmiSelected.setChecked(false);

        if (emi.getSelected())
        {
            holder.cbEmiSelected.setChecked(true);
        }else{
            holder.cbEmiSelected.setChecked(false);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anInterface.onEmiSelected(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojoEmiArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView clMain;
        private CheckBox cbEmiSelected;
        private TextView tvEmiAmount, tvEmiDuration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clMain = itemView.findViewById(R.id.component_emi_cl_main);
            cbEmiSelected = itemView.findViewById(R.id.cb_emi);
            tvEmiAmount = itemView.findViewById(R.id.tv_emi_amount);
            tvEmiDuration = itemView.findViewById(R.id.tv_emi_duration);

        }
    }



    public interface InterfaceEmi{
        void onEmiSelected(int position);
    }

}
