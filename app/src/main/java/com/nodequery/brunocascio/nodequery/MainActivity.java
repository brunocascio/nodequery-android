package com.nodequery.brunocascio.nodequery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class MainActivity extends AppCompatActivity
        implements Callback<NodeQueryService.NodeQueryListServersResponse> {

    private RecyclerView recList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList = (RecyclerView) findViewById(R.id.serverList);
        recList.setLayoutManager(llm);

        this.fetchServers();
    }

    private void renderServers(List<NodeQueryService.Server> list) {
        recList.setAdapter(new ServersListAdapter(list));
        recList.getAdapter().notifyDataSetChanged();
    }

    private void fetchServers() {
        progressBar.setIndeterminate(true);
        NodeQueryService
                .getInstance()
                .getNodeQuery()
                .servers()
                .enqueue(this);
    }

    @Override
    public void onResponse(Call<NodeQueryService.NodeQueryListServersResponse> call,
                           Response<NodeQueryService.NodeQueryListServersResponse> response) {
        Log.d("Response OK", response.body().data.get(0).get(0).name);
        this.renderServers(response.body().data.get(0));
        progressBar.setIndeterminate(false);
    }

    @Override
    public void onFailure(Call<NodeQueryService.NodeQueryListServersResponse> call, Throwable t) {
        Log.e("Response FAIL", t.getMessage(), t);
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT);
        progressBar.setIndeterminate(false);
    }
}
