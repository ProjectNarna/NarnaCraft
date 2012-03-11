package net.minecraft.src;

import java.io.PrintStream;
import java.net.URLEncoder;
import java.util.*;

public class PlayerUsageSnooper
{
    public static final PlayerUsageSnooper field_48478_a = new PlayerUsageSnooper();
    private long field_48476_b;
    private Map field_48477_c;
    private Map field_48475_d;

    private PlayerUsageSnooper()
    {
        field_48476_b = -1L;
        field_48477_c = new HashMap();
        field_48475_d = new HashMap();
    }

    public void func_48472_a()
    {
        long l = System.currentTimeMillis();

        if (field_48476_b == -1L)
        {
            field_48476_b = System.currentTimeMillis();
            return;
        }

        long l1 = l - field_48476_b;

        if (l1 < 0xfffffffffff6d840L || l1 > 0x124f80L)
        {
            System.out.println("Skipping snoop interval");
            field_48476_b = l;
            return;
        }

        if (l1 > 0x927c0L)
        {
            func_48473_b();
            field_48476_b += 0x927c0L;
        }
    }

    public void func_48474_a(String par1Str, String par2Str)
    {
        field_48475_d.put(par1Str, par2Str);
    }

    private void func_48473_b()
    {
        String s = "";

        for (Iterator iterator = field_48475_d.keySet().iterator(); iterator.hasNext();)
        {
            String s1 = (String)iterator.next();
            String s3 = (String)field_48475_d.get(s1);

            if (s.length() > 0)
            {
                s = (new StringBuilder()).append(s).append("&").toString();
            }

            try
            {
                s = (new StringBuilder()).append(s).append(s1).append("=").append(URLEncoder.encode(s3, "UTF-8")).toString();
            }
            catch (Exception exception) { }
        }

        for (Iterator iterator1 = field_48477_c.keySet().iterator(); iterator1.hasNext();)
        {
            String s2 = (String)iterator1.next();
            String s4 = ((Integer)field_48477_c.get(s2)).toString();

            if (s.length() > 0)
            {
                s = (new StringBuilder()).append(s).append("&").toString();
            }

            try
            {
                s = (new StringBuilder()).append(s).append(s2).append("=").append(URLEncoder.encode(s4, "UTF-8")).toString();
            }
            catch (Exception exception1) { }
        }

        field_48477_c.clear();
        PlayerUsageSnooperThread playerusagesnooperthread = new PlayerUsageSnooperThread(this, "reporter");
        playerusagesnooperthread.setDaemon(true);
        playerusagesnooperthread.start();
    }
}
