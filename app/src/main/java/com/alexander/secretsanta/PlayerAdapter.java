package com.alexander.secretsanta;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

  private Context mCtx;
  private List<Player> playerList;
  private Map<String, String> data = new HashMap<>();
  private String currentName = "";
  private String currentEmail = "";

  public Map<String, String> getData() {
    return data;
  }

  public PlayerAdapter(Context mCtx, List<Player> playerList) {
    this.mCtx = mCtx;
    this.playerList = playerList;
  }

  @Override
  public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mCtx);
    View view = inflater.inflate(R.layout.list_layout, null);
    return new PlayerViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
    Player player = playerList.get(position);

    holder.editName.setText(player.getName());
    holder.editEmail.setText(player.getEmail());
    holder.profileImageView.setImageDrawable(mCtx.getResources().getDrawable(player.getPhotoId()));
  }

  @Override
  public int getItemCount() {
    return playerList.size();
  }

  class PlayerViewHolder extends RecyclerView.ViewHolder {

    ImageView profileImageView;
    EditText editName, editEmail;

    PlayerViewHolder(View itemView) {
      super(itemView);

      profileImageView = itemView.findViewById(R.id.profileImageView);
      editName = itemView.findViewById(R.id.editName);
      editName.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          //BEFORE TEXT CHANGED
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          // WHILE TEXT IS CHANGE
        }

        @Override
        public void afterTextChanged(Editable editable) {
          if (currentEmail.isEmpty()) {
            currentName = editable.toString();
          } else {
            data.put(editable.toString(), currentEmail);
          }

        }
      });
      editEmail = itemView.findViewById(R.id.editEmail);
      editEmail.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          //BEFORE TEXT CHANGED
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
          // WHILE TEXT IS CHANGE
        }

        @Override
        public void afterTextChanged(Editable editable) {
          if (currentName.isEmpty()) {
            currentEmail = editable.toString();
          } else {
            data.put(currentName, editable.toString());
          }
        }
      });
    }
  }
}
