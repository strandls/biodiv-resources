/**
 * 
 */
package com.strandls.resource.services.Impl;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.strandls.resource.services.ResourceServices;

/**
 * @author Abhishek Rudra
 *
 */
public class ResourceServicesModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ResourceServices.class).to(ResourceServicesImpl.class).in(Scopes.SINGLETON);
	}
}
