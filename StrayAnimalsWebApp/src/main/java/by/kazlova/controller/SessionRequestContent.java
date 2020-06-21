package by.kazlova.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class SessionRequestContent {
    private static final int FIRST_PARAMETER_INDEX = 0; // ?
    private Map<String, Object> requestAttributes;
    private Map<String, String[]> requestParameters;
    private Map<String, Object> sessionAttributes;
    private boolean invalidate = false; // ??

    public SessionRequestContent(HttpServletRequest request) {
        requestAttributes = new HashMap<>();
        requestParameters = new HashMap<>();
        sessionAttributes = new HashMap<>();
        extractValues(request);
    }

    private void extractValues(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Enumeration<String> sessionAttributesIterator = session.getAttributeNames(); //берем все, что лежит в сессии, а в сесии лежит все то, что мы туда положили до этого
        while (sessionAttributesIterator.hasMoreElements()) {
            String name = sessionAttributesIterator.nextElement();
            sessionAttributes.put(name, session.getAttribute(name)); // еще раз читаем про структуру Map
        }

        Enumeration<String> requestAttributesIterator = request.getAttributeNames();//сначала положили значения в request, а потом при передачи то, что ложим в request
        while (requestAttributesIterator.hasMoreElements()) {
            String name = requestAttributesIterator.nextElement();
            requestAttributes.put(name, request.getAttribute(name));

        }

        requestParameters = request.getParameterMap();// достаем то, что пришло с front
    }

    public void setAttribute(String name, Object value) {
        requestAttributes.put(name, value);
    }

    public Object getAttribute(String attribute) { // может быть null при вызове этого метода???
        return requestAttributes.get(attribute);
    }

    public Object getSessionAttribute(String name) { // Object, так как мы не знаем что придет?
        return sessionAttributes.get(name);
    }

    public void setSessionAttribute(String name, Object value) {
        sessionAttributes.put(name, value);
    }

    public String getParameter(String name) { // ??
        String[] strings = requestParameters.get(name);
        if (strings != null) {
            return strings[FIRST_PARAMETER_INDEX];
        } else {
            return null; // ???
        }
    }

    public String[] getParametersValue(String name) {
        return requestParameters.get(name);
    }

    public void insert(HttpServletRequest request) { //наполняем request из content и отдаем jsp
        requestAttributes.forEach(request::setAttribute);
        HttpSession session = request.getSession();
        sessionAttributes.forEach(session::setAttribute);
        if (invalidate) {
            request.getSession().invalidate(); // invalidate() - удаляет из сесии все объекты
        }
    }

    public void invalidateSession() { // ??
        this.invalidate = true;
    }
}
