package com.game.data;

import com.game.models.Round;

import java.util.List;

public interface RoundDao {

    Round addRound(Round round);

    Round updateRound(Round round);

    List<Round> getAllRoundsByGameId(int gameId);

}
