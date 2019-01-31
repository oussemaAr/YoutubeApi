package com.android.youtubeapi.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.youtubeapi.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView thumbnail;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        thumbnail = itemView.findViewById(R.id.thumbnail);
    }
}
