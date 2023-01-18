package com.game.data;

import com.game.models.Game;
import com.game.models.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoundDaoStubImpl implements RoundDao{


    private List<Round> rounds = new ArrayList<>();

    @Override
    public Round addRound(Round round) {
        round.setRoundId(rounds.size() + 1);
        rounds.add(round);
        return round;
    }

    @Override
    public Round updateRound(Round round) {
        return round;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
        List<Round> result = rounds.stream().filter(g -> g.getGameId() == gameId).collect(Collectors.toList());
        return result;
    }
}
