package org.tophat.QRzar.models;

import java.util.ArrayList;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * This class is mapping for the user 
 * @author Kevin
 *
 */
public class Kill extends org.tophat.android.mapping.Kill implements Parcelable {

	private String victimQrcode;


	/**
	 * 
	 */
	public Kill()
	{
		super();
		
		this.setKiller(null);
		this.setVictim(null);
		this.setTime(null);
		this.setVerified(null);
	}
	
	/**
	 * Setup the User object with the details supplied from the JSON from the platform.
	 * @param kill
	 */
	public Kill(Map<String, Object> kill)
	{
		super (kill);
		
		if (kill.containsKey("victim_qrcode"))
			this.setVictimQrcode((String)kill.get("victim_qrcode"));
	}
	
	// Parcelling part
	public Kill(Parcel in){
		super(in);
		this.setVictimQrcode(in.readString());
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(this.getVictimQrcode());
	}

	/**
	 * @return the victimQrcode
	 */
	public String getVictimQrcode() {
		return victimQrcode;
	}

	/**
	 * @param victimQrcode the victimQrcode to set
	 */
	public void setVictimQrcode(String victimQrcode) {
		this.victimQrcode = victimQrcode;
	}
}
