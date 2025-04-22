package com.app.console;

public class TestConsole {
    public static void showSomething(){
//        System.out.println("Happy");
////        System.out.println("\033[H\033[2J");
////        System.out.println("\u001b[2J" + "\u001b[H");
//        System.out.println("\033c");
        //throws Exception

//            ConsoleReader r = new ConsoleReader();

//            while (true)
//            {
//                r.println("Good morning");
//                r.flush();
//
//                String input = r.readLine("prompt>");
//
//                if ("clear".equals(input))
//                    r.clearScreen();
//                else if ("exit".equals(input))
//                    return;
//                else
//                    System.out.println("You typed '" + input + "'.");
//
//            }
        try {
            final String os = System.getProperty("os.name");
            System.out.println(os);
            if(os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
