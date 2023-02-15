package com.sismics.music.core.listener.async;

import com.sismics.music.core.event.async.DirectoryModificationAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.model.dbi.Directory;
import com.sismics.music.core.service.collection.CollectionService;
import com.sismics.music.core.util.TransactionUtil;

/**
 * New directory created listener.
 *
 * @author jtremeaux
 */
public class DirectoryCreatedAsyncListener extends AbstractAsyncListener<DirectoryModificationAsyncEvent>
{

	@Override
	protected void handleInternal(DirectoryModificationAsyncEvent directoryModificationAsyncEvent)
	{
		final Directory directory = directoryModificationAsyncEvent.getDirectory();

		TransactionUtil.handle(() -> {
			// Index new directory
			CollectionService collectionService = AppContext.getInstance().getCollectionService();
			collectionService.addDirectoryToIndex(directory);

			// Watch new directory
			AppContext.getInstance().getCollectionWatchService().watchDirectory(directory);

			// Update the scores
			collectionService.updateScore();
		});

	}
}
