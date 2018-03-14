package io.apexcreations.cancelling;

import io.apexcreations.ConversationContext;
import io.apexcreations.Conversation;
import io.apexcreations.ConversationCanceller;

public class ManualEndedConversationCanceller implements ConversationCanceller {

    @Override
    public void setConversation(Conversation conversation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean cancelBasedOnInput(ConversationContext conversationContext, String input) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConversationCanceller clone() {
        throw new UnsupportedOperationException();
    }
}
