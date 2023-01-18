package com.game.data;

import com.game.models.Game;
import com.game.models.GameStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class GameDatabaseDao implements GameDao{

    private final JdbcTemplate jdbcTemplate;

    public GameDatabaseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addGame(Game game) {
        final String sql = "Insert INTO game(answer, gameStatus)  VALUES(?,?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement stm = conn.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, game.getAnswer());
            stm.setString(2, game.getGameStatus().toString());
            return stm;
        }, keyHolder);
        game.setId(keyHolder.getKey().intValue());
        return game.getId();
    }

    @Override
    public boolean updateGame(Game game) {

        final String sql = "UPDATE game SET "
                + "answer = ?, "
                + "gameStatus = ? "
                + "WHERE id = ?;";

        return jdbcTemplate.update(sql,
                game.getAnswer(),
                game.getGameStatus().toString(),
                game.getId()) > 0;
    }


    @Override
    public Game getGameById(int gameId) {
        final String sql = "SELECT id, CASE" +
                " WHEN gameStatus = 'FINISHED' " +
                " THEN  answer " +
                " ELSE '' " +
                " END as answer , gameStatus FROM game  WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public Game getGameAnswerById(int gameId) {
        final String sql = "SELECT id, answer, gameStatus FROM game  WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
    }

    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT id, CASE" +
        " WHEN gameStatus = 'FINISHED' " +
        " THEN  answer " +
        " ELSE '' " +
        " END as answer , gameStatus FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

private static final class GameMapper implements RowMapper<Game> {

    @Override
    public Game mapRow(ResultSet rs, int index) throws SQLException {
        Game game = new Game();
        game.setId(rs.getInt("id"));
        game.setAnswer(rs.getString("answer"));
        game.setGameStatus(GameStatus.valueOf(rs.getString("gameStatus")));

        return game;
    }
}
}
