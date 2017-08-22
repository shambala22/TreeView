package com.splat.dao;

import com.splat.model.Folder;
import jdk.internal.util.xml.impl.Pair;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by shambala on 21.08.17.
 */
public class FolderJdbcDao extends JdbcDaoSupport implements FolderDao {

    private HashMap<Integer, String> folderNames;
    private HashMap<Integer, List<Integer>> folderChildren;

    public FolderJdbcDao(DataSource dataSource) {
        super();
        getJdbcTemplate().execute("CREATE TABLE IF NOT EXISTS FOLDER (ID INTEGER, NAME TEXT, PARENT INTEGER)");
        setDataSource(dataSource);
    }

    @Override
    public int addFolder(Folder folder, Folder parent) {
        String sql = "INSERT INTO FOLDER (ID, NAME, PARENT) VALUES (?, ?, ?)";
        return getJdbcTemplate().update(sql, folder.getId(), folder.getName(), parent == null ? -1 : parent.getId());
    }

    @Override
    public Folder getTree() {
        String sqlFolders = "SELECT (NAME, ID) FROM FOLDER";
        List<Folder> folders = getJdbcTemplate().query(sqlFolders, new BeanPropertyRowMapper<>(Folder.class));
        folderChildren = new HashMap<>();
        folderNames = new HashMap<>();
        for (Folder folder : folders) {
            folderNames.put(folder.getId(), folder.getName());
            folderChildren.put(folder.getId(), new ArrayList<>());
        }
        String sqlParents = "SELECT (ID, PARENT) FROM FOLDER";
        int root = getJdbcTemplate().query(sqlParents, resultSet -> {
            int result = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int parentId = resultSet.getInt("PARENT");
                if (parentId == -1) {
                    result = id;
                }
                List<Integer> children = folderChildren.get(id);
                children.add(parentId);
                folderChildren.put(id, children);
            }
            return result;
        });
        return buildTree(root);
    }

    private Folder buildTree(int id) {
        Folder result = new Folder(folderNames.get(id), id);
        for (int childId : folderChildren.get(id)) {
            result.addChild(buildTree(childId));
        }
        return result;
    }

}
