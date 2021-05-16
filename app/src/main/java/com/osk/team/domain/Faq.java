package com.osk.team.domain;

import java.sql.Date;

public class Faq {

  private int no;
  private String title;
  private String content;
  private Date date;


  @Override
  public String toString() {
    return "Faq [no=" + no + ", title=" + title + ", content=" + content + ", date=" + date + "]";
  }


  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public Date getDate() {
    return date;
  }


  public void setDate(Date date) {
    this.date = date;
  }



}