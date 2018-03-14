package io.apexcreations.end;

import io.apexcreations.ConversationContext;
import io.apexcreations.Conversation;
import io.apexcreations.ConversationCanceller;
import java.util.EventObject;

public class ConversationEndEvent extends EventObject {

    private final ConversationContext context;
    private final ConversationCanceller canceller;

    public ConversationEndEvent(Conversation conversation) {
        this(conversation, null);
    }

    public ConversationEndEvent(Conversation conversation, ConversationCanceller canceller) {
        super(conversation);
        this.context = conversation.getConversationContext();
        this.canceller = canceller;
    }

    public boolean forcedExit() {
        return canceller == null;
    }

    public ConversationContext getContext() {
        return context;
    }

    public ConversationCanceller getCanceller() {
        return canceller;
    }
}
