package com.example.nbateamviewer.ui.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nbateamviewer.NbaApplication;
import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.TeamsActivityBinding;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.network.viewmodels.TeamsViewModel;

import java.util.ArrayList;
import java.util.List;

public class TeamsActivity extends AppCompatActivity {
    TeamsActivityBinding teamsActivityBinding;
    TeamsViewModel teamsViewModel;
    TeamsListAdapter teamsAdapter;
    ArrayList<Teams> teamsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamsActivityBinding = DataBindingUtil.setContentView(this, R.layout.teams_activity);
        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        teamsViewModel.init();
        teamsViewModel.getTeamsRepository().observe(this, new Observer<List<Teams>>() {
                    @Override
                    public void onChanged(List<Teams> teamsResponse) {
                        teamsArrayList.addAll(teamsResponse);
                        if (teamsResponse != null)
                        setupRecyclerView();
                    }
                }
        );
    }

    private void setupRecyclerView() {
       if (teamsAdapter == null) {
        teamsAdapter = new TeamsListAdapter(this, teamsArrayList, teamsViewModel);
        teamsActivityBinding.setTeamsAdapter(teamsAdapter);
    } else {
        teamsAdapter.notifyDataSetChanged();
    }
    }
}
