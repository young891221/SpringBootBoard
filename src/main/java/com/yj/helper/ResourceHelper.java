package com.yj.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

public class ResourceHelper {

	@Autowired
	private ResourceUrlProvider resourceUrlProvider;

	public CharSequence src(String path) {
		return this.resourceUrlProvider.getForLookupPath(path);
	}

}
