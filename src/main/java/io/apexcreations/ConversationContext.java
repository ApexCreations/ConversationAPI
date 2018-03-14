package io.apexcreations;

import java.util.Map;

public class ConversationContext {

    private final Map<Object, Object> sessionData;
    private final Conversable forWhom;

    public ConversationContext(Conversable forWhom, Map<Object, Object> sessionData) {
        this.forWhom = forWhom;
        this.sessionData = sessionData;
    }

    public Object getSessionData(Object key) {
        return sessionData.get(key);
    }

    public void setSessionData(Object key, Object value) {
        sessionData.putIfAbsent(key, value);
    }

    public Map<Object, Object> getSessionData() {
        return sessionData;
    }

    public Conversable getForWhom() {
        return forWhom;
    }
}
