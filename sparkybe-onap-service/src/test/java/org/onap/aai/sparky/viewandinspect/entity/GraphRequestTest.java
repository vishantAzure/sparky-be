/**
 * ============LICENSE_START===================================================
 * SPARKY (AAI UI service)
 * ============================================================================
 * Copyright © 2017 AT&T Intellectual Property.
 * Copyright © 2017 Amdocs
 * All rights reserved.
 * ============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=====================================================
 *
 * ECOMP and OpenECOMP are trademarks
 * and service marks of AT&T Intellectual Property.
 */

package org.onap.aai.sparky.viewandinspect.entity;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.onap.aai.sparky.viewandinspect.entity.GraphRequest;

public class GraphRequestTest {

	private GraphRequest graphRequest; 
	 
	@Before
	  public void init() throws Exception {
		graphRequest = new GraphRequest();
	      
	  }
	
	@Test 
	public void updateValues() {
	
		graphRequest.setHashId("364c836b7f4c0d2a5b917693719741fa5e576b3da818a");
		assertNotNull(graphRequest.getHashId());
		graphRequest.setIncludeGraphMeta(true);
		assertTrue(graphRequest.isIncludeGraphMeta());
		assertNotNull(graphRequest.toString());
		graphRequest.setHashId(null);
		assertNull(graphRequest.getHashId());
		assertNotNull(graphRequest.toString());
		
		
	}

}
