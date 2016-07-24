package com.example.outsidehack.outsidelove;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StageActivity extends Activity {

    ListView listView;
    List<stageItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);


        populateList();
        listView = (ListView)findViewById(R.id.listViewItems);
        ItemListAdapter listAdapter = new ItemListAdapter(this,listItems);


        listView.setAdapter(listAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateList(){

        listItems = new ArrayList<stageItem>();
        stageItem item = new stageItem();
        item.setArtistName("Rl Grime");
        item.setStageName("Lands End");
        item.setHypePercent("95 %");
        item.setTurntPercent("100 %");
        listItems.add(item);

        stageItem item2 = new stageItem();
        item.setArtistName("BonerJamz");
        item.setStageName("Sutro");
        item.setHypePercent("88 %");
        item.setTurntPercent("92 %");
        listItems.add(item2);



        stageItem item3 = new stageItem();
        item.setArtistName("J Cole");
        item.setStageName("Twin Peaks");
        item.setHypePercent("80 %");
        item.setTurntPercent("90 %");
        listItems.add(item3);



        stageItem item4 = new stageItem();
        item.setArtistName("Tokimonsta");
        item.setStageName("Panhandle");
        item.setHypePercent("73 %");
        item.setTurntPercent("69 %");
        listItems.add(item4);

        stageItem item5 = new stageItem();
        item.setArtistName("IDAGOS");
        item.setStageName("The Barbary");
        item.setHypePercent("78 %");
        item.setTurntPercent("37 %");
        listItems.add(item5);


    }



}
