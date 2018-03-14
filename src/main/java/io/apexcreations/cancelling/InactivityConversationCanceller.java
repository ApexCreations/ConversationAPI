package io.apexcreations.cancelling;

import io.apexcreations.ConversationContext;
import io.apexcreations.Conversation;
import io.apexcreations.ConversationCanceller;
import io.apexcreations.ConversationState;
import io.apexcreations.end.ConversationEndEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class InactivityConversationCanceller implements ConversationCanceller {

    static ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> future;
    private Conversation conversation;
    private long timeout;
    private TimeUnit timeUnit;

    public InactivityConversationCanceller(long timeout, TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    @Override
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
        startTimer();
    }

    @Override
    public boolean cancelBasedOnInput(ConversationContext conversationContext, String input) {
        stopTimer();
        startTimer();
        return false;
    }

    private void startTimer() {
        future = service.schedule(() -> {
            if (conversation.getState() == ConversationState.UNSTARTED) {
                startTimer();
                return;
            } else if (conversation.getState() == ConversationState.STARTED) {
                cancelling(conversation);
            }
            conversation.end(new ConversationEndEvent(conversation, this));
        }, timeout, timeUnit);
    }

    private void stopTimer() {
        future.cancel(true);
    }

    @Override
    public ConversationCanceller clone() {
        return new InactivityConversationCanceller(timeout, timeUnit);
    }

    protected void cancelling(Conversation conversation) {
    }
}
