package com.android.youtubeapi.utils;

import com.android.youtubeapi.model.ChannelData.Response;
import com.android.youtubeapi.model.videoData.VideoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeAPI {

    @GET(Constant.YOUTUBE_URL)
    Call<Response> getChannelDetail();


    @GET(Constant.VIDEO_URL)
    Call<VideoResponse> getVideoDetail(@Query("id") String id);


}
