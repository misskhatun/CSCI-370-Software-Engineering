package edu.qc.seclass.glm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    public Button logOut, addReminder;
    private Toolbar mytoolbar;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference root;
    private MyAdapter adapter;
    private ArrayList<Model> list;
    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog dialog;
    private CardView reminderCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mytoolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle("Reminders");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        root = database.getReference().child("User Reminders").child(String.valueOf(mAuth.getCurrentUser().getUid()));

        logOut = findViewById(R.id.logout_btn);

        addReminder = findViewById(R.id.addReminder_btn);

        logOut.setOnClickListener(this);
        addReminder.setOnClickListener(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(database.getReference().child("User Reminders").child(String.valueOf(mAuth.getCurrentUser().getUid())), Model.class)
                        .build();

        list = new ArrayList<>();
        adapter = new MyAdapter(options);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.logout_btn:
                Log.d("log Out tag","logged Out User");
                mAuth.signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.addReminder_btn:
                Log.d("Adding reminder","Adding reminder");
                startActivity(new Intent(getApplicationContext(),AddReminderActivity.class));
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem item = menu.findItem(R.id.new_search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s){
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(database.getReference().child("User Reminders").child(String.valueOf(mAuth.getCurrentUser().getUid())).orderByChild("type").startAt(s).endAt(s +"\uf8ff"), Model.class)
                        .build();

        adapter = new MyAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}