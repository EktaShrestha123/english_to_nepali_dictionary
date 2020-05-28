package com.language.mini.miniProject;

public class Word {


    private String mDefaultTranslation;


    private String mNepaliTranslation;

    private int mImageResourceId = NO_IMAGE_PROVIDED;


    private static final int NO_IMAGE_PROVIDED = -1;

    private int mAudioResourceId;


    public Word(String defaultTranslation, String NepaliTranslation, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mNepaliTranslation = NepaliTranslation;
        mAudioResourceId = audioResourceId;

    }


    public Word(String defaultTranslation, String NepaliTranslation, int imageResourceId, int audioResourceId) {
        mDefaultTranslation = defaultTranslation;
        mNepaliTranslation = NepaliTranslation;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }


    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }


    public String getmMiwokTranslation() {
        return mNepaliTranslation;
    }


    public int getImageResourceId() {
        return mImageResourceId;
    }


    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }


    public int getmAudioResourceId() {

        return mAudioResourceId;
    }
}
