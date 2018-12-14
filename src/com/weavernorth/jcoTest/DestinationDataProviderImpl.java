package com.weavernorth.jcoTest;

import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;

import java.util.HashMap;
import java.util.Properties;

public class DestinationDataProviderImpl implements DestinationDataProvider {

    private HashMap<String, Properties> destinationMap = new HashMap<String, Properties>();
    private static DestinationDataProviderImpl providerImpl = new DestinationDataProviderImpl();

    private DestinationDataProviderImpl() {
    }

    public static DestinationDataProviderImpl getInstance() {
        return providerImpl;
    }

    @Override
    public Properties getDestinationProperties(String destinationName) {
        if (destinationMap.containsKey(destinationName)) {
            return destinationMap.get(destinationName);
        } else {
            return null;
        }
    }

    public synchronized void addDestination(String destinationName, Properties properties) {
        destinationMap.put(destinationName, properties);
    }

    @Override
    public boolean supportsEvents() {
        return false;
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener destinationDataEventListener) {

    }
}
