package com.example.nbateamviewer.ui.team;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.TeamsActivityBinding;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.network.viewmodels.TeamsViewModel;

import java.util.List;

public class TeamsActivity extends AppCompatActivity{
    TeamsActivityBinding teamsActivityBinding;
    TeamsViewModel teamsViewModel;
    TeamsListAdapter teamsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamsActivityBinding = DataBindingUtil.setContentView(this, R.layout.teams_activity);
        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        teamsActivityBinding.setTeamsViewModel(teamsViewModel);
        teamsActivityBinding.progressBar.setVisibility(View.VISIBLE);
        teamsViewModel.init();
        teamsViewModel.getTeamsMutableLiveData().observe(this, new Observer<List<Teams>>() {
                    @Override
                    public void onChanged(List<Teams> teamsResponse) {
                        teamsActivityBinding.progressBar.setVisibility(View.GONE);
                        if (teamsResponse != null)
                        setupRecyclerView();
                        else
                        Toast.makeText(TeamsActivity.this, "Something went wrong. Please try again later!!",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void setupRecyclerView() {
       if (teamsAdapter == null) {
        teamsAdapter = new TeamsListAdapter(this, teamsViewModel);
        teamsActivityBinding.setTeamsAdapter(teamsAdapter);
    } else {
        teamsAdapter.notifyDataSetChanged();
    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_alphabetical_order_asc:
                teamsViewModel.sortTeamList(getString(R.string.team_name_asc));
                teamsAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_by_alphabetical_order_desc:
                teamsViewModel.sortTeamList(getString(R.string.team_name_desc));
                teamsAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_by_losses_asc:
                teamsViewModel.sortTeamList(getString(R.string.loss_asc));
                teamsAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_by_losses_desc:
                teamsViewModel.sortTeamList(getString(R.string.loss_desc));
                teamsAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_by_wins_asc:
                teamsViewModel.sortTeamList(getString(R.string.win_asc));
                teamsAdapter.notifyDataSetChanged();
                return true;
            case R.id.sort_by_wins_desc:
                teamsViewModel.sortTeamList(getString(R.string.win_desc));
                teamsAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
