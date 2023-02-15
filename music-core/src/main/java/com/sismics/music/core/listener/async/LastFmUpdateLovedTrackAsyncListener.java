package com.sismics.music.core.listener.async;

import com.sismics.music.core.event.async.LastFmUpdateAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.model.dbi.User;
import com.sismics.music.core.service.lastfm.LastFmService;
import com.sismics.music.core.util.TransactionUtil;

/**
 * Last.fm update loved tracks listener.
 *
 * @author jtremeaux
 */
public class LastFmUpdateLovedTrackAsyncListener extends AbstractAsyncListener<LastFmUpdateAsyncEvent>
{
	@Override
	protected void handleInternal(LastFmUpdateAsyncEvent lastFmUpdateAsyncEvent)
	{
		final User user = lastFmUpdateAsyncEvent.getUser();

		TransactionUtil.handle(() -> {
			final LastFmService lastFmService = AppContext.getInstance().getLastFmService();
			lastFmService.importLovedTrack(user);
		});

	}
}
