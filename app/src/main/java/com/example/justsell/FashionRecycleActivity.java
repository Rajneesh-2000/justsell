
package com.example.justsell;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.DividerItemDecoration;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.storage.FirebaseStorage;

        import java.util.ArrayList;

public class FashionRecycleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mdatabaseref;

    MainAdapter mainAdapter;
    ArrayList<FurnitureRecyclelass> recycleList;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fashion_recycle);

        //mstorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mdatabaseref= FirebaseDatabase.getInstance().getReference("uploads");
        recyclerView=findViewById(R.id.recyclercontact);

        firebaseDatabase =FirebaseDatabase.getInstance();
        mdatabaseref =firebaseDatabase.getReference("fashion");
        // database=FirebaseDatabase.getInstance().getReference("furniture");
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recycleList=new ArrayList<>();

        //firebaseDatabase=FirebaseDatabase.getInstance();
        // mainAdapter=new MainAdapter(recycleList, this);
        //   recyclerView.setAdapter(mainAdapter);
        MainAdapter recyclerAdapter=new MainAdapter(recycleList,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
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
                recyclerAdapter.notifyDataSetChanged();;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}