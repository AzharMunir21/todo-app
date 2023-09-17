package com.example.todo_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.databinding.EachRvBinding;

public class RVAdapter extends ListAdapter<Note,RVAdapter.ViewHolder> {
 public  RVAdapter(){
     super((callBack));
 }
private static  final DiffUtil.ItemCallback<Note> callBack=new DiffUtil.ItemCallback<Note>(){

    @Override
    public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getId()== newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
        return oldItem.getTitle().equals(newItem.getTitle())
                &&oldItem.getDisc().equals(newItem.getDisc());
    }
};
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.each_rv,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     Note note=getItem(position);
        holder.binding.titleRv.setText(note.getTitle());
        holder.binding.discRv.setText(note.getTitle());

    }
  public Note getNote( int position){
     return  getItem(position);

  }
    public  class ViewHolder extends RecyclerView.ViewHolder{
             EachRvBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=EachRvBinding.bind(itemView);
        }
    }




}
