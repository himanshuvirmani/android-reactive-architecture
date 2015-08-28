package com.himanshuvirmani.androidreactivearch.data.entity;

import lombok.Data;

/**
 * Created by himanshu.virmani on 09/05/15.
 */
@Data
public class Post {

  private int userId;

  private int id;

  private String title;

  private String body;
}
