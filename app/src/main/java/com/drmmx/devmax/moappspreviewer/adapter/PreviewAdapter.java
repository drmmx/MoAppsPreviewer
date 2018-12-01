package com.drmmx.devmax.moappspreviewer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.drmmx.devmax.moappspreviewer.R;
import com.drmmx.devmax.moappspreviewer.model.Datum;
import com.drmmx.devmax.moappspreviewer.model.PreviewResponse;
import com.drmmx.devmax.moappspreviewer.ui.WebViewActivity;
import com.drmmx.devmax.moappspreviewer.util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.ViewHolder> {

    private Context context;
    private List<Datum> previewResponse;

    public PreviewAdapter(Context context, List<Datum> previewResponse) {
        this.context = context;
        this.previewResponse = previewResponse;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_preview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        //load picture
        Picasso.get().load(previewResponse.get(i).getApplicationIcoUrl()).into(viewHolder.imageViewAppIco);
        //load text
        viewHolder.textViewAppName.setText(previewResponse.get(i).getApplicationName());
        if (previewResponse.get(i).getIsPayment()) {
            viewHolder.textViewPayment.setText(String.valueOf("Оплачено"));
        } else {
            viewHolder.textViewPayment.setText(String.valueOf("Не оплачено"));
        }
        if (previewResponse.get(i).getApplicationStatus()) {
            viewHolder.textViewPayment.setText(String.valueOf("Завершено"));
            viewHolder.imageViewAppStatus.setImageDrawable(context.getDrawable(R.drawable.icon_zakoncheno));
        } else {
            viewHolder.textViewPayment.setText(String.valueOf("Не завершено"));
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(Constants.WEB_VIEW_URL, previewResponse.get(i).getApplicationUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return previewResponse.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewAppIco;
        ImageView imageViewAppStatus;
        TextView textViewAppName;
        TextView textViewPayment;
        TextView textViewAppStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewAppIco = itemView.findViewById(R.id.imageViewAppIco);
            imageViewAppStatus = itemView.findViewById(R.id.imageViewAppStatus);
            textViewAppName = itemView.findViewById(R.id.textViewAppName);
            textViewPayment = itemView.findViewById(R.id.textViewPayment);
            textViewAppStatus = itemView.findViewById(R.id.textViewAppStatus);
        }
    }
}
