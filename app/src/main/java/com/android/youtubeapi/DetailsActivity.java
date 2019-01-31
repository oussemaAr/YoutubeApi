package com.android.youtubeapi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.youtubeapi.model.PassedData;
import com.android.youtubeapi.utils.YouTubeDurationUtils;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        PassedData data = (PassedData) getIntent().getSerializableExtra("data");

        Picasso.get().load(data.getThumbnail())
                .placeholder(R.drawable.ic_image_black_24dp)
                .into((ImageView) findViewById(R.id.image_view));

        TextView description = findViewById(R.id.description);
        TextView upload = findViewById(R.id.publish);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(data.getVideoPublishedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        upload.setText(output.format(d));
        TextView duration = findViewById(R.id.duration);
        duration.setText(YouTubeDurationUtils.convertYouTubeDuration(data.getDuration()));
        description.setText(data.getDescription());
        TextView title = findViewById(R.id.title);
        title.setText(data.getTitle());
    }


}
