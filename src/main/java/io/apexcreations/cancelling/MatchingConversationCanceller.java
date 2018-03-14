package io.apexcreations.cancelling;

import io.apexcreations.ConversationContext;
import io.apexcreations.Conversation;
import io.apexcreations.ConversationCanceller;

public class MatchingConversationCanceller implements ConversationCanceller {
    private final String exitCommand;

    public MatchingConversationCanceller(String exitCommand) {
        this.exitCommand = exitCommand;
    }

    @Override
    public void setConversation(Conversation conversation) {}

    @Override
    public boolean cancelBasedOnInput(ConversationContext conversationContext, String input) {
        return input.equals(exitCommand);
    }

    @Override
    public ConversationCanceller clone() {
        return new MatchingConversationCanceller(exitCommand);
    }
}
