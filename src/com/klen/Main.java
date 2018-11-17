package com.klen;

import com.klen.imstillhere.ImStillHere;

public class Main {


    public static void main(String[] args) {

        printSplash();

        ImStillHere imStillHere = new ImStillHere(args);

        imStillHere.start();
    }

    private static void printSplash() {
        System.out.println("\n");
        System.out.println("  _____            _____ _   _ _ _ _    _               ");
        System.out.println(" |_   _|          / ____| | (_) | | |  | |              ");
        System.out.println("   | |  _ __ ___ | (___ | |_ _| | | |__| | ___ _ __ ___ ");
        System.out.println("   | | | '_ ` _ \\ \\___ \\| __| | | |  __  |/ _ \\ '__/ _ \\");
        System.out.println("  _| |_| | | | | |____) | |_| | | | |  | |  __/ | |  __/");
        System.out.println(" |_____|_| |_| |_|_____/ \\__|_|_|_|_|  |_|\\___|_|  \\___|");
        System.out.println("\n\n");
    }
}
