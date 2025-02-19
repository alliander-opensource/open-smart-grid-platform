/*
 * Copyright 2022 Alliander N.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package org.opensmartgridplatform.simulator.protocol.dlms.cosem;

import org.openmuc.jdlms.AttributeAccessMode;
import org.openmuc.jdlms.CosemAttribute;
import org.openmuc.jdlms.CosemClass;
import org.openmuc.jdlms.CosemInterfaceObject;
import org.openmuc.jdlms.datatypes.DataObject;
import org.openmuc.jdlms.datatypes.DataObject.Type;

@CosemClass(id = 22)
public class SingleActionScheduler extends CosemInterfaceObject {

  @CosemAttribute(id = 2, type = Type.STRUCTURE, accessMode = AttributeAccessMode.READ_ONLY)
  public DataObject executedScript;

  @CosemAttribute(id = 3, type = Type.ENUMERATE, accessMode = AttributeAccessMode.READ_ONLY)
  public DataObject type;

  @CosemAttribute(id = 4, type = Type.ARRAY, accessMode = AttributeAccessMode.READ_AND_WRITE)
  public DataObject executionTime;

  public SingleActionScheduler(final String obisCode) {
    super(obisCode);
  }
}
