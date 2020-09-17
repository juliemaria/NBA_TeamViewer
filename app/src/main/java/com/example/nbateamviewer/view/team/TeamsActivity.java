package com.example.nbateamviewer.view.team;

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
import com.example.nbateamviewer.model.TeamRepositoryModel;
import com.example.nbateamviewer.viewmodels.TeamsViewModel;

public class TeamsActivity extends AppCompatActivity{
    TeamsActivityBinding teamsActivityBinding;
    TeamsViewModel teamsViewModel;
    TeamsListAdapter teamsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        teamsActivityBinding = DataBindingUtil.setContentView(this, R.layout.teams_activity);
        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        teamsActivityBinding.setTeamsViewModel(teamsViewModel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchTeamsList();
    }


    private void fetchTeamsList() {
            teamsActivityBinding.progressBar.setVisibility(View.VISIBLE);
            teamsViewModel.init();
            teamsViewModel.getTeamsLiveData().observe(this, new Observer<TeamRepositoryModel>() {
                        @Override
                        public void onChanged(TeamRepositoryModel teamsResponse) {
                            teamsActivityBinding.progressBar.setVisibility(View.GONE);
                            if (teamsResponse != null && teamsResponse.getTeamsArrayList()!=null)
                                setupRecyclerView();
                            else if (teamsResponse.getThrowable()!=null)
                                Toast.makeText(TeamsActivity.this, teamsResponse.getThrowable().getMessage(),Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(TeamsActivity.this, getResources().getString(R.string.no_internet_message),Toast.LENGTH_SHORT).show();
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
        if(teamsViewModel.getTeamsLiveData()!=null &&
                teamsViewModel.getTeamsLiveData().getValue()!=null &&
                teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList()!=null &&
                !teamsViewModel.getTeamsLiveData().getValue().getTeamsArrayList().isEmpty() ) {

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
        else
        {
            Toast.makeText(TeamsActivity.this, R.string.no_items_found,Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
