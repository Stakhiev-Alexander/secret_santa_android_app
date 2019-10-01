package com.alexander.secretsanta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Shuffler {
  public static Map<String, Entry<String, String>> shuffle(Map<String, String> namesAndAddreses){
    Map<String, Entry<String, String>> unshuffledMap = new HashMap<>();

    for (Entry nameAndAddress: namesAndAddreses.entrySet()) {
      unshuffledMap.put((String)nameAndAddress.getKey(), nameAndAddress);
    }

    List<String> keys = new ArrayList<>(unshuffledMap.keySet());
    Collections.shuffle(keys);

    Map<String, Entry<String, String>> shuffledMap = new HashMap<>();
    Collections.shuffle(keys);
    for (Object o : keys) {
      shuffledMap.put((String) o, unshuffledMap.get(o));
    }
    return shuffledMap;
  }
}
