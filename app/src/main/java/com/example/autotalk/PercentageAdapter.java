package com.example.autotalk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PercentageAdapter extends RecyclerView.Adapter<PercentageAdapter.PercentageViewHolder> {

    private List<String> percentages;

    public PercentageAdapter(List<String> percentages) {
        this.percentages = percentages;
    }

    @NonNull
    @Override
    public PercentageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new PercentageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PercentageViewHolder holder, int position) {
        holder.percentageTextView.setText(percentages.get(position));
    }

    @Override
    public int getItemCount() {
        return percentages.size();
    }

    public static class PercentageViewHolder extends RecyclerView.ViewHolder {

        public TextView percentageTextView;

        public PercentageViewHolder(View itemView) {
            super(itemView);
            percentageTextView = itemView.findViewById(R.id.title);
        }
    }
}
