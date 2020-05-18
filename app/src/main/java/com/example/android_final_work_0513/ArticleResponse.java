package com.example.android_final_work_0513;

import com.google.gson.annotations.SerializedName;

class ArticleResponse {

        @SerializedName("_id")
        public String _id ;
        @SerializedName("feedurl")
        public String feedurl;
        @SerializedName("nickname")
        public String nickname;
        @SerializedName("description")
        public String description;
        @SerializedName("likecount")
        public String likecount;
        @SerializedName("avatar")
        public String avatar;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getFeedurl() {
            return feedurl;
        }

        public void setFeedurl(String feedurl) {
            this.feedurl = feedurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLikecount() {
            return likecount;
        }

        public void setLikecount(String likecount) {
            this.likecount = likecount;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "_id='" + _id + '\'' +
                ", feedurl='" + feedurl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", likecount='" + likecount + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
