package io.apexcreations;

import io.apexcreations.cancelling.ManualEndedConversationCanceller;
import io.apexcreations.end.ConversationEndEvent;
import io.apexcreations.end.ConversationEndListener;
import io.apexcreations.prompt.Prompt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conversation {

    private ConversationContext conversationContext;
    private boolean ended;
    private Prompt initialPrompt, currentPrompt;
    private List<ConversationEndListener> endListeners;
    private List<ConversationCanceller> cancellers;

    Conversation(Conversable forWhom, Prompt initialPrompt, Map<Object, Object> sessionData) {
        this.ended = false;
        this.initialPrompt = initialPrompt;
        this.conversationContext = new ConversationContext(forWhom, sessionData);
        this.endListeners = new ArrayList<>();
        this.cancellers = new ArrayList<>();
    }

    public void processInput(String input) {
        if (currentPrompt == null) {
            return;
        }
        cancellers.stream().filter(canceller ->
                canceller.cancelBasedOnInput(conversationContext, input))
                .forEach(canceller ->
                        end(new ConversationEndEvent(this, canceller)));
        currentPrompt = currentPrompt.processInput(conversationContext, input);
        outputNextPrompt();
    }

    public void begin() {
        if (currentPrompt != null) {
            return;
        }
        ended = false;
        currentPrompt = initialPrompt;
        outputNextPrompt();
    }

    public void outputNextPrompt() {
        if (currentPrompt == null) {
            end(new ConversationEndEvent(this));
            return;
        }
        conversationContext.getForWhom().sendMessage(currentPrompt.getPromptText(conversationContext));
        if (!currentPrompt.waitForInput(conversationContext)) {
            currentPrompt = currentPrompt.processInput(conversationContext, null);
            outputNextPrompt();
        }
    }

    public void end() {
        end(new ConversationEndEvent(this, new ManualEndedConversationCanceller()));
    }

    public void end(ConversationEndEvent event) {
        if (ended) {
            return;
        }
        this.ended = true;
        currentPrompt = null;
        conversationContext.getForWhom().endConversation(this);
        endListeners.forEach(endListener -> endListener.onEnd(event));
    }

    public synchronized void addListener(ConversationEndListener listener) {
        endListeners.add(listener);
    }

    public synchronized void removeListener(ConversationEndListener listener) {
        endListeners.remove(listener);
    }

    public void addConversationCanceller(ConversationCanceller canceller) {
        canceller.setConversation(this);
        this.cancellers.add(canceller);
    }

    public List<ConversationCanceller> getCancellers() {
        return cancellers;
    }

    public ConversationContext getConversationContext() {
        return conversationContext;
    }

    public Prompt getCurrentPrompt() {
        return currentPrompt;
    }

    public ConversationState getState() {
        return (currentPrompt != null) ? ConversationState.STARTED
                : (ended ? ConversationState.ENDED : ConversationState.UNSTARTED);
    }
}
