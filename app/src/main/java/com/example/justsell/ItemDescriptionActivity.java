package com.example.justsell;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;

public class ItemDescriptionActivity extends AppCompatActivity {

    TextView singleContact,singledesc,singleLocation,singlePrice,singleHeadline;
    ImageView singleImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_description);

        singledesc=findViewById(R.id.singledesc);
        singleContact=findViewById(R.id.singleContact);
        singleLocation=findViewById(R.id.singleLocation);
        singlePrice=findViewById(R.id.singlePrice);
        singleHeadline=findViewById(R.id.singleHeadline);
        singleImage=findViewById(R.id.singleImage);

        Picasso.get().load(getIntent().getStringExtra("singleImage"))
                .placeholder(R.drawable.imageloading)
                .into(singleImage);

        singleHeadline.setText(getIntent().getStringExtra("singleHeadline"));
        singleContact.setText(getIntent().getStringExtra("singleContact"));
        singleLocation.setText(getIntent().getStringExtra("singleLocation"));
        singlePrice.setText(getIntent().getStringExtra("singlePrice"));
        singledesc.setText(getIntent().getStringExtra("singledesc"));

    }
}