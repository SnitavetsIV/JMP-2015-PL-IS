package com.snit.cl;

import com.snit.cl.entity.Game;
import com.snit.cl.entity.Player;
import com.snit.cl.service.GameService;
import com.snit.cl.service.PlayerService;
import com.snit.cl.util.IntegerUtil;
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
      CommandLine cmd;
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
        printEntityInfo();
    }
  }

  private static void printEntityInfo() {
    System.out.println("Available entities:");
    System.out.println("Player{name}");
    System.out.println("Game{blueAttack,blueDefence,redAttack,redDefence,scoreBlue,scoreRed}");
  }

  private static void processCreateEntity(CommandLine cmd) {
    String[] fieldValues = cmd.getOptionValues("f");
    if (fieldValues == null || fieldValues.length < 1) {
      System.out.println("Please use -f option to add fields to entity");
      printHelp();
      return;
    }
    String optArg = cmd.getOptionValue("c");
    if (optArg == null) {
      optArg = "";
    }
    switch (optArg) {
      case "Player":
        Player player = PLAYER_SERVICE.savePlayer(fieldValues);
        System.out.println(player == null ? "Cannot create player =(" : player);
        break;
      case "Game":
        Game game = GAME_SERVICE.createGame(fieldValues);
        System.out.println(game == null ? "Cannot create game =(" : game);
        break;
      default:
        printEntityInfo();
    }
  }

  private static void processDeleteEntity(CommandLine cmd) {
    String idValue = cmd.getOptionValue("id");
    if (idValue == null || !IntegerUtil.isInteger(idValue)) {
      System.out.println("Please use -id option to define id of entity");
      printHelp();
      return;
    }
    int id = Integer.parseInt(idValue);
    String optArg = cmd.getOptionValue("d");
    if (optArg == null) {
      optArg = "";
    }
    switch (optArg) {
      case "Player":
        if (PLAYER_SERVICE.deletePlayer(id)) {
          System.out.println("Player with id= " + id + " has been successfully deleted");
        } else {
          System.out.println("Cannot delete player with id= " + id + " =(");
        }
        break;
      case "Game":
        if (GAME_SERVICE.deleteGame(id)) {
          System.out.println("Game with id= " + id + " has been successfully deleted");
        } else {
          System.out.println("Cannot delete game with id= " + id + " =(");
        }
        break;
      default:
        printEntityInfo();
    }
  }

  private static void processUpdateEntity(CommandLine cmd) {
    String idValue = cmd.getOptionValue("id");
    if (idValue == null || !IntegerUtil.isInteger(idValue)) {
      System.out.println("Please use -id option to define id of entity");
      printHelp();
      return;
    }
    int id = Integer.parseInt(idValue);
    String[] fieldValues = cmd.getOptionValues("f");
    if (fieldValues == null || fieldValues.length < 1) {
      System.out.println("Please use -f option to update fields of entity");
      printHelp();
      return;
    }
    String optArg = cmd.getOptionValue("u");
    if (optArg == null) {
      optArg = "";
    }
    switch (optArg) {
      case "Player":
        Player player = PLAYER_SERVICE.updatePlayer(id, fieldValues);
        System.out.println(player == null ? "Cannot update player with id= " + id + " =(" : player);
        break;
      case "Game":
        Game game = GAME_SERVICE.updateGame(id, fieldValues);
        System.out.println(game == null ? "Cannot update game with id= " + id + " =(" : game);
        break;
      default:
        printEntityInfo();
    }
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
    fieldsEntity.setOptionalArg(true);
    fieldsEntity.setValueSeparator(',');

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
