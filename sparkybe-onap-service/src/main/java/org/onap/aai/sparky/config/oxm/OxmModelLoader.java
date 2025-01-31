/**
 * ============LICENSE_START=======================================================
 * org.onap.aai
 * ================================================================================
 * Copyright © 2017-2018 AT&T Intellectual Property. All rights reserved.
 * Copyright © 2017-2018 Amdocs
 * ================================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ============LICENSE_END=========================================================
 */
package org.onap.aai.sparky.config.oxm;

import java.util.Set;

import org.eclipse.persistence.jaxb.dynamic.DynamicJAXBContext;
import org.onap.aai.cl.api.Logger;
import org.onap.aai.cl.eelf.LoggerFactory;
import org.onap.aai.nodes.NodeIngestor;
import org.onap.aai.setup.SchemaVersion;
import org.onap.aai.setup.SchemaVersions;
import org.onap.aai.sparky.logging.AaiUiMsgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("oxmModelLoader")
public class OxmModelLoader {

  private static final Logger LOG = LoggerFactory.getInstance().getLogger(OxmModelLoader.class);

  /*
   * The intent of this parameter is to be able to programmatically over-ride the latest AAI schema
   * version discovered from the aai-schema jar file. This property is optional, but if set on the
   * bean or by another class in the system, then it will override the spec version that is loaded.
   * 
   * If the latestVersionOverride is greater than 0 then it will set the latest version to the
   * specified version, and that stream will be returned if available.
   */

  protected SchemaVersion oxmApiVersion;
  protected Set<OxmModelProcessor> processors;

  private NodeIngestor nodeIngestor;

  public OxmModelLoader(String apiVersionOverride, Set<OxmModelProcessor> oxmModelProcessors) {
    this.oxmApiVersion = new SchemaVersion(apiVersionOverride);
    this.processors = oxmModelProcessors;
  }

  public OxmModelLoader(Set<OxmModelProcessor> oxmModelProcessors, SchemaVersions schemaVersions) {
    this.oxmApiVersion = schemaVersions.getDefaultVersion();
    this.processors = oxmModelProcessors;
  }

  public SchemaVersion getOxmApiVersion() {
    return oxmApiVersion;
  }

  public OxmModelLoader() {
	  
  }

  public NodeIngestor getNodeIngestor() {
    return nodeIngestor;
  }

  @Autowired
  public void setNodeIngestor(NodeIngestor nodeIngestor) {
    this.nodeIngestor = nodeIngestor;
  }

  /**
   * Load an oxm model.
   * 
   * @param inputStream file handle for oxm
   */
  public void loadModel() {
    try {
      final DynamicJAXBContext oxmContext = nodeIngestor.getContextForVersion(oxmApiVersion);
      parseOxmContext(oxmContext);
      // populateSearchableOxmModel();
      LOG.info(AaiUiMsgs.OXM_LOAD_SUCCESS, String.valueOf(oxmApiVersion));
    } catch (Exception exc) {
      LOG.info(AaiUiMsgs.OXM_PARSE_ERROR_NONVERBOSE);
      LOG.error(AaiUiMsgs.OXM_PARSE_ERROR_VERBOSE, "OXM v" + oxmApiVersion, exc.getMessage());
    }
  }

  /**
   * Parses the oxm context.
   *
   * @param oxmContext the oxm context
   */
  private void parseOxmContext(DynamicJAXBContext oxmContext) {

    if (processors != null && processors.size() > 0) {

      for (OxmModelProcessor processor : processors) {
        
        try {

          processor.processOxmModel(oxmContext);

        } catch (Exception exc) {

          LOG.warn(AaiUiMsgs.WARN_GENERIC,
              "OxmModelProcessor experienced an error. Error: " + exc.getMessage());

        }

      }

    }

  }

}
