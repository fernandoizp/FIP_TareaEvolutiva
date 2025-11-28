package com.example.dockedtoolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayDeque;
import java.util.Deque;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnHome, btnBack, btnForward, btnCart;
    private final Deque<String> forwardStack = new ArrayDeque<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View dockedToolbar = findViewById(R.id.docked_toolbar_container);
        btnHome = dockedToolbar.findViewById(R.id.btn_home);
        btnBack = dockedToolbar.findViewById(R.id.btn_back);
        btnForward = dockedToolbar.findViewById(R.id.btn_forward);
        btnCart = dockedToolbar.findViewById(R.id.btn_cart);

        setupNavigation();

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment(), false);
        }
    }

    public void navigateTo(Fragment fragment) {
        forwardStack.clear();
        loadFragment(fragment, true);
    }

    private void setupNavigation() {
        btnHome.setOnClickListener(v -> {
            forwardStack.clear();
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            loadFragment(new HomeFragment(), false);
        });

        btnCart.setOnClickListener(v -> navigateTo(new ShoppingFragment()));

        btnBack.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                Fragment currentFragment = fm.findFragmentById(R.id.fragment_container);
                if (currentFragment != null) {
                    forwardStack.push(currentFragment.getClass().getName());
                }
                fm.popBackStack();
            }
        });

        btnForward.setOnClickListener(v -> {
            if (!forwardStack.isEmpty()) {
                String className = forwardStack.pop();
                try {
                    Fragment forwardFragment = (Fragment) Class.forName(className).getDeclaredConstructor().newInstance();
                    loadFragment(forwardFragment, true); // Add to backstack to allow back navigation
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
        );

        transaction.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }
}
