package gjcm.kxf.mytimelayout;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxf on 2016/12/13.
 */
public class RecyclerActivtiy extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.recycler_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("添加的" + i);
        }
        RecylerAdapter adapter = new RecylerAdapter(list, this);
        recyclerView.setAdapter(adapter);
        Log.i("kxflog", "getChildCount:" + recyclerView.getChildCount());
//        showre();
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                showre();
            }
        });

    }


    private void showre() {


    }
}
