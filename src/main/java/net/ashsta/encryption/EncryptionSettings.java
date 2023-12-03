package net.ashsta.encryption;

public record EncryptionSettings(Algorithm algorithm, Mode mode, Padding padding) {

    public static final EncryptionSettings DEFAULT = new EncryptionSettings(Algorithm.AES, Mode.ECB, Padding.PKCS5Padding);

    public String getTransformation() {
        return algorithm + "/" + mode + "/" + padding;
    }

    public int getKeySize() {
        return algorithm.KEY_SIZE;
    }

    public Mode getDefaultMode() {
        return algorithm.DEFAULT_MODE;
    }

    public Padding getDefaultPadding() {
        return algorithm.DEFAULT_PADDING;
    }

    public enum Algorithm {
        AES(16, Mode.ECB, Padding.PKCS5Padding),
        DES(8, Mode.ECB, Padding.PKCS5Padding);

        private final int KEY_SIZE;
        private final Mode DEFAULT_MODE;
        private final Padding DEFAULT_PADDING;

        Algorithm(int keysize, Mode defaultMode, Padding defaultPadding) {
            KEY_SIZE = keysize;
            DEFAULT_MODE = defaultMode;
            DEFAULT_PADDING = defaultPadding;
        }
    }

    public enum Mode {
        ECB;

        public static Mode[] getAlgorithmModes(Algorithm algorithm) {
            return switch (algorithm) {
                case AES, DES -> new Mode[]{ECB};
            };
        }

        public static Mode[] getDefaultAlgorithmModes() {
            return getAlgorithmModes(DEFAULT.algorithm);
        }
    }

    public enum Padding {
        PKCS5Padding;

        public static Padding[] getAlgorithmPaddings(Algorithm algorithm) {
            return switch (algorithm) {
                case AES, DES -> new Padding[]{PKCS5Padding};
            };
        }

        public static Padding[] getDefaultAlgorithmPaddings() {
            return getAlgorithmPaddings(DEFAULT.algorithm);
        }
    }
}
