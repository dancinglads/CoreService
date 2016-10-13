package com.snp.spring.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by neeraj on 16/6/16.
 */
public class ProcessorMap {
    private static final Logger logger = Logger.getLogger(ProcessorMap.class.getName());


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private Map map;


    public HashMap getProcessors()
    {
        HashMap map = new HashMap<String,Class>();


        Set<String> keySet = this.getMap().keySet();
        Iterator<String> keySetIterator = keySet.iterator();
        while (keySetIterator.hasNext()) {
            System.out.println("------------------------------------------------");
            System.out.println("Iterating Map in Java using KeySet Iterator");
            String key = keySetIterator.next();
            System.out.println("key: " + key + " value: " + this.getMap().get(key));

            try {
                map.put(key, Class.forName(((String) this.getMap().get(key))));
            } catch (ClassNotFoundException e) {

                logger.throwing("ProcessorMap", "getProcessors()", e);
            }

        }


        return map;
    }
}
