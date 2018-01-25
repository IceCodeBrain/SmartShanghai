package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelFeedItem{ // there are two kind of constructors,with and without isbookmarked

    private int id;
    private int type_id;
    private int entry_id;
    private String title;
    private String thumb;
    private String meta1;
    private String meta2;
    private String meta3;
    private String meta4;
    private String meta5;
    private String meta6;
    private String meta7;
    private String meta8;
    private String meta9;
    private String meta10;
    private String meta11;
    private String meta12;
    private String meta13;
    private String meta14;
    private String meta15;
    private String meta16;
    private String date_published;
    private String last_edited;
    private String date_timeago;
    private boolean isBookmarked;
    private String share_url;
    private String share_title;
    private String share_phrase;


    public ModelFeedItem(int id, int type_id, int entry_id, String title, String thumb, String meta1, String meta2, String meta3, String meta4, String meta5, String meta6, String meta7, String meta8, String meta9, String meta10, String meta11, String meta12, String meta13, String meta14, String meta15, String meta16, String date_published, String last_edited, String date_timeago, boolean isBookmarked, String share_url, String share_title, String share_phrase) {
        this.id = id;
        this.type_id = type_id;
        this.entry_id = entry_id;
        this.title = title;
        this.thumb = thumb;
        this.meta1 = meta1;
        this.meta2 = meta2;
        this.meta3 = meta3;
        this.meta4 = meta4;
        this.meta5 = meta5;
        this.meta6 = meta6;
        this.meta7 = meta7;
        this.meta8 = meta8;
        this.meta9 = meta9;
        this.meta10 = meta10;
        this.meta11 = meta11;
        this.meta12 = meta12;
        this.meta13 = meta13;
        this.meta14 = meta14;
        this.meta15 = meta15;
        this.meta16 = meta16;
        this.date_published = date_published;
        this.last_edited = last_edited;
        this.date_timeago = date_timeago;
        this.isBookmarked = isBookmarked;
        this.share_url = share_url;
        this.share_title = share_title;
        this.share_phrase = share_phrase;
    }

    public ModelFeedItem(int id, int type_id, int entry_id, String title, String thumb, String meta1, String meta2, String meta3, String meta4, String meta5, String meta6, String meta7, String meta8, String meta9, String meta10, String meta11, String meta12, String meta13, String meta14, String meta15, String meta16, String date_published, String last_edited, String date_timeago, String share_url, String share_title, String share_phrase) {
        this.id = id;
        this.type_id = type_id;
        this.entry_id = entry_id;
        this.title = title;
        this.thumb = thumb;
        this.meta1 = meta1;
        this.meta2 = meta2;
        this.meta3 = meta3;
        this.meta4 = meta4;
        this.meta5 = meta5;
        this.meta6 = meta6;
        this.meta7 = meta7;
        this.meta8 = meta8;
        this.meta9 = meta9;
        this.meta10 = meta10;
        this.meta11 = meta11;
        this.meta12 = meta12;
        this.meta13 = meta13;
        this.meta14 = meta14;
        this.meta15 = meta15;
        this.meta16 = meta16;
        this.date_published = date_published;
        this.last_edited = last_edited;
        this.date_timeago = date_timeago;
        this.share_url = share_url;
        this.share_title = share_title;
        this.share_phrase = share_phrase;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getEntry_id() {
        return entry_id;
    }

    public void setEntry_id(int entry_id) {
        this.entry_id = entry_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getMeta1() {
        return meta1;
    }

    public void setMeta1(String meta1) {
        this.meta1 = meta1;
    }

    public String getMeta2() {
        return meta2;
    }

    public void setMeta2(String meta2) {
        this.meta2 = meta2;
    }

    public String getMeta3() {
        return meta3;
    }

    public void setMeta3(String meta3) {
        this.meta3 = meta3;
    }

    public String getMeta4() {
        return meta4;
    }

    public void setMeta4(String meta4) {
        this.meta4 = meta4;
    }

    public String getMeta5() {
        return meta5;
    }

    public void setMeta5(String meta5) {
        this.meta5 = meta5;
    }

    public String getMeta6() {
        return meta6;
    }

    public void setMeta6(String meta6) {
        this.meta6 = meta6;
    }

    public String getMeta7() {
        return meta7;
    }

    public void setMeta7(String meta7) {
        this.meta7 = meta7;
    }

    public String getMeta8() {
        return meta8;
    }

    public void setMeta8(String meta8) {
        this.meta8 = meta8;
    }

    public String getMeta9() {
        return meta9;
    }

    public void setMeta9(String meta9) {
        this.meta9 = meta9;
    }

    public String getMeta10() {
        return meta10;
    }

    public void setMeta10(String meta10) {
        this.meta10 = meta10;
    }

    public String getMeta11() {
        return meta11;
    }

    public void setMeta11(String meta11) {
        this.meta11 = meta11;
    }

    public String getMeta12() {
        return meta12;
    }

    public void setMeta12(String meta12) {
        this.meta12 = meta12;
    }

    public String getMeta13() {
        return meta13;
    }

    public void setMeta13(String meta13) {
        this.meta13 = meta13;
    }

    public String getMeta14() {
        return meta14;
    }

    public void setMeta14(String meta14) {
        this.meta14 = meta14;
    }

    public String getMeta15() {
        return meta15;
    }

    public void setMeta15(String meta15) {
        this.meta15 = meta15;
    }

    public String getMeta16() {
        return meta16;
    }

    public void setMeta16(String meta16) {
        this.meta16 = meta16;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }

    public String getLast_edited() {
        return last_edited;
    }

    public void setLast_edited(String last_edited) {
        this.last_edited = last_edited;
    }

    public String getDate_timeago() {
        return date_timeago;
    }

    public void setDate_timeago(String date_timeago) {
        this.date_timeago = date_timeago;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_phrase() {
        return share_phrase;
    }

    public void setShare_phrase(String share_phrase) {
        this.share_phrase = share_phrase;
    }
}
