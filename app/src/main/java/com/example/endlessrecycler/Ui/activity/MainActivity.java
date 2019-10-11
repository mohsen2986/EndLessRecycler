package com.example.endlessrecycler.Ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.endlessrecycler.R;
import com.example.endlessrecycler.Ui.Adapter.RecyclerAdapter;
import com.example.endlessrecycler.Ui.helper.EndLessRecyclerInfo;
import com.example.endlessrecycler.Ui.listener.OnLoadMore;
import com.example.endlessrecycler.Ui.listener.RecyclerListener;
import com.example.endlessrecycler.Ui.listener.RecyclerTouchListener;
import com.example.endlessrecycler.Ui.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> items = new ArrayList<>();
    private RecyclerView recycler;
    private RecyclerAdapter adapter;
    // items info
    private RecyclerListener itemListener;
    private EndLessRecyclerInfo itemsPage = new EndLessRecyclerInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        provideData();
        initRecycler();
    }
    private void initUi(){
        recycler = findViewById(R.id.ac_main_recycler);
    }
    private void initRecycler(){
        recycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerAdapter();
        adapter.addItem(items);
        recycler.setAdapter(adapter);
        itemListener = new RecyclerListener(new OnLoadMore() {
            @Override
            public boolean isLoading() { return itemsPage.isLoading;}

            @Override
            public boolean isLastPage() { return itemsPage.isLastPage;}

            @Override
            public void LoadMore() {
                if (!isLoading()){
//                    callApi();
                    itemsPage.isLoading = true;
                }
            }
        });
        recycler.addOnScrollListener(itemListener);
        // todo fix touch listener
        recycler.addOnItemTouchListener(new RecyclerTouchListener(recycler, this, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(MainActivity.this, "clicked => "+ position, Toast.LENGTH_SHORT).show();
            }
        }));

    }
    private void provideData(){
        for (int i =0 ; i< 30 ; i++)
            items.add(new Item("Mohsen"));
    }
}
