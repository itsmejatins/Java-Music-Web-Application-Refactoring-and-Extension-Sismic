package com.sismics.music.core.listener.async;

import com.google.common.base.Stopwatch;
import com.google.common.eventbus.Subscribe;
import com.sismics.music.core.event.async.TrackLikedUnlikeAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.model.dbi.Track;
import com.sismics.music.core.model.dbi.User;
import com.sismics.music.core.service.lastfm.LastFmService;
import com.sismics.music.core.util.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * Track liked listener.
 *
 * @author jtremeaux
 */
public class TrackLikedAsyncListener extends AbstractAsyncListener<TrackLikedUnlikeAsyncEvent>
{

	@Override
	protected void handleInternal(TrackLikedUnlikeAsyncEvent trackLikedUnlikeAsyncEvent)
	{
		final User user = trackLikedUnlikeAsyncEvent.getUser();
		final Track track = trackLikedUnlikeAsyncEvent.getTrack();

		TransactionUtil.handle(() -> {
			if (user.getLastFmSessionToken() != null)
			{
				final LastFmService lastFmService = AppContext.getInstance().getLastFmService();
				lastFmService.loveTrack(user, track);
			}
		});

	}
}
