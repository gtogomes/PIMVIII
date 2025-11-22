package br.com.pimviii;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        EditText edt = v.findViewById(R.id.edtSearch);
        v.findViewById(R.id.btnDoSearch).setOnClickListener(view -> {
            String q = edt.getText().toString().trim();
            Toast.makeText(getContext(), "Busca simulada: " + q, Toast.LENGTH_SHORT).show();
        });
        return v;
    }
}
