package com.sismics.music.core.listener.async;

import com.sismics.music.core.event.async.DirectoryDeletedAsyncEvent;
import com.sismics.music.core.model.context.AppContext;
import com.sismics.music.core.model.dbi.Directory;
import com.sismics.music.core.service.collection.CollectionService;
import com.sismics.music.core.util.TransactionUtil;

/**
 * Directory deleted listener.
 *
 * @author jtremeaux
 */
public class DirectoryDeletedAsyncListener extends AbstractAsyncListener<DirectoryDeletedAsyncEvent>
{

	@Override
	protected void handleInternal(DirectoryDeletedAsyncEvent directoryDeletedAsyncEvent)
	{
		final Directory directory = directoryDeletedAsyncEvent.getDirectory();

		TransactionUtil.handle(() -> {
			// Stop watching the directory
			AppContext.getInstance().getCollectionWatchService().unwatchDirectory(directory);

			// Remove directory from index
			CollectionService collectionService = AppContext.getInstance().getCollectionService();
			collectionService.removeDirectoryFromIndex(directory);
		});

	}
}
