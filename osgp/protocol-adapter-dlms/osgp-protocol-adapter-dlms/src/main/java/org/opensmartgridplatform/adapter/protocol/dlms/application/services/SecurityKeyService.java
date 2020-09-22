/**
 * Copyright 2016 Smart Society Services B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package org.opensmartgridplatform.adapter.protocol.dlms.application.services;

import org.opensmartgridplatform.adapter.protocol.dlms.domain.entities.SecurityKeyType;
import org.opensmartgridplatform.adapter.protocol.dlms.exceptions.ProtocolAdapterException;
import org.opensmartgridplatform.shared.exceptionhandling.FunctionalException;

public interface SecurityKeyService {

    //get multiple keys in one call to secret management
    byte[][] getKeys(String deviceIdentification, SecurityKeyType[] keyTypes);

    //get one specific decrypted key in a call to secret management
    byte[] getDlmsMasterKey(final String deviceIdentification);
    byte[] getDlmsAuthenticationKey(final String deviceIdentification);
    byte[] getDlmsGlobalUnicastEncryptionKey(final String deviceIdentification);
    byte[] getMbusDefaultKey(final String mbusDeviceIdentification); //G_MASTER key
    byte[] getDlmsPassword(final String deviceIdentification); //PPP_PASSWORD
    byte[] getMbusUserKey(final String mbusDeviceIdentification); //G_METER_ENCRYPTION_KEY, currently not used

    /**
     * Store new key
     * <p>
     * A new key is a security key with a device which status NEW.
     * This status is used when the new key is known, but not yet set on the device.
     * <p>
     * <strong>CAUTION:</strong> Only call this method when a successful
     * connection with the device has been set up (that is: a valid
     * communication key that works is known), and you are sure any existing new
     * key data that is not activated yet (for instance a new key stored earlier in an
     * attempt to replace the communication key that got aborted).<br>
     * <p>
     * The moment the new key is known to be transferred to the device, make
     * sure to activate it by calling
     * {@link #activateNewKey(String, SecurityKeyType)}.
     *
     * @param deviceIdentification
     *         DLMS device id
     * @param plainKeys
     *        keys to store, not encrypted
     * @param keyTypes
     *         type of key
     *
     *
     * @see #activateNewKey(String, SecurityKeyType)
     */
    void storeNewKey(final String deviceIdentification, final SecurityKeyType keyTypes, final byte[] plainKeys);

    //store multiple keys in one call
    void storeNewKeys(final String deviceIdentification, final SecurityKeyType[] keyTypes, final byte[][] plainKeys);

    /**
     * @see #storeNewKey(String, SecurityKeyType, byte[])
     */
    void aesDecryptAndStoreNewKey(final String deviceIdentification, final SecurityKeyType keyTypes,
            final byte[] aesEncryptedKeys) throws FunctionalException;

    boolean isActivated(final String deviceIdentification, final SecurityKeyType keyType);

    /**
     * Updates the state of a new key from 'new' to 'active'
     * <p>
     * This method should be called to activate a new key stored with
     * {@link #storeNewKeys(String, SecurityKeyType[], byte[][])} after it has
     * been confirmed to be set on the device.
     *
     * @param deviceIdentification
     *         DLMS device id
     * @param keyType
     *         type of key
     *
     * @throws ProtocolAdapterException
     *         if no new key is stored with the given device
     * @see #storeNewKeys(String, SecurityKeyType[], byte[][])
     */
    void activateNewKey(final String deviceIdentification, final SecurityKeyType keyType) throws ProtocolAdapterException;

    /**
     * Generates a new key that can be used as DLMS master key, authentication
     * key, global unicast encryption key, M-Bus Default key or M-Bus User key.
     * <p>
     * The master keys (DLMS master or M-Bus Default) cannot be changed on a
     * device, but can be generated for use in tests or with simulated devices.
     *
     * @return a new 16-byte AES key.
     *
    byte[] generateAES128BitsKey();*/

    byte[][] generateAES128BitsKeysAndStoreAsNewKeys(final String deviceIdentification,
            final SecurityKeyType[] keyTypes);

    //RSA decrypt key (from incoming requests) and encrypt with AES (for in memory storage)
    byte[] reEncryptKey(final byte[] externallyEncryptedKey, final SecurityKeyType keyType) throws FunctionalException;

    byte[] rsaDecrypt(final byte[] externallyEncryptedKey, final SecurityKeyType keyType) throws FunctionalException;

    //AES decrypt (decrypt memory storage for actual use of key)
    byte[] aesDecryptKey(final byte[] encryptedKey, final SecurityKeyType keyType) throws FunctionalException;

    //AES encrypt (encrypt for safe memory storage)
    byte[] aesEncryptKey(final byte[] plainKey, final SecurityKeyType keyType) throws FunctionalException;

}
