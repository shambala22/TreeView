package com.splat.dao;

import com.splat.model.Folder;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shambala on 21.08.17.
 */
public class FolderJdbcDao extends JdbcDaoSupport implements FolderDao {

    public FolderJdbcDao(DataSource dataSource) {
        super();
        getJdbcTemplate().execute("CREATE TABLE IF NOT EXISTS FOLDER (ID INTEGER, NAME TEXT, PARENT INTEGER)");
        setDataSource(dataSource);
    }

    @Override
    public int addFolder(Folder folder, Folder parent) {
        String sql = "INSERT INTO FOLDER (ID, NAME, PARENT) VALUES (?, ?, ?)";
        return getJdbcTemplate().update(sql, new Object[] {folder.getId(), folder.getName(), parent.getId()});
    }

    @Override
    public List<Folder> getTree() {
        String sql = "SELECT * FROM FOLDER";
        
    }
}
