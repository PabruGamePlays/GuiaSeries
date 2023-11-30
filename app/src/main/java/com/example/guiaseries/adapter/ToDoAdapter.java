package com.example.guiaseries.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guiaseries.AddNewTask;
import com.example.guiaseries.ListaGuia;
import com.example.guiaseries.R;
import com.example.guiaseries.model.ToDoModel;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder>{

    private List<ToDoModel> todoList;
    private ListaGuia listaGuia;
    private FirebaseFirestore firestore;

    public ToDoAdapter(ListaGuia lista, List<ToDoModel> todoList){
        this.todoList = todoList;
        listaGuia = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(listaGuia).inflate(R.layout.each_task, parent, false);
        firestore= FirebaseFirestore.getInstance();

        return new MyViewHolder(view);
    }

    public void deleteTask(int position){
        ToDoModel toDoModel = todoList.get(position);
        firestore.collection("task").document(toDoModel.TaskId).delete();
        todoList.remove(position);
        notifyItemRemoved(position);
    }

    public Context getContext(){
        return  listaGuia;
    }

    public void editTask(int position){
        ToDoModel toDoModel = todoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("task", toDoModel.getTask());
        bundle.putString("ep", toDoModel.getEp());
        bundle.putString("ver", toDoModel.getVer());
        bundle.putString("id", toDoModel.TaskId);

        AddNewTask addNewTask = new AddNewTask();
        addNewTask.setArguments(bundle);
        addNewTask.show(listaGuia.getSupportFragmentManager(), addNewTask.getTag());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ToDoModel toDoModel = todoList.get(position);
        holder.mEp.setText("assistir ao ep " + toDoModel.getEp());
        holder.mVer.setText("Disponível em " + toDoModel.getVer());




    }

    private boolean toBoolean(int status){
        return status !=0;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        EditText mEp;
        EditText mVer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mEp = itemView.findViewById(R.id.ep_serie);
            mVer = itemView.findViewById(R.id.onde_ver);


        }
    }
}
