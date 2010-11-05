package com.example.brewclock;

public class Tea {
  protected String name;
  protected int brewTime;

  public Tea(String name, int brewTime) {
    setName(name);
    setBrewTime(brewTime);
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBrewTime() {
    return brewTime;
  }

  public void setBrewTime(int brewTime) {
    this.brewTime = brewTime;
  }
}
