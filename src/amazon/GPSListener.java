/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
*/

package plugin.geolocation;

import android.os.Looper;
import android.location.LocationManager;

/**
 * This class handles requests for GPS location services.
 *
 */
public class GPSListener extends CordovaLocationListener {
    public GPSListener(LocationManager locationManager, GeoBroker m) {
        super(locationManager, m, "[Cordova GPSListener]");
    }


    /**
     * Start requesting location updates.
     *
     * @param interval
     */
    @Override
    protected void start() {
        if (!this.running) {
            if (this.locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
                this.running = true;
                this.locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 0, this, Looper.getMainLooper());
            } else {
                this.fail(CordovaLocationListener.POSITION_UNAVAILABLE, "GPS provider is not available.");
            }
        }
    }
}
