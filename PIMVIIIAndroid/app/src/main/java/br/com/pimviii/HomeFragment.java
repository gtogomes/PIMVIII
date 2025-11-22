package br.com.pimviii;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.pimviii.models.Content;

public class HomeFragment extends Fragment implements ContentAdapter.OnShareClickListener {

    private RecyclerView recycler;
    private ContentAdapter adapter;

    public HomeFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recycler = view.findViewById(R.id.recyclerContent);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new ContentAdapter(mockContents(), this);
        recycler.setAdapter(adapter);

        return view;
    }

    private List<Content> mockContents() {
        List<Content> list = new ArrayList<>();
        list.add(new Content(1, "Documentário: Vida em Rede", "Vídeo", "Um documentário sobre plataformas."));
        list.add(new Content(2, "Top Hits 2025", "Áudio", "Playlist com as músicas do momento."));
        list.add(new Content(3, "Podcast: DevTalks", "Podcast", "Discussões sobre tecnologia."));
        return list;
    }

    @Override
    public void onShareClicked(Content content) {
        Intent send = new Intent(Intent.ACTION_SEND);
        send.setType("text/plain");

        String shareText = content.getTitle() + " — " + content.getDescription() + "\nAssista em StreamApp!";
        send.putExtra(Intent.EXTRA_SUBJECT, content.getTitle());
        send.putExtra(Intent.EXTRA_TEXT, shareText);

        startActivity(Intent.createChooser(send, "Compartilhar conteúdo via"));
    }
}
