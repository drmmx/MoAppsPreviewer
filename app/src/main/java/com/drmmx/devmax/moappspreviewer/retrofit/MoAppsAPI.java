package com.drmmx.devmax.moappspreviewer.retrofit;

import com.drmmx.devmax.moappspreviewer.model.Datum;
import com.drmmx.devmax.moappspreviewer.model.PreviewData;
import com.drmmx.devmax.moappspreviewer.model.PreviewResponse;
import com.drmmx.devmax.moappspreviewer.model.SignInData;
import com.drmmx.devmax.moappspreviewer.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MoAppsAPI {

    @POST("signIn")
    Call<UserResponse> getStringSignIn(@Body SignInData body);

    @POST("application")
    Call<PreviewResponse> getStringPreview(@Body PreviewData body);
}
