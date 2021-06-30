package com.pbe;

import jdk.swing.interop.SwingInterOpUtils;

/** Study on Java Exception Handling
 @author Pieter Beernink
 @version 1.0
 @since 1.0
 */

// *********************
// EXCEPTION HANDLING
// *********************
// An exception is an unexpected event occurring during the execution of a program and as such disrupting the normal flow of instructions.

// Whenever an exceptional condition (error) arises, an object representing that exception is created and 'thrown' (in the method that caused the error).
// That method than either handles the exception itself or passes it on. Next, at some point the exception is 'caught' and processed.

// Exceptions are broadly categorized into two sections:
// 1. Checked exceptions (or 'compile time exceptions', as they are checked by the compiler)
// These are considered error scenarios which are outside the immediate control of the program.
// Occurring usually when interacting with other systems/resources, resulting in database errors, network connection errors, missing files etc.
// All checked exceptions are subclasses of Exception class. For example: ClassNotFoundException, IOException, SQLException.
// 2. Unchecked exceptions
// These are runtime exceptions (and thus not checked by the compiler) occurring during use of the program.
// Unchecked Exceptions are subclasses of RuntimeException class. For example: ArithmeticException, ArrayStoreException, ClassCastException.

// I.e. exceptions can be generated by 1) te Java run-time system or 2) manually generated by own code:
// - Exceptions thrown by Java relate to errors that violate the rules of Java or the constraints of Java execution environment.
// - Exceptions thrown by own code are typically used to report some error condition to the caller of a method.

// In Java, all exception types are subclasses of the build-in class Throwable: Throwable is at the topic of the exception class hierarchy.
// Throwable branches in two subclasses:
// 1. Exception - Used for exceptional conditions that user programs should catch. It has a subclass RuntimeException. Exceptions of this type are automatically defined.
// 2. Error - used by the Java run-time system for errors that have to do with the run-time environment, such as a stack overflow.

// When an error occurs within a method, the method creates an object (or any subtype of Throwable) and hands it off to the runtime system.
// This object is called the exception object. It is handled by Java default handler, which will:
// 1. Display a string describing the exception
// 2. Print a stack trace from the point at which the exception occurred
// 3. Terminate the program

// The total generated exception 'stack trace' will contain: 1) the class name, 2) method name, 3) filename and 4) line number of the exception
// The stack trace will always show the sequence of method invocations that led up to the error.
// This is useful for debugging as it pinpoints the precise sequence of steps that led to the error.

// Besides Java run-time system default exception handler, it's possible -and usually preferred- to handle an exception by the program itself.
// This 1) provides an opportunity to fix the error and 2) prevents the program from automatically terminating

// Exception handling is managed via five keywords:
// 1. try - used to contain program statements to monitor for exceptions
// 2. catch - used to catch a 'thrown' exception that occurs in the try block
// 3. throw - to manually throw an exception
// 4. throws - to specify exceptions that is thrown out of a method
// 5. finally - specifies any code that absolutely must be executed after a try block completes

public class Main {

    public static void main(String[] args) {

        // Example of a divide by zero error, not being handled
        System.out.println("Division by zero error, without own exception handling");
        System.out.println("-------------------------------------------");
//        int a = 0;
//        int b = 10 / 0; // will cause a 'ArithmeticException: / by zero' error
        System.out.println();

        // Example of a divide by zero error, with try & catch
        // Try and catch form a unit. Once an exception is thrown, the program control transfers out of the try block into the catch block.
        // From a catch, execution never returns to the try block.
        // Goal of a try-catch is to resolve an exceptional condition and continue on as if the error never happened.
        // Exceptions are returned with a string that contains a description of the exception, that can be displayed.
        // Or an own exception description can be displayed.
        // NOTE: variables introduced in the try block, only exist within that block
        System.out.println("Division by zero error, with try and catch");
        System.out.println("-------------------------------------------");
        try {
            int a = 0;
            int b = 10 / 0; // will cause a 'ArithmeticException: / by zero' error
            System.out.println("This line will not be executed.");
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e); // this will display the string containing a description of the exception
            System.out.println("Division by zero."); // alternatively: use an own description
        }
        System.out.println("After try/catch \n");

        // Example of multiple catch clauses
        // In some cases, it's useful to be able to catch multiple exceptions
        // For this purpose, two or more catch clauses can be used, each catching a different exception
        // With an exception thrown, each catch statement is inspected one after another
        // The catch statement which type matches first, will be executed, bypassing the others
        // Execution then continues after the try / catch block
        System.out.println("Multiple catch clauses");
        System.out.println("-------------------------------------------");
        try {
            int a = args.length; // will result in a = 0 if no command line arguments are provided at program start
            int b = 10 / a; // causing a '/ zero' exception
            int c[] = {1, 2, 3};
            c[10] = 11; // causing an 'out of bounds' exception as c[] consists of only 3 elements
        } catch (ArithmeticException e) {
            System.out.println("Divide by 0: " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index oob: " + e); // program will only get to this point if args are provided (read: if a > 0)
        }
        System.out.println("After try/catch blocks \n");


        // With multiple catch statements, subclasses must come before any of their superclasses.
        // Because a catch statement that uses a superclass, will catch exceptions of that type PLUS any of its subclasses.
        // A subclass would never be reached if it came after its superclass.
        // Note: in Java, unreachable code is an error
        System.out.println("Multiple catch clauses: subclass must come before its superclass");
        System.out.println("-------------------------------------------");
        try {
            int a = 0;
            int b = 1 / 0;
        } catch (Exception e) {
            System.out.println("Generic Exception catch \n");
        }
//        catch (ArithmeticException e) {
//            As ArithmeticException is a subclass of exception, this catch line is unreachable and will be causing an error
//            This is because the general exception catch has already caught the exception.
//            This problem can be fixed by switching the catch statements, with the ArithmeticException catch coming first.
//            System.out.println("This line will never be reached.");
//        }

        // Example of nested try statement
        // A try statement can be inside the block of another try.
        // Each try statement will be passed and the context of each exception added on the stack.
        // If a try statement does not have a catch handler for a particular exception, the next try statement's catch handler is checked for a match.
        // If none of the catch statements matches, Java run-time system will handle the exception.
        // In below example, set a to 0, 1 or 2, to get different exceptions
        System.out.println("Nested try statement");
        System.out.println("-------------------------------------------");
        try {
            int a = 0;
            int b = 1 / a; // causing '/ zero' exception if a = 0
            try { // NESTED TRY !
                if (a == 1)
                    a = a / (a - a);  // causing another '/ zero' exception
                if (a == 2) {
                    int c[] = { 1 };
                    c[10] = 11; // causing 'out of bounds' exception
                }
            } catch(ArrayIndexOutOfBoundsException e) { // first catch in nested try
                    System.out.println("Array index out-of-bounds: " + e);
            } catch (ArithmeticException e) { // second catch in nested try
                System.out.println("Divide by 0: " + e);
            }
        } catch (ArithmeticException e) { // catch in main try
            System.out.println("Divide by 0: " + e);
        }
        System.out.println();

        // Example of nested try statement, with method call
        // A call to a method can be enclosed within a try block
        // Inside the method can be another (nested) try statement
        // The try within the method is nested inside the try block that called the method
        System.out.println("Nested try statement, with method call");
        System.out.println("-------------------------------------------");
        try {
            int a = 0;
            int b = 1 / a; // causing '/ zero' exception if a = 0
            nesttry(a);
        } catch (ArithmeticException e) {
            System.out.println("Divide by 0: " + e);
        }

        // Example of throwing own exception (throw)
        // With use of the throw statement a program can throw an exception explicitly: 'throw ThrowableInstance'
        // ThrowableInstance must be an object of type Throwable or a subclass
        // Primitive types (int, char, ..) and non-Throwable classes (String, Object, ..) can't be used as exception
        // Two ways to obtain a Throwable object:
        // 1. Using a parameter in a catch clause
        // 2. Creating one with the new operator
        // The flow of execution stops immediately after the throw statement. No further statements are executed.
        // The (nested) try block(s) are inspected to see if there's a catch statement that matches.
        // If no matching catch is found, Java default exception handler takes care.
        System.out.println("Throw example");
        System.out.println("-------------------------------------------");
         try {
             throwtest(); // calling method that will throw and catch a NullPointer exception
         } catch (NullPointerException e) { // the exception is caught again
             System.out.println("throw exception caught again: " + e + "\n");
         }

        // Example of throws
        // A 'throws' clause can be used in a method's declaration to list the types of exceptions that the method is capable to cause, but does not handle.
        // This way, callers of the method can guard themselves against these possible exceptions.
        // The throws clause specifies an exception list; a comma-seperated list of the exceptions that the applicable method can throw.
        System.out.println("Throws example");
        System.out.println("-------------------------------------------");
        try {
            throwsException();
        } catch (IllegalAccessException e) {
            System.out.println("Caught: " + e + "\n");
        }

        // Example of use of finally keyword
        // Thrown exceptions causes the execution flow in a method to be abruptly altered. Potentially causing it to return prematurely.
        // The 'finally' keyword can be used to create a block of code that will be executed after a try/catch block has completed,
        // ...and before the code following the try/catch block.
        // The finally block will execute whether or not an exception is thrown. Even if no matching catch statement is present.
        // The finally clause is optional (although note that each try statement requires at least a catch OR finally clause)
        System.out.println("Finally example");
        System.out.println("-------------------------------------------");
        try {
            methodA(); // throws and catches an exception: prematurely breaking out of try and executes finally on it's way out
        } catch (Exception e) {
            System.out.println("Exception caught: " + e);
        }
        methodB(); // exits try block via a return statement and executes finally on it's way out
        methodC(); // executes try/catch block without issues and executes finally on it's way out
    }


    // ************************
    // FOLLOWING: METHODS USED IN EXAMPLES
    // ************************

    // Method used for example of nested try statement, with method call
    static void nesttry(int a) {
        try { // NESTED TRY !
            System.out.println("try in nesttry() called");
            if (a == 1)
                a = a / (a - a);  // causing another '/ zero' exception
            if (a == 2) {
                int c[] = {1};
                c[10] = 11; // causing 'out of bounds' exception
            }
        } catch (ArrayIndexOutOfBoundsException e) { // first catch in nested try
            System.out.println("Array index out-of-bounds: " + e);
        }
    }

    // Method used for throw example
    static void throwtest() {
        try {
            throw new NullPointerException("throw test"); // throw exception - notice 'new' here creates an instance of NullPointerException
        } catch (NullPointerException e) { // catch exception
            System.out.println("exception caught inside throwtest()");
            throw e; // rethrow the exception
        }
    }

    // Method used to demonstrate use of throws
    static void throwsException() throws IllegalAccessException {
        System.out.println("Inside causeException()");
        throw new IllegalAccessException("demonstration");
    }


    // Method used to demonstrate finally keyword
    // Throw an exception out of a method
    static void methodA() {
        try {
            System.out.println("inside methodA()");
            throw new RuntimeException("demo");
        } finally {
            System.out.println("A's finally");
        }
    }

    // Method used to demonstrate finally keyword
    // Return from within a try block
    static void methodB() {
        try {
            System.out.println("inside methodB()");
            //throw new RuntimeException("demo");
            return;
        } finally {
            System.out.println("B's finally");
        }
    }

    // Method used to demonstrate finally keyword
    // Execute a try block normally
    static void methodC() {
        try {
            System.out.println("inside methodC()");
        } finally {
            System.out.println("C's finally");
        }
    }

}