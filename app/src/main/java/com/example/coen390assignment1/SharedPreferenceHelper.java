package com.example.coen390assignment1;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceHelper {
    private SharedPreferences sharedPreferences;
    public SharedPreferenceHelper(Context context)
    {
        sharedPreferences = context.getSharedPreferences("ProfilePreference",
                Context.MODE_PRIVATE );
    }
    public void saveProfileName(String name)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("profileName",name );
        editor.commit();
    }
    public String getProfileName()
    {
        return sharedPreferences.getString("profileName", null);
    }

    public void saveProfileAge(int age)
    {
        if(age>18&&age<99){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("profileAge",age);
            editor.commit();
        }else{
            //input wrong
        }

    }
    public String getProfileAge()
    {
        return Integer.toString(sharedPreferences.getInt("profileAge", 0));
    }

    public void saveProfileID(int id)
    {

        if(id <999999){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("profileID",id);
            editor.commit();
        }else{

        }

    }
    public String getProfileID()
    {
        return Integer.toString(sharedPreferences.getInt("profileID", 0));
    }

}