package Java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        b.movePlayer(missScarlett,g.getBoard().getSquare(startingSquare.getY()-2,startingSquare.getX()));
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
        b.movePlayer(msWhite,g.getBoard().getSquare(startingSquare.getY()+1,startingSquare.getX()));
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
        b.movePlayer(profPlum,g.getBoard().getSquare(startingSquare.getY(),startingSquare.getX()-3));
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
        b.movePlayer(colMustard,g.getBoard().getSquare(startingSquare.getY(),startingSquare.getX()+5));
        assertEquals(startingSquare.getX()+5,colMustard.getLocation().getX());
    }


    @Test
    public void moveToRoomNotThroughEntry(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        assertFalse(b.checkValidMove(g.getBoard().getSquare(23,7),g.getBoard().getSquare(23,6)));
    }
    @Test
    public void moveToDeadSquare(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        assertFalse(b.checkValidMove(g.getBoard().getSquare(24,7),g.getBoard().getSquare(24,8)));
    }
    @Test
    public void moveToRoomThroughEntry(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        assertTrue(b.checkValidMove(g.getBoard().getSquare(20,6),g.getBoard().getSquare(19,6)));
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
        b.movePlayer(missScarlett,g.getBoard().getSquare(19,6));
        List<Square> moves = new ArrayList<>();
        moves.add(g.getBoard().getSquare(19,6));
        moves.add(g.getBoard().getSquare(20,6));
        assertEquals(b.numOfMoves(missScarlett,moves),0);
    }

    @Test
    public void exitRoomNotThroughExit(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        b.movePlayer(missScarlett,g.getBoard().getSquare(19,6));
        assertFalse(b.checkValidMove(g.getBoard().getSquare(19,6),g.getBoard().getSquare(19,7)));
    }

    @Test
    public void testPlayerMovesToRoomWhenSuggested(){
        Game g = new Game();
        g.dealCards();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        Player missScarlett  = g.getPlayers().get(0);

        b.movePlayer(missScarlett.getPiece(),g.getBoard().getSquare(19,6));
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
        b.movePlayer(missScarlett.getPiece(),g.getBoard().getSquare(19,6));

        Room lounge = b.getRooms().get("Lounge");
        g.makeSuggestion(missScarlett,"Colonel Mustard","Lounge","Rope");
        Boolean found = false;
        for(RoomSquare r:lounge.getRoomSquares()) if(r.getWeapon()!=null && r.getWeapon().getName().equals("Rope")) found = true;

        assertTrue(found);
    }
    /**
     * Tests if a player is blocked in a room by another player thus unable to complete their turn.
     **/


    @Test
    public void playerIsBlocked(){
        Game g = new Game();
        g.initializePlayers(6);
        Board b = g.getBoard();
        b.initializeBoard();
        PlayerPiece missScarlett = g.getPlayers().get(0).getPiece();
        PlayerPiece colMustard = g.getPlayers().get(1).getPiece();
        b.movePlayer(missScarlett,g.getBoard().getSquare(19,6));
        b.movePlayer(colMustard,g.getBoard().getSquare(18,6));
        boolean ba = b.isPlayerBlocked(missScarlett);
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
        b.movePlayer(missScarlett,g.getBoard().getSquare(19,6));
        assertFalse(b.checkValidMove(g.getBoard().getSquare(20,6),g.getBoard().getSquare(19,6)));
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
        b.movePlayer(colMustard.getPiece(),g.getBoard().getSquare(19,6));
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
        b.movePlayer(colMustard.getPiece(),g.getBoard().getSquare(19,6));
        g.makeSuggestion(colMustard,"Mrs White","Lounge","Rope");

        Room lounge = b.getRooms().get("Lounge");
        Boolean found = false;
        for(RoomSquare r:lounge.getRoomSquares()) if(r.getPlayer()!=null && r.getPlayer().getName().equals("Mrs White")) found = true;

        assertTrue(found);

    }

}