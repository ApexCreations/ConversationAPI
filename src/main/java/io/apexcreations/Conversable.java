package io.apexcreations;

import io.apexcreations.end.ConversationEndEvent;

public interface Conversable {

    boolean isConversing();

    void processConversationInput(String input);

    boolean beginConversation(Conversation conversation);

    void endConversation(Conversation conversation);

    void endConversation(Conversation conversation, ConversationEndEvent event);

    void sendMessage(String message);
}
