package com.sismics.music.core.listener.async;

import com.sismics.music.core.event.async.CollectionReindexAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.service.collection.CollectionService;
import com.sismics.music.core.util.TransactionUtil;

/**
 * Collection reindex listener.
 *
 * @author jtremeaux
 */
public class CollectionReindexAsyncListener extends AbstractAsyncListener<CollectionReindexAsyncEvent>
{

	@Override
	protected void handleInternal(CollectionReindexAsyncEvent t)
	{
		TransactionUtil.handle(() -> {
			// Reindex the whole collection
			CollectionService collectionService = AppContext.getInstance().getCollectionService();
			collectionService.reindex();

			// Update the scores
			collectionService.updateScore();
		});

	}
}
