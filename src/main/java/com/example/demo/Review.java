package com.example.demo;

public class Review {
    private String review_id;
    private String u_id;
    private String rec_id;
    private float points;
    private String review_desc;

    public String getReview_id() {
      return review_id;
    }

    public void setReview_id(String review_id) {
      this.review_id = review_id;
    }

    public String getU_id() {
      return u_id;
    }

    public void setU_id(String u_id) {
      this.u_id = u_id;
    }

    public String getRec_id() {
      return rec_id;
    }

    public void setRec_id(String rec_id) {
      this.rec_id = rec_id;
    }

    public float getPoints() {
      return points;
    }

    public void setPoints(float points) {
      this.points = points;
    }

    public String getReview_desc() {
      return review_desc;
    }

    public void setReview_desc(String review_desc) {
      this.review_desc = review_desc;
    }

    public Review(String review_id, String u_id, String rec_id, float points,
        String review_desc) {
      this.review_id = review_id;
      this.u_id = u_id;
      this.rec_id = rec_id;
      this.points = points;
      this.review_desc = review_desc;
    }
}
