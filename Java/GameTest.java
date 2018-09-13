package Java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class tests for potential bugs.
 *
 * @author Tom Baddeley
 * @version 1.0
 */

class GameTest {
    @Test
    public void testMoveUp(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        Square startingSquare = missScarlett.getLocation();
        b.movePlayer(missScarlett,new String[]{"2","u"});
        assertEquals(startingSquare.getY()-2,missScarlett.getLocation().getY());
    }
    @Test
    public void testMoveDown(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece msWhite = g.getPlayers().get(2).getPiece();
        Square startingSquare = msWhite.getLocation();
        b.movePlayer(msWhite,new String[]{"1","d"});
        assertEquals(startingSquare.getY()+1,msWhite.getLocation().getY());
    }
    @Test
    public void testMoveLeft(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece profPlum = g.getPlayers().get(5).getPiece();
        Square startingSquare = profPlum.getLocation();
        b.movePlayer(profPlum,new String[]{"3","l"});
        assertEquals(startingSquare.getX()-3,profPlum.getLocation().getX());
    }
    @Test
    public void testMoveRight(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece colMustard = g.getPlayers().get(1).getPiece();
        Square startingSquare = colMustard.getLocation();
        b.movePlayer(colMustard,new String[]{"5","r"});
        assertEquals(startingSquare.getX()+5,colMustard.getLocation().getX());
    }
    @Test
    public void testVisitSquareTwiceOneTurn(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        Square startingSquare = missScarlett.getLocation();
        assertFalse(b.checkValidMove(missScarlett,new String[]{"2","u","1","d"}));
    }
    @Test
    public void moveOffBoard(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece colMustard = g.getPlayers().get(1).getPiece();
        assertFalse(b.checkValidMove(colMustard,new String[]{"2","l"}));
    }
    @Test
    public void moveToRoomNotThroughEntry(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        assertFalse(b.checkValidMove(missScarlett,new String[]{"1","u","1","L"}));
    }
    @Test
    public void moveToDeadSquare(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        assertFalse(b.checkValidMove(missScarlett,new String[]{"1","L"}));
    }
    @Test
    public void moveToRoomThroughEntry(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        assertTrue(b.checkValidMove(missScarlett,new String[]{"6","U","1","L","1","d"}));
    }
    @Test
    public void playerIsBlocked(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        PlayerPiece colMustard = g.getPlayers().get(1).getPiece();
        b.movePlayer(missScarlett,new String[]{"6","U","1","L","1","d"});
        b.movePlayer(colMustard,new String[]{"6","R","1","D"});
        assertTrue(b.isPlayerBlocked(missScarlett));
    }
    @Test
    public void playerCantMoveOnOtherPlayersSquare(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        PlayerPiece colMustard = g.getPlayers().get(1).getPiece();
        b.movePlayer(missScarlett,new String[]{"6","U","1","L","1","d"});
        assertFalse(b.checkValidMove(colMustard,new String[]{"6","R","2","D"}));
    }
    @Test
    public void PlayerCannotReenterRoom(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        b.movePlayer(missScarlett,new String[]{"6","U","1","L","1","d"});
        assertFalse(b.checkValidMove(missScarlett,new String[]{"1","u","1","d"}));
    }
    @Test
    public void falseAccusation(){
        Game g = new Game();
        g.initializePlayers(6);
        g.setSolution(new ArrayList<>(Arrays.asList(new Card("Rope"),new Card("Lounge"),new Card("Colonel Mustard"))));
        g.dealCards();
        Player missScarlett = g.getPlayers().get(0);
        g.makeAccusation(missScarlett,"Colonel Mustard","Lounge","Candlestick");
        assertTrue(missScarlett.isExcluded());
    }
    @Test
    public void excludedPlayerPieceMovesWhenSuggested(){
        Game g = new Game();
        g.dealCards();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();

        g.setSolution(new ArrayList<>(Arrays.asList(new Card("Rope"),new Card("Lounge"),new Card("Colonel Mustard"))));

        Player missScarlett = g.getPlayers().get(0);
        g.makeAccusation(missScarlett,"Colonel Mustard","Lounge","Candlestick");

        Player colMustard = g.getPlayers().get(1);
        b.movePlayer(colMustard.getPiece(),new String[]{"6","R","2","D"});
        g.makeSuggestion(colMustard,"Miss Scarlett","Lounge","Rope");
        Room lounge = b.getRooms().get("Lounge");
        Boolean found = false;
        for(RoomSquare r:lounge.getRoomSquares()) if(r.getPlayer()!=null && r.getPlayer().getName().equals("Miss Scarlett")) found = true;

        assertTrue(found);

    }

    @Test
    public void inactivePieceMovesWhenSuggested(){//tests if a piece not being used by a player moves to room when suggested
        Game g = new Game();
        g.dealCards();
        g.initializePlayers(2);
        Board b = g.getBoard();
        b.initializeBoard();


        Player colMustard = g.getPlayers().get(1);
        b.movePlayer(colMustard.getPiece(),new String[]{"6","R","2","D"});
        g.makeSuggestion(colMustard,"Mrs White","Lounge","Rope");

        Room lounge = b.getRooms().get("Lounge");
        Boolean found = false;
        for(RoomSquare r:lounge.getRoomSquares()) if(r.getPlayer()!=null && r.getPlayer().getName().equals("Mrs White")) found = true;

        assertTrue(found);

    }

    @Test
    public void trueAccusation(){
        Game g = new Game();
        g.initializePlayers(6);
        g.dealCards();
        g.setSolution(new ArrayList<>(Arrays.asList(new Card("Candlestick"),new Card("Lounge"),new Card("Colonel Mustard"))));
        Player missScarlett = g.getPlayers().get(0);
        g.makeAccusation(missScarlett,"Colonel Mustard","Lounge","Candlestick");
        assertFalse(missScarlett.isExcluded());
    }

    @Test
    public void testRefute(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        Player colMustard  = g.getPlayers().get(1);
        Card c = (new Card("Rope"));
        Card c2 = new Card("Lounge");
        Card c3 = new Card("Miss Scarlett");

        colMustard.addCard(c);
        assertEquals(colMustard.refute(c,c2,c3),c);

    }

    @Test
    public void movingThroughRoomDoesntCostMoves(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        b.movePlayer(missScarlett,new String[]{"6","U","1","L","1","d"});
        assertEquals(b.numOfMoves(missScarlett,new String[]{"5","L","2","d"}),0);
    }

    @Test
    public void exitRoomNotThroughExit(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        b.movePlayer(missScarlett,new String[]{"6","U","1","L","1","d"});
        assertFalse(b.checkValidMove(missScarlett,new String[]{"1","r"}));
    }

    @Test
    public void testPlayerMovesToRoomWhenSuggested(){
        Game g = new Game();
        g.dealCards();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        Player missScarlett  = g.getPlayers().get(0);

        b.movePlayer(missScarlett.getPiece(),new String[]{"6","U","1","L","1","d"});
        Room lounge = b.getRooms().get("Lounge");
        g.makeSuggestion(missScarlett,"Colonel Mustard","Lounge","Rope");
        Boolean found = false;
        for(RoomSquare r:lounge.getRoomSquares()) if(r.getPlayer()!=null && r.getPlayer().getName().equals("Colonel Mustard")) found = true;

        assertTrue(found);
    }
    @Test
    public void testWeaponMovesToRoomWhenSuggested(){
        Game g = new Game();
        g.dealCards();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        Player missScarlett  = g.getPlayers().get(0);
        b.movePlayer(missScarlett.getPiece(),new String[]{"6","U","1","L","1","d"});

        Room lounge = b.getRooms().get("Lounge");
        g.makeSuggestion(missScarlett,"Colonel Mustard","Lounge","Rope");
        Boolean found = false;
        for(RoomSquare r:lounge.getRoomSquares()) if(r.getWeapon()!=null && r.getWeapon().getName().equals("Rope")) found = true;

        assertTrue(found);
    }
}