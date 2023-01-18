package com.game.data;

import com.game.models.Round;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class RoundDatabaseDao implements  RoundDao{

    private final JdbcTemplate jdbcTemplate;

    public RoundDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Round addRound(Round round) {
        final String sql = "Insert INTO gameround(gameId, guess, result, gametime)  VALUES(?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement stm = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, round.getGameId());
            stm.setString(2, round.getGuess());
            stm.setString(3, round.getResult());
            stm.setTimestamp(4, Timestamp.valueOf(round.getGameTime()));
            return stm;
        }, keyHolder);
        round.setRoundId(keyHolder.getKey().intValue());
        return round;

    }

    @Override
    public Round updateRound(Round round) {
        return null;
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {

        final String sql = "SELECT roundId, gameId,guess,result,gametime from gameround where gameId = ?;";
        return jdbcTemplate.query(sql, new RoundMapper(), gameId);
    }

    private static final class RoundMapper implements RowMapper<Round> {

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round();
            round.setRoundId(rs.getInt("roundId"));
            round.setGameId(rs.getInt("gameId"));
            round.setResult(rs.getString("result"));
            round.setGuess(rs.getString("guess"));
            round.setGameTime(rs.getTimestamp("gametime").toLocalDateTime());

            return round;
        }
    }

}
