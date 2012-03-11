package net.minecraft.src;

import java.net.ConnectException;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;

class ThreadConnectToServer extends Thread
{
    /** A reference to the Minecraft object. */
    final Minecraft mc;
    final String field_48479_b;

    /** A reference to the Minecraft object. */
    final int port;

    /** A reference to the GuiConnecting object. */
    final GuiConnecting connectingGui;

    ThreadConnectToServer(GuiConnecting par1GuiConnecting, Minecraft par2Minecraft, String par3Str, int par4)
    {
        connectingGui = par1GuiConnecting;
        mc = par2Minecraft;
        field_48479_b = par3Str;
        port = par4;
    }

    public void run()
    {
        try
        {
            GuiConnecting.setNetClientHandler(connectingGui, new NetClientHandler(mc, field_48479_b, port));

            if (GuiConnecting.isCancelled(connectingGui))
            {
                return;
            }

            GuiConnecting.getNetClientHandler(connectingGui).addToSendQueue(new Packet2Handshake(mc.session.username, field_48479_b, port));
        }
        catch (UnknownHostException unknownhostexception)
        {
            if (GuiConnecting.isCancelled(connectingGui))
            {
                return;
            }

            mc.displayGuiScreen(new GuiDisconnected("connect.failed", "disconnect.genericReason", new Object[]
                    {
                        (new StringBuilder()).append("Unknown host '").append(field_48479_b).append("'").toString()
                    }));
        }
        catch (ConnectException connectexception)
        {
            if (GuiConnecting.isCancelled(connectingGui))
            {
                return;
            }

            mc.displayGuiScreen(new GuiDisconnected("connect.failed", "disconnect.genericReason", new Object[]
                    {
                        connectexception.getMessage()
                    }));
        }
        catch (Exception exception)
        {
            if (GuiConnecting.isCancelled(connectingGui))
            {
                return;
            }

            exception.printStackTrace();
            mc.displayGuiScreen(new GuiDisconnected("connect.failed", "disconnect.genericReason", new Object[]
                    {
                        exception.toString()
                    }));
        }
    }
}
