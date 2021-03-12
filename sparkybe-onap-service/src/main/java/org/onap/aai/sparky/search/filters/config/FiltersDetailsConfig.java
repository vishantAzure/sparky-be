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
package org.onap.aai.sparky.search.filters.config;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class FiltersDetailsConfig {
  @JsonProperty("filters")
  private List<UiFilterConfig> filters = new ArrayList<UiFilterConfig>();

  public FiltersDetailsConfig(){}

  @JsonCreator
  public FiltersDetailsConfig(@JsonProperty("filters") final List<UiFilterConfig> filters) {
      this.filters = filters;
  }
  
  public List<UiFilterConfig> getFilters() {
    return filters;
  }

  public void setFilters(List<UiFilterConfig> filters) {
    this.filters = filters;
  }

  @Override
  public String toString() {
    return "UiFiltersConfig [filters=" + filters + "]";
  } 

}
