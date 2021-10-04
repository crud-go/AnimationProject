package com.test.animation.logic.Utils;

import android.content.Context;

import com.test.animation.AnimationApp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import okhttp3.Dns;

public class dns implements Dns {
    public static String[] ao(Context paramContext) {
        Set set = paramContext.getSharedPreferences("data", 0).getStringSet("KEY_DNS_IP", null);
        return (set == null) ? null : (String[])set.toArray((Object[])new String[set.size()]);
    }
   String [] sZ=ao(AnimationApp.context);

    public List<InetAddress> lookup(String paramString) throws UnknownHostException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DNS lookup hostname: ");
        stringBuilder.append(paramString);

        try {
            ArrayList<InetAddress> arrayList = new ArrayList();
            if (this.sZ != null && this.sZ.length > 0) {
                for (int i = 0; i < this.sZ.length; i++)
                    arrayList.add(InetAddress.getByName(this.sZ[i]));
            } else {

            }

            return arrayList;
        } catch (Exception exception) {
            exception.printStackTrace();
            return Dns.SYSTEM.lookup(paramString);
        }
    }

}
