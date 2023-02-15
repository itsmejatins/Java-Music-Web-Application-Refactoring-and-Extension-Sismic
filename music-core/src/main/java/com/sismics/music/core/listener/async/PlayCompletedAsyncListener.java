package com.sismics.music.core.listener.async;

import com.google.common.eventbus.Subscribe;
import com.sismics.music.core.dao.dbi.UserDao;
import com.sismics.music.core.dao.dbi.UserTrackDao;
import com.sismics.music.core.event.async.PlayAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.model.dbi.Track;
import com.sismics.music.core.model.dbi.User;
import com.sismics.music.core.service.lastfm.LastFmService;
import com.sismics.music.core.util.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Play completed listener.
 *
 * @author jtremeaux
 */
public class PlayCompletedAsyncListener {
    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(PlayCompletedAsyncListener.class);

    /**
     * Process the event.
     *
     * @param playAsyncEvent Play completed event
     */
    @Subscribe
    public void onPlayCompleted(final PlayAsyncEvent playAsyncEvent) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("Play completed event: " + playAsyncEvent.toString());
        }

        final String userId = playAsyncEvent.getUserId();
        final Track track = playAsyncEvent.getTrack();

        TransactionUtil.handle(() -> {
            // Increment the play count
            UserTrackDao userTrackDao = new UserTrackDao();
            userTrackDao.incrementPlayCount(userId, track.getId());

            final User user = new UserDao().getActiveById(userId);
            if (user != null && user.getLastFmSessionToken() != null) {
                final LastFmService lastFmService = AppContext.getInstance().getLastFmService();
                lastFmService.scrobbleTrack(user, track);
            }
        });
    }
}
