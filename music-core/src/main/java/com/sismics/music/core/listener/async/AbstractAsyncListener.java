package com.sismics.music.core.listener.async;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.google.common.eventbus.Subscribe;

public abstract class AbstractAsyncListener<T>
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Stopwatch doBefore(T t)
	{
		if (log.isInfoEnabled())
		{
			log.info(t.getClass() + ": " + t.toString());
		}
		return Stopwatch.createStarted();
	}

	private void doAfter(T t, Stopwatch stopwatch)
	{
		if (log.isInfoEnabled())
		{
			log.info(MessageFormat.format(t.getClass() + " completed in {0}", stopwatch));
		}
	}

	protected abstract void handleInternal(T t);

	@Subscribe
	public void handle(final T t) throws Exception
	{
		Stopwatch stopwatch = doBefore(t);
		handleInternal(t);
		doAfter(t, stopwatch);
	}
}
