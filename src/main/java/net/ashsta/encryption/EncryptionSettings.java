package net.ashsta.encryption;

public record EncryptionSettings(Algorithm algorithm, Mode mode, Padding padding) {

    public static final EncryptionSettings DEFAULT = new EncryptionSettings(Algorithm.AES, Mode.ECB, Padding.PKCS5Padding);

    public String getTransformation() {
        return algorithm + "/" + mode + "/" + padding;
    }

    public int getKeyLength() {
        return algorithm.KEY_LENGTH;
    }

    public enum Algorithm {
        AES(16, Mode.ECB, Padding.PKCS5Padding),
        DES(8, Mode.ECB, Padding.PKCS5Padding);

        private final int KEY_LENGTH;
        private final Mode DEFAULT_MODE;
        private final Padding DEFAULT_PADDING;

        Algorithm(int keyLength, Mode defaultMode, Padding defaultPadding) {
            KEY_LENGTH = keyLength;
            DEFAULT_MODE = defaultMode;
            DEFAULT_PADDING = defaultPadding;
        }

        public Mode getDefaultMode() {
            return DEFAULT_MODE;
        }

        public Padding getDefaultPadding() {
            return DEFAULT_PADDING;
        }
    }

    public enum Mode {
        ECB;

        public static Mode[] getAlgorithmModes(Algorithm algorithm) {
            return switch (algorithm) {
                case AES, DES -> new Mode[]{ECB};
            };
        }
    }

    public enum Padding {
        PKCS5Padding;

        public static Padding[] getAlgorithmPaddings(Algorithm algorithm) {
            return switch (algorithm) {
                case AES, DES -> new Padding[]{PKCS5Padding};
            };
        }
    }
}
