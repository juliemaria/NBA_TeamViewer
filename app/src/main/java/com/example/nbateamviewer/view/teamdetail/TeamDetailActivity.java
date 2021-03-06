package com.example.nbateamviewer.view.teamdetail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.TeamDetailActivityBinding;
import com.example.nbateamviewer.model.Teams;

public class TeamDetailActivity extends AppCompatActivity {
    Teams teamDetail;
    TeamDetailActivityBinding teamDetailActivityBinding;
    PlayersListAdapter playersListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.team_detail_activity);
        teamDetail = getIntent().getExtras().getParcelable("TeamDetail");
        teamDetailActivityBinding = DataBindingUtil.setContentView(this, R.layout.team_detail_activity);
        teamDetailActivityBinding.setTeam(teamDetail);

         if (teamDetail!=null && teamDetail.getPlayers()!=null && teamDetail.getPlayers().size()>0)
         {
             setupRecyclerView();
         }
    }
    private void setupRecyclerView() {
        if (playersListAdapter == null) {
            playersListAdapter = new PlayersListAdapter(this, teamDetail.getPlayers());
            teamDetailActivityBinding.setPlayersListAdapter(playersListAdapter);
        } else {
            playersListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

}
