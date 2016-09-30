package com.ogliari.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class ProjectResourceConfig extends ResourceConfig{
	public ProjectResourceConfig() {
		packages("com.ogliari.service");
	}
}
