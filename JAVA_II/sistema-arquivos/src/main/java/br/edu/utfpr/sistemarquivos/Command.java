/**
 * File: Command.java
 * Author: Giovanni L. Rozza
 * Date: January 30, 2025
 *
 * Description:
 * This enum represents various file system commands for navigating and interacting
 * with the file system, such as listing contents, showing file details, changing directories,
 * opening files, and exiting the application. Each command is implemented as a separate
 * enum constant with specific logic for handling its associated functionality.
 *
 * Methods:
 * - parseCommand(String commandToParse): Parses a given command string and returns the corresponding
 *   Command enum, or throws an exception if the command is invalid.
 * - validateParameters(String[] parameters, String errorMessage): Validates that the parameters
 *   (default is 1) passed to a command are sufficient, throwing an exception if not.
 * - validateParameters(String[] parameters, int expectedLength, String errorMessage):
 *   Validates that the parameters have the expected length.
 *
 * Enum Constants:
 * - LIST: Command for listing directory contents.
 * - SHOW: Command for displaying the contents of a text file.
 * - BACK: Command for navigating to the parent directory.
 * - OPEN: Command for opening a specified directory.
 * - DETAIL: Command for displaying file or directory details, such as creation time, size, etc.
 * - EXIT: Command for exiting the application.
 *
 * Exception Handling:
 * - The implementation handles various exceptions such as file not found, invalid parameters,
 *   and unsupported operations, providing appropriate error messages to the user.
 */


package br.edu.utfpr.sistemarquivos;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

import static br.edu.utfpr.sistemarquivos.Application.ROOT;

public enum Command {

    LIST() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("LIST") || commands[0].startsWith("list");
        }

        @Override
        Path execute(Path path) {

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                System.out.println("Contents of " + path);
                for (Path entry : stream) {
                    System.out.println(entry.getFileName());
                }
            } catch (IOException e) {
                System.err.println("Error listing directory: " + e.getMessage());
            }
            return path;
        }
    },
    SHOW() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("SHOW") || commands[0].startsWith("show");
        }

        @Override
        Path execute(Path path) {

            boolean success = validateParameters(parameters,  "Error: Filename parameter not specified.");
            if (!success) {
                return path;
            }

            // build new path with parameter file
            Path filePath = path.resolve(parameters[1]);

            try {

                if (!filePath.toString().toLowerCase().endsWith(".txt") && !Files.isDirectory(filePath)) {
                    throw new UnsupportedOperationException("Error: Only files with .txt extension are supported.");
                }
                if (!Files.exists(filePath)) {
                    throw new UnsupportedOperationException("Error: File does not exist.");
                }
                if (Files.isDirectory(filePath)) {
                    throw new UnsupportedOperationException("Error: SHOW command should be used with files only.");
                }

                FileReader fileReader = new FileReader();
                fileReader.read(filePath);

            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
            }
            catch (IOException ex) {
                System.out.println("Error reading file: " + ex.getMessage());
            }
            return path;
        }
    },
    BACK() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("BACK") || commands[0].startsWith("back");
        }

        @Override
        Path execute(Path path) {

            Path root = Path.of(ROOT).toAbsolutePath(); // root directory
            Path current = path.toAbsolutePath(); // absolute path of current directory
            try{
                if (!current.equals(root)) {    // Not root directory? Move to parent directory
                    return current.getParent();
                }
                throw new UnsupportedOperationException("Error: It is not allowed to go back beyond the root directory.");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                return path;
            }
         }
    },
    OPEN() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("OPEN") || commands[0].startsWith("open");
        }

        @Override
        Path execute(Path path) {

            boolean success = validateParameters(parameters,  "Error: Directory parameter not specified.");
            if (!success) {
                return path;
            }

            //The second parameter is the name of the directory to be opened,
            // ignoring other parameters if the array size > 2.
            String dirName = parameters[1];
            Path newPath = path.resolve(dirName); // build new path

            try{
                // Directory does not exist?
                if (!Files.isDirectory(newPath)) {
                    newPath = path;
                    throw new UnsupportedOperationException("Error: Directory '" + dirName + "' does not exist or is not a directory.");

                }
            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());

            }
            return newPath;

        }
    },
    DETAIL() {
        private String[] parameters = new String[]{};

        @Override
        void setParameters(String[] parameters) {
            this.parameters = parameters;
        }

        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("DETAIL") || commands[0].startsWith("detail");
        }

        @Override
        Path execute(Path path) {

            boolean success = validateParameters(parameters, "Error: Filename or directory parameter not specified.");
            if (!success) {
                return path;
            }

            // build new path with parameter file
            Path fullPath = path.resolve(parameters[1]);

            try {
                if (!Files.exists(fullPath)) {
                    throw new UnsupportedOperationException("Error: File or directory don´t exist.");
                }

            } catch (UnsupportedOperationException ex) {
                System.out.println(ex.getMessage());
                return path;
            }

            // Getting the BasicFileAttributeView
            Optional<BasicFileAttributeView> attributeView = Optional.ofNullable(Files.getFileAttributeView(fullPath, BasicFileAttributeView.class));

            // Using Optional to avoid null check
            attributeView.ifPresent(view -> {
                try {
                    BasicFileAttributes attributes = view.readAttributes();

                    // default system timezone
                    ZoneId systemZone = ZoneId.systemDefault();

                    // Converting the file attributes' date and time from UTC to the system's default timezone

                    ZonedDateTime creationTime = attributes.creationTime().toInstant().atZone(systemZone);
                    ZonedDateTime lastModifiedTime = attributes.lastModifiedTime().toInstant().atZone(systemZone);
                    ZonedDateTime lastAccessTime = attributes.lastAccessTime().toInstant().atZone(systemZone);


                    // Displaying file information
                    System.out.println("Full path: " + fullPath);
                    System.out.println("Created on: " + creationTime);
                    System.out.println("Last modified: " + lastModifiedTime);
                    System.out.println("Last accessed: " + lastAccessTime);
                    System.out.println("Size: " + attributes.size() + " bytes");
                    System.out.println("Is directory? " + Files.isDirectory(fullPath));
                } catch (IOException ex) {
                    System.out.println("Error reading file attributes: " + ex.getMessage());
                }
            });


            return path;
        }
    },
    EXIT() {
        @Override
        boolean accept(String command) {
            final var commands = command.split(" ");
            return commands.length > 0 && commands[0].startsWith("EXIT") || commands[0].startsWith("exit");
        }

        @Override
        Path execute(Path path) {
            System.out.print("Exiting...");
            return path;
        }

        @Override
        boolean shouldStop() {
            return true;
        }
    };

    abstract Path execute(Path path) throws IOException;

    abstract boolean accept(String command);

    void setParameters(String[] parameters) {
    }

    boolean shouldStop() {
        return false;
    }

    // validate for 1 parameter required
    boolean validateParameters(String[] parameters, String errorMessage) {
        return validateParameters(parameters, 2, errorMessage);
    }

    // validate for n parameters required
    boolean validateParameters(String[] parameters, int expectedLength, String errorMessage) {
        try {
            if (parameters.length < expectedLength) {
                throw new UnsupportedOperationException(errorMessage);
            }
            return true; // valid parameters
        } catch (UnsupportedOperationException ex) {

            System.out.println(ex.getMessage());
            return false;
        }
    }

    public static Command parseCommand(String commandToParse) {

        if (commandToParse.isBlank()) {
            throw new UnsupportedOperationException("Type something...");
        }

        final var possibleCommands = values();

        for (Command possibleCommand : possibleCommands) {
            if (possibleCommand.accept(commandToParse)) {
                possibleCommand.setParameters(commandToParse.split(" "));
                return possibleCommand;
            }
        }

        throw new UnsupportedOperationException("Can't parse command [%s]".formatted(commandToParse));
    }
}
