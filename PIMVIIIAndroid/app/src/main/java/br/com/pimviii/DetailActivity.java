package br.com.pimviii;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_DESC = "extra_desc";

    public static Intent createIntent(Context ctx, String title, String desc) {
        Intent i = new Intent(ctx, DetailActivity.class);
        i.putExtra(EXTRA_TITLE, title);
        i.putExtra(EXTRA_DESC, desc);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvTitle = findViewById(R.id.detailTitle);
        TextView tvDesc = findViewById(R.id.detailDesc);

        tvTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
        tvDesc.setText(getIntent().getStringExtra(EXTRA_DESC));
    }
}
