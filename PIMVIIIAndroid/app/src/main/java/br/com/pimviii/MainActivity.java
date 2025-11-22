package br.com.pimviii;

import android.os.Bundle;
import br.com.pimviii.HomeFragment;
import br.com.pimviii.SearchFragment;
import br.com.pimviii.ProfileFragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav = findViewById(R.id.bottom_navigation);

        // Fragment inicial
        replaceFragment(new HomeFragment());

        nav.setOnItemSelectedListener(item -> {
            Fragment selected = null;

            if (item.getItemId() == R.id.nav_home) {
                selected = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_search) {
                selected = new SearchFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selected = new ProfileFragment();
            }

            if (selected != null) {
                replaceFragment(selected);
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
