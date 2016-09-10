package com.nodequery.brunocascio.nodequery;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by krypton on 24/07/16.
 */
public class ServersListAdapter extends RecyclerView.Adapter<ServerViewHolder> {

    private List<NodeQueryService.Server> servers;

    public ServersListAdapter(List<NodeQueryService.Server> list) {
        this.servers = list;
    }

    @Override
    public ServerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.server_card, parent, false);

        return new ServerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServerViewHolder svh, int position) {
        NodeQueryService.Server s = servers.get(position);

        svh.vName.setText(s.name);
        svh.vIpv4.setText(s.ipv4);
        svh.vStatus.setText(s.status);
        svh.vDiskUsage.setText("-");
        svh.vRamUsage.setText("-");
        svh.vLoadPercentage.setText(s.load_percent + "%");
        svh.vAvailability.setText(s.availability);

        if ( ! s.status.equals("active") ) {
            svh.vStatus.setTextColor(Color.RED);
        }

        if ( s.disk_total > 0) {
            double percentageDiskUsage = ((s.disk_usage * 100) / s.disk_total);
            svh.vDiskUsage.setText(percentageDiskUsage + "%");
        }

        if ( s.ram_total > 0 ) {
            double percentageRamUsage = ((s.ram_usage * 100) / s.ram_total);
            svh.vRamUsage.setText(percentageRamUsage + "%");
        }
    }

    @Override
    public int getItemCount() {
        return servers.size();
    }
}
