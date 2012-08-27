package org.tophat.QRzar;

/**
 * Parses the team join QR code in order to the user to join a specific game.
 * 
 * QR Code Format:
 * 
 * _________________________________________________
 * |   0   |   1   |   2   |   3   |   4   |   5   |
 * |________________________________________________
 * | Color | <------- Player T-Shirt ID		  ---> |
 * |________________________________________________
 * 
 * 
 * @author Kevins
 */
public class QRparser
{

    private String data = "";
    
    /**
     * So that the team only needs to be extracted once in order to be used, it is saved in this variable after one
     */
    private String team;
    
    /**
     * This playerId
     */
    private Integer playerId;
    
    private boolean valid;

    public QRparser(String QRinput)
    {
    	//Clone the string
        data = new String ( QRinput);  
        
        //Ensure standard format
        data = data.toUpperCase();
        
        // Convert the team string
        if ( data.length() != 6 )
        {
        	this.valid = false;
        }
        else
        {
        	this.valid = true;
        }
    }
    
    @Override
    public String toString()
    {
    	return data;
    }

    /**
     * Gets the team from the given QRcode.
     * @return
     */
    public String getTeam()
    {
    	return ((Character)this.data.charAt(0)).toString();
    }

    /**
     * Calculates and returns the player ID which is retrieved from the given QRcode.
     * @return Returns the ID of player in the given QRcode String
     */
    public int getPlayerId()
    {
    	if (playerId == null )
    	{
	        int res = 0;
	        res += (int)data.charAt(1);
	        res = res << (int)Math.pow((double)2, (double)8);
	        res += (int)data.charAt(2);
	        res = res << (int)Math.pow((double)2, (double)8);
	        res += (int)data.charAt(3);
	        res = res << (int)Math.pow((double)2, (double)8);
	        res += (int)data.charAt(4);
	        res = res << (int)Math.pow((double)2, (double)8);
	        res += (int)data.charAt(5);
	        
	        playerId = res;
    	}
    	
        return playerId;
    }
    
    /**
     * Checks if the scanned QRcode is valid for the format required for the QRzar game
     * @return True for a valid code and false for an invalid code
     */
    public boolean isValid()
    {
    	return this.valid;
    }
}
