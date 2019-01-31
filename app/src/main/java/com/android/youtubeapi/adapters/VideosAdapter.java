package com.android.youtubeapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.youtubeapi.R;
import com.android.youtubeapi.model.ChannelData.Item;
import com.android.youtubeapi.utils.OnVideoItemClickListener;
import com.android.youtubeapi.viewHolder.VideoViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private List<Item> itemArrayList;
    private Context context;
    private OnVideoItemClickListener onVideoItemClickListener;

    public void setOnVideoItemClickListener(OnVideoItemClickListener onVideoItemClickListener) {
        this.onVideoItemClickListener = onVideoItemClickListener;
    }

    public VideosAdapter(List<Item> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        return new VideoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        final Item item = itemArrayList.get(position);
        holder.title.setText(item.getSnippet().getTitle());
        Picasso.get().load(item.getSnippet().getThumbnails().getMedium().getUrl())
                .placeholder(R.drawable.ic_image_black_24dp)
                .into(holder.thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onVideoItemClickListener.onVideoItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
}
