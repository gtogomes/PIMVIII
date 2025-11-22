package br.com.pimviii.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.pimviii.R;
import br.com.pimviii.models.Content;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ContentViewHolder> {

    private final List<Content> itemList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Content item);
        void onShareClick(Content item);
    }

    public ContentAdapter(List<Content> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content_card, parent, false);
        return new ContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        Content item = itemList.get(position);

        holder.title.setText(item.getTitle());
        holder.type.setText(item.getType());
        holder.desc.setText(item.getDescription());

        holder.card.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(item);
        });

        holder.shareButton.setOnClickListener(v -> {
            if (listener != null) listener.onShareClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView title, type, desc;
        ImageButton shareButton;
        CardView card;

        public ContentViewHolder(@NonNull View itemView) {
            super(itemView);
            card = (CardView) itemView;
            title = itemView.findViewById(R.id.contentTitle);
            type  = itemView.findViewById(R.id.contentType);
            desc  = itemView.findViewById(R.id.contentDesc);
            shareButton = itemView.findViewById(R.id.btnShare);
        }
    }
}
