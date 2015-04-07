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

    private static final String TAG = "ParallaxRecycller";
    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    private List<Matches> mDataSet = Collections.emptyList();
    private List<RoundRobin> mRoundRobinList = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle(getString(R.string.parallax));
        setSupportActionBar(mToolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mDataSet = populateData();
        createAdapter(mRecyclerView);
    }

    private void createAdapter(RecyclerView mRecyclerView) {
        final ParallaxRecyclerAdapter<Matches> adapter = new ParallaxRecyclerAdapter<>
                ((List<Matches>) mDataSet);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        View header = getLayoutInflater().inflate(R.layout.header, mRecyclerView, false);
        adapter.setParallaxHeader(header, mRecyclerView);
        adapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter
                .RecyclerAdapterMethods() {

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
                Log.i(TAG, adapter.getData().get(i).toString());
                Log.i(TAG, adapter.getData().get(i)
                        .getWinningTeam());
                if (viewHolder == null)
                    Log.i(TAG, "Fuck you");
                if (((ListViewHolder)viewHolder).mTextView == null)
                    Log.i(TAG, "Fuck you twice");
                ((ListViewHolder) viewHolder).mTextView.setText(adapter.getData().get(i)
                        .getWinningTeam());
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                return new ListViewHolder(getLayoutInflater().inflate(R.layout.single_item_row, viewGroup, false));
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
        info.addAll(populateRoundRobinMatches());
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

        for (int i = 0; i < team1.length; i++) {
            EliminationRound elimMatch = new EliminationRound();
            elimMatch.setTeams(team1[i], team2[i]);
            elimMatch.setTeamScores(team1Scores[i], team2Scores[i]);
            elimMatch.setWinner(winningTeam[i]);

            eliminationRoundsList.add(elimMatch);
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
}
