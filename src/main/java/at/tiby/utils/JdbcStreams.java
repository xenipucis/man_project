package at.tiby.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.ResultSetWrappingSqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcStreams<T> {
	public static <T> T streamQuery(JdbcTemplate jdbcTemplate, String sql, Function<Stream<SqlRowSet>, ? extends T> streamer, Object... args) {
		  return jdbcTemplate.query(sql, resultSet -> {
		    final SqlRowSet rowSet = new ResultSetWrappingSqlRowSet(resultSet);
		    final boolean parallel = false;

		    // The ResultSet API has a slight impedance mismatch with Iterators, so this conditional
		    // simply returns an empty iterator if there are no results
		    if (!rowSet.next()) {
		      return streamer.apply(StreamSupport.stream(Spliterators.emptySpliterator(), parallel));
		    }

		    Spliterator<SqlRowSet> spliterator = Spliterators.spliteratorUnknownSize(new Iterator<SqlRowSet>() {
		      private boolean first = true;
		  
		      @Override
		      public boolean hasNext() {
		        return !rowSet.isLast();
		      }

		      @Override
		      public SqlRowSet next() {
		        if (!first || !rowSet.next()) {
		          throw new NoSuchElementException();
		        }
		        first = false; // iterators can be unwieldy sometimes
		        return rowSet;
		      }
		    }, Spliterator.IMMUTABLE);
		    return streamer.apply(StreamSupport.stream(spliterator, parallel));
		  }, args);
		}

}

