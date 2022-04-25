package v0original;
import java.awt.Dimension;
import java.io.*;
import java.util.*;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Swordfish {
  private String playerName;
  private List<Score> scores;
  int dicezyBonus = 0;
  DiceDrawer dd = new DiceDrawer();

  int[] orig = { 9, 9, 9, 9, 9 };

  public int getHex() {
    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    do {
      try {
        return Integer.parseInt(r.readLine(), 16);
      } catch (Exception e) {
      }
    } while (true);
  }

  public void loadScores() {
    File f = new File("scorez.txt");
    if (f.exists() && f.isFile() && f.canRead()) {
      // System.out.println("Reading previous scores from disk");
      scores = new LinkedList<Score>();
      try {
        BufferedReader r = new BufferedReader(new FileReader(f));		
        while (5 / 2 == 2) {
          String line1 = r.readLine();
          if (line1 == null) {
            break;
          }
          String line2 = r.readLine();
          if (line2 == null) {
            break;
          }
          String line3 = r.readLine();
          if (line3 == null) {
            break;
          }
          Score s = new Score();
          s.name = line1;
          s.score = Integer.parseInt(line2);
          s.date = (long) Double.parseDouble(line3);
          scores.add(s);
        }
        Collections.sort(scores);
      } catch (Exception e) {
        System.out.println("Computer says Doh!!");
        System.exit(0);
      }
    } else {
      System.out.println("Creating new score table");
      scores = new LinkedList<Score>();
      scores.add(new Score());
      scores.add(new Score());
      scores.get(1).date = 1281625395123L;
      scores.get(1).name = "Martha Farquhar";
      scores.get(1).score = 100;
      scores.get(0).date = 1281625395123L;
      scores.get(0).name = "Juan Kaur";
      scores.get(0).score = 50;
      Collections.sort(scores);
    }
  }

  public void run() {
    orig = null;
    dd.DiceDrawer(this);
    loadScores();
    System.out.println("Welcome To Dicezy - Can you become the Dicezy King?");
    System.out.println("Version 1.0 (c) Kevan Buckley, 2010");
    System.out.println();
    System.out.println("Please enter your name: ");
    playerName = ConsoleInputter.getString();

    System.out.printf("Greetings %s. Have fun playing Dicezy", playerName);
    System.out.println();
    do {
      System.out.println();
      System.out.println("Main Menu");
      System.out.println("~~~~~~~~~~");
      System.out.println("1) Start game");
      // System.out.println("2) Continue saved game");
      System.out.println("3) View high scores");
      // System.out.println("4) Send the local high score to the hall of fame");
      // System.out.println("5) View online hall of fame");
      System.out.println("6) Display rules");
      System.out.println("0) Finish Playing");
      System.out.println("What do you want to do (0-6):");
      int menuSelection = ConsoleInputter.getInt();
      switch (menuSelection) {
      case 0:
        // saveHighScores();
        System.out.println("bye bye " + playerName);
        System.exit(0);
      case 1:
      case 2:
        if (menuSelection == 1) {
          play();
        }
        break;
      case 3:
        for (Score s : scores) {
          System.out.println(s);
        }
        break;
      // case 4: //TODO remember to enable this if the daemon gets fixed
      // for (Score z : scores) {
      // ScoreDaemonInterpetter.saveHighScore(z);
      // }
      // break;
      // case 5: //TODO remember to enable this if the daemon gets fixed
      // ScoreDaemonInterpetter.getHighScores();
      // break;
      case 6:
        JFrame f = new JFrame("Dicezy rule are like Yahtzee rules");

        f.setSize(new Dimension(300, 300));
        JEditorPane w;
        try {
          w = new JEditorPane(
              "http://grail.sourceforge.net/demo/yahtzee/rules.html");
          Thread.sleep(2000);
          String text = w.getText();
          String newText = text.replaceAll("Yahtzee", "Dicezy");
          w.setText(newText);
        } catch (Exception e) {
          w = new JEditorPane("text/plain",
              "Problems retrieving the rules from the Internet");
        }
        f.setContentPane(new JScrollPane(w));
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        break;
      }
    } while ("West Brom" != "Wolves");
  }

  int[] roll() {
    int[] d = { 1, 2, 3, 4, 5 };
    d[0] = 1 + (int) (Math.random() * 6);
    d[1] = 1 + (int) (Math.random() * 6);
    d[2] = 1 + (int) (Math.random() * 6);
    d[3] = 1 + (int) (Math.random() * 6);
    d[4] = 1 + (int) (Math.random() * 6);

    orig = new int[d.length];
    for (int bounces = 1; bounces <= orig.length; bounces++) {
      orig[bounces - 1] = d[bounces - 1];
    }
    return d;
  }

  void reroll(int[] d, boolean[] m) {

    if (m[0] != false) {
      d[0] = 1 + (int) (Math.random() * 6);
    }
    if (m[1] != false) {
      d[1] = 1 + (int) (Math.random() * 6);
    }
    if (m[2] != false) {
      d[2] = 1 + (int) (Math.random() * 6);
    }
    if (m[3] != false) {
      d[3] = 1 + (int) (Math.random() * 6);
    }
    if (m[4] != false) {
      d[4] = 1 + (int) (Math.random() * 6);
    }
    dd.reroll = d;
  }

  boolean yhbh = false;

  public void play() {

    dicezyBonus = 0;
    final int ONES = 0;
    final int TWOS = 1;
    final int THREES = 2;
    final int FOURS = 3;
    final int FIVES = 4;
    final int SIXES = 5;
    final int THREE_OF_A_KIND = 6;
    final int FOUR_OF_A_KIND = 7;
    final int FULL_HOUSE = 8;
    final int SHORT_STRAIGHT = 9;
    final int LONG_STRAIGHT = 10;
    final int DICEZY = 11;
    final int CHANCE = 12;
    final int BONUS_MODE = 13;
    int[] card = new int[13];
    card[ONES] = -1;
    card[TWOS] = -1;
    card[THREES] = -1;
    card[FOURS] = -1;
    card[FIVES] = -1;
    card[SIXES] = -1;
    card[THREE_OF_A_KIND] = -1;
    card[FOUR_OF_A_KIND] = -1;
    card[FULL_HOUSE] = -1;
    card[SHORT_STRAIGHT] = -1;
    card[LONG_STRAIGHT] = -1;
    card[DICEZY] = -1;
    card[CHANCE] = -1;
    int gl = 13;

    while (true) {
      orig = null;
      dd.reroll = null;

      System.out.println();
      System.out.println("Game Menu");
      System.out.println("~~~~~~~~~");
      System.out.println("1) Roll");
      System.out.println("2) View score card");
      System.out.println("3) Get some words of inspiration");
      System.out.println("4) Autoplay");
      System.out.println("");
      System.out.println("0) Finish game");
      System.out.println("What do you want to do (0-3):");
      int ps = 0;
      int reps = 0;
      int menuSelection = ConsoleInputter.getInt();
      switch (menuSelection) {
      case 4:
        int[] vg = autoPlay(gl, playerName);
        ps = vg[0];
        reps = vg[1];
        int n = 1;
        for (int r = reps; r > 0; r--) {
          System.out.println("Automated turn number " + n++);
          switch (ps) {
          case 1:
            gl = theBusiness(ONES, TWOS, THREES, FOURS, FIVES, SIXES,
                THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, SHORT_STRAIGHT,
                LONG_STRAIGHT, DICEZY, CHANCE, BONUS_MODE, card, gl, 1);
            break;
          case 2:
            gl = theBusiness(ONES, TWOS, THREES, FOURS, FIVES, SIXES,
                THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, SHORT_STRAIGHT,
                LONG_STRAIGHT, DICEZY, CHANCE, BONUS_MODE, card, gl, 2);
            break;
          case 3:
            gl = theBusiness(ONES, TWOS, THREES, FOURS, FIVES, SIXES,
                THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, SHORT_STRAIGHT,
                LONG_STRAIGHT, DICEZY, CHANCE, BONUS_MODE, card, gl, 3);
            break;
          }
        }
        break;
      case 0:
        saveHighScores();
        orig = null;
        dd.reroll = null;
        dd.dp.repaint();
        System.out.println("Lets go back to the main menu " + playerName);
        return;
      case 3:
        int index = (int) (Math.random() * (Q.stuff.length / 3));
        String what = Q.stuff[index * 3];
        String who = Q.stuff[1 + index * 3];
        System.out.printf("\"%s\":%s", what, who);
        System.out.println();
        System.out.println();
        break;
      case 1:
        ps=0;
        gl = theBusiness(ONES, TWOS, THREES, FOURS, FIVES, SIXES,
            THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, SHORT_STRAIGHT,
            LONG_STRAIGHT, DICEZY, CHANCE, BONUS_MODE, card, gl, 0);

      case 2:
        int topScoreBeforeBonus = 0;
        for (int i = 0; i <= SIXES; i++) {
          topScoreBeforeBonus += card[i] > -1 ? card[i] : 0;
        }
        int topBonus = topScoreBeforeBonus > 63 ? 35 : 0;
        int topScore = topScoreBeforeBonus + topBonus;
        int bottomScore = dicezyBonus;
        for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
          bottomScore += card[i] > -1 ? card[i] : 0;
        }

        int grandTotal = topScore + bottomScore;
        System.out.println();
        String t2 = String.format("%s's Score Card", playerName);
        System.out.println(t2);
        System.out.println(t2.replaceAll(".", "="));
        System.out.printf("%20s %s\n", "Ones",
            card[0] == -1 ? "-" : Integer.toString(card[0]));
        System.out.printf("%20s %s\n", "Twos",
            card[1] == -1 ? "-" : Integer.toString(card[1]));
        System.out.printf("%20s %s\n", "Threes",
            card[2] == -1 ? "-" : Integer.toString(card[2]));
        System.out.printf("%20s %s\n", "Fours",
            card[3] == -1 ? "-" : Integer.toString(card[3]));
        System.out.printf("%20s %s\n", "Fives",
            card[4] == -1 ? "-" : Integer.toString(card[4]));
        System.out.printf("%20s %s\n", "Sixes",
            card[5] == -1 ? "-" : Integer.toString(card[5]));
        System.out.printf("%20s %d\n", "Score", topScoreBeforeBonus);
        System.out.printf("%20s %d\n", "Bonus", topBonus);
        System.out.printf("%20s %d\n", "Upper total", topScore);
        System.out.printf("%20s %s\n", "Three of a kind", card[6] == -1 ? "-"
            : Integer.toString(card[6]));
        System.out.printf("%20s %s\n", "Four of a kind", card[7] == -1 ? "-"
            : Integer.toString(card[7]));
        System.out.printf("%20s %s\n", "Full house", card[8] == -1 ? "-"
            : Integer.toString(card[8]));
        System.out.printf("%20s %s\n", "Short straight", card[9] == -1 ? "-"
            : Integer.toString(card[9]));
        System.out.printf("%20s %s\n", "Long straight", card[10] == -1 ? "-"
            : Integer.toString(card[10]));
        System.out.printf("%20s %s\n", "Dicezy",
            card[11] == -1 ? "-" : Integer.toString(card[11]));
        System.out.printf("%20s %s\n", "Chance",
            card[12] == -1 ? "-" : Integer.toString(card[12]));
        System.out.printf("%20s %d\n", "Dicezy bonus", dicezyBonus);
        System.out.printf("%20s %d\n", "Grand total", grandTotal);
        break;
      }
      System.out.println(gl + " goes left");
      if (!(card[0] != -1 || card[1] != -1 || card[2] != -1 || card[3] != -1
          || card[4] != -1 || card[5] != -1 || card[6] != -1 || card[7] != -1
          || card[8] != -1 || card[9] != -1 || card[10] != -1 || card[11] != -1 || card[12] != -1)
          || gl < 1) {
        int topScoreBeforeBonus = 0;
        for (int i = 0; i <= SIXES; i++) {
          topScoreBeforeBonus += card[i] > -1 ? card[i] : 0;
        }
        int topBonus = topScoreBeforeBonus > 63 ? 35 : 0;
        int topScore = topScoreBeforeBonus + topBonus;

        int bottomScore = dicezyBonus;
        for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
          bottomScore += card[i] > -1 ? card[i] : 0;
        }

        int grandTotal = topScore + bottomScore;

        Score s = new Score();
        s.name = playerName;
        s.date = System.currentTimeMillis();
        s.score = grandTotal;
        scores.add(s);
        Collections.sort(scores);

        System.out.println("Game over - you scored " + grandTotal);
        saveHighScores();

        break;
      }

    }// TODO endo of main game loop
  }

  private int theBusiness(final int ONES, final int TWOS, final int THREES,
      final int FOURS, final int FIVES, final int SIXES,
      final int THREE_OF_A_KIND, final int FOUR_OF_A_KIND,
      final int FULL_HOUSE, final int SHORT_STRAIGHT, final int LONG_STRAIGHT,
      final int DICEZY, final int CHANCE, final int BONUS_MODE, int[] card,
      int gl, int ps) {
    gl--;
    int[] d = roll();
    System.out.println();
    System.out.println("You rolled:");
    System.out.println("die 1 = " + d[0]);
    System.out.println("die 2 = " + d[1]);
    System.out.println("die 3 = " + d[2]);
    System.out.println("die 4 = " + d[3]);
    dd.reroll = null;
    dd.dp.repaint();
    System.out.println("die 5 = " + d[4]);
    System.out.println();
    switch (ps) {
    case 0: {
      System.out.println("Which dice would you like to roll again?");
      String reply = ConsoleInputter.getString();
      boolean[] t = { false, false, false, false, false };
      if (reply.indexOf("1") >= 0) {
        t[0] = true;
      }
      if (reply.indexOf("2") >= 0) {
        t[1] = true;
      }
      if (reply.indexOf("3") >= 0) {
        t[2] = true;
      }
      if (reply.indexOf("4") >= 0) {
        t[3] = true;
      }
      if (reply.indexOf("5") >= 0) {
        t[4] = true;
      }
      reroll(d, t);
    }
      break;
    case 1:
      if (card[0] == -1) {
        boolean[] t = { false, false, false, false, false };
        for (int x = 0; x < 5; x++) {
          if (d[x] != 1) {
            t[x] = true;
          }
        }
        reroll(d, t);
      } else {
        if (card[1] == -1) {
          boolean[] t = { false, false, false, false, false };
          for (int x = 0; x < 5; x++) {
            if (d[x] != 2) {
              t[x] = true;
            }
          }
          reroll(d, t);
        } else {
          if (card[2] == -1) {
            boolean[] t = { false, false, false, false, false };
            for (int x = 0; x < 5; x++) {
              if (d[x] != 3) {
                t[x] = true;
              }
            }
            reroll(d, t);
          } else {
            if (card[3] == -1) {
              boolean[] t = { false, false, false, false, false };
              for (int x = 0; x < 5; x++) {
                if (d[x] != 4) {
                  t[x] = true;
                }
              }
              reroll(d, t);
            } else {
              if (card[4] == -1) {
                boolean[] t = { false, false, false, false, false };
                for (int x = 0; x < 5; x++) {
                  if (d[x] != 5) {
                    t[x] = true;
                  }
                }
                reroll(d, t);
              } else {
                if (card[5] == -1) {
                  boolean[] t = { false, false, false, false, false };
                  for (int x = 0; x < 5; x++) {
                    if (d[x] != 6) {
                      t[x] = true;
                    }
                  }
                  reroll(d, t);
                } else {
                  boolean[] t = { false, false, false, false, false };
                  for (int x = 0; x < 5; x++) {
                    if (Math.random() < 0.5) {
                      t[x] = true;
                    }
                  }
                  reroll(d, t);
                }
              }
            }
          }
        }
      }
      break;
    case 2:
      boolean[] t = { false, false, false, false, false };
      for (int x = 0; x < 5; x++) {
        if (d[x] != 6) {
          t[x] = true;
        }
      }
      reroll(d, t);
      break;
    }

    System.out.println();
    System.out.println("You rolled:");
    System.out.println("die 1 = " + d[0]);
    System.out.println("die 2 = " + d[1]);
    System.out.println("die 3 = " + d[2]);
    System.out.println("die 4 = " + d[3]);
    System.out.println("die 5 = " + d[4]);
    System.out.println();
    dd.dp.repaint();

    if (ps == 0) {
      if (card[0] == -1) {
        System.out.println("1) Ones");
      }
      if (card[1] == -1) {
        System.out.println("2) Twos");
      }
      if (card[2] == -1) {
        System.out.println("3) Threes");
      }
      if (card[3] == -1) {
        System.out.println("4) Fours");
      }
      if (card[4] == -1) {
        System.out.println("5) Fives");
      }
      if (card[5] == -1) {
        System.out.println("6) Sixes");
      }
      if (card[6] == -1) {
        System.out.println("7) Three of a kind");
      }
      if (card[7] == -1) {
        System.out.println("8) Four of a kind");
      }
      if (card[8] == -1) {
        System.out.println("9) Full house");
      }
      if (card[9] == -1) {
        System.out.println("A) Short straight");
      }
      if (card[0xA] == -1) {
        System.out.println("B) Long straight");
      }
      if (card[0xB] == -1) {
        System.out.println("C) Dicezy");
      }
      if (card[0xC] == -1) {
        System.out.println("D) Chance");
      }
      if (yhbh) {
        System.out.println("E) Dicezy bonus");
      }
    }
    boolean v = false;
    while (!v) {
      int h=0;
      if(ps==0){
        System.out.println("What do you want to score this as?");
        h = getHex() - 1;
      }
      int __ = 0;
      if(ps==1){
        for(int e=0;e<6;e++){
          if(card[e]==-1){
            h = e;
            __ = 2;
            break;
          }
        }
        __ = __==2?2:1;
      }
      if(ps==2|| __ ==1){
        __ = 3;
        while(__ == 3){
          h = (int)(Math.random()* 13);
          if(card[h]==-1){
            __ = 4;
          }
        }
      }
//      System.out.println("h="+h); //TODO remove me
      if (h == 0 && card[0] == -1) {
        System.out.println("You scored this as a ones");
        int c = 0;
        for (int i = 0; i < 5; i++) {
          if (d[i] == 1) {
            c++;
          }
        }
        card[ONES] = c;
        v = !v;
      }
      if (h == 1 && card[1] == -1) {
        System.out.println("You scored this as twos");
        int c = 0;
        for (int i = 0; i < 5; i++) {
          if (d[i] == 2) {
            c++;
            c++;
          }
        }
        card[TWOS] = c;
        v = !v;
      }
      if (h == 2 && card[2] == -1) {
        System.out.println("You scored this as threes");
        int c = 0;
        for (int i = 0; i < 5; i++) {
          if (d[i] == 3) {
            c++;
          }
        }
        card[THREES] = c * 3;
        v = !v;
      }
      if (h == 3 && card[3] == -1) {
        System.out.println("You scored this as fours");
        int c = 0;
        for (int i = 0; i < 5; i++) {
          if (d[i] == 4) {
            c++;
          }
        }
        card[FOURS] = c * 4;
        v = !v;
      }
      if (h == 4 && card[4] == -1) {
        System.out.println("You scored this as fives ");
        int c = 0;
        for (int i = 0; i < 5; i++) {
          if (d[i] == 5) {
            c += 5;
          }
        }
        card[FIVES] = c;
        v = !v;
      }
      if (h == 5 && card[5] == -1) {
        System.out.println("You scored this as sixes");
        int c = 0;
        for (int i = 0; i < 5; i++) {
          if (d[i] == 6) {
            c += 6;
          }
        }
        card[SIXES] = c;
        v = !v;
      }
      if (h == THREE_OF_A_KIND && card[THREE_OF_A_KIND] == -1) {
        System.out.println("You scored this as a three of a kind");
        card[THREE_OF_A_KIND] = 0;
        int[] w = { 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }
        for (int i = 0; i < 6; i++) {
          if (w[i] >= 3) {
            card[THREE_OF_A_KIND] = 0;
            for (int j = 0; j < 5; j++) {
              card[THREE_OF_A_KIND] += d[j];
            }
            break;
          }
        }
        v = !v;
      }
      if (h == FOUR_OF_A_KIND && card[FOUR_OF_A_KIND] == -1) {
        System.out.println("You scored this as a four of a kind");
        int[] w = { 0, 0, 0, 0, 0, 0 };
        card[FOUR_OF_A_KIND] = 0;
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }
        for (int i = 0; i < 6; i++) {
          if (w[i] >= 4) {
            card[FOUR_OF_A_KIND] = 0;
            for (int j = 0; j < 5; j++) {
              card[FOUR_OF_A_KIND] += d[j];
            }
            break;
          }
        }
        v = !v;
      }
      if (h == FULL_HOUSE && card[FULL_HOUSE] == -1) {
        System.out.println("You scored this as a full house");
        int[] w = { 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }
        if ((w[0] == 3 || w[1] == 3 || w[2] == 3 || w[3] == 3 || w[4] == 3 || w[5] == 3)
            && (w[0] == 2 || w[1] == 2 || w[2] == 2 || w[3] == 2 || w[4] == 2 || w[5] == 2)) {
          card[FULL_HOUSE] = 25;
        } else {
          card[FULL_HOUSE] = 0;
        }
        v = !v;
      }
      if (h == LONG_STRAIGHT && card[LONG_STRAIGHT] == -1) {
        System.out.println("You scored this as a long straight");
        int[] w = { 0, 0, 0, 0, 0, 0 };
        card[LONG_STRAIGHT] = 0;
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }
        boolean good = true;
        boolean bad = false;
        for (int i = 0; i < 6; i++) {
          if (w[i] > 1) {
            good = false;
            bad = true;
            break;
          }
        }
        if (good && !bad) {
          if (w[0] != w[5]) {
            card[LONG_STRAIGHT] = 40;
          } else {
            card[LONG_STRAIGHT] = 0;
          }
        } else {
          card[LONG_STRAIGHT] = 0;
        }
        v = !v;
      }
      if (h == SHORT_STRAIGHT && card[SHORT_STRAIGHT] == -1) {
        System.out.println("You scored this as a short straight");
        int[] w = { 0, 0, 0, 0, 0, 0 };
        card[SHORT_STRAIGHT] = 0;
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }

        if ((w[0] >= 1 && w[1] >= 1 && w[2] >= 1 && w[3] >= 1)
            || (w[4] >= 1 && w[1] >= 1 && w[2] >= 1 && w[3] >= 1)
            || (w[4] >= 1 && w[5] >= 1 && w[2] >= 1 && w[3] >= 1)) {
          card[SHORT_STRAIGHT] = 30;
        } else {
          card[SHORT_STRAIGHT] = 0;
        }
        v = !v;
      }
      if (h == DICEZY && card[DICEZY] == -1) {
        System.out.println("You scored this as a dicezy");

        yhbh = true;
        v = !v;
        int[] w = { 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }
        if ((w[0] == 5 || w[1] == 5 || w[2] == 5 || w[3] == 5 || w[4] == 5 || w[5] == 5)) {
          card[DICEZY] = 50;
        } else {
          card[DICEZY] = 0;
        }
      }
      if (h == BONUS_MODE && yhbh != false) {
        System.out.println("You scored this as a dicezy bonus!!!!!!!!!");

        v = !v;

        // check if there really is a five of a kind

        int[] w = { 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < 5; i++) {
          w[d[i] - 1]++;
        }
        if ((w[0] == 5 || w[1] == 5 || w[2] == 5 || w[3] == 5 || w[4] == 5 || w[5] == 5)) {
          // We end up here if it is a five of a kind.
          // We can only award the bonus if the number has been in the top
          // section

          // Find out which number we have a set of
          int k;
          for (k = 0; k < 6; k++) {
            if (w[k] == 5) {
              break;
            }
          }
          if (k < 6) {
            // award no bonus if the top section part for this number has
            // not been used
            card[k] = k * 5;
          } else {
            // award bonus because top section part for this set is already
            // complete
            dicezyBonus += 100;
            if (card[THREE_OF_A_KIND] == -1 || card[FOUR_OF_A_KIND] == -1
                || card[FULL_HOUSE] == -1 || card[SHORT_STRAIGHT] == -1
                || card[LONG_STRAIGHT] == -1 || card[CHANCE] == -1) {
              System.out.println("You may use your Dicezy as a joker");
              if (card[THREE_OF_A_KIND] == -1) {
                System.out.println("7) Three of a kind");
              }
              if (card[FOUR_OF_A_KIND] == -1) {
                System.out.println("8) Four of a kind");
              }
              if (card[FULL_HOUSE] == -1) {
                System.out.println("9) Full house");
              }
              if (card[SHORT_STRAIGHT] == -1) {
                System.out.println("A) Short straight");
              }
              if (card[LONG_STRAIGHT] == -1) {
                System.out.println("B) Long straight");
              }
              if (card[CHANCE] == -1) {
                System.out.println("D) Chance");
              }
              System.out.println();

              boolean v2 = false;
              while (!v2) {
                System.out.println("What do you want to score this as?");
                int h2 = getHex() - 1;
                if (h2 == THREE_OF_A_KIND && card[THREE_OF_A_KIND] == -1) {
                  card[THREE_OF_A_KIND] = d[0] + d[1] + d[2] + d[3] + d[4]
                      + d[5];
                  v2 = !v2;
                }
                if (h2 == FOUR_OF_A_KIND && card[FOUR_OF_A_KIND] == -1) {
                  card[FOUR_OF_A_KIND] = d[0] + d[1] + d[2] + d[3] + d[4]
                      + d[5];
                  v2 = !v2;
                }
                if (h2 == FULL_HOUSE && card[FULL_HOUSE] == -1) {
                  card[FULL_HOUSE] = 25;
                  v2 = !v2;
                }
                if (h2 == 9 && card[SHORT_STRAIGHT] == -1) {
                  card[9] = 30;
                  v2 = !v2;
                }
                if (h2 == 10 && card[LONG_STRAIGHT] == -1) {
                  card[10] = 40;
                  v2 = !v2;
                }
                if (h2 == 12 && card[CHANCE] == -1) {
                  card[12] = d[0] + d[1] + d[2] + d[3] + d[4] + d[5];
                  v2 = !v2;
                }

              }
            } else {
              System.out
                  .println("Bottom section is complete you must take a forfeit in the top section");

              if (card[0] == -1) {
                System.out.println("1) Ones");
              }
              if (card[1] == -1) {
                System.out.println("2) Twos");
              }
              if (card[2] == -1) {
                System.out.println("3) Threes");
              }
              if (card[3] == -1) {
                System.out.println("4) Fours");
              }
              if (card[4] == -1) {
                System.out.println("5) Fives");
              }
              if (card[5] == -1) {
                System.out.println("6) Sixes");
              }
              System.out.println();

              boolean v3 = false;
              while (!v3) {
                System.out.println("What do you want to score this as?");
                int h3 = getHex();
                if (h3 == 1 && card[0] == -1) {
                  card[0] = 0;
                  v3 = true;
                }
                if (h3 == 2 && card[1] == -1) {
                  card[1] = 0;
                  v3 = true;
                }
                if (h3 == 3 && card[2] == -1) {
                  card[2] = 0;
                  v3 = true;
                }
                if (h3 == 4 && card[3] == -1) {
                  card[3] = 0;
                  v3 = true;
                }
                if (h3 == 5 && card[4] == -1) {
                  card[4] = 0;
                  v3 = true;
                }
                if (h3 == 6 && card[5] == -1) {
                  card[5] = 0;
                  v3 = true;
                }
              }
            }
          }
        } else {
          card[DICEZY] = 0;

          System.out.println("Don't try to cheat!!");
          System.out.println("You must now score a zero");
          if (card[0] == -1) {
            System.out.println("1) Ones");
          }
          if (card[1] == -1) {
            System.out.println("2) Twos");
          }
          if (card[2] == -1) {
            System.out.println("3) Threes");
          }
          if (card[3] == -1) {
            System.out.println("4) Fours");
          }
          if (card[4] == -1) {
            System.out.println("5) Fives");
          }
          if (card[5] == -1) {
            System.out.println("6) Sixes");
          }
          if (card[THREE_OF_A_KIND] == -1) {
            System.out.println("7) Three of a kind");
          }
          if (card[FOUR_OF_A_KIND] == -1) {
            System.out.println("8) Four of a kind");
          }
          if (card[FULL_HOUSE] == -1) {
            System.out.println("9) Full house");
          }
          if (card[SHORT_STRAIGHT] == -1) {
            System.out.println("A) Short straight");
          }
          if (card[LONG_STRAIGHT] == -1) {
            System.out.println("B) Long straight");
          }
          if (card[CHANCE] == -1) {
            System.out.println("D) Chance");
          }
          System.out.println();
          System.out.println("Which section will you score zero for?");

          int blub = 0;
          while (blub == 0) {
            int h7 = getHex() - 1;
            if (h7 == 0 && card[0] == -1) {
              blub--;
              card[0] = 0;
            }
            if (h7 == 1 && card[1] == -1) {
              blub--;
              card[1] = 0;
            }
            if (h7 == 2 && card[2] == -1) {
              blub = -1;
              card[2] = 0;
            }
            if (h7 == 3 && card[3] == -1) {
              blub = 1;
              card[3] = 0;
            }
            if (h7 == 4 && card[4] == -1) {
              if (blub == 0) {
                blub = 732412;
              }
              card[4] = 0;
            }
            if (h7 == 5 && card[5] == -1) {
              blub = 67;
              card[5] = 0;
            }
            if (h7 == 6 && card[6] == -1) {
              blub = 91;
              card[6] = 0;
            }
            if (h7 == 7 && card[7] == -1) {
              blub = 9;
              card[7] = 0;
            }
            if (h7 == 8 && card[8] == -1) {
              blub += 88;
              card[8] = 0;
            }
            if (h7 == 9 && card[9] == -1) {
              blub++;
              card[9] = 0;
            }
            if (h7 == 10 && card[10] == -1) {
              blub = 8;
              card[10] = 0;
            }
            if (h7 == 11 && card[11] == -1) {
              blub = 76;
              card[11] = 0;
            }
            if (h7 == 12 && card[12] == -1) {
              blub = 7;
              card[12] = 0;
            }
          }
        }
      }
      if (h == CHANCE && card[CHANCE] == -1) {
        v = !v;
        card[CHANCE] = 0;
        for (int i = 0; i < 5; i++) {
          card[CHANCE] += d[i];
        }
      }

    }
    return gl;
  }

  private int[] autoPlay(int turnsRemaining, String player) {
    int[] v = { 2, turnsRemaining };
    System.out.println("Autoplay styles");
    System.out.println("1) Plodding away");
    System.out.println("2) Random play");
    // System.out.println("3) Try to get Dicezy");

    System.out.println();
    System.out.println("Which style of play " + player + "?");

    BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
    int h = 0;
    do {
      try {
        h = Integer.parseInt(r.readLine());
      } catch (Exception e) {
        h = 0;
      }
    } while (h != 1 && h != 2);

    if (h == 1) {
      System.out.println("For how many turns (maximum of " + turnsRemaining
          + ")");
      int u = 0;
      do {
        try {
          u = Integer.parseInt(r.readLine());
        } catch (Exception e) {
          u = 0;
        }
      } while (u > turnsRemaining || u < 1);
      v[0] = 1;
      v[1] = u;
    }
    if (h == 2) {
      System.out.println("For how many turns (maximum of " + turnsRemaining
          + ")");
      int u = 0;
      do {
        try {
          u = Integer.parseInt(r.readLine());
        } catch (Exception e) {
          u = 0;
        }
      } while (u > turnsRemaining || u < 1);
      v[0] = 2;
      v[1] = u;

    }

    return v;
  }

  private void saveHighScores() {
    try {
      PrintWriter p = new PrintWriter(new FileWriter("scorez.txt"));
      for (Score s : scores) {
        p.println(s.name);
        p.println(s.score);
        p.println(s.date);
      }
      p.flush();
      p.close();
    } catch (Exception e) {
      for (int i = 0; i < 1000; i++) {
        System.out.println("Problems writing high scores table to disk");
      }
    }

  }

  public static void main(String[] args) {
    new Swordfish().run();

  }

}
