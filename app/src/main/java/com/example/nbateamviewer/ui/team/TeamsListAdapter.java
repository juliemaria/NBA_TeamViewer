package com.example.nbateamviewer.ui.team;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.TeamsListItemBinding;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.network.viewmodels.TeamsViewModel;
import com.example.nbateamviewer.ui.interfaces.TeamDetailNavigator;
import com.example.nbateamviewer.ui.interfaces.TeamListModifications;
import com.example.nbateamviewer.ui.teamdetail.TeamDetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TeamsListAdapter extends RecyclerView.Adapter<TeamsListAdapter.TeamsListViewViewHolder>  implements TeamDetailNavigator, TeamListModifications {

    Activity context;
    ArrayList<Teams> teamsArrayList;
    TeamsViewModel teamsViewModel;

    public TeamsListAdapter(Activity context, ArrayList<Teams> teams, TeamsViewModel tViewModel) {
        this.context = context;
        this.teamsArrayList = teams;
        this.teamsViewModel = tViewModel;
    }


    @NonNull
    @Override
    public TeamsListViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TeamsListItemBinding teamsListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.teams_list_item, parent, false);
        return new TeamsListViewViewHolder(teamsListItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsListViewViewHolder holder, int position) {
        teamsViewModel.setTeamDetailNavigator(this);
        teamsViewModel.setTeamListModifications(this);
        Teams teams = teamsArrayList.get(position);
        holder.teamsListItemBinding.setTeam(teams);
        holder.teamsListItemBinding.setViewmodel(teamsViewModel);
    }

    @Override
    public int getItemCount() {
        return teamsArrayList.size();
    }

    @Override
    public void onTeamItemClicked(Teams teams) {
        Intent intent = new Intent(context, TeamDetailActivity.class);
        intent.putExtra("TeamDetail", teams);
        context.startActivity(intent);
    }

    @Override
    public void sortTeamList(String sortType) {
        Collections.sort(teamsViewModel.getTeamsRepository().getValue(), new Comparator<Teams>() {
            public int compare(Teams obj1, Teams obj2) {
                //Ascending order of name
                if (sortType.equalsIgnoreCase(context.getString(R.string.team_name_asc))) {
                    return obj1.getFull_name().compareToIgnoreCase(obj2.getFull_name());
                } else if (sortType.equalsIgnoreCase(context.getString(R.string.win_desc))) {
                    return obj2.getWins().compareTo(obj1.getWins());
                } else if (sortType.equalsIgnoreCase(context.getString(R.string.loss_desc))) {
                    return obj2.getLosses().compareTo(obj1.getLosses());
                } else if (sortType.equalsIgnoreCase(context.getString(R.string.team_name_desc))) {
                    return obj2.getFull_name().compareToIgnoreCase(obj1.getFull_name());
                } else if (sortType.equalsIgnoreCase(context.getString(R.string.win_asc))) {
                    return obj1.getWins().compareTo(obj2.getWins());
                } else {
                    return obj1.getLosses().compareTo(obj2.getLosses());
                }
            }
        });
        notifyDataSetChanged();
    }

    class TeamsListViewViewHolder extends RecyclerView.ViewHolder {
        private TeamsListItemBinding teamsListItemBinding;

        public TeamsListViewViewHolder(@NonNull View item) {
            super(item);
            this.teamsListItemBinding = DataBindingUtil.bind(item);
        }
    }
}
