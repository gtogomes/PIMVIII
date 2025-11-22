package br.com.pimviii;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.pimviii.adapters.ContentAdapter;
import br.com.pimviii.models.Content;

public class PlaylistsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlists);

        recyclerView = findViewById(R.id.recyclerPlaylists);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ContentAdapter(mockPlaylists(), new ContentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Content item) {
                // Abrir detalhes da playlist (mesma tela de detalhe)
                startActivity(DetailActivity.createIntent(
                        PlaylistsActivity.this,
                        item.getTitle(),
                        item.getDescription()
                ));
            }

            @Override
            public void onShareClick(Content item) {
                Intent send = new Intent(Intent.ACTION_SEND);
                send.setType("text/plain");
                String shareText = item.getTitle() + " — " + item.getDescription();
                send.putExtra(Intent.EXTRA_SUBJECT, item.getTitle());
                send.putExtra(Intent.EXTRA_TEXT, shareText);
                startActivity(Intent.createChooser(send, "Compartilhar playlist via"));
            }
        });

        recyclerView.setAdapter(adapter);

        // Botão voltar
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }

    // Mock — lista de playlists simuladas
    private List<Content> mockPlaylists() {
        List<Content> list = new ArrayList<>();
        list.add(new Content(1, "Playlist de Vídeos", "Playlist", "Coleção com 10 vídeos recentes."));
        list.add(new Content(2, "Podcast da Semana", "Playlist", "Lista com 5 episódios novos."));
        list.add(new Content(3, "Favoritos", "Playlist", "Conteúdos que você marcou como favorito."));
        return list;
    }
}
