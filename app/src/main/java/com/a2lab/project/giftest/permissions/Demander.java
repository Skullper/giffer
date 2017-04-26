package com.a2lab.project.giftest.permissions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.tbruyelle.rxpermissions.Permission;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by pugman on 09.02.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class Demander{

	private final RxPermissions rxPermissions;
	private       String[]      permissions;
	private final Activity activity;

	public Demander(Activity activity){
		this.rxPermissions = new RxPermissions(activity);
		this.activity = activity;
	}

	/**
	 * Ask user to confirm displayed permissions
	 *
	 * @param permissions all needed permissions
	 */
	public void demand(final String... permissions){
		copyToArray(permissions);
		rxPermissions.requestEach(permissions).subscribe(new Action1<Permission>(){
			@Override
			public void call(Permission permission){
				if(!permission.granted)
					if(permission.shouldShowRequestPermissionRationale)
						demandPermissionAgain(permission.name);
			}
		});
	}

	/**
	 * Checking if all permissions granted
	 *
	 * @return true if granted otherwise false
	 */
	public boolean isGranted(){
		boolean granted = true;
		for(String permission : permissions){
			if(!rxPermissions.isGranted(permission))
				granted = false;
		}
		return granted;
	}

	private void demandPermissionAgain(String permission){
		rxPermissions.request(permission).subscribe(new Action1<Boolean>(){
			@Override
			public void call(Boolean isGranted){
				if(!isGranted) {
					Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", activity.getPackageName(), null));
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					activity.startActivity(intent);
				}
			}
		});
	}

	private void copyToArray(String... permissions){
		this.permissions = new String[permissions.length];
		System.arraycopy(permissions, 0, this.permissions, 0, permissions.length);
	}
}
