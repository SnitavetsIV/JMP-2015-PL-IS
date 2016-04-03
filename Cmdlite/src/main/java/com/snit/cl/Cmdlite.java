package com.snit.cl;

import com.snit.cl.service.GameService;
import com.snit.cl.service.PlayerService;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author Ilya Snitavets
 */
public class Cmdlite {

  private static final PlayerService PLAYER_SERVICE = PlayerService.getInstance();
  private static final GameService GAME_SERVICE = GameService.getInstance();

  public static void main(String[] args) {
    System.out.println(PLAYER_SERVICE.getAllPlayers());
    System.out.println(GAME_SERVICE.getAllGames());
  }

  private static Options buildOptions() {
    Options options = new Options();
    options.addOption(new Option("l", "list available entities"));

    return options;
  }

  public static void printHelp(
      final Options options,
      final int printedRowWidth,
      final String header,
      final String footer,
      final int spacesBeforeOption,
      final int spacesBeforeOptionDescription,
      final boolean displayUsage,
      final OutputStream out) {

    final String commandLineSyntax = "java com.snit.cl.Cmdlite";
    final PrintWriter writer = new PrintWriter(out);
    final HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp(
        writer,
        printedRowWidth,
        commandLineSyntax,
        header,
        options,
        spacesBeforeOption,
        spacesBeforeOptionDescription,
        footer,
        displayUsage);
    writer.flush();
  }

}
