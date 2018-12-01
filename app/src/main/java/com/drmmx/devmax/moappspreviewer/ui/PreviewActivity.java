package com.drmmx.devmax.moappspreviewer.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.drmmx.devmax.moappspreviewer.R;
import com.drmmx.devmax.moappspreviewer.adapter.PreviewAdapter;
import com.drmmx.devmax.moappspreviewer.model.PreviewData;
import com.drmmx.devmax.moappspreviewer.model.PreviewResponse;
import com.drmmx.devmax.moappspreviewer.retrofit.MoAppsAPI;
import com.drmmx.devmax.moappspreviewer.util.Constants;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.drmmx.devmax.moappspreviewer.util.Constants.TOKEN_RESPONSE;

public class PreviewActivity extends AppCompatActivity {

    private String tokenResponse;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        tokenResponse = getIntent().getStringExtra(TOKEN_RESPONSE);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getPreviewRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreviewRequest();
    }

    private void getPreviewRequest() {
        if (tokenResponse != null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_PREVIEW_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MoAppsAPI service = retrofit.create(MoAppsAPI.class);

            Call<PreviewResponse> call = service.getStringPreview(new PreviewData(0, 1000, 0, tokenResponse));
            call.enqueue(new Callback<PreviewResponse>() {
                @Override
                public void onResponse(@NonNull Call<PreviewResponse> call, @NonNull Response<PreviewResponse> response) {
                    if (response.isSuccessful()) {
                        PreviewAdapter adapter = new PreviewAdapter(PreviewActivity.this, response.body().getData());
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PreviewResponse> call, @NonNull Throwable t) {
                    Toast.makeText(PreviewActivity.this, "Ошибка" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Пожалуйста, авторизируйтесь в приложении", Toast.LENGTH_SHORT).show();
        }
    }
}
