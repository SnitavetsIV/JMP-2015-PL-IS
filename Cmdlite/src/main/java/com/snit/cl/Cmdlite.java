package com.snit.cl;

import com.snit.cl.service.GameService;
import com.snit.cl.service.PlayerService;
import org.apache.commons.cli.*;

import java.io.PrintWriter;

/**
 * @author Ilya Snitavets
 */
public class Cmdlite {

  private static final PlayerService PLAYER_SERVICE = PlayerService.getInstance();
  private static final GameService GAME_SERVICE = GameService.getInstance();
  private static final Options OPTIONS = buildOptions();

  public static void main(String[] args) {
    if (args.length < 1) {
      printHelp();
    } else {
      CommandLineParser parser = new DefaultParser();
      CommandLine cmd = null;
      try {
        cmd = parser.parse(OPTIONS, args);
        if (cmd.hasOption("l")) {
          processListEntities(cmd);
        } else if (cmd.hasOption("c")) {
          processCreateEntity(cmd);
        } else if (cmd.hasOption("u")) {
          processUpdateEntity(cmd);
        } else if (cmd.hasOption("d")) {
          processDeleteEntity(cmd);
        } else {
          printHelp();
        }
      } catch (ParseException e) {
        System.out.println(e);
        printHelp();
      }
    }
  }

  private static void processListEntities(CommandLine cmd) {
    String optArg = cmd.getOptionValue("l");
    if (optArg == null) {
      optArg = "";
    }
    switch (optArg) {
      case "Player":
        System.out.println(PLAYER_SERVICE.getAllPlayers());
        break;
      case "Game":
        System.out.println(GAME_SERVICE.getAllGames());
        break;
      default:
        System.out.println("Available entities:");
        System.out.println("Player");
        System.out.println("Game");
    }
  }

  private static void processCreateEntity(CommandLine cmd) {
  }

  private static void processDeleteEntity(CommandLine cmd) {
  }

  private static void processUpdateEntity(CommandLine cmd) {
  }

  private static Options buildOptions() {


    Option listEntities = new Option("l", /*hasArgs*/ true, "list available entities");
    listEntities.setArgName("entity");
    listEntities.setOptionalArg(true);

    Option deleteEntity = new Option("d", /*hasArgs*/ true, "delete entity with its children");
    deleteEntity.setArgName("entity");

    Option updateEntity = new Option("u", /*hasArgs*/ true, "update entity");
    updateEntity.setArgName("entity");

    Option createEntity = new Option("c", /*hasArgs*/ true, "create entity");
    createEntity.setArgName("entity");

    Option idEntity = new Option("id", /*hasArgs*/ true, "id of entity");
    idEntity.setArgName("id");

    Option fieldsEntity = new Option("f", "fields of entity");
    fieldsEntity.setArgs(10);
    listEntities.setOptionalArg(true);

    Options options = new Options();

    OptionGroup crudGroup = new OptionGroup();
    crudGroup.addOption(createEntity).addOption(listEntities).addOption(updateEntity).addOption(deleteEntity);

    options.addOptionGroup(crudGroup);
    options.addOption(fieldsEntity);
    options.addOption(idEntity);
    return options;
  }

  private static void printHelp() {
    final String commandLineSyntax = "java com.snit.cl.Cmdlite";
    final PrintWriter writer = new PrintWriter(System.out);
    final HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp(
        writer,
        80,
        commandLineSyntax,
        "Options",
        OPTIONS,
        3,
        5,
        "-- HELP --",
        true);
    writer.flush();
  }

}
