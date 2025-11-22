package br.com.pimviii;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.pimviii.models.Content;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    public interface OnShareClickListener {
        void onShareClicked(Content content);
    }

    private final List<Content> list;
    private final OnShareClickListener listener;

    public ContentAdapter(List<Content> list, OnShareClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_content_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {
        Content c = list.get(position);

        holder.title.setText(c.getTitle());
        holder.type.setText(c.getType());
        holder.desc.setText(c.getDescription());

        holder.shareBtn.setOnClickListener(v -> {
            if (listener != null) listener.onShareClicked(c);
        });

        // Clique no card para abrir a tela de detalhes
        holder.itemView.setOnClickListener(v ->
                holder.itemView.getContext().startActivity(
                        DetailActivity.createIntent(
                                holder.itemView.getContext(),
                                c.getTitle(),
                                c.getDescription()
                        )
                )
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, type, desc;
        ImageButton shareBtn;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.contentTitle);
            type = itemView.findViewById(R.id.contentType);
            desc = itemView.findViewById(R.id.contentDesc);
            shareBtn = itemView.findViewById(R.id.btnShare);
        }
    }
}
