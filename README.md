# Console
GUI for console

For use this project as a library, you can download the jar file in "jar" folder.

How use it?

- Import it ( import consolegui.Console; )
- Start your code with Console.open() to ... open the GUI.
- Use methods that you need ; Examples :

    * "System.out.print(String);" becomes "Console.print(String);"
    * "System.out.println(String);" becomes "Console.println(String);"
    * "Scanner sc = new Scanner(System.in); String s = sc.nextLine();" becomes "String s = Console.next()"
    ...
    Check the "Console.java" file for more methods !

- Close the GUI at the end of your code ; 2 choices :

    * "Console.close();" to instantly close the GUI
    * "Console.exit();" to let a last interaction of the user before closing

    
