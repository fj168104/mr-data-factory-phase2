package com.mr.modules.api.caller;

import com.mr.modules.api.site.ResourceGroup;
import com.mr.modules.api.site.SiteTask;

import java.util.concurrent.Future;

/**
 * Created by feng on 18-3-16
 */
public interface SiteVisitor<T> {

	Future<String> visit(SiteTask task);
}
