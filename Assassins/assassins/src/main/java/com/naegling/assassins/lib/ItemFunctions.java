package com.naegling.assassins.lib;

import java.util.HashMap;
import java.util.Random;
import org.json.JSONException;
import android.content.Context;

public class ItemFunctions {
	
	//Counting the lootbonus based on the player's current killstreak
	private static int lootBonus(String uid) {
		
		int uKillstreak = 0; 
        
        ProfileFunction profile = new ProfileFunction();
        try {
            uKillstreak = profile.getUserKillstreak(uid).getInt("killstreak");
    	} catch(JSONException e) {
    		e.printStackTrace();
    	}
        
        return uKillstreak * 2;
	}
	
	//Roll to see if there is any item to loot at the hub 
	public static boolean loot(String uid) {

		Random r = new Random();
		int roll = r.nextInt(10)+1;
		
		if (roll <= lootBonus(uid)) {
            //Item exist at hub
            return true;
        }
		//No item exist at hub
		return false;
		
	}
    public static boolean isItemCollectable(String uid) {

        ProfileFunction profileFunction = new ProfileFunction();
        try {
            int itemToCollect = profileFunction.isItemCollectable(uid).getInt("item_to_collect");
            if (itemToCollect == 1) {
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}