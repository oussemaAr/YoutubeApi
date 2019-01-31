package com.android.youtubeapi.model;

import java.io.Serializable;

public class PassedData implements Serializable {

    private String thumbnail;
    private String title;
    private String videoPublishedAt;
    private String description;
    private String duration;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPublishedAt() {
        return videoPublishedAt;
    }

    public void setVideoPublishedAt(String videoPublishedAt) {
        this.videoPublishedAt = videoPublishedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "PassedData{" +
                "thumbnail='" + thumbnail + '\'' +
                ", title='" + title + '\'' +
                ", videoPublishedAt='" + videoPublishedAt + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
