package com.well.hung.hangman;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CategorySelector extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_selector);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_category_selector, menu);
        return true;
    }
}
