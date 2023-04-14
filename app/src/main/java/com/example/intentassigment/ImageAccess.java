package com.example.intentassigment;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ImageAccess implements Parcelable {
    public ImageAccess() {}
    private Uri ImageUri;

    public Uri getImageUri(){
        return ImageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.ImageUri = imageUri;
    }

    public ImageAccess (Uri imageUri){
        this.ImageUri = imageUri;
    }

    protected ImageAccess(Parcel in) {
        ImageUri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<ImageAccess> CREATOR = new Creator<ImageAccess>() {
        @Override
        public ImageAccess createFromParcel(Parcel in) {
            return new ImageAccess(in);
        }

        @Override
        public ImageAccess[] newArray(int size) {
            return new ImageAccess[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeParcelable(ImageUri, i);
    }
}
