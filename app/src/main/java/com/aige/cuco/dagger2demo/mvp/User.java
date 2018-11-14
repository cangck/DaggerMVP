package com.aige.cuco.dagger2demo.mvp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @package com.aige.cuco.dagger2demo.mvp
 * @fileName User
 * @date 2018/11/14
 * @describe
 * @email shenzhencuco@gmail
 */
public class User implements Parcelable {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User();
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
