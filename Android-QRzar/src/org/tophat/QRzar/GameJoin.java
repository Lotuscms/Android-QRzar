package org.tophat.QRzar;

import org.tophat.QRzar.mapper.PlayerMapper;
import org.tophat.QRzar.models.Player;
import org.tophat.android.exceptions.HttpException;
import org.tophat.android.mapping.Game;
import org.tophat.android.networking.ApiCommunicator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GameJoin extends Activity{

    /* Called when the app is first started. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameplay);
        
        TextView ta  = (TextView) findViewById(R.id.player_team);
        
        ta.setText((MainScreenActivity.playerDetails.getTeam()));
        
        TextView ta1  = (TextView) findViewById(R.id.playerId);
        
        ta1.setText(""+(MainScreenActivity.playerDetails.getPlayerId()));
        
        qrcodeScan();
    }
    
    @Override
    public void onStart(){
    	super.onStart();
    }
    
    public void qrcodeScan()
    {
		Intent intent = new Intent("com.google.zxing.client.android.SCAN_QR");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0x01);
    }
    
    public void onActivityResult(int requestCode, int resultCode, Intent intent) 
    {
    	  if (intent != null) 
    	  {
    		  String response = intent.getStringExtra("SCAN_RESULT");
    		  
    		  QRzarLaunch.showNotification("The Scanned QR Code was invalid.");
    		  
    		  Game g = new Game();
    		  
    		  g.setId(Integer.parseInt(response));
    		  
    		  Player p = new Player();
    		  p.setGame(g);
    		  p.setQrcode(MainScreenActivity.playerDetails.toString());
    		  p.setName("Anon");
    		  
    		  PlayerMapper pm = new PlayerMapper(QRzarLaunch.apic);
    		  
    		  try
    		  {
    			  pm.create(p);
    		  }
    		  catch (HttpException e)
    		  {
    			  e.printStackTrace();
    		  }
    	  }
    }
}
