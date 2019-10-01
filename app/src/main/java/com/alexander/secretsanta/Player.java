package com.alexander.secretsanta;

import java.util.Random;

public class Player {

  private String name;
  private String email;
  private int photoId;

  public Player(String name, String email, int photoId) {
    this.name = name;
    this.email = email;
    this.photoId = photoId;
  }

  public Player() {
    this.name = "";
    this.email = "";
    Random rand = new Random();
    int picNumber = rand.nextInt(6);
    switch (picNumber) {
      case 0:
        this.photoId = R.drawable.p1;
        break;
      case 1:
        this.photoId = R.drawable.p2;
        break;
      case 2:
        this.photoId = R.drawable.p3;
        break;
      case 3:
        this.photoId = R.drawable.p4;
        break;
      case 4:
        this.photoId = R.drawable.p5;
        break;
      case 5:
        this.photoId = R.drawable.p6;
        break;
      case 6:
        this.photoId = R.drawable.p7;
        break;
      case 7:
        this.photoId = R.drawable.p8;
        break;
      default:
        this.photoId = R.drawable.p1;
        break;
    }
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public int getPhotoId() {
    return photoId;
  }
}
