package com.example.nbateamviewer.ui.team;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.network.model.Teams;

import java.util.ArrayList;

public class TeamsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    ArrayList<Teams> teamsArrayList;

    public TeamsListAdapter(Activity context, ArrayList<Teams> teams) {
        this.context = context;
        this.teamsArrayList = teams;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.teams_list_item,parent,false);
        return new TeamsListViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Teams teams = teamsArrayList.get(position);
        TeamsListViewViewHolder viewHolder= (TeamsListViewViewHolder) holder;

        viewHolder.name.setText(teams.getFull_name());
        viewHolder.wins.setText(teams.getWins().toString());
        viewHolder.losses.setText(teams.getLosses().toString());
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }

    class TeamsListViewViewHolder extends RecyclerView.ViewHolder {
        TextView wins;
        TextView losses;
        TextView name;

        public TeamsListViewViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            wins = itemView.findViewById(R.id.wins);
            losses = itemView.findViewById(R.id.losses);
        }
    }
}
