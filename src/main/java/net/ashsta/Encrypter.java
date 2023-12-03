package net.ashsta;

public class Encrypter {

    private static App APP;

    public static App getApp() {
        return APP;
    }

    public static void main(String[] args) {
        APP = new App();
        APP.setVisible(true);
    }
}
