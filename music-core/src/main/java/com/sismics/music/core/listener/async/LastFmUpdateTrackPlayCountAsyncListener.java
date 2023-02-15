package com.sismics.music.core.listener.async;

import com.sismics.music.core.event.async.LastFmUpdateAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.model.dbi.User;
import com.sismics.music.core.service.lastfm.LastFmService;
import com.sismics.music.core.util.TransactionUtil;

/**
 * Last.fm registered listener.
 *
 * @author jtremeaux
 */
public class LastFmUpdateTrackPlayCountAsyncListener extends AbstractAsyncListener<LastFmUpdateAsyncEvent>
{

	@Override
	protected void handleInternal(LastFmUpdateAsyncEvent lastFmUpdateTrackPlayCountAsyncEvent)
	{
		final User user = lastFmUpdateTrackPlayCountAsyncEvent.getUser();

		TransactionUtil.handle(() -> {
			final LastFmService lastFmService = AppContext.getInstance().getLastFmService();
			lastFmService.importTrackPlayCount(user);
		});

	}
}
