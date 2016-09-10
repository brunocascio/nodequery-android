package com.nodequery.brunocascio.nodequery;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by krypton on 25/07/16.
 */
public final class NodeQueryService {

    static final String API_URL = "https://nodequery.com/api/";
    private static NodeQueryService instance = null;
    private Retrofit retrofit;
    private NodeQuery nodeQuery;

    public static NodeQueryService getInstance() {
        if ( instance == null ) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(NodeQueryService.API_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            NodeQuery nq = retrofit.create(NodeQueryService.NodeQuery.class);

            instance = new NodeQueryService(retrofit, nq);
        }

        return instance;
    }

    private NodeQueryService(Retrofit retrofit, NodeQuery nq) {
        this.retrofit = retrofit;
        this.nodeQuery = nq;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public NodeQuery getNodeQuery() {
        return nodeQuery;
    }

    static class NodeQueryListServersResponse {
        public String status;
        public List<List<Server>> data;
    }

    static class Server {
        public long id;
        public String name;
        public String status;
        public String availability;
        public String load_average;
        public String ipv4;
        public String ipv6;
        public long ram_total;
        public long ram_usage;
        public long disk_total;
        public long disk_usage;
        public long update_time;
        public int load_percent;
        public long current_rx;
        public long current_tx;
    }

    interface NodeQuery {

        @GET("servers?api_key=YOUR_API_SECRET_TOKEN")
        Call<NodeQueryListServersResponse> servers();
    }

}
