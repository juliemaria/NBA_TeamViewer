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
import java.util.Objects;

public class TeamsListAdapter extends RecyclerView.Adapter<TeamsListAdapter.TeamsListViewViewHolder>  implements TeamDetailNavigator {

    Activity context;
    ArrayList<Teams> teamsArrayList;
    TeamsViewModel teamsViewModel;

    public TeamsListAdapter(Activity context,TeamsViewModel tViewModel) {
        this.context = context;
        this.teamsViewModel = tViewModel;
        this.teamsArrayList = teamsViewModel.getMutableLiveData().getValue();
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


    class TeamsListViewViewHolder extends RecyclerView.ViewHolder {
        private TeamsListItemBinding teamsListItemBinding;

        public TeamsListViewViewHolder(@NonNull View item) {
            super(item);
            this.teamsListItemBinding = DataBindingUtil.bind(item);
        }
    }
}
