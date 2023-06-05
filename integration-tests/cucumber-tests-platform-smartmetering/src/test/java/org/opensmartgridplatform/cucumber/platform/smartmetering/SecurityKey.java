// SPDX-FileCopyrightText: 2023 Contributors to the GXF project
// SPDX-FileCopyrightText: Copyright Contributors to the GXF project
//
// SPDX-License-Identifier: Apache-2.0

package org.opensmartgridplatform.cucumber.platform.smartmetering;

/*
 * The SOAP-request keys are RSA encrypted using 'RSA/ECB/OAEPPadding' transformation
 */
public enum SecurityKey {
  SECURITY_KEY_A(
      "bf1a7553451978483b6bcaf8d6d3135d6d7879ef8e353149aff4e791321df5b5f572c7bcc8937e02048a1f50483344ea",
      "5eef89cb53cb3319049403d26d73ff640f7c48512d9d4da2b4d54a75a0276402"
          + "db1ea7c1dc2907ff2ca1ddb8b72fe08cc9b07632c688596b2608462016110897"
          + "9b4ac3e39dcd3667a543bb2a6cf56a56ddddece6913e6244d5d5d539eb17ba1c"
          + "7c214a8a5f3ce74bb63075928cd28f443532ec1c662f6c90d18fa9f1e34ca0d5"
          + "cd0e7224a9b422229d434a0f469ff137c37ef8fbf67001b4cd484a59daac38a0"
          + "73cbacfd9f34b43dfccfd0e194b5fd5fafd7e6d3ed2806efc2fab6d21272ec8b"
          + "88573043d11ea270a3de7fd9175db344862c974c9528ac5b94d0c109665fb80f"
          + "e94eaa4a942a07110be991a51028aedf6cc2c5668267d8737b075c013a55a4c9"),
  SECURITY_KEY_E(
      "319d4a83c11500297ee85dd56e6708b786b6d0b202631ceb3bc6645a585802ec5ba8b6b1e15d73ce5ba534feb26a38db",
      "4b8d8a0beac5078a4d3d39d9628ec5f94781cf153f739c07d1ba64ae581fd81c"
          + "cba91aae24188e12e55c6afbe97cd5fc9473488fafddc603fd59a4658885bd61"
          + "47756e42e5259e9bb7ef4b8f5212e92793d47cce79ec9b5f3be0015ff1e372d2"
          + "f641346a50cacaa85e51de62403b2d2ae73298d05bf65440cfeb5d162f4f5076"
          + "8b2c0af8a5611d1379cfdb62b00314eaf8616dee6454974c60d14ef61e050fe6"
          + "1fa8117f7bdc49f2876aca75c4ea1f253656a981bf9d86370ad5841766234207"
          + "c204634656f349ed7156f6e3685e415389d6f9352c388439c7e4c6c8491a896d"
          + "c008e121ae3bd7b8bc21a44174f31463e7f4433f5bbbad7c7a5cb0c90634f08c"),
  SECURITY_KEY_M(
      "fc4f59bbbf0ed195a22c23aacd91d8522fdd6d51d22de9cb2cc3573b76916ae9f9eda6f3be70e87312664eee537c0923",
      "95f993610f3a5956062052815a7dcb95c4165b361b9cb633a7aa16bd4c402235"
          + "4859ac0668a37476508ebe912d2e55a819c2cd233d6c239b272a86b1fc8416d6"
          + "1c15ccb3a5b195f75eb3f6b7a34c2e6780d6d2835a30d6ef23df1228bacf04bc"
          + "e3ce2da041f8d64afd9eeb21903e5032465cb518a8c1dd91dc51b2163d380ae2"
          + "2b13943bfa5898e06741355516dd433dae3fa9fe8edaacb3b9459ac68f650a0d"
          + "d7a4eab9317f6e9f9b99dcee8f0b3fdd81751e6eb9a143656f40aa15bd32feca"
          + "fd2b76ebc1cba421cc205941552a00a4e645d2370a4995052935d55af515f739"
          + "4bcddf173ad2082ad206fcb894ddbd3a7abe8196f6d0ad78535bd0bb510c4828"),
  MBUS_USER_KEY(
      "a87a468a065bd4311894dfb2902891cda56e3da7540147f6d51545a79a9c2c608f65bb3e7919ff1c0b5feb315cfed2d8",
      "005528c5f47bd5abeae2eff7bcb36cde0910b97fbc2e88ee0afd80b6629bd760"
          + "0777755e734334bb33772fce892df58ba4bc29a31f836a468bb441ffb6fefe93"
          + "846976710ca4cd500bc41a603d3a206db206f83e32b1129d22511911ba35e357"
          + "6ca5d56e08de3d50e402c96e550af07b75f8a7362bf6398e8fd23f597fe843ee"
          + "1f091f87db6c50754183df729b86afe2f060332e01451ecfad5b508fbc674a29"
          + "fde420eab3220069fcecee3ac0c704ed3bc2f8ec0aad25844bf62548cf1afa79"
          + "16b93d841d37ad26373d776e4fef04adc592cb55a51891c536dd3a089fbf0507"
          + "98b9468db5f66c6a1c057796a9ba34cbba6d8dec86b3b8d4ad437429cbdd7abe"),
  SECURITY_KEY_G_ENCRYPTION(
      "23c7754b66d48da6d34cc72b1ae68188144c7488ae409eb04bd32af91d44e902450ca1739c42fd5f005624b8fa26d65e",
      null),
  SECURITY_KEY_G_MASTER(
      "23c7754b66d48da6d34cc72b1ae68188144c7488ae409eb04bd32af91d44e902450ca1739c42fd5f005624b8fa26d65e",
      null),

  LLS_PASSWORD(
      "a23e88d5a3145b9b8b6f621fc58af6ebc0e24e7d01c1897bfb88407d6f1ca7437319c744edad1484", ""),

  SECURITY_KEY_1(
      "9367e7b78e6ebf40c9e7c1a7fff1955af89d0436abeb8c1f91a4931ab9bb339ade708df02f54ba4ff309eecf30289115",
      "36f60f8d9f6477b63a8d00bf20593df3f966079128e60a096fca163f8ea1e411"
          + "914347f3fb42d01dca032a2ac815a5cae413a70a99d0f69c86f86cdb8c140669"
          + "8998b52806f2a1e949fdddc6a749c16f2de4d6a86ae35ffeb94c5c57b4bb3641"
          + "e806ebfe6ed7c595fe8c99619a3a3c1eb6cb7ce8d3af64f8274c0472469cdbb7"
          + "263fca7de4f6eac4dd1e30d70a43e61daf518b752658ab348043e1f71f962ce4"
          + "6cebfe23c3219838d16bda1e5c337e7a1ba256318c3c35d2e7435dde1091c3c5"
          + "bb54ef2c505696028f44e1cf0df0a4fe1683a836f7d2d173b73cb59b86ac107d"
          + "03a3b4ca9407e6d12eb6cbd9af11412a6d6e63b09f42bfda08df6bb6425cba8b"),
  SECURITY_KEY_2(
      "1489ddf11c82217a216578db3e2455333a9c6c0c4b58c67700bfe4d6dfd62198c33aa1454c1c13883915dbdd67ab51f8",
      "02835b236bfe5fe65b2340c35d80c1bf15ecda58bccbc780ff614ce4e3c72014"
          + "32968c6a45662296b368337fbe71c129720829753f2a52482e3e792c441b3260"
          + "2e96ed2a869c3ca4531dd726eaf2d16d44eb1618447c7babe6f70f525773756f"
          + "76656b2bb790f58d347ae30483229309cdcf88e65d092940a3b6d50425774212"
          + "e9d0e4fc221300a62a3e91597debf93fdf7819c5f19492d74f87ae0286b5f955"
          + "6b6c7b2d464b7462ae9d9a6cb57f0c9fc6a8e9a1d77f6ba293b8410350a455c6"
          + "d553e6c99f79eca124b20acd88e167596161a9092f44ecfc3b42dba154f9e7b1"
          + "ede9a36da4869c45daa8d7ce3b9bc499f3e959d4d0f837eeeab7fe92b5492c0a"),
  SECURITY_KEY_3(
      "588dda1fd5d22436a136cb55ea0151dd0df992ad05011681e948b7cce25d659417621810d24ad65d96a416717bf0c29b",
      "4924196175a983c496a1b7e327e7925a60aaf29036941ba2115da58b9b7aa972"
          + "ca4ec6cae58f73e63e6d0b48ed29ac15b2d5b48b773194c8a8d23042b78412c0"
          + "c23fb8bf753ebc2033cf614beaab493ab2873e3597b5faaaab343b7225c06f39"
          + "50754f4f29fd3680cd9697074dedb33c8e11861c5a38166a5abc7e9a6906c041"
          + "4af8d579ffcf94d754a439e3edf8c129f682038e7b4671c5837cc6839c3baa65"
          + "0aad7e6d779bd5b565ec3fa80196fe1b7842ea28a90babbfe7e6bb7b643160f5"
          + "8bb86527ee95adc154c95b2cd07b956da59891bea4c35305fc1e6f535297afc2"
          + "5b2f495a76d3d4333ddf4de5766e14a3a7924fb699bcc7a8ee25068690fb5403"),
  SECURITY_KEY_4(
      "a976789ab3ffa175b861d736b3c9ca66691e176c7dceea9d287187cf1f9fc160ff65aaf3ab4e5694931ba26c3039be76",
      "4e5d93666aee922e0e09985383f74f5dfeae149a9e3a7b5430967b61d5e4e74a"
          + "d83127728bf61cf1ab09083bc14f21a3c67176323b95666614f6cadb69606b5d"
          + "2a950a892d65576d69e25b5a6de92f9b66be32d1f0f43beddd957d534ed2f615"
          + "078017affbd4e60d3154ef2d6e13388a6d6892146766d7a024f3eda61a4ad6ce"
          + "36629367c24dbf495dd84ab44d87e8b2b4f5b903f0b600abfef32f3775e6f6fc"
          + "b007b309fe8eae5b56df9a682feccb015c7e1018b83fc2b3374b3a6b72909642"
          + "136e41edc62846fa8560a8ac2723cd20e0e07898911b91958131d15f593d5f55"
          + "4742304eb987256d2f3084cd9b7fc031f656ce37a47efc473832c10fad7a32c4"),
  SECURITY_KEY_5(
      "ef28b4e8521aea7bb41660812ca10a48793c26c002dd11e064dfe786d0546c065d09c31006676efcdaa877a1c8d747a1",
      "4e1c2f3c7cebf98942f7902758ad4153251472db7cddc332627d20a0ddc22f87d63f9a65e8fe99f83cfdcc3b8290b3086a8085ea0db6118fb686156c0101960561a73771e3044f622445fdd6e6ecb4e79616e0c1433dbded6a008aae15498f69dddc17c2fc97821af5edf80b1fcf3a95cab11af3f26258778bb5fea47e85b7b56aa2118ea702ab09db919c40db85897770826942a3f45c49982c7e8f725694528732d82de0948b3ee75fdc5b0cc87c8adb1700b41411fa1e05cb43eb386df88bac5d79444efd77df07cd41505534dd9b6214257f367202ae244431b2211cde0d24f4fd4105698a42766efb6cff4129153fc920ecf320bc01b39081a00b60871b"),
  SECURITY_KEY_6(
      "195b7987da411fffdb5e14cc75f6c76796330e4b0c56478c8da7304077910e73bb4e476cb35760452572d4bb00d1ae9d",
      "ab362b712a4e71561c57d4e2f2647c1c64826e452fc4e818a39a88654373abacdd5bce1060c3d250ee2922b67df2fa5e371c85eebb640c7232258c51b4f39bd95c21a85c659b56c104def8e0f93e4e705f2126bd318ee0a4b0f4736db0713aadb3f8b7bdddcf5b8fe6f4077523a3f7971989f4ad5e8b53e48c910f504b54f9396868d3d863cdee95322875aca7425de7230a2b29d13b12ff013dc9ce61e39fad04b86b6ede3cb51e78bb1b621ffd8817e52de7978c570117ba623e1c005112535aadfcaa12bbe6177e3c4260f7d149f53fb532fa8bc858daffdf987de706cf725ceb8beac3bbc03109aa4c54319fd268989c1ec2ace7802a210b06f113d186da"),

  INCORRECT_SECURITY_KEY_1(
      "34567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef12", "def0123456789abc"),
  INCORRECT_SECURITY_KEY_2(
      "1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef", "abc0123456789def"),
  INCORRECT_SECURITY_KEY_3(
      "567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234", "abcdef0123456789"),

  EMPTY_SECURITY_KEY("", ""),

  MBUS_DEFAULT_KEY(
      "f2d45f475b56051b92b9ad188f557c42bdbbefc863f8fba5b9dd90e5714391d6b2208dd7d740112720c70d80c2449c94",
      "68f6e459452e3430605dee8899f141e1a6d6a778822ef56c0b70bd925ee9eb7bc3befe8d34f04137ad4fb4da41f7bb07"),
  MBUS_USERKEY(
      "d2a54c35a800755df0b8d0f7bb02af040473714068bfe3c5d0804d06a56364a7072cdcf422fc1a7fc1ce27dcc7103564",
      "416d677947475a7a42427145416e7436"),
  MBUS_FIRMWARE_UPDATE_AUTHENTICATION_KEY(
      "a70fe2e641c0bed7a0e84952dae7014863c1a012508d16af96bef46689cee5ab91b5aa877c4c45168296f55fc5e96d3e",
      "416d677947475a7a42427145416e7436"),
  MBUS_P0_KEY(
      "76f6b8bbdc5ac5d726e012282e6c232ab8d3cac6f937a8e05e95cbeb57ccf505df4e93f8400ac2f55a71e4c6cb10cf80",
      "416d677947475a7a42427145416e7436");

  private final String databaseKey;
  private final String soapRequestKey;

  SecurityKey(final String databaseKey, final String soapRequestKey) {
    this.databaseKey = databaseKey;
    this.soapRequestKey = soapRequestKey;
  }

  public String getDatabaseKey() {
    return this.databaseKey;
  }

  public String getSoapRequestKey() {
    return this.soapRequestKey;
  }
}
