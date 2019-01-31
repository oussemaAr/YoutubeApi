package com.android.youtubeapi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.youtubeapi.adapters.VideosAdapter;
import com.android.youtubeapi.model.ChannelData.Item;
import com.android.youtubeapi.model.ChannelData.Response;
import com.android.youtubeapi.model.PassedData;
import com.android.youtubeapi.model.videoData.VideoResponse;
import com.android.youtubeapi.utils.MyDividerItemDecoration;
import com.android.youtubeapi.utils.OnVideoItemClickListener;
import com.android.youtubeapi.utils.YoutubeAPI;
import com.android.youtubeapi.utils.YoutubeInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements OnVideoItemClickListener {
    private static final String TAG = "MainActivity";
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private YoutubeAPI youtubeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getDate();
    }


    private void getDate() {
        YoutubeInstance.getInstance().getYoutubeAPI().getChannelDetail().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body() != null) {
                    enableView();
                    initAdapter(response.body().getItems());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }

    private void initAdapter(List<Item> items) {
        Log.e(TAG, "initAdapter: " + items.size());
        VideosAdapter adapter = new VideosAdapter(items, this);
        adapter.setOnVideoItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(adapter);
    }

    private void enableView() {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void onVideoItemClick(final Item item) {
        Log.e(TAG, "onVideoItemClick: "+item.getContentDetails().getVideoId());
        YoutubeInstance.getInstance().getYoutubeAPI().getVideoDetail(item.getContentDetails().getVideoId()).enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, retrofit2.Response<VideoResponse> response) {
                if (response.body() != null) {
                    PassedData data = new PassedData();
                    data.setDuration(response.body().getItems().get(0).getContentDetails().getDuration());
                    data.setDescription(item.getSnippet().getDescription());
                    data.setTitle(item.getSnippet().getTitle());
                    data.setVideoPublishedAt(item.getSnippet().getPublishedAt());
                    data.setThumbnail(item.getSnippet().getThumbnails().getMedium().getUrl());
                    Intent in = new Intent(MainActivity.this, DetailsActivity.class);
                    in.putExtra("data", data);
                    startActivity(in);
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {

            }
        });
    }
}
