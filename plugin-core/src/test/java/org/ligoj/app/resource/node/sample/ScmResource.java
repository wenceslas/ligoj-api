package org.ligoj.app.resource.node.sample;

import org.springframework.stereotype.Component;

import org.ligoj.app.resource.plugin.AbstractServicePlugin;



/**
 * The Source Code Management service.
 */
@Component
public class ScmResource extends AbstractServicePlugin {

	/**
	 * Plug-in key.
	 */
	public static final String SERVICE_URL = BASE_URL + "/scm";

	/**
	 * Plug-in key.
	 */
	public static final String SERVICE_KEY = SERVICE_URL.replace('/', ':').substring(1);

	/**
	 * SCM site URL
	 */
	public static final String PARAMETER_URL = SERVICE_KEY + ":url";

	@Override
	public String getKey() {
		return SERVICE_KEY;
	}

}
