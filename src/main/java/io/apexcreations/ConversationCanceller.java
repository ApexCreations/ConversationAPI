package io.apexcreations;

public interface ConversationCanceller extends Cloneable {

    void setConversation(Conversation conversation);

    boolean cancelBasedOnInput(ConversationContext conversationContext, String input);

    ConversationCanceller clone();
}
