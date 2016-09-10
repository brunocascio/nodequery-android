package com.nodequery.brunocascio.nodequery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by krypton on 24/07/16.
 */
public class ServerViewHolder extends RecyclerView.ViewHolder {

    protected TextView vName;
    protected TextView vIpv4;
    protected TextView vStatus;
    protected TextView vDiskUsage;
    protected TextView vRamUsage;
    protected TextView vLoadPercentage;
    protected TextView vAvailability;

    public ServerViewHolder(View itemView) {
        super(itemView);
        vName = (TextView) itemView.findViewById(R.id.server_name);
        vIpv4 = (TextView) itemView.findViewById(R.id.ipv4);
        vStatus = (TextView) itemView.findViewById(R.id.status);
        vDiskUsage = (TextView) itemView.findViewById(R.id.disk_usage);
        vRamUsage = (TextView) itemView.findViewById(R.id.ram_usage);
        vLoadPercentage= (TextView) itemView.findViewById(R.id.load_percentage);
        vAvailability= (TextView) itemView.findViewById(R.id.availability);
    }
}
