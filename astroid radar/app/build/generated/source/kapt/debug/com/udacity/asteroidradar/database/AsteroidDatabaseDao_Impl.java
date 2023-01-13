package com.udacity.asteroidradar.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AsteroidDatabaseDao_Impl implements AsteroidDatabaseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DatabaseAsteroid> __insertionAdapterOfDatabaseAsteroid;

  private final EntityInsertionAdapter<DatabasePicOfDay> __insertionAdapterOfDatabasePicOfDay;

  private final SharedSQLiteStatement __preparedStmtOfClearOldAsteroids;

  private final SharedSQLiteStatement __preparedStmtOfClearOldPics;

  public AsteroidDatabaseDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDatabaseAsteroid = new EntityInsertionAdapter<DatabaseAsteroid>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `asteroids_table` (`id`,`codename`,`closeApproachDate`,`absoluteMagnitude`,`estimatedDiameter`,`relativeVelocity`,`distanceFromEarth`,`isPotentiallyHazardous`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabaseAsteroid value) {
        stmt.bindLong(1, value.getId());
        if (value.getCodename() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCodename());
        }
        if (value.getCloseApproachDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCloseApproachDate());
        }
        if (value.getAbsoluteMagnitude() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindDouble(4, value.getAbsoluteMagnitude());
        }
        if (value.getEstimatedDiameter() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindDouble(5, value.getEstimatedDiameter());
        }
        if (value.getRelativeVelocity() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindDouble(6, value.getRelativeVelocity());
        }
        if (value.getDistanceFromEarth() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindDouble(7, value.getDistanceFromEarth());
        }
        final Integer _tmp = value.isPotentiallyHazardous() == null ? null : (value.isPotentiallyHazardous() ? 1 : 0);
        if (_tmp == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp);
        }
      }
    };
    this.__insertionAdapterOfDatabasePicOfDay = new EntityInsertionAdapter<DatabasePicOfDay>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `pic_of_day_table` (`date`,`url`,`mediaType`,`title`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DatabasePicOfDay value) {
        if (value.getDate() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getDate());
        }
        if (value.getUrl() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUrl());
        }
        if (value.getMediaType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMediaType());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTitle());
        }
      }
    };
    this.__preparedStmtOfClearOldAsteroids = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from asteroids_table where closeApproachDate < ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearOldPics = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from pic_of_day_table where date < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAsteroids(final DatabaseAsteroid[] asteroids,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDatabaseAsteroid.insert(asteroids);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object insertPicOfDay(final DatabasePicOfDay pic,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDatabasePicOfDay.insert(pic);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object clearOldAsteroids(final String today,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearOldAsteroids.acquire();
        int _argIndex = 1;
        if (today == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, today);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearOldAsteroids.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object clearOldPics(final String today, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearOldPics.acquire();
        int _argIndex = 1;
        if (today == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, today);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfClearOldPics.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public LiveData<List<DatabaseAsteroid>> getNextWeekAsteroids(final String startDate) {
    final String _sql = "select * from asteroids_table where closeApproachDate >= ? order by closeApproachDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (startDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, startDate);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"asteroids_table"}, false, new Callable<List<DatabaseAsteroid>>() {
      @Override
      public List<DatabaseAsteroid> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCodename = CursorUtil.getColumnIndexOrThrow(_cursor, "codename");
          final int _cursorIndexOfCloseApproachDate = CursorUtil.getColumnIndexOrThrow(_cursor, "closeApproachDate");
          final int _cursorIndexOfAbsoluteMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "absoluteMagnitude");
          final int _cursorIndexOfEstimatedDiameter = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDiameter");
          final int _cursorIndexOfRelativeVelocity = CursorUtil.getColumnIndexOrThrow(_cursor, "relativeVelocity");
          final int _cursorIndexOfDistanceFromEarth = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceFromEarth");
          final int _cursorIndexOfIsPotentiallyHazardous = CursorUtil.getColumnIndexOrThrow(_cursor, "isPotentiallyHazardous");
          final List<DatabaseAsteroid> _result = new ArrayList<DatabaseAsteroid>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseAsteroid _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCodename;
            if (_cursor.isNull(_cursorIndexOfCodename)) {
              _tmpCodename = null;
            } else {
              _tmpCodename = _cursor.getString(_cursorIndexOfCodename);
            }
            final String _tmpCloseApproachDate;
            if (_cursor.isNull(_cursorIndexOfCloseApproachDate)) {
              _tmpCloseApproachDate = null;
            } else {
              _tmpCloseApproachDate = _cursor.getString(_cursorIndexOfCloseApproachDate);
            }
            final Double _tmpAbsoluteMagnitude;
            if (_cursor.isNull(_cursorIndexOfAbsoluteMagnitude)) {
              _tmpAbsoluteMagnitude = null;
            } else {
              _tmpAbsoluteMagnitude = _cursor.getDouble(_cursorIndexOfAbsoluteMagnitude);
            }
            final Double _tmpEstimatedDiameter;
            if (_cursor.isNull(_cursorIndexOfEstimatedDiameter)) {
              _tmpEstimatedDiameter = null;
            } else {
              _tmpEstimatedDiameter = _cursor.getDouble(_cursorIndexOfEstimatedDiameter);
            }
            final Double _tmpRelativeVelocity;
            if (_cursor.isNull(_cursorIndexOfRelativeVelocity)) {
              _tmpRelativeVelocity = null;
            } else {
              _tmpRelativeVelocity = _cursor.getDouble(_cursorIndexOfRelativeVelocity);
            }
            final Double _tmpDistanceFromEarth;
            if (_cursor.isNull(_cursorIndexOfDistanceFromEarth)) {
              _tmpDistanceFromEarth = null;
            } else {
              _tmpDistanceFromEarth = _cursor.getDouble(_cursorIndexOfDistanceFromEarth);
            }
            final Boolean _tmpIsPotentiallyHazardous;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfIsPotentiallyHazardous)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfIsPotentiallyHazardous);
            }
            _tmpIsPotentiallyHazardous = _tmp == null ? null : _tmp != 0;
            _item = new DatabaseAsteroid(_tmpId,_tmpCodename,_tmpCloseApproachDate,_tmpAbsoluteMagnitude,_tmpEstimatedDiameter,_tmpRelativeVelocity,_tmpDistanceFromEarth,_tmpIsPotentiallyHazardous);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DatabaseAsteroid>> getTodayAsteroids(final String today) {
    final String _sql = "select * from asteroids_table where closeApproachDate = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (today == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, today);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"asteroids_table"}, false, new Callable<List<DatabaseAsteroid>>() {
      @Override
      public List<DatabaseAsteroid> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCodename = CursorUtil.getColumnIndexOrThrow(_cursor, "codename");
          final int _cursorIndexOfCloseApproachDate = CursorUtil.getColumnIndexOrThrow(_cursor, "closeApproachDate");
          final int _cursorIndexOfAbsoluteMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "absoluteMagnitude");
          final int _cursorIndexOfEstimatedDiameter = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDiameter");
          final int _cursorIndexOfRelativeVelocity = CursorUtil.getColumnIndexOrThrow(_cursor, "relativeVelocity");
          final int _cursorIndexOfDistanceFromEarth = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceFromEarth");
          final int _cursorIndexOfIsPotentiallyHazardous = CursorUtil.getColumnIndexOrThrow(_cursor, "isPotentiallyHazardous");
          final List<DatabaseAsteroid> _result = new ArrayList<DatabaseAsteroid>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseAsteroid _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCodename;
            if (_cursor.isNull(_cursorIndexOfCodename)) {
              _tmpCodename = null;
            } else {
              _tmpCodename = _cursor.getString(_cursorIndexOfCodename);
            }
            final String _tmpCloseApproachDate;
            if (_cursor.isNull(_cursorIndexOfCloseApproachDate)) {
              _tmpCloseApproachDate = null;
            } else {
              _tmpCloseApproachDate = _cursor.getString(_cursorIndexOfCloseApproachDate);
            }
            final Double _tmpAbsoluteMagnitude;
            if (_cursor.isNull(_cursorIndexOfAbsoluteMagnitude)) {
              _tmpAbsoluteMagnitude = null;
            } else {
              _tmpAbsoluteMagnitude = _cursor.getDouble(_cursorIndexOfAbsoluteMagnitude);
            }
            final Double _tmpEstimatedDiameter;
            if (_cursor.isNull(_cursorIndexOfEstimatedDiameter)) {
              _tmpEstimatedDiameter = null;
            } else {
              _tmpEstimatedDiameter = _cursor.getDouble(_cursorIndexOfEstimatedDiameter);
            }
            final Double _tmpRelativeVelocity;
            if (_cursor.isNull(_cursorIndexOfRelativeVelocity)) {
              _tmpRelativeVelocity = null;
            } else {
              _tmpRelativeVelocity = _cursor.getDouble(_cursorIndexOfRelativeVelocity);
            }
            final Double _tmpDistanceFromEarth;
            if (_cursor.isNull(_cursorIndexOfDistanceFromEarth)) {
              _tmpDistanceFromEarth = null;
            } else {
              _tmpDistanceFromEarth = _cursor.getDouble(_cursorIndexOfDistanceFromEarth);
            }
            final Boolean _tmpIsPotentiallyHazardous;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfIsPotentiallyHazardous)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfIsPotentiallyHazardous);
            }
            _tmpIsPotentiallyHazardous = _tmp == null ? null : _tmp != 0;
            _item = new DatabaseAsteroid(_tmpId,_tmpCodename,_tmpCloseApproachDate,_tmpAbsoluteMagnitude,_tmpEstimatedDiameter,_tmpRelativeVelocity,_tmpDistanceFromEarth,_tmpIsPotentiallyHazardous);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<DatabaseAsteroid>> getAllAsteroids() {
    final String _sql = "select * from asteroids_table order by closeApproachDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"asteroids_table"}, false, new Callable<List<DatabaseAsteroid>>() {
      @Override
      public List<DatabaseAsteroid> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCodename = CursorUtil.getColumnIndexOrThrow(_cursor, "codename");
          final int _cursorIndexOfCloseApproachDate = CursorUtil.getColumnIndexOrThrow(_cursor, "closeApproachDate");
          final int _cursorIndexOfAbsoluteMagnitude = CursorUtil.getColumnIndexOrThrow(_cursor, "absoluteMagnitude");
          final int _cursorIndexOfEstimatedDiameter = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDiameter");
          final int _cursorIndexOfRelativeVelocity = CursorUtil.getColumnIndexOrThrow(_cursor, "relativeVelocity");
          final int _cursorIndexOfDistanceFromEarth = CursorUtil.getColumnIndexOrThrow(_cursor, "distanceFromEarth");
          final int _cursorIndexOfIsPotentiallyHazardous = CursorUtil.getColumnIndexOrThrow(_cursor, "isPotentiallyHazardous");
          final List<DatabaseAsteroid> _result = new ArrayList<DatabaseAsteroid>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final DatabaseAsteroid _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCodename;
            if (_cursor.isNull(_cursorIndexOfCodename)) {
              _tmpCodename = null;
            } else {
              _tmpCodename = _cursor.getString(_cursorIndexOfCodename);
            }
            final String _tmpCloseApproachDate;
            if (_cursor.isNull(_cursorIndexOfCloseApproachDate)) {
              _tmpCloseApproachDate = null;
            } else {
              _tmpCloseApproachDate = _cursor.getString(_cursorIndexOfCloseApproachDate);
            }
            final Double _tmpAbsoluteMagnitude;
            if (_cursor.isNull(_cursorIndexOfAbsoluteMagnitude)) {
              _tmpAbsoluteMagnitude = null;
            } else {
              _tmpAbsoluteMagnitude = _cursor.getDouble(_cursorIndexOfAbsoluteMagnitude);
            }
            final Double _tmpEstimatedDiameter;
            if (_cursor.isNull(_cursorIndexOfEstimatedDiameter)) {
              _tmpEstimatedDiameter = null;
            } else {
              _tmpEstimatedDiameter = _cursor.getDouble(_cursorIndexOfEstimatedDiameter);
            }
            final Double _tmpRelativeVelocity;
            if (_cursor.isNull(_cursorIndexOfRelativeVelocity)) {
              _tmpRelativeVelocity = null;
            } else {
              _tmpRelativeVelocity = _cursor.getDouble(_cursorIndexOfRelativeVelocity);
            }
            final Double _tmpDistanceFromEarth;
            if (_cursor.isNull(_cursorIndexOfDistanceFromEarth)) {
              _tmpDistanceFromEarth = null;
            } else {
              _tmpDistanceFromEarth = _cursor.getDouble(_cursorIndexOfDistanceFromEarth);
            }
            final Boolean _tmpIsPotentiallyHazardous;
            final Integer _tmp;
            if (_cursor.isNull(_cursorIndexOfIsPotentiallyHazardous)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(_cursorIndexOfIsPotentiallyHazardous);
            }
            _tmpIsPotentiallyHazardous = _tmp == null ? null : _tmp != 0;
            _item = new DatabaseAsteroid(_tmpId,_tmpCodename,_tmpCloseApproachDate,_tmpAbsoluteMagnitude,_tmpEstimatedDiameter,_tmpRelativeVelocity,_tmpDistanceFromEarth,_tmpIsPotentiallyHazardous);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<DatabasePicOfDay> getPicOfDay(final String dayDate) {
    final String _sql = "select * from pic_of_day_table where date = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (dayDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, dayDate);
    }
    return __db.getInvalidationTracker().createLiveData(new String[]{"pic_of_day_table"}, false, new Callable<DatabasePicOfDay>() {
      @Override
      public DatabasePicOfDay call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfMediaType = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final DatabasePicOfDay _result;
          if(_cursor.moveToFirst()) {
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpMediaType;
            if (_cursor.isNull(_cursorIndexOfMediaType)) {
              _tmpMediaType = null;
            } else {
              _tmpMediaType = _cursor.getString(_cursorIndexOfMediaType);
            }
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _result = new DatabasePicOfDay(_tmpDate,_tmpUrl,_tmpMediaType,_tmpTitle);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
