package edu.qc.seclass.glm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.Holder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyAdapter extends FirebaseRecyclerAdapter<Model,MyAdapter.MyViewHolder> {
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    ArrayList<Model> mList;
    Context context;

    public MyAdapter(@NonNull FirebaseRecyclerOptions<Model> options){
        super(options);
    }



    @Override
    protected void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull final Model model) {
        holder.type.setText(model.getType());
        holder.name.setText(model.getName());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());
        holder.check.setChecked(model.getCheck());
        System.out.println("Model " + model.getCheck());

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.getCheck() == true){
                    model.setChecked(false);
                }
                else{
                    model.setChecked(true);
                }
                System.out.println("Clicked" + model.getCheck());
                Map<String,Object> map=new HashMap<>();
                map.put("type",model.getType());
                map.put("name",model.getName());
                map.put("date",model.getDate());
                map.put("time",model.getTime());
                map.put("check", model.getCheck());
                FirebaseDatabase.getInstance().getReference().child("User Reminders").child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                        .child(getRef(position).getKey()).updateChildren(map);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.type.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText type=myview.findViewById(R.id.utype);
                final EditText name=myview.findViewById(R.id.uname);
                final EditText date=myview.findViewById(R.id.udate);
                final EditText time=myview.findViewById(R.id.utime);
                Button submit=myview.findViewById(R.id.usubmit);

                type.setText(model.getType());
                name.setText(model.getName());
                date.setText(model.getDate());
                time.setText(model.getTime());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("type",type.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("date",date.getText().toString());
                        map.put("time",time.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("User Reminders").child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
                
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.type.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("User Reminders").child(String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_items, parent, false);
        return new MyViewHolder(v);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView  type, name, date, time;
        AppCompatImageView edit, delete;
        CheckBox check;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            type = itemView.findViewById(R.id.txtType);
            name = itemView.findViewById(R.id.txtName);
            date = itemView.findViewById(R.id.txtDate);
            time = itemView.findViewById(R.id.txtTime);
            edit = itemView.findViewById(R.id.editIcon);
            delete = itemView.findViewById(R.id.deleteIcon);
            check = itemView.findViewById(R.id.checkBox);
        }
    }
}
