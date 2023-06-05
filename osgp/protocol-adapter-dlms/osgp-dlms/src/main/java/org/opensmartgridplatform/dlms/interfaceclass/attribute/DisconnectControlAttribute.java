// SPDX-FileCopyrightText: 2023 Contributors to the GXF project
// SPDX-FileCopyrightText: Copyright Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0

package org.opensmartgridplatform.dlms.interfaceclass.attribute;

import org.opensmartgridplatform.dlms.interfaceclass.InterfaceClass;

/** This class contains the attributes defined for IC DisconnectControl. */
public enum DisconnectControlAttribute implements AttributeClass {
  LOGICAL_NAME(1),
  OUTPUT_STATE(2),
  CONTROL_STATE(3),
  CONTROL_MODE(4);

  static final InterfaceClass INTERFACE_CLASS = InterfaceClass.DISCONNECT_CONTROL;

  private final int attributeId;

  private DisconnectControlAttribute(final int attributeId) {
    this.attributeId = attributeId;
  }

  @Override
  public InterfaceClass interfaceClass() {
    return INTERFACE_CLASS;
  }

  @Override
  public int attributeId() {
    return this.attributeId;
  }

  @Override
  public String attributeName() {
    return this.name();
  }
}
