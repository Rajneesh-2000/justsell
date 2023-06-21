package com.example.justsell;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.DividerItemDecoration;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.SearchView;

        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.storage.FirebaseStorage;

        import java.util.ArrayList;

public class BooksRecycleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mdatabaseref;


   MainAdapter mainAdapter;
    ArrayList<FurnitureRecyclelass> recycleList;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_recycle);


       mdatabaseref = FirebaseDatabase.getInstance().getReference("uploads");
        recyclerView = findViewById(R.id.recyclercontact);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mdatabaseref = firebaseDatabase.getReference("Books");

        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleList = new ArrayList<>();


        MainAdapter recyclerAdapter = new MainAdapter(recycleList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);


        mdatabaseref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //here furmitureRecycleclass use
                    FurnitureRecyclelass furnitureRecyclelass = dataSnapshot.getValue(FurnitureRecyclelass.class);

                    recycleList.add(furnitureRecyclelass);

                }
                recyclerAdapter.notifyDataSetChanged();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        /*recyclerView=(RecyclerView) findViewById(R.id.recyclercontact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<FurnitureRecyclelass> options =
                new FirebaseRecyclerOptions.Builder<FurnitureRecyclelass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Books"),FurnitureRecyclelass.class)
                        .build();

        mainAdapter=new bookadapter(options);
        recyclerView.setAdapter(mainAdapter);

    }
 @Override
    protected void onStart(){
        super.onStart();
        mainAdapter.stopListening();
 }
    @Override
    protected void onStop(){
        super.onStop();
        mainAdapter.stopListening();
    }





   @Override
        public boolean onCreateOptionsMenu (Menu menu){

            getMenuInflater().inflate(R.menu.menu_item, menu);
            MenuItem menuItem = menu.findItem(R.id.search_action);

            SearchView searchView = (SearchView) menuItem.getActionView();

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    processearch(s);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    processearch(s);
                    return false;
                }
            });
            return super.onCreateOptionsMenu(menu);
        }

        private void processearch (String s){
            recyclerView = findViewById(R.id.recyclercontact);

            recyclerView.setHasFixedSize(false);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


            FirebaseRecyclerOptions<FurnitureRecyclelass>options =
                    new FirebaseRecyclerOptions.Builder<FurnitureRecyclelass>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Books").orderByChild("name").startAt(s).endAt(s+"\uf8ff"),FurnitureRecyclelass.class)
                            .build();

            mainAdapter=new bookadapter(options);
            mainAdapter.stopListening();
            recyclerView.setAdapter(mainAdapter);

        }*/
    }
}