package com.alexander.secretsanta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
    View.OnClickListener {

  RecyclerView recyclerView;
  PlayerAdapter adapter;
  List<Player> playerList;
  SeekBar seekBar;
  Button nextPageButton;
  private TextView mTextView;
  private int currentPage = 0;

  private int numberOfPlayers = 2;

  private List<Player> shuffledList;

  private void shuffle() {

  }

  void welcomePageInit() {
    setContentView(R.layout.welcome_page);

    nextPageButton = findViewById(R.id.nextPageButton);
    nextPageButton.setOnClickListener(this);

    seekBar = findViewById(R.id.seekBar);
    seekBar.setOnSeekBarChangeListener(this);

    mTextView = findViewById(R.id.seekProgress);
    numberOfPlayers = 2;
    mTextView.setText("2");
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.welcome_page);

    welcomePageInit();
  }

  @Override
  public void onClick(View v) {
    if (numberOfPlayers < 2) {
      Toast toast = Toast.makeText(getApplicationContext(),
          "Должно быть как минимум 2 участника!", Toast.LENGTH_SHORT);
      toast.setGravity(Gravity.CENTER, 0, 0);
      toast.show();
    } else {
      setContentView(R.layout.activity_main);

      currentPage = 1;

      playerList = new ArrayList<>();
      recyclerView = findViewById(R.id.recyclerView);
      recyclerView.setHasFixedSize(true);

      recyclerView.setLayoutManager(new LinearLayoutManager(this));

      for (int i = 0; i < numberOfPlayers; i++) {
        playerList.add(new Player());
      }

      adapter = new PlayerAdapter(this, playerList);
      recyclerView.setAdapter(adapter);

      Button sendButton = findViewById(R.id.sendButton);

      sendButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          Map<String, Entry<String, String>> shuffledMap = Shuffler.shuffle(adapter.getData());

          String subject = "Secret Santa App";
          String message = "Your victim is ";
          for (Entry player: shuffledMap.entrySet()) {
            String victimEmail = ((Entry<String, String>)player.getValue()).getValue();
            String victimName = ((Entry<String, String>)player.getValue()).getKey();
            message += victimName + "!";

            //sending email to the victim
            SendMail sm = new SendMail(MainActivity.this, victimEmail, subject, message);
            sm.execute();
            message = "Your victim is ";
          }
        }
      });

    }
  }

  @Override
  public void onBackPressed() {
    if (currentPage == 1) {
      welcomePageInit();
      currentPage = 0;
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    mTextView.setText(String.valueOf(seekBar.getProgress()));
  }

  @Override
  public void onStartTrackingTouch(SeekBar seekBar) {
  }

  @Override
  public void onStopTrackingTouch(SeekBar seekBar) {
    mTextView.setText(String.valueOf(seekBar.getProgress()));
    numberOfPlayers = seekBar.getProgress();
  }
}
