package io.apexcreations.end;

import java.util.EventListener;

public interface ConversationEndListener extends EventListener {

    public void onEnd(ConversationEndEvent event);

}
