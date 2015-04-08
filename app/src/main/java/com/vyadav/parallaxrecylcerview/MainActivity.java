package com.vyadav.parallaxrecylcerview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "ParallaxRecycler";
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    private List<Matches> mDataSet = Collections.emptyList();
    private List<RoundRobin> mRoundRobinList = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mDataSet = populateData();
        createAdapter(mRecyclerView);
    }

    private void createAdapter(RecyclerView mRecyclerView) {
        final ParallaxRecyclerAdapter<Matches> adapter = new ParallaxRecyclerAdapter<>(mDataSet);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        View header = getLayoutInflater().inflate(R.layout.header, mRecyclerView, false);
        adapter.setParallaxHeader(header, mRecyclerView);
        adapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter
                .RecyclerAdapterMethods() {

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Log.i(TAG, adapter.getData().get(position).toString());
                Log.i(TAG, adapter.getData().get(position).getWinningTeam());

                if (holder == null) {
                    Log.i(TAG, "Position: " + position + ", Fuck you");
                } else if (((ListViewHolder) holder).mTextView == null) {
                    Log.i(TAG, "Position: " + position + ", Fuck you twice");
                } else {
                    String winningTeam = adapter.getData().get(position).getWinningTeam();
                    Log.i(TAG, "Position: " + position + ", winningTeam: " + winningTeam);
                    ((ListViewHolder) holder).mTextView.setText(winningTeam);
                }
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Log.i(TAG, "ViewType: " + printViewTypes(viewType));
                return new ListViewHolder(getLayoutInflater().inflate(
                        R.layout.single_item_row, parent, false));
            }

            @Override
            public int getItemCount() {
                return adapter.getData().size();
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private List<Matches> populateData() {
        List<Matches> info = new ArrayList<>();
        // info.addAll(populateRoundRobinMatches());
        info.addAll(populateEliminationRoundMatches());
        return info;
    }

    private List<EliminationRound> populateEliminationRoundMatches() {
        List<EliminationRound> eliminationRoundsList = new ArrayList<>();

        String[] team1 = getResources().getStringArray(R.array.team1);
        String[] team2 = getResources().getStringArray(R.array.team2);
        int[] team1Scores = getResources().getIntArray(R.array.team1scores);
        int[] team2Scores = getResources().getIntArray(R.array.team2scores);
        String[] winningTeam = getResources().getStringArray(R.array.matchwinners);

        if (team1.length != team2.length || team1.length != team1Scores.length ||
                team1.length != team2Scores.length || team1.length != winningTeam.length) {
            throw new IllegalStateException("Matches are not proper");
        }

        for (int i = 0; i < team1.length; i++) {
            EliminationRound eliminationMatch = new EliminationRound();
            eliminationMatch.setTeams(team1[i], team2[i]);
            eliminationMatch.setTeamScores(team1Scores[i], team2Scores[i]);
            eliminationMatch.setWinner(winningTeam[i]);

            eliminationRoundsList.add(eliminationMatch);
        }
        return eliminationRoundsList;
    }

    private List<RoundRobin> populateRoundRobinMatches() {
        List<RoundRobin> roundRobinList = new ArrayList<>();
        return roundRobinList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        if (id == R.id.type_of_matches) {
            item.setTitle(item.getTitle().toString().equalsIgnoreCase(getString(R.string
                    .round_robin)) ? R.string.elimination : R.string.round_robin);
        }
        return super.onOptionsItemSelected(item);
    }

    class ListViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ListViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) findViewById(R.id.single_row_text);
        }
    }

    private String printViewTypes(int viewType) {
        switch (viewType) {
            case ParallaxRecyclerAdapter.VIEW_TYPES.NORMAL:
                return "NORMAL";
            case ParallaxRecyclerAdapter.VIEW_TYPES.HEADER:
                return "HEADER";
            case ParallaxRecyclerAdapter.VIEW_TYPES.FIRST_VIEW:
                return "FIRST_VIEW";
            case ParallaxRecyclerAdapter.VIEW_TYPES.NORMAL_VIEW:
                return "NORMAL_VIEW";
            default:
                return "";
        }
    }
}
