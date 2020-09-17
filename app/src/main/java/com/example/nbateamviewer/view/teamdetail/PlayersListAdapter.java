package com.example.nbateamviewer.view.teamdetail;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.PlayerListItemBinding;
import com.example.nbateamviewer.model.Players;

import java.util.ArrayList;

public class PlayersListAdapter extends RecyclerView.Adapter<PlayersListAdapter.PlayersListViewViewHolder> {

    Activity context;
    ArrayList<Players> playersArrayList;

    public PlayersListAdapter(Activity context, ArrayList<Players> players) {
        this.context = context;
        this.playersArrayList = players;
    }


    @NonNull
    @Override
    public PlayersListViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlayerListItemBinding playerListItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.player_list_item, parent, false);
        return new PlayersListViewViewHolder(playerListItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersListViewViewHolder holder, int position) {
        Players players = playersArrayList.get(position);
        holder.playerListItemBinding.setPlayer(players);
    }

    @Override
    public int getItemCount() {
        return playersArrayList.size();
    }


    class PlayersListViewViewHolder extends RecyclerView.ViewHolder {
        private PlayerListItemBinding playerListItemBinding;

        public PlayersListViewViewHolder(@NonNull View item) {
            super(item);
            this.playerListItemBinding = DataBindingUtil.bind(item);
        }
    }
}
