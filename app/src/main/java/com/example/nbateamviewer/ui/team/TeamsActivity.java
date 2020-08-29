package com.example.nbateamviewer.ui.team;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.nbateamviewer.R;
import com.example.nbateamviewer.databinding.TeamsActivityBinding;
import com.example.nbateamviewer.network.model.Teams;
import com.example.nbateamviewer.network.viewmodels.TeamsViewModel;

import java.util.List;

public class TeamsActivity extends AppCompatActivity {

    RecyclerView teamsRecyclerView;
    TeamsActivityBinding teamsActivityBinding;
    TeamsViewModel teamsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        teamsActivityBinding = DataBindingUtil.setContentView(this, R.layout.teams_activity);
        teamsRecyclerView = findViewById(R.id.teamsRecyclerView);
        teamsActivityBinding.setTeamsvm(teamsViewModel);

        teamsViewModel = new ViewModelProvider(this).get(TeamsViewModel.class);
        teamsViewModel.init();
        teamsViewModel.getTeamsRepository().observe(this, new Observer<List<Teams>>() {
                    @Override
                    public void onChanged(List<Teams> teamsResponse) {
                        Log.e("Response: ",teamsResponse.get(0).toString());
                    }
                }
        );

        setupRecyclerView();
    }

    private void setupRecyclerView() {

    }

}
