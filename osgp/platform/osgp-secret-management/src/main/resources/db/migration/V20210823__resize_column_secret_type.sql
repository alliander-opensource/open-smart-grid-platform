DO
$$
BEGIN

IF  EXISTS (SELECT 1 FROM information_schema.columns
  WHERE table_schema=current_schema AND table_name = 'encrypted_secret' AND column_name = 'secret_type') THEN

    ALTER TABLE "encrypted_secret" ALTER COLUMN "secret_type" type VARCHAR(42);

END IF;

END;
$$