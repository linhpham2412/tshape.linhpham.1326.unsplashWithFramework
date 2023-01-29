package nt.tshape.automation.selenium;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestContext {
    private final Map<String, String> contextAttribute = new HashMap<>();
    private Customer_Information customerInformation;
    private final Map<String, ArrayList<?>> contextObjects = new HashMap<>();

    public Customer_Information getCustomerInformation() {
        return customerInformation;
    }

    public Customer_Information setCustomerInformation(Customer_Information customerInformation) {
        this.customerInformation = customerInformation;
        return customerInformation;
    }

    public void setAttribute(String key, String value) {
        contextAttribute.put(key, value);
    }

    public String getAttributeByName(String keyName) {
        return contextAttribute.get(keyName);
    }

    public List<?> getContextObjectsWithName(String objectName) {
        return contextObjects.get(objectName);
    }

    public void setContextObjectsWithName(String objectName, ArrayList<?> listObjects) {
        contextObjects.put(objectName, listObjects);
    }

}
