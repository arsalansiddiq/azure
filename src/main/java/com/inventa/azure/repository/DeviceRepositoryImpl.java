package com.inventa.azure.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventa.azure.common.Constants;
import com.inventa.azure.converter.DeviceConverter;
import com.inventa.azure.domain.Device;
import com.inventa.azure.valueobject.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.*;


public class DeviceRepositoryImpl implements CustomDeviceRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public boolean existsByCriteria(Criteria criteria){
        Query query = new Query();
        if(criteria!=null) {
            query.addCriteria(criteria);
            return mongoTemplate.exists(query, Device.class);
        }
        return false;
    }

    @Override
    public void update(List<Map> devices, String key, DeviceConverter converter) {

        for(Map deviceMap : devices){

            try {

                Query query = new Query();

                Criteria criteria = converter.getCorrelationCriteria(deviceMap);

                if (criteria != null) {

                    query.addCriteria(criteria);

//                    Device device = mongoTemplate.findOne(query, Device.class);

                    //update all adapter data
                    mongoTemplate.updateFirst(query, Update.update(key, converter.toAdapterData(deviceMap)), Device.class);

                    //update common adapter data
                    updateCommonData(query, deviceMap, converter);

                    //update instance association ids
                    updateInstanceAssociation(deviceMap,converter);
                    
                    mongoTemplate.updateFirst(query, Update.update("isRemoved", false), Device.class);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void updateCommonData(Query query, Map deviceMap, DeviceConverter converter){

        HashMap<String,Object> commonMapToOverwrite = new HashMap<>();

        Map<String, Object> commonMap = objectMapper.convertValue(converter.toCommon(deviceMap),Map.class);
        commonMap.values().removeIf(Objects::isNull);

        commonMap.forEach( (x,y) -> {
            if(!x.equals("source") && !x.equals("fetchTime")) {
                commonMapToOverwrite.put(Constants.COMMON_ + x, y);
            }
        });

        Update update = new Update();

        try {
            Common common = converter.toCommon(deviceMap);
            if (common.getSource() != null && !common.getSource().isEmpty()) {
                update.addToSet(Constants.COMMON_ + "source", common.getSource().get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        commonMapToOverwrite.forEach(update::set);
        update.set("fetchTime",new Date());
        update.set("lastSeen",new Date());
        update.set(Constants.COMMON_ + "fetchTime", new Date());
        mongoTemplate.updateFirst(query, update, Device.class);

    }

    private void updateInstanceAssociation(Map deviceHashMap,DeviceConverter converter){

        Query query = new Query();
        query.addCriteria(converter.getCorrelationCriteria(deviceHashMap));
        query.fields().include(Constants.INSTANCE_ASSOCATION);

        Device device = mongoTemplate.findOne(query,Device.class);

        String instanceId = converter.toInstanceId(deviceHashMap);
        if(instanceId!=null && !instanceId.isEmpty()) {
            Set<String> updatedSet = new HashSet<>();
            updatedSet.add(instanceId);
            try {
                if (device.getInstanceAssociationSet() != null && !device.getInstanceAssociationSet().isEmpty()) {
                    updatedSet.addAll(device.getInstanceAssociationSet());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            mongoTemplate.updateFirst(query, Update.update(Constants.INSTANCE_ASSOCATION,updatedSet),Device.class);

        }

    }

}
