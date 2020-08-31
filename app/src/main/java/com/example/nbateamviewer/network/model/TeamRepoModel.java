package com.example.nbateamviewer.network.model;

import java.util.ArrayList;

public class TeamRepoModel{

    private ArrayList<Teams> teamsArrayList;
    private Throwable mThrowable;
    private String errorMessage;


    public TeamRepoModel(ArrayList<Teams> body) {
        this.teamsArrayList = body;
    }
    public TeamRepoModel(String body) {
        this.errorMessage = body;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ArrayList<Teams> getTeamsArrayList() {
        return teamsArrayList;
    }

    public TeamRepoModel (Throwable throwable) {
        mThrowable = throwable;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

}
