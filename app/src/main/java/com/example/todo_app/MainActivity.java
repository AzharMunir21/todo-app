package com.example.todo_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo_app.DataInsertActivity;
import com.example.todo_app.Note;
import com.example.todo_app.NoteViewModel;
import com.example.todo_app.databinding.ActivityDataInsertBinding;
import com.example.todo_app.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
@NonNull
ActivityMainBinding binding;
public NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        noteViewModel= new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.
                        getInstance(this.getApplication())).get(NoteViewModel.class);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataInsertActivity.class);
                intent.putExtra("type","addMode");
                   startActivityForResult(intent, 1);
            }
        });

           binding.Rv.setLayoutManager(new LinearLayoutManager(this));
           binding.Rv.setHasFixedSize(true);
        RVAdapter rvAdapter= new RVAdapter();
           binding.Rv.setAdapter(rvAdapter);
           noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
               @Override
               public void onChanged(List<Note> notes) {
                   rvAdapter.submitList(notes);
               }
           });
           new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
               @Override
               public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                   return false;
               }

               @Override
               public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                   if(direction==ItemTouchHelper.RIGHT){

                       noteViewModel.delete(rvAdapter.getNote(viewHolder.getAdapterPosition()));
                       Toast.makeText(MainActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
                   }else{

                      Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
                      intent.putExtra("type","update");
                    intent.putExtra("title",rvAdapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("disc",rvAdapter.getNote(viewHolder.getAdapterPosition()).getDisc());
                    intent.putExtra("id",rvAdapter.getNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(intent,2);
                       Toast.makeText(MainActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
                   }

               }
           }).attachToRecyclerView(binding.Rv);
    }
    protected  void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            assert data != null;
            String title=data.getStringExtra("title");
            String disc=data.getStringExtra("disc");
            Note note=new Note(title,disc);
            noteViewModel.insert(note);
            Toast.makeText(this,"Note added",Toast.LENGTH_LONG).show();
        }
        else if(requestCode==2){


            String title=data.getStringExtra("title");
            String disc=data.getStringExtra("disc");
            Note note=new Note(title,disc);
            noteViewModel.insert(note);
            note.setId(data.getIntExtra("id",0));
            noteViewModel.update(note);
            Toast.makeText(this,"Note added",Toast.LENGTH_LONG).show();

        }
    }




}